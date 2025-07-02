package com.example.tvapp.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.tvapp.data.model.Video
import com.example.tvapp.viewmodel.VideoViewModel

@Composable
fun HomeScreen(
    onVideoClick: (Video) -> Unit,
    onSearchClick: () -> Unit,
    viewModel: VideoViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f))
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            SideNavigationBar(onSearchClick)
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            } else if (uiState.error != null) {
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = Color.Red
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.retry() }) {
                        Text("Retry")
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    item {
                        if (uiState.videos.isNotEmpty()) {
                            FeaturedMovie(
                                video = uiState.videos.first(),
                                onClick = { onVideoClick(uiState.videos.first()) }
                            )
                        }
                    }
                    item {
                        MovieList(
                            title = "Continue watching",
                            videos = uiState.videos,
                            onVideoClick = onVideoClick
                        )
                    }
                    item {
                        MovieList(
                            title = "Popular Movies",
                            videos = uiState.videos.reversed(),
                            onVideoClick = onVideoClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SideNavigationBar(onSearchClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        NavigationItem(icon = Icons.Default.Search, label = "Search", onClick = onSearchClick)
        Spacer(modifier = Modifier.height(32.dp))
        NavigationItem(icon = Icons.Default.Home, label = "Home", onClick = {})
        Spacer(modifier = Modifier.height(32.dp))
        NavigationItem(icon = Icons.Default.Movie, label = "Movies", onClick = {})
        Spacer(modifier = Modifier.height(32.dp))
        NavigationItem(icon = Icons.Default.Settings, label = "Settings", onClick = {})
    }
}

@Composable
fun NavigationItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Icon(
        imageVector = icon,
        contentDescription = label,
        tint = Color.White,
        modifier = Modifier
            .size(32.dp)
            .clickable { onClick() }
    )
}

@Composable
fun FeaturedMovie(
    video: Video,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = video.image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(32.dp)
        ) {
            Text(
                text = "Video by ${video.user.name}",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Duration: ${formatDuration(video.duration)}",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun MovieList(
    title: String,
    videos: List<Video>,
    onVideoClick: (Video) -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow {
            items(videos) { video ->
                MovieListItem(
                    video = video,
                    onClick = { onVideoClick(video) }
                )
            }
        }
    }
}

@Composable
fun MovieListItem(
    video: Video,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(180.dp)
            .height(240.dp)
            .padding(end = 16.dp)
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = video.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        AnimatedVisibility(visible = isFocused) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                        )
                    ),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "Video by: ${video.user.name}",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

fun formatDuration(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%d:%02d", minutes, remainingSeconds)
}