package com.example.application_for_forgetful_people;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.application_for_forgetful_people.entity.Reminder;

public class NewReminder extends AppCompatActivity {

    private ReminderViewModel reminderViewModel;
    private EditText nameOfNewReminder;
    private Button addNewReminderButton;
    private CheckBox checkBoxMon;
    private CheckBox checkBoxTue;
    private CheckBox checkBoxWen;
    private CheckBox checkBoxThu;
    private CheckBox checkBoxFr;
    private CheckBox checkBoxSat;
    private CheckBox checkBoxSun;
    boolean isMon;
    boolean isTue;
    boolean isWen;
    boolean isThu;
    boolean isFr;
    boolean isSat;
    boolean isSun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
        reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        nameOfNewReminder = findViewById(R.id.nameOfNewActivity);
        addNewReminderButton = findViewById(R.id.AddNew);
        checkBoxMon = findViewById(R.id.checkBoxMonday);
        checkBoxTue = findViewById(R.id.checkBoxTuesday);
        checkBoxWen = findViewById(R.id.checkBoxWednesday);
        checkBoxThu = findViewById(R.id.checkBoxThursday);
        checkBoxFr = findViewById(R.id.checkBoxFriday);
        checkBoxSat = findViewById(R.id.checkBoxSaturday);
        checkBoxSun = findViewById(R.id.checkBoxSunday);

        addNewReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDays();
                String nameOfRemidner = nameOfNewReminder.getText().toString();
                Reminder reminder = new Reminder(nameOfRemidner,isMon,isTue,isWen,isThu,isFr,isSat,isSun);
                reminderViewModel.insert(reminder);
                Intent intent = new Intent(NewReminder.this,MainActivity.class);
                startActivityForResult(intent,RESULT_OK);
            }
        });



    }

    public void checkDays(){
        isMon = checkBoxMon.isChecked();
        isTue = checkBoxTue.isChecked();
        isWen = checkBoxWen.isChecked();
        isThu = checkBoxThu.isChecked();
        isFr = checkBoxFr.isChecked();
        isSat = checkBoxSat.isChecked();
        isSun = checkBoxSun.isChecked();
    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NewReminder.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
