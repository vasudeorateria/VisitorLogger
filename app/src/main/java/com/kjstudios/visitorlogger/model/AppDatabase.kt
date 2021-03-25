package com.kjstudios.visitorlogger.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Visitor::class], version = 1, exportSchema = false)
abstract class VisitorDatabase : RoomDatabase() {

    abstract fun visitorDao(): VisitorDao

    companion object {

        @Volatile
        private var INSTANCE: VisitorDatabase? = null

        fun getDatabase(context: Context): VisitorDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VisitorDatabase::class.java,
                    "visitor_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}