package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class NotificationService extends Service {

    NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        PendingIntent pendingIntentActivity1 =
                PendingIntent.getActivity(
                        this,
                        0,
                        new Intent(this, NotificationActivity1.class),
                        PendingIntent.FLAG_CANCEL_CURRENT
                );

        PendingIntent pendingIntentActivity2 =
                PendingIntent.getActivity(
                        this,
                        0,
                        new Intent(this, NotificationActivity2.class),
                        PendingIntent.FLAG_CANCEL_CURRENT
                );

        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Уведомление")
                        .setContentText("Уведомление из сервиса")
                        .setContentIntent(pendingIntentActivity1)
                        .addAction(R.mipmap.ic_launcher, "Activity 1", pendingIntentActivity1)
                        .addAction(R.mipmap.ic_launcher, "Activity 2", pendingIntentActivity2)
                        .build();

        startForeground(1, notification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}