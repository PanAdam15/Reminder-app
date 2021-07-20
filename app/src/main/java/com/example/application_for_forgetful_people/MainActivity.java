package com.example.application_for_forgetful_people;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application_for_forgetful_people.databinding.ActivityMainBinding;
import com.example.application_for_forgetful_people.entity.Reminder;
import com.tomer.fadingtextview.FadingTextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button addNewReminderButton;
    Button settingsButton;
    ImageButton bluetoothButton;
    private ReminderViewModel reminderViewModel;
    private ReminderListAdapter reminderListAdapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createNotificationChannel();

        startService(new Intent(this,BackgroundService.class));

        addNewReminderButton = findViewById(R.id.AddNewReminder);
        settingsButton = findViewById(R.id.settingsButton);
        bluetoothButton = findViewById(R.id.imageButton);

        RecyclerView recyclerView = findViewById(R.id.recycler_view1);
        reminderListAdapter = new ReminderListAdapter(this);

        ItemTouchHelper.Callback callback = new MyItemTouchHelper(reminderListAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        reminderListAdapter.setItemTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(reminderListAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);

        reminderListAdapter.setReminderViewModel(reminderViewModel);

        reminderViewModel.getAllReminders().observe(this, elements ->{
            reminderListAdapter.setListOfReminders(elements);
        });


        addNewReminderButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewReminder.class);
            startActivity(intent);
        });

        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

    }

    private void createNotificationChannel(){
        CharSequence name = "foxandroidReminderChannel";
        String description = "channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel("foxandroid",name,importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

    }




}
