package com.example.application_for_forgetful_people;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application_for_forgetful_people.entity.Reminder;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button addNewReminderButton;
    Button settingsButton;
    private ReminderViewModel reminderViewModel;
    private ReminderListAdapter reminderListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNewReminderButton = findViewById(R.id.AddNewReminder);
        settingsButton = findViewById(R.id.settingsButton);

        RecyclerView recyclerView = findViewById(R.id.recycler_view1);
        reminderListAdapter = new ReminderListAdapter(this);
        recyclerView.setAdapter(reminderListAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);

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


}
