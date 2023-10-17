package com.artemissoftware.apollomedia.videomediaplayer

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.artemissoftware.apollomedia.videomediaplayer.data.datareader.MetaDataReader
import com.artemissoftware.apollomedia.videomediaplayer.data.models.VideoItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class VideoMediaPlayerViewModel constructor(
    private val savedStateHandle: SavedStateHandle,
    val player: Player,
    private val metaDataReader: MetaDataReader,
) : ViewModel() {

    private val videoUris = savedStateHandle.getStateFlow(VIDEO_URIS, emptyList<Uri>())

    val videoItems = videoUris.map { uris ->
        uris.map { uri ->
            VideoItem(
                contentUri = uri,
                mediaItem = MediaItem.fromUri(uri),
                name = metaDataReader.getMetaDataFromUri(uri)?.fileName ?: "No name",
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        player.prepare()
    }

    fun addVideoUri(uri: Uri) {
        savedStateHandle[VIDEO_URIS] = videoUris.value + uri
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    fun playVideo(uri: Uri) {
        player.setMediaItem(
            videoItems.value.find { it.contentUri == uri }?.mediaItem ?: return,
        )
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

    private companion object {
        private const val VIDEO_URIS = "videoUris"
    }
}
