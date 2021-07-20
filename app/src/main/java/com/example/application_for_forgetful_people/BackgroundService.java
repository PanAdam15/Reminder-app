package com.example.application_for_forgetful_people;

import android.app.*;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class BackgroundService extends Service {

    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        this.registerReceiver(mReceiver, filter);


        startForeground();

        return super.onStartCommand(intent, flags, startId);
    }

    private void startForeground() {

        createNotificationChannel()
                ;
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        startForeground(NOTIF_ID, new NotificationCompat.Builder(this,
                NOTIF_CHANNEL_ID) // don't forget create a notification channel first
                .setOngoing(true)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Service is running background")
                .setContentIntent(pendingIntent)
                .build());
    }

    //The BroadcastReceiver that listens for bluetooth broadcasts
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

//            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//           ... //Device found
//            }
//            else if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
//           ... //Device is now connected
//            }
//            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
//           ... //Done searching
//            }
//            else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(action)) {
//           ... //Device is about to disconnect
//            }
//            else
            if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                setAlarm();
            }
        }
    };

    public void setAlarm(){
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this,AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,0,0,pendingIntent);

        Toast.makeText(this, "Rozłączono z urządzeniem",Toast.LENGTH_LONG).show();

    }

    private void createNotificationChannel(){
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(NOTIF_CHANNEL_ID,NOTIF_CHANNEL_ID,importance);
        channel.setDescription("Background service notification");

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

    }

}
