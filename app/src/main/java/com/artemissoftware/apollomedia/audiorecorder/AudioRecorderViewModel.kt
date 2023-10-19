package com.artemissoftware.apollomedia.audiorecorder

import android.app.Application
import androidx.lifecycle.ViewModel
import com.artemissoftware.apollomedia.audiorecorder.playback.AudioPlayer
import com.artemissoftware.apollomedia.audiorecorder.record.AudioRecorder
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AudioRecorderViewModel @Inject constructor(
    private val application: Application,
    private val audioRecorder: AudioRecorder,
    private val audioPlayer: AudioPlayer,
) : ViewModel() {

    private var audioFile: File? = null

    fun startRecording() {
        File(application.cacheDir, "audio.mp3").also {
            audioRecorder.start(it)
            audioFile = it
        }
    }

    fun stopRecording() {
        audioRecorder.stop()
    }

    fun play(){
        audioPlayer.playFile(audioFile ?: return)
    }

    fun stopPlaying() {
        audioPlayer.stop()
    }
}
