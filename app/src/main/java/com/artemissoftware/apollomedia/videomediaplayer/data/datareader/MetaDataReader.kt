package com.artemissoftware.apollomedia.videomediaplayer.data.datareader

import android.net.Uri
import com.artemissoftware.apollomedia.videomediaplayer.data.models.MetaData

interface MetaDataReader {
    fun getMetaDataFromUri(contentUri: Uri): MetaData?
}
