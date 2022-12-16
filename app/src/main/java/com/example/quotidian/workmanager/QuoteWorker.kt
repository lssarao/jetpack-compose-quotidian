package com.example.quotidian.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.quotidian.QuotidianApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val repository = (context as QuotidianApplication).quoteRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuotes()
        }
        Log.d("doWork", "Worker Called")
        return Result.success()
    }
}