package com.android.contentprovider

import androidx.annotation.IntDef

data class MessageModel(
    var address: String?,
    var body: String?,
    var date: Long?,
    @Message.Type var type: Int
)

object Message {
    const val INCOMING = 1
    const val SEND = 2
    const val DRAFT = 3

    @IntDef(INCOMING, SEND, DRAFT)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Type
}