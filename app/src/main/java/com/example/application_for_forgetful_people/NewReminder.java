package com.example.application_for_forgetful_people;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.application_for_forgetful_people.entity.Reminder;

public class NewReminder extends AppCompatActivity {

    private ReminderViewModel reminderViewModel;
    private EditText nameOfNewReminder;
    private Button addNewReminderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
        reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        nameOfNewReminder = findViewById(R.id.nameOfNewActivity);
        addNewReminderButton = findViewById(R.id.AddNew);


        addNewReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameOfRemidner = nameOfNewReminder.getText().toString();
                reminderViewModel.insert(new Reminder(nameOfRemidner));
                Intent intent = new Intent(NewReminder.this,MainActivity.class);
                startActivityForResult(intent,RESULT_OK);
            }
        });

    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NewReminder.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
