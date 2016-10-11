package com.grepx.androidsecurityhole;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * This gets called each time the phone starts up.
 */
public class BootBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context arg0, Intent arg1) {
        ServiceInstaller.installServices(arg0);
    }
}
