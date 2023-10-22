package com.artemissoftware.apollomedia

import android.Manifest
import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.artemissoftware.apollomedia.audiorecorder.AudioRecorderScreen
import com.artemissoftware.apollomedia.receivers.PipReceiver
import com.artemissoftware.apollomedia.ui.theme.ApolloMediaTheme
import com.artemissoftware.apollomedia.video.mediaplayer.VideoMediaPlayerScreen
import com.artemissoftware.apollomedia.video.streaming.VideoStreamingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private val isPipSupported by lazy {
        packageManager.hasSystemFeature(
            PackageManager.FEATURE_PICTURE_IN_PICTURE,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0,
        )
        setContent {
            ApolloMediaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
//                    VideoStreamingScreen()
                    // ImageCropScreen()
                     AudioRecorderScreen()
//                    VideoMediaPlayerScreen(
//                        updateVideoViewBounds = { bounds ->
//                            mainViewModel.setVideoViewBounds(bounds)
//                        },
//                        updatePipMode = { inPipMode ->
//                            mainViewModel.setInPipMode(inPipMode)
//                        },
//                        isInPipMode = mainViewModel.isInPipMode.value,
//                    )
                }
            }
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if (!isPipSupported) {
            return
        }
        mainViewModel.setInPipMode(true)
        enterPictureInPictureMode(updatedPipParams())
    }

    private fun updatedPipParams(): PictureInPictureParams {
        return PictureInPictureParams
            .Builder()
            .setSourceRectHint(mainViewModel.videoViewBounds)
            .setAspectRatio(Rational(16, 9))
            .setActions(
                listOf(
                    RemoteAction(
                        Icon.createWithResource(
                            applicationContext,
                            R.drawable.ic_personal_video,
                        ),
                        "Trailer video",
                        "Trailer video",
                        PendingIntent.getBroadcast(
                            applicationContext,
                            0,
                            Intent(applicationContext, PipReceiver::class.java),
                            PendingIntent.FLAG_IMMUTABLE,
                        ),
                    ),
                ),
            )
            .build()
    }
}
