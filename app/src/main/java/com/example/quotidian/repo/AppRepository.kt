package com.example.quotidian.repo

import com.example.quotidian.repo.data.Quotes
import com.example.quotidian.repo.data.QuotesDao
import com.example.quotidian.repo.network.QuotesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(
    private val api: QuotesAPI,
    private val dao: QuotesDao
) {
    suspend fun getQuotes(): Result<List<Quotes>> {
        return withContext(Dispatchers.IO) {
            val quotesFromDatabase = dao.getQuotes()
            if (quotesFromDatabase.isEmpty()) {
                getQuotesFromApi()
            } else {
                Result.success(quotesFromDatabase)
            }
        }
    }

    private suspend fun getQuotesFromApi() = kotlin.runCatching {
        val quotesFromApi = api.getQuotes()
        dao.saveQuotes(quotesFromApi)
        quotesFromApi
    }
}