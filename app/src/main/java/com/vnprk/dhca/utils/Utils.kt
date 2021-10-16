package com.vnprk.dhca
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import com.vnprk.dhca.models.CalculateWorker
import java.util.*

fun NotificationManager.createForegroundInfo(context: Context, id: UUID, progress: String): ForegroundInfo {
    val channelId = context.getString(R.string.notification_calc_channel_id)
    val title = context.getString(R.string.notification_calc_title)
    val cancel = context.getString(R.string.notification_cancel)
    // This PendingIntent can be used to cancel the worker
    val intent = WorkManager.getInstance(context)
        .createCancelPendingIntent(id)

    // Create a Notification channel if necessary
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        createNotificationChannel(getDefaultChannel(context))
    }

    val notification = NotificationCompat.Builder(context, channelId)
        .setContentTitle(title)
        .setTicker(title)
        .setContentText(progress)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setOngoing(true)
        // Add the cancel action to the notification which can
        // be used to cancel the worker
        .addAction(android.R.drawable.ic_delete, cancel, intent)
        .build()

    return ForegroundInfo(CalculateWorker.NTF_ID_DEFAULT, notification)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NotificationManager.getDefaultChannel(context: Context): NotificationChannel {
    val channelId = context.getString(R.string.notification_calc_channel_id)
    val channel = NotificationChannel(
        channelId,
        "Channel human readable title",
        NotificationManager.IMPORTANCE_DEFAULT
    )
    return channel
}