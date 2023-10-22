package com.artemissoftware.apollomedia

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Stream
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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

        Icon(
            imageVector = Icons.Filled.Stream,
            "",
            tint = Purple80,
            modifier = Modifier
                .size(100.dp)
                .clickable {
                    navController.navigate("VideoStreaming")
                },
        )

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

        Icon(
            imageVector = Icons.Filled.Crop,
            "",
            tint = Purple80,
            modifier = Modifier
                .size(100.dp)
                .clickable {
                    navController.navigate("imagecrop")
                },
        )
    }
}
