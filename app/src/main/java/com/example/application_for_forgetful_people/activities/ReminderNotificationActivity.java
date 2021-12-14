package com.example.application_for_forgetful_people.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application_for_forgetful_people.R;
import com.example.application_for_forgetful_people.adapters.NotificationReminderListAdapter;
import com.example.application_for_forgetful_people.entity.Statistics;
import com.example.application_for_forgetful_people.viewModels.ReminderViewModel;
import com.example.application_for_forgetful_people.viewModels.StatisticsViewModel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReminderNotificationActivity extends AppCompatActivity {

    private ReminderViewModel reminderViewModel;
    private NotificationReminderListAdapter notificationReminderListAdapter;
    private StatisticsViewModel statisticViewModel;
    private Button confirmButton;
    private HashMap<Long, Boolean> statmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        confirmButton = findViewById(R.id.buttonNotificationConfirm);

        Thread t = new Thread(() -> {
            RecyclerView recyclerView = findViewById(R.id.recycler_view_notification);
            notificationReminderListAdapter = new NotificationReminderListAdapter(ReminderNotificationActivity.this);

            recyclerView.setAdapter(notificationReminderListAdapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(ReminderNotificationActivity.this));
            reminderViewModel = new ViewModelProvider(ReminderNotificationActivity.this).get(ReminderViewModel.class);
            statisticViewModel = new ViewModelProvider(ReminderNotificationActivity.this).get(StatisticsViewModel.class);
            notificationReminderListAdapter.setStatisticViewModel(statisticViewModel);

            notificationReminderListAdapter.setReminderViewModel(reminderViewModel);
        });
        t.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reminderViewModel.getListOfRemindersWhoseStatusIsActive().observe(ReminderNotificationActivity.this, reminders -> {
            notificationReminderListAdapter.setListOfReminders(reminders);
        });

        confirmButton.setVisibility(View.INVISIBLE);

        notificationReminderListAdapter.setConfirmButton(confirmButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statmap = notificationReminderListAdapter.getStatMap();
                for (Map.Entry<Long, Boolean> entry : statmap.entrySet()) {
                    LocalDate currentDate = LocalDate.now();
                    statisticViewModel.insert(new Statistics(entry.getKey(), entry.getValue(), String.valueOf(currentDate.getDayOfMonth()), String.valueOf(currentDate.getMonthValue()), String.valueOf(currentDate.getYear())));
                    Intent intent = new Intent(ReminderNotificationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}