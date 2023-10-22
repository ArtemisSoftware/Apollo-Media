package com.artemissoftware.apollomedia

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.apollomedia.audiorecorder.AudioRecorderScreen
import com.artemissoftware.apollomedia.imagecrop.ImageCropScreen
import com.artemissoftware.apollomedia.video.mediaplayer.VideoMediaPlayerScreen
import com.artemissoftware.apollomedia.video.streaming.VideoStreamingScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "mainmenu",
    mainViewModel: MainViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable("mainmenu") {
            mainViewModel.setAllowPipMode(false)
            MainScreen(navController = navController)
        }
        composable("VideoMedia") {
            VideoMediaPlayerScreen(
                updateVideoViewBounds = { bounds ->
                    mainViewModel.setVideoViewBounds(bounds)
                },
                updatePipMode = { inPipMode ->
                    mainViewModel.setInPipMode(inPipMode)
                },
                allowPipMode = { allow ->
                    mainViewModel.setAllowPipMode(allow)
                },
                isInPipMode = mainViewModel.isInPipMode.value,
            )
        }
        composable("VideoStreaming") {
            VideoStreamingScreen(
                allowPipMode = { allow ->
                    mainViewModel.setAllowPipMode(allow)
                },
            )
        }
        composable("imagecrop") {
            ImageCropScreen()
        }
        composable("audiorecorder") {
            AudioRecorderScreen()
        }
    }
}
