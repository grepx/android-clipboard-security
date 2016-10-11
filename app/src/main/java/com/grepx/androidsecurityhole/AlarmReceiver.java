package com.grepx.androidsecurityhole;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    public void onReceive(Context arg0, Intent arg1) {
        // if the user killed the service, start it back up again
        if (!ClipboardWatcherService.serviceIsRunning) {
            Intent intent = new Intent(arg0, ClipboardWatcherService.class);
            arg0.startService(intent);
        }
    }
}
