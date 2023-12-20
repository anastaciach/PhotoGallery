package com.bignerdranch.android.photogallery

import android.app.Activity
import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationManagerCompat

private const val TAG = "NotificationReceiver"

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "Received result: $resultCode")
        if (resultCode != Activity.RESULT_OK) {
            // A foreground activity cancelled the broadcast
            return
        }

        val requestCode = intent.getIntExtra(PollWorker.REQUEST_CODE, 0)
        val notification: Notification? =
            intent.getParcelableExtra(PollWorker.NOTIFICATION)

        if (notification != null) {
            // Теперь у вас есть ненулевое значение для использования
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(requestCode, notification)
        } else {
            // Обработка случая, когда значение null
            Log.e(TAG, "Notification is null")
        }
    }
}