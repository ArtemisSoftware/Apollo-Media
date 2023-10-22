package com.artemissoftware.apollomedia

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Stream
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artemissoftware.apollomedia.ui.theme.Purple40
import com.artemissoftware.apollomedia.ui.theme.Purple80

@Composable
fun MainScreen(
    navController: NavHostController,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Filled.VideoFile,
                "",
                tint = Purple80,
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        navController.navigate("VideoMedia")
                    },
            )
            Text(text = "Video media player")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Filled.Stream,
                "",
                tint = Purple40,
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        navController.navigate("VideoStreaming")
                    },
            )
            Text(text = "Video streaming")
        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Filled.RecordVoiceOver,
                "",
                tint = Purple80,
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        navController.navigate("audiorecorder")
                    },
            )
            Text(text = "Audio recorder")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Filled.Crop,
                "",
                tint = Purple40,
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        navController.navigate("imagecrop")
                    },
            )
            Text(text = "Image crop")
        }
    }
}
