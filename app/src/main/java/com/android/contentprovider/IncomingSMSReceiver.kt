package com.android.contentprovider

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.android.camp.R
import com.android.camp.data.CampHelper
import com.android.camp.data.database.CampDatabase
import com.android.camp.data.entity.MessageEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class IncomingSMSReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        intent.extras?.let { bundle ->
            val format = bundle.getString("format")

            (bundle["pdus"] as Array<Any>)?.let { datas ->
                val smsAttrs = arrayOfNulls<SmsMessage>(datas.size)

                smsAttrs.indices.forEach { attr ->
                    val sms = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        SmsMessage.createFromPdu(datas[attr] as ByteArray, format)
                    } else {
                        SmsMessage.createFromPdu(datas[attr] as ByteArray)
                    }

                    Toast.makeText(
                        context,
                        "${sms.originatingAddress} tarafında sms geldi. Mesajınızı: ${sms.messageBody}",
                        Toast.LENGTH_LONG
                    ).show()

                    val intent = Intent(context, ChatActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        putExtra("icerik", sms.originatingAddress)
                    }

                    val pendingIntent = PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )

                    val notification = NotificationCompat.Builder(context, "camp_channel_id")
                        .setContentText(sms.messageBody)
                        .setContentTitle(sms.originatingAddress)
                        .setSmallIcon(R.drawable.ic_song)
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .build()

                    val notificationManager =
                        context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.notify(0, notification)



                    CoroutineScope(Dispatchers.IO).launch {
                        val entity = MessageEntity(
                            jid = sms.originatingAddress,
                            body = sms.messageBody,
                            date = Calendar.getInstance().time.time,
                            type = Message.INCOMING
                        )
                        val dao = CampDatabase.getDatabase(context).messageDao()
                        dao.insert(entity)
                    }

                }
            }
        }
    }
}