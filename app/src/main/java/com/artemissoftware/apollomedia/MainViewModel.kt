package com.artemissoftware.apollomedia

import android.graphics.Rect
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    var videoViewBounds = Rect()
        private set

    var isInPipMode = mutableStateOf(false)
        private set

    var canGoToPipMode = mutableStateOf(false)
        private set

    fun setVideoViewBounds(bounds: Rect) {
        videoViewBounds = bounds
    }

    fun setAllowPipMode(pipModeAllowed: Boolean) {
        canGoToPipMode.value = pipModeAllowed
    }

    fun setInPipMode(isInPipMode: Boolean) {
        this.isInPipMode.value = isInPipMode
    }
}
