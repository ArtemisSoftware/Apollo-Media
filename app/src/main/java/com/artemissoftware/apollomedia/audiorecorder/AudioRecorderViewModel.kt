package com.artemissoftware.apollomedia.audiorecorder

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.artemissoftware.apollomedia.audiorecorder.playback.AudioPlayer
import com.artemissoftware.apollomedia.audiorecorder.record.AudioRecorder
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileTime
import javax.inject.Inject

@HiltViewModel
class AudioRecorderViewModel @Inject constructor(
    private val application: Application,
    private val audioRecorder: AudioRecorder,
    private val audioPlayer: AudioPlayer,
) : ViewModel() {

    private var audioFile: File? = null
    var isRecording = mutableStateOf(false)
    var isPlaying = mutableStateOf(false)
    var fileInfo = mutableStateOf(getFileInfo())

    fun startRecording() {
        File(application.cacheDir, "audio.mp3").also {
            audioRecorder.start(it)
            audioFile = it
        }
        isRecording.value = true
        isPlaying.value = false
    }

    fun stopRecording() {
        audioRecorder.stop()
        isRecording.value = false
        isPlaying.value = false
        fileInfo.value = getFileInfo()
    }

    fun play() {
        audioPlayer.playFile(audioFile ?: return)
        isPlaying.value = true
    }

    fun stopPlaying() {
        audioPlayer.stop()
        isPlaying.value = false
    }

    private fun getFileInfo(): String {
        return audioFile?.let {
            val creationDate = getFileCreationDate(it)
            val dateOfCreation = if (creationDate != null) {
                "date: $creationDate"
            } else {
                "creation date not available (or unsupported on your platform)."
            }

            "${it.length()} bytes " + dateOfCreation
        } ?: kotlin.run {
            "No file available"
        }
    }

    private fun getFileCreationDate(file: File): FileTime? {
        val path = file.toPath()
        val attrs: BasicFileAttributes = Files.readAttributes(path, BasicFileAttributes::class.java)
        return attrs.creationTime()
    }
}
