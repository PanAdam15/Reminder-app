package com.example.application_for_forgetful_people;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application_for_forgetful_people.entity.Statistics;

import java.util.HashMap;
import java.util.Map;

public class ReminderNotificationActivity extends AppCompatActivity {

    private ReminderViewModel reminderViewModel;
    private NotificationReminderListAdapter notificationReminderListAdapter;
    private StatisticViewModel statisticViewModel;
    private Button confirmButton;
    private HashMap<Long,Boolean> statmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        confirmButton = findViewById(R.id.buttonNotificationConfirm);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_notification);
        notificationReminderListAdapter = new NotificationReminderListAdapter(this);

        recyclerView.setAdapter(notificationReminderListAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        statisticViewModel = new ViewModelProvider(this).get(StatisticViewModel.class);

        notificationReminderListAdapter.setStatisticViewModel(statisticViewModel);

        notificationReminderListAdapter.setReminderViewModel(reminderViewModel);

        reminderViewModel.getListOfRemindersWhoseStatusIsActive().observe(this, reminders -> {
            notificationReminderListAdapter.setListOfReminders(reminders);
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statmap = notificationReminderListAdapter.getStatMap();
                for(Map.Entry<Long,Boolean> entry: statmap.entrySet()){
                    statisticViewModel.insert(new Statistics(entry.getKey(),entry.getValue()));
                }
            }
        });
    }
}