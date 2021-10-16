package com.vnprk.dhca.models

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.vnprk.dhca.createForegroundInfo
import kotlinx.coroutines.delay

class CalculateWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager

    override suspend fun doWork(): Result {
        Log.d("CalculateWorker", "doWork: start")
        val progress = "Starting Download"
        setForeground(notificationManager.createForegroundInfo(applicationContext, id, progress))
        calculate(/*inputUrl, outputFile*/)
        Log.d("CalculateWorker", "doWork: end")
        return Result.success()
    }

    private suspend fun calculate(/*inputUrl: String, outputFile: String*/) {
        val CFL:Double = 0.4
        val Time = 20
        var t=0.0
        val allTime = 5
        val n = 50;
        val u = DoubleArray(n)
        val uNew = DoubleArray(n)
        val F = DoubleArray(n)
        val h:Double = allTime.toDouble() / n
        val tau:Double = CFL*h

        var startTime = 0.0;
        for (i in u.indices)
        {
            startTime += tau;
            if (i in 9..19) {
                u[i] = 2.0
                uNew[i] =2.0
            }
            else {
                u[i] = 1.0
                uNew[i] = 1.0
            }

        }

        while (t<Time)
        {
            t += tau
            for (i in 1 until u.size)
            {
                val UR = uNew[i]
                val UL = uNew[i - 1]
                uNew[i] = uNew[i] - tau / h*(UR - UL)
            }
            val progress = "Progress: $t/$Time"
            this.setForeground(notificationManager.createForegroundInfo(applicationContext, id, progress))
        }
        /*for (i in 1..100) {
            val progress = "Progress: $i"
            this.setForeground(notificationManager.createForegroundInfo(applicationContext, id, progress))
            delay(100)
        }*/// Downloads a file and updates bytes read
        // Calls setForegroundInfo() periodically when it needs to update
        // the ongoing Notification
    }

    companion object {
        const val NTF_ID_DEFAULT = 0
        const val KEY_INPUT_URL = "KEY_INPUT_URL"
        const val KEY_OUTPUT_FILE_NAME = "KEY_OUTPUT_FILE_NAME"
    }
}