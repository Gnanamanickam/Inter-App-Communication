package com.gnani.application2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String message = "Permission received for Application Two " + intent.getAction();
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.gnani.application3");
//        context.startActivity(launchIntent);

    }
}