package com.artemissoftware.apollomedia.video.mediaplayer

import android.graphics.Rect
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.ui.PlayerView
import com.artemissoftware.apollomedia.video.mediaplayer.data.models.VideoItem

@Composable
fun VideoMediaPlayerScreen(
    viewModel: VideoMediaPlayerViewModel = hiltViewModel(),
    updateVideoViewBounds: (Rect) -> Unit,
    updatePipMode: (Boolean) -> Unit,
    isInPipMode: Boolean,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var lifecycle by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }

    val videoItems by viewModel.videoItems.collectAsState()

    val selectVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let(viewModel::addVideoUri)
        },
    )

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        AndroidView(
            factory = { context ->
                PlayerView(context).also {
                    it.player = viewModel.player
                }
            },
            update = {
                when (lifecycle) {
                    Lifecycle.Event.ON_PAUSE -> {
                        it.onPause()
                        if (!isInPipMode) it.player?.pause()
                    }
                    Lifecycle.Event.ON_RESUME -> {
                        it.onResume()
                        updatePipMode.invoke(false)
                    }
                    else -> Unit
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .onGloballyPositioned {
                    val boundsInWindow = it.boundsInWindow()
                    updateVideoViewBounds.invoke(Rect(boundsInWindow.left.toInt(), boundsInWindow.top.toInt(), boundsInWindow.right.toInt(), boundsInWindow.bottom.toInt()))
                },
        )

        Spacer(modifier = Modifier.height(8.dp))

        VideoMediaPlayerContent(videoItems, viewModel, selectVideoLauncher)
    }
}

@Composable
fun ColumnScope.VideoMediaPlayerContent(
    videoItems: List<VideoItem>,
    viewModel: VideoMediaPlayerViewModel,
    selectVideoLauncher: ManagedActivityResultLauncher<String, Uri?>,
) {
    IconButton(
        onClick = {
            selectVideoLauncher.launch("video/mp4")
        },
        content = {
            Icon(
                imageVector = Icons.Default.FileOpen,
                contentDescription = "Select video",
            )
        },
    )

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(videoItems) { item ->
            Text(
                text = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.playVideo(item.contentUri)
                    }
                    .padding(16.dp),
            )
        }
    }
}
