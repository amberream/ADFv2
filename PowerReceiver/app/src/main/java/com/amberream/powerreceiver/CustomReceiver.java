package com.amberream.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // This method is called when the BroadcastReceiver is receiving
        String action = intent.getAction();
        if (action != null)
        {
            if (action.equals(Intent.ACTION_POWER_CONNECTED))
            {
                Toast.makeText(context, R.string.power_connected, Toast.LENGTH_SHORT).show();
            }
            else if (action.equals(Intent.ACTION_POWER_DISCONNECTED))
            {
                Toast.makeText(context, R.string.power_disconnected, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
