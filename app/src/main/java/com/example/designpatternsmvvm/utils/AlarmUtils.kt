/*
 * Developed by: @ImaginativeShohag
 *
 * Md. Mahmudul Hasan Shohag
 * imaginativeshohag@gmail.com
 *
 * MVVM Pattern Source: https://github.com/ImaginativeShohag/Simple-MVVM
 */

package com.example.designpatternsmvvm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

/**
 * This utility object provide easy to use methods to set and cancel alarms.
 *
 * Usage:
 *
 * 1. Set an Alarm:
 *
 * val pendingIntent = PendingIntent.getBroadcast(
 *     context,
 *     requestCode,
 *     intent,
 *     PendingIntent.FLAG_CANCEL_CURRENT // or PendingIntent.FLAG_UPDATE_CURRENT
 * )
 *
 * val calendar = Calendar.getInstance().apply {
 *     // ...
 * }
 *
 * AlarmUtils.setAlarm(
 *     context,
 *     pendingIntent,
 *     calendar.timeInMillis
 * )
 *
 * 2. Cancel an Alarm:
 *
 * val intent = Intent(context, NotifyNotification::class.java)
 * AlarmUtils.cancelAlarm(
 *     context,
 *     PendingIntent.getBroadcast(
 *         context,
 *         requestCode,
 *         intent,
 *         0
 *     )
 * )
 *
 *
 * Check existing alarm for package `com.example.xyz`:
 * adb shell dumpsys alarm | grep -A 5 com.example.xyz
 *
 * Help resource:
 * - https://proandroiddev.com/android-alarmmanager-as-deep-as-possible-909bd5b64792
 */
object AlarmUtils {

    /**
     * Get the AlarmManager.
     */
    private fun Context.getAlarmManager(): AlarmManager {
        return getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    /**
     * Set alarm.
     *
     * The alarm will be replaced if same pending intent is given.
     *
     * @param context A Context to get the AlarmManager service.
     * @param operation A PendingIntent which will be called when alarm goes off.
     * @param alarmTime The time when alarm should go off.
     */
    fun setAlarm(context: Context, operation: PendingIntent, alarmTime: Long) {
        val alarmMgr = context.getAlarmManager()

        alarmMgr.setExact(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            operation
        )
    }

    /**
     * Set a repeating alarm.
     *
     * The alarm will be replaced if same pending intent is given.
     *
     * @param context A Context to get the AlarmManager service.
     * @param operation A PendingIntent which will be called when alarm goes off.
     * @param alarmTime The time when alarm should go off.
     * @param intervalTime Interval in milliseconds between subsequent repeats of the alarm.
     */
    fun setRepeatingAlarm(
        context: Context,
        operation: PendingIntent,
        alarmTime: Long,
        intervalTime: Long
    ) {
        val alarmMgr = context.getAlarmManager()

        alarmMgr.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            intervalTime,
            operation,
        )
    }

    /**
     * Cancel alarm. It can be a one time alarm or repeating alarm.
     *
     * It is not possible to cancel any alarm without exact PendingIntent.
     *
     * @param context A Context to get the AlarmManager service.
     * @param operation The PendingIntent, which is related to the alarm which we want to cancel.
     */
    fun cancelAlarm(context: Context, operation: PendingIntent) {
        val alarmMgr = context.getAlarmManager()

        alarmMgr.cancel(operation)
    }

    /**
     * Check if an alarm is scheduled.
     *
     * @param context A Context to get the AlarmManager service.
     * @param requestCode The requestCode of the PendingIntent.
     * @param intent The Intent of the PendingIntent.
     */
    @SuppressLint("UnspecifiedImmutableFlag")
    fun isAlarmExist(
        context: Context,
        requestCode: Int,
        intent: Intent,
    ): Boolean {
        return PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_NO_CREATE
        ) != null
    }
}