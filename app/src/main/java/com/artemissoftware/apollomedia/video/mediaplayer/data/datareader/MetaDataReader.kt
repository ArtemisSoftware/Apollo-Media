package com.artemissoftware.apollomedia.video.mediaplayer.data.datareader

import android.net.Uri
import com.artemissoftware.apollomedia.video.mediaplayer.data.models.MetaData

interface MetaDataReader {
    fun getMetaDataFromUri(contentUri: Uri): MetaData?
}
