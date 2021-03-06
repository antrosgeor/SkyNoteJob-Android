package com.example.antrosgeor.skynotejob;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by ANTROS on 29/5/2016.
 */
public class Notification_reciever extends BroadcastReceiver {
    NotificationManager notificationManager;
    Intent repeating_intent;
    PendingIntent pendingIntent;
    @Override
    public void onReceive(Context context, Intent intent) {

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        repeating_intent = new Intent(context,Repeating_activity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        pendingIntent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.arrow_down_float)
                .setContentTitle("Notification Remember")
                .setContentText("Remember You")
                .setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_SOUND);

        notificationManager.notify(100,builder.build());
    }
}
