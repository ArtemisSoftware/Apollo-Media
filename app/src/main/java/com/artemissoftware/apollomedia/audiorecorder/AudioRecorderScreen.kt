package com.artemissoftware.apollomedia.audiorecorder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.apollomedia.R
import com.artemissoftware.apollomedia.audiorecorder.composables.AudioAnimation

@Composable
fun AudioRecorderScreen(viewModel: AudioRecorderViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AudioAnimation(
            isAnimating = viewModel.isRecording.value,
            modifier = Modifier
                .width(120.dp),
            imageVector = Icons.Filled.Mic,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
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
        }

        Spacer(modifier = Modifier.height(16.dp))

        AudioAnimation(
            isAnimating = viewModel.isPlaying.value,
            modifier = Modifier
                .width(120.dp),
            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = viewModel.fileInfo.value)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
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
}
