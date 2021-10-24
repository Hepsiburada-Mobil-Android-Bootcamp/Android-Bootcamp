package com.android.camp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.camp.data.dao.MessageDao
import com.android.camp.data.entity.MessageEntity

@Database(entities = [MessageEntity::class], version = 1, exportSchema = false)
abstract class CampDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    companion object {
        var instance: CampDatabase? = null

        fun getDatabase(context: Context): CampDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CampDatabase::class.java,
                    "android_bootcamp_db"
                ).build()
            }

            return instance!!
        }
    }
}