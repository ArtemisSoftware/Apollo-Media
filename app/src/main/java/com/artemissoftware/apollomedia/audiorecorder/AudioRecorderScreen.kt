package com.artemissoftware.apollomedia.audiorecorder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.apollomedia.R

@Composable
fun AudioRecorderScreen(viewModel: AudioRecorderViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                viewModel.startRecording()
            },
            content = {
                Icon(
                    imageVector = Icons.Default.Mic,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )

                Text(text = stringResource(R.string.start_recording))
            },
        )

        Button(
            onClick = {
                viewModel.stopRecording()
            },
            content = {
                Icon(
                    imageVector = Icons.Default.MicOff,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )

                Text(text = stringResource(R.string.stop_recording))
            },
        )

        Button(
            onClick = {
                viewModel.play()
            },
            content = {
                Icon(
                    imageVector = Icons.Default.PlayCircle,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )

                Text(text = stringResource(R.string.play))
            },
        )

        Button(
            onClick = {
                viewModel.stopPlaying()
            },
            content = {
                Icon(
                    imageVector = Icons.Default.StopCircle,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )

                Text(text = stringResource(R.string.stop_playing))
            },
        )
    }
}
