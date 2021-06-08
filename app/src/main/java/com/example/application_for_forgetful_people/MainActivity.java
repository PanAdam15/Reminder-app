package com.example.application_for_forgetful_people;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button addNewReminderButton;
    Button settingsButton;
    ListView list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNewReminderButton = findViewById(R.id.AddNewReminder);
        settingsButton = findViewById(R.id.settingsButton);
        list = findViewById(R.id.recycler_view1);
        String[] reminders = {"Pranie", "Woda", "Światło","Żelazko na gazie"};
        ArrayList<String> reminderL = new ArrayList<>(Arrays.asList(reminders));
        adapter = new ArrayAdapter<>(this, R.layout.activity_row, reminderL);
        list.setAdapter(adapter);
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
