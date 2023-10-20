package com.artemissoftware.apollomedia.imagecrop

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageCropViewModel @Inject constructor(
    private val application: Application,
) : ViewModel() {

    var bitmap = mutableStateOf<Bitmap?>(null)

    fun setImage(uri: Uri) {
        val source = ImageDecoder.createSource(application.contentResolver, uri)
        bitmap.value = ImageDecoder.decodeBitmap(source)
    }
}
