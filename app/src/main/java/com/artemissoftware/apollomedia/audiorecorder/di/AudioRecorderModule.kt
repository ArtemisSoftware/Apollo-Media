package com.artemissoftware.apollomedia.audiorecorder.di

import android.content.Context
import com.artemissoftware.apollomedia.audiorecorder.playback.AndroidAudioPlayer
import com.artemissoftware.apollomedia.audiorecorder.playback.AudioPlayer
import com.artemissoftware.apollomedia.audiorecorder.record.AndroidAudioRecorder
import com.artemissoftware.apollomedia.audiorecorder.record.AudioRecorder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AudioRecorderModule {

    @Provides
    @Singleton
    fun provideAudioRecorder(@ApplicationContext context: Context): AudioRecorder {
        return AndroidAudioRecorder(context)
    }

    @Provides
    @Singleton
    fun provideAudioPlayer(@ApplicationContext context: Context): AudioPlayer {
        return AndroidAudioPlayer(context)
    }
}