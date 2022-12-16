package com.example.quotidian.screens

import android.app.Application
import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.alexstyl.swipeablecard.Direction
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.example.quotidian.repo.AppRepository
import com.example.quotidian.repo.data.Quotes
import com.example.quotidian.ui.theme.myColour
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application,
    private val repository: AppRepository
) : AndroidViewModel(application) {

    var uiState by mutableStateOf<HomeUiState>(HomeUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            quotes()
        }
    }

    private suspend fun quotes() {
        val result = repository.getQuotes()
        result.fold(
            onSuccess = {
                uiState = HomeUiState.Success(result.getOrNull()!!)
                Log.d("Success", "quotes: $result")
            },
            onFailure = { e ->
                HomeUiState.Error
                Log.d("Failure", "Throwable exception on failure: $e")
            }
        )
    }

    companion object {
        private class AppRepositoryKey : CreationExtras.Key<AppRepository>

        @JvmField
        val APP_REPO_KEY: CreationExtras.Key<AppRepository> = AppRepositoryKey()
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    object Error : HomeUiState()
    data class Success(var quotes: List<Quotes>) : HomeUiState()
}


@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    when (val state: HomeUiState = viewModel.uiState) {
        is HomeUiState.Loading -> {
            LoadingScreen()
        }
        is HomeUiState.Success -> {
            SuccessScreen(state.quotes)
        }
        HomeUiState.Error -> {
            ErrorScreen()
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(1f)) {
        CircularProgressIndicator(
            modifier = Modifier.align(Center)
        )
    }
}

@Composable
fun ErrorScreen() {
    Box(modifier = Modifier.fillMaxSize(1f)) {
        Text(
            text = "Something went wrong",
            style = typography.h1
        )
    }
}

@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun SuccessScreen(quotes: List<Quotes>) {
    // Screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Text(
            text = "quotidian",
            color = Color.White,
            style = typography.h5,
            modifier = Modifier.padding(start = 22.dp, top = 28.dp)
        )
        val states = quotes.reversed()
            .map { it to rememberSwipeableCardState() }
        // Card Box
        Box(
            Modifier
                .padding(24.dp)
                .fillMaxSize()
                .aspectRatio(1f)
                .align(Center)
        ) {
            states.forEach { (quotes, state) ->
                if (state.swipedDirection == null) {
                    QuoteCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .swipableCard(
                                state = state,
                                blockedDirections = listOf(Direction.Down),
                                onSwiped = {
                                    // swipes are handled by the LaunchedEffect
                                    Log.d("Swipe-able-Card", "Swiped")
                                },
                                onSwipeCancel = {
                                    Log.d("Swipe-able-Card", "Cancelled")
                                }
                            ),
                        quote = quotes
                    )
                }
                LaunchedEffect(quotes, state.swipedDirection) {
                }
            }
        }
    }
}

@Composable
fun QuoteCard(
    modifier: Modifier,
    quote: Quotes
) {
    Card(
        modifier,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.Black,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .background(myColour)
                .padding(40.dp)
        ) {
            Text(
                text = """ " """,
                style = typography.h4,
                color = Color.White,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = quote.quote ?: "Something went wrong",
                style = typography.body1,
                color = Color.White,
                modifier = Modifier.padding(start = 12.dp)
            )

            Spacer(Modifier.height(100.dp))

            Text(
                text = quote.author.ifBlank { "Unknown" },
                style = typography.caption,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 12.dp)
            )
        }
    }
}

@Composable
fun TransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = myColour,
            darkIcons = useDarkIcons,
            isNavigationBarContrastEnforced = false
        )
        onDispose {}
    }
}