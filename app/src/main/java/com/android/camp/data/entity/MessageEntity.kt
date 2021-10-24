package com.android.camp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_message")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "jabber_id") val jid: String?,
    @ColumnInfo(name = "body") val body: String?,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "type") val type: Int
)