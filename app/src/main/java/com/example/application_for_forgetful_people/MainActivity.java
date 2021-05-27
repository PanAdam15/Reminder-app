package com.example.application_for_forgetful_people;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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

        addNewReminderButton = (Button) findViewById(R.id.AddNewReminder);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        list = (ListView) findViewById(R.id.listView1);
        String reminders[] = {"Pranie", "Lalala", "Marmolada"};
        ArrayList<String> reminderL = new ArrayList<String>();
        reminderL.addAll(Arrays.asList(reminders));
        adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,reminderL);
        list.setAdapter(adapter);
        addNewReminderButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewReminder.class);
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }


}
