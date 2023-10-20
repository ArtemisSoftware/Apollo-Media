package com.artemissoftware.apollomedia.imagecrop

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.canhub.cropper.CropImageView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageCropViewModel @Inject constructor(
    private val application: Application,
) : ViewModel() {

    var bitmap = mutableStateOf<Bitmap?>(null)
    var error = mutableStateOf<String>("")
        private set

    fun setImage(cropResult: CropImageView.CropResult) {

        if (cropResult.isSuccessful) {
            cropResult.uriContent?.let {
                val source = ImageDecoder.createSource(application.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
        } else {
            error.value = cropResult.error?.message.toString()
        }
    }
}
