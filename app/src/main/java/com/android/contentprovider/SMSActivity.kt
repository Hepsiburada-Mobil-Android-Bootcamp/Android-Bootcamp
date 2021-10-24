package com.android.contentprovider

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.provider.Telephony
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.database.getIntOrNull
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.android.camp.R
import com.android.camp.data.database.CampDatabase
import com.android.camp.data.entity.MessageEntity
import kotlinx.coroutines.*
import java.util.jar.Manifest

class SMSActivity : AppCompatActivity() {

    companion object {
        const val PERMISSION_READ_SMS_REQ_CODE = 100
    }

    val sharedPref by lazy { getSharedPreferences("camp_pref", MODE_PRIVATE) }
    val text_View_message_count by lazy { findViewById<TextView>(R.id.text_View_message_count) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)


        val password = sharedPref.getString("sifre", "")

        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)


        val sharedPrefsFile: String = "encrypted_shared_pref"
        val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
            sharedPrefsFile,
            mainKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        sharedPref.edit().putString("sifre", "123456").apply()
        sharedPreferences.edit().putString("sifre", "123456").apply()

        Log.d("sharedPreferencesSifre", "şifrem: ${sharedPreferences.getString("sifre", "")}")

        findViewById<View>(R.id.button_call).setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivityForResult(intent, 1990)
        }

        findViewById<View>(R.id.button_gallery).setOnClickListener {
            val intent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                    type = "image/*"
                }
            startActivityForResult(intent, 2000)
        }

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.READ_SMS
                ),
                PERMISSION_READ_SMS_REQ_CODE
            )
        } else {
            readSMSList()
        }

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.RECEIVE_SMS
                ),
                PERMISSION_READ_SMS_REQ_CODE
            )
        }


        /*
        val manager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager

        val request =
            DownloadManager.Request(Uri.parse("https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_480_1_5MG.mp4"))
                .setDescription("indiriliyor....").setTitle("Camp App")
                .setAllowedOverRoaming(true)
                .setAllowedOverMetered(true)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        manager.enqueue(request)

         */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannelForDefaultSMS()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannelForDefaultSMS() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            "camp_channel_id",
            "Camp SMS Bildirimi",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.enableVibration(true)

        channel.description = "camp sms bildirimini ifade eder...."

        notificationManager.createNotificationChannel(channel)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_READ_SMS_REQ_CODE) {
            if (grantResults[0] == 0) {
                readSMSList()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == 1990) {
                val message = if (data?.getIntExtra(
                        "state",
                        -1
                    ) == 2
                ) "Arama Başlatıldı..." else "Arama Sonlandırıldı..."
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun readSMSList() {
        val addressCol = Telephony.TextBasedSmsColumns.ADDRESS
        val bodyCol = Telephony.TextBasedSmsColumns.BODY
        val dateCol = Telephony.TextBasedSmsColumns.DATE
        val typeCol = Telephony.TextBasedSmsColumns.TYPE

        val projection = arrayOf(addressCol, bodyCol, dateCol, typeCol)

        val cursor = contentResolver.query(Telephony.Sms.CONTENT_URI, null, null, null, null)


        val list = arrayListOf<MessageEntity>()

        while (cursor?.moveToNext() == true) {
            val address = cursor.getStringOrNull(cursor.getColumnIndexOrThrow(addressCol))
            val body = cursor.getStringOrNull(cursor.getColumnIndexOrThrow(bodyCol))
            val date = cursor.getLong(cursor.getColumnIndexOrThrow(dateCol))
            val type = cursor.getInt(cursor.getColumnIndexOrThrow(typeCol))

            val message = MessageModel(address, body, date, 12)


            val messageEntity = MessageEntity(jid = address, body = body, date = date, type = type)
            list.add(messageEntity)


            Log.d(
                "SMSActivityList",
                "${cursor.position}. sms => address: $address, body: $body, date: $date, type: $type"
            )
        }

        cursor?.close()


        CoroutineScope(Dispatchers.IO).launch {
            val db = CampDatabase.getDatabase(this@SMSActivity)
            val messageDao = db.messageDao()
            messageDao.insertAll(list.toList())
        }

        CoroutineScope(Dispatchers.Main).launch {
            val db = CampDatabase.getDatabase(this@SMSActivity)
            val messageDao = db.messageDao()

            messageDao.getMessages().observe(this@SMSActivity) { list ->
                text_View_message_count.text = list.size.toString()
            }
        }

    }
}