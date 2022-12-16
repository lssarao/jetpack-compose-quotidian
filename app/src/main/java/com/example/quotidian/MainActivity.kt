package com.example.quotidian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.quotidian.navigation.NavGraph
import com.example.quotidian.repo.AppRepository
import com.example.quotidian.repo.data.QuoteDatabase
import com.example.quotidian.repo.network.createQuotesApi
import com.example.quotidian.screens.TransparentSystemBars
import com.example.quotidian.ui.theme.QuotidianTheme
import com.example.quotidian.ui.theme.myColour

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = QuoteDatabase.getInstance(applicationContext)

        val appRepository = AppRepository(
            api = createQuotesApi(),
            dao = database.quotesDao()
        )

        setContent {
            QuotidianTheme {
                TransparentSystemBars()
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = myColour
                ) {
                    NavGraph(
                        navController = navController,
                        application = application,
                        appRepository = appRepository
                    )
                }
            }
        }
    }
}