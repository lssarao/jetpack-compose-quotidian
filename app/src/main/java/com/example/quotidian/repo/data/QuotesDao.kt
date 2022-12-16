package com.example.quotidian.repo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuotesDao {
    @Query("SELECT * FROM Daily_Quotes")
    suspend fun getQuotes(): List<Quotes>

    @Insert
    suspend fun saveQuotes(quotes: List<Quotes>)
}
