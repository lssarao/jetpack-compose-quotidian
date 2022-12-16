package com.example.quotidian.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quotidian.repo.AppRepository
import com.example.quotidian.screens.HomeScreen
import com.example.quotidian.screens.HomeViewModel
import com.example.quotidian.screens.HomeViewModel.Companion.APP_REPO_KEY

@Composable
fun NavGraph(
    application: Application,
    appRepository: AppRepository,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(
            route = Screens.Home.route
        ) {
            val factory = viewModelFactory {
                initializer {
                    HomeViewModel(
                        application = get(APPLICATION_KEY)!!,
                        repository = get(APP_REPO_KEY)!!
                    )
                }
            }

            HomeScreen(
                viewModel = viewModel(
                    factory = factory,
                    extras = MutableCreationExtras().apply {
                        set(APPLICATION_KEY, application)
                        set(APP_REPO_KEY, appRepository)
                    }
                )
            )
        }
    }
}

sealed class Screens(val route: String) {
    object Home : Screens("home")
}
