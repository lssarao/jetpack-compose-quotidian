package com.example.quotidian

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.quotidian.repo.AppRepository
import com.example.quotidian.repo.data.QuoteDatabase
import com.example.quotidian.repo.network.createQuotesApi
import com.example.quotidian.workmanager.QuoteWorker
import java.util.concurrent.TimeUnit

class QuotidianApplication : Application() {

    lateinit var quoteRepository: AppRepository

    override fun onCreate() {
        super.onCreate()

        val database = QuoteDatabase.getInstance(applicationContext)

        quoteRepository = AppRepository(
            api = createQuotesApi(),
            dao = database.quotesDao()
        )
        setupWorker()
    }

    private fun setupWorker() {
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .build()
        val workerRequest: PeriodicWorkRequest =
            PeriodicWorkRequest.Builder(QuoteWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(this).enqueue(workerRequest)
    }
}