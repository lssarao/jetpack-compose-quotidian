package com.example.quotidian.repo.network

import com.example.quotidian.repo.data.Quotes
import retrofit2.http.GET

interface QuotesAPI {
    @GET("api/quotes")
    suspend fun getQuotes(): List<Quotes>
}