package com.artemissoftware.apollomedia.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PipReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        println("Clicked on PIP action")
    }
}
