package com.example.tvapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tvapp.data.model.Video
import com.example.tvapp.viewmodel.VideoViewModel

@Composable
fun SearchScreen(
    viewModel: VideoViewModel,
    onVideoClick: (Video) -> Unit,
    onSearchClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxSize()) {
        SideNavigationBar(onSearchClick = onSearchClick)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    viewModel.searchVideos(it)
                },
                label = { Text("Search for videos") }
            )

            if (uiState.isLoading) {
                Text("Loading...")
            }

            uiState.error?.let {
                Text("Error: $it")
            }

            LazyColumn {
                items(uiState.searchResults) { video ->
                    MovieListItem(
                        video = video,
                        onClick = { onVideoClick(video) }
                    )
                }
            }
        }
    }
}