package com.amberream.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // The action must provide the app's package name syntax (this matches BuildConfig.APPLICATION_ID)
    // It's common practice to prepend the package name to the action name
    static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    CustomReceiver customReceiver = new CustomReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(customReceiver, intentFilter);
        LocalBroadcastManager.getInstance(this).registerReceiver(customReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(customReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(customReceiver);
        super.onDestroy();
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
}
