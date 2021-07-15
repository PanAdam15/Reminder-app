package com.example.application_for_forgetful_people;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReminderNotificationActivity extends AppCompatActivity {

    private ReminderViewModel reminderViewModel;
    private NotificationReminderListAdapter notificationReminderListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_notification);
        notificationReminderListAdapter = new NotificationReminderListAdapter(this);

        recyclerView.setAdapter(notificationReminderListAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);

        notificationReminderListAdapter.setReminderViewModel(reminderViewModel);

        reminderViewModel.getAllReminders().observe(this, reminders -> {
            notificationReminderListAdapter.setListOfReminders(reminders);
        });
    }
}