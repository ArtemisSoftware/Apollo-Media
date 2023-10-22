package com.artemissoftware.apollomedia.video.streaming

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage

@Composable
fun VideoStreamingScreen(
    viewModel: VideoStreamingViewModel = hiltViewModel(),
    allowPipMode: (Boolean) -> Unit,
) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    LazyVerticalGrid(
        modifier = Modifier.padding(0.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(0.dp),
    ) {
        items(viewModel.streamingData) { streamingData ->
            AsyncImage(
                model = streamingData.poster,
                contentScale = ContentScale.Crop,
                contentDescription = "video thumbnail",
                modifier = Modifier
                    .size(width = screenHeight / 2, 200.dp)
                    .clickable {
                        allowPipMode.invoke(true)
                        viewModel.playVideo(streamingData)
                    },
            )
        }
    }

    viewModel.currentStreaming.value?.let { stream ->
        VideoPlayer(
            player = viewModel.player,
            stopVideo = {
                allowPipMode.invoke(false)
                viewModel.stopVideo()
            },
        )
    }
}

@Composable
private fun VideoPlayer(
    stopVideo: () -> Unit,
    player: Player,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f)),
    ) {
        AndroidView(
            factory = { context ->
                PlayerView(context).also {
                    it.player = player
                }
            },
            modifier = Modifier.fillMaxSize(),
        )
        IconButton(
            onClick = { stopVideo() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.White,
            )
        }
    }
}
