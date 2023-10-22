package com.artemissoftware.apollomedia.video.streaming

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.artemissoftware.apollomedia.video.streaming.data.models.StreamItem
import com.artemissoftware.apollomedia.video.streaming.util.StreamingDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoStreamingViewModel @Inject constructor(
    val player: Player,
) : ViewModel() {

    val streamingData = StreamingDataSource.streamingList
    var currentStreaming = mutableStateOf<StreamItem?>(null)
        private set

    init {
        player.prepare()
    }

    fun playVideo(streamItem: StreamItem) {
        player.setMediaItem(MediaItem.fromUri(Uri.parse(streamItem.source)))
        player.play()
        currentStreaming.value = streamItem
    }

    fun stopVideo() {
        currentStreaming.value = null
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}
