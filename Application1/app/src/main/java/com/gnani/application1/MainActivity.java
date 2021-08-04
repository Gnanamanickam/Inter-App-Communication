package com.gnani.application1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String Permission = "edu.uic.cs478.s19.kaboom";
    private BroadcastReceiver receiver;
    IntentFilter filter;
    private static final String INTENT = "com.gnani.application3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkPermissionAndBroadcast();
            }
        }) ;
    }

    protected void checkPermissionAndBroadcast() {
        //Checks whether the user has given the permission or not .
        if (ActivityCompat.checkSelfPermission(this, Permission) == PackageManager.PERMISSION_GRANTED) {
            launchActivity();
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Permission}, 0);
        }
    }

    @Override
    //To get the permission from the user .
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] result) {

        //To check whether permission is given or denied
        if (result.length > 0)
        {
            if (result[0] == PackageManager.PERMISSION_GRANTED)
            {
                launchActivity();
            }
            //Toast the permission denied message in case if its denied .
            else
                Toast.makeText(this, "Permission not Available", Toast.LENGTH_SHORT).show();
        }
    }

    // To launch the second application .
    protected void launchActivity() {
        filter = new IntentFilter(INTENT);
        filter.setPriority(1);
        receiver = new Receiver();
        //Broadcast receiver
        registerReceiver(receiver, filter);
        //To Launch Second Application
        Intent launchIntent = new Intent(Intent.ACTION_MAIN);
        launchIntent.setClassName("com.gnani.application2", "com.gnani.application2.MainActivity");
        finish();
        //Check whether launch event is null . If not start the activity .
        if (launchIntent != null) {
            startActivity(launchIntent);
        }
    }

}
