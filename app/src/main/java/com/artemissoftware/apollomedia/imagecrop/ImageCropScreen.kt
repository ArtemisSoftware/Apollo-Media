package com.artemissoftware.apollomedia.imagecrop

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.apollomedia.ui.theme.Gold
import com.canhub.cropper.CropImage.CancelledResult.uriContent
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions

@Composable
fun ImageCropScreen(viewModel: ImageCropViewModel = hiltViewModel()) {
    val imageCropLauncher = rememberLauncherForActivityResult(
        contract = CropImageContract(),
        onResult = { result ->
            viewModel.setImage(result)
        },
    )
    ImageCropContent(
        onStartCropping = {
            val cropOption = CropImageContractOptions(uriContent, CropImageOptions())
            imageCropLauncher.launch(cropOption)
        },
        bitmap = viewModel.bitmap.value,
    )
}

@Composable
private fun ImageCropContent(
    onStartCropping: () -> Unit,
    bitmap: Bitmap? = null,
    error: String = "",
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.Center),
            visible = error.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Text(text = error)
        }

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.Center),
            visible = (bitmap != null),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            bitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(200.dp),
                )
            }
        }

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.Center),
            visible = (bitmap == null),
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally(),
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Gold)
                    .padding(16.dp)
                    .clickable {
                        onStartCropping()
                    },
            ) {
                Image(
                    imageVector = Icons.Default.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp),

                )
            }
        }
    }
}

@Preview
@Composable
private fun ImageCropContentPreview() {
    ImageCropContent(
        onStartCropping = {},
    )
}
