package com.gnani.application1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

    String url = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        if (extras != null) {
            // Get the url from the bundle
            url = extras.getString("url");
        }

        Intent webIntent = new Intent(context, WebActivity.class);
        webIntent.putExtra("url", url);
        context.startActivity(webIntent);
    }
}
