package com.example.tvapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tvapp.ui.screen.HomeScreen
import com.example.tvapp.ui.screen.SearchScreen
import com.example.tvapp.ui.screen.VideoPlayerScreen
import com.example.tvapp.ui.theme.TvAppTheme
import com.example.tvapp.viewmodel.VideoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TvApp()
                }
            }
        }
    }
}

@Composable
fun TvApp() {
    val navController = rememberNavController()
    val viewModel: VideoViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onVideoClick = { video ->
                    navController.navigate("player/${video.id}")
                },
                onSearchClick = {
                    navController.navigate("search")
                }
            )
        }
        composable("player/{videoId}") { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId")
            val video = (uiState.videos + uiState.searchResults).find { it.id.toString() == videoId }
            if (video != null) {
                VideoPlayerScreen(
                    video = video,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable("search") {
            SearchScreen(
                viewModel = viewModel,
                onVideoClick = { video ->
                    navController.navigate("player/${video.id}")
                },
                onSearchClick = {}
            )
        }
    }
}
