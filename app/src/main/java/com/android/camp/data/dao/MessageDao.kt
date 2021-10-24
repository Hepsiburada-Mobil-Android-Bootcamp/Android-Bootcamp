package com.android.camp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.camp.data.entity.MessageEntity

@Dao
interface MessageDao {
    @Insert
    fun insert(messageEntity: MessageEntity)

    @Insert
    fun insertAll(messageEntities: List<MessageEntity>)

    @Delete
    fun delete(messageEntity: MessageEntity)

    @Query("SELECT * FROM table_message")
    fun getMessages(): LiveData<List<MessageEntity>>
}