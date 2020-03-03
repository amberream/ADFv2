package com.amberream.notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String PRIMARY_NOTIFICATION_CHANNEL = "primary_notification_channel";
    public static final int NOTIFICATION_ID = 0;

    NotificationManager notificationManager;

    Button buttonNotify;
    Button buttonUpdate;
    Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNotify = findViewById(R.id.button_notify);
        buttonUpdate = findViewById(R.id.button_update);
        buttonCancel = findViewById(R.id.button_cancel);
        setNotificationButtonState(true, false, false);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();
    }

    private void createNotificationChannel()
    {
        // notification channels are only available in sdk 26 and higher so check before creating
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(
                    PRIMARY_NOTIFICATION_CHANNEL, getString(R.string.mascot_notification), NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription(getString(R.string.notification_from_mascot));

            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder()
    {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, PRIMARY_NOTIFICATION_CHANNEL);
        builder.setContentTitle("Title: You've been notified");
        builder.setContentText("Text: This is your notification text");
        builder.setSmallIcon(R.drawable.ic_android);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        // this is for backward compatibility since 25 and lower do not use notification channels
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);

        return builder;
    }

    public void sendNotification(View view) {
        notificationManager.notify(NOTIFICATION_ID, getNotificationBuilder().build());
        setNotificationButtonState(false, true, true);
    }

    public void updateNotification(View view) {
        NotificationCompat.Builder builder = getNotificationBuilder();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setBigContentTitle(getString(R.string.notification_updated)));
        notificationManager.notify(NOTIFICATION_ID, builder.build());
        setNotificationButtonState(false, false, true);
    }

    public void cancelNotification(View view) {
        notificationManager.cancel(NOTIFICATION_ID);
        setNotificationButtonState(true, false, false);
    }

    private void setNotificationButtonState(boolean notify, boolean update, boolean cancel)
    {
        buttonNotify.setEnabled(notify);
        buttonUpdate.setEnabled(update);
        buttonCancel.setEnabled(cancel);
    }
}
