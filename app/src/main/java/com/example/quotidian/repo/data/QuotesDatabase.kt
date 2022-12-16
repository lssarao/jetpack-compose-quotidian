package com.example.quotidian.repo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quotes::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getInstance(applicationContext: Context): QuoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = build(applicationContext)
                    }
                }
            }
            return INSTANCE!!
        }

        private fun build(applicationContext: Context) =
            Room.databaseBuilder(
                applicationContext.applicationContext,
                QuoteDatabase::class.java,
                "quote database"
            ).build()
    }

    abstract fun quotesDao(): QuotesDao // // will returns an instance of the DAO class
}