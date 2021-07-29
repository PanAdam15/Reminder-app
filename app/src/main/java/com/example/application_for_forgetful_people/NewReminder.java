package com.example.application_for_forgetful_people;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.time.LocalTime;

public class NewReminder extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private Bundle pack;
    private Long id;
    private String name;
    private ReminderViewModel reminderViewModel;
    private EditText nameOfNewReminder;
    private Button addNewReminderButton;
    private Button chooseTimeOfReminder;
    private CheckBox checkBoxMon;
    private CheckBox checkBoxTue;
    private CheckBox checkBoxWen;
    private CheckBox checkBoxThu;
    private CheckBox checkBoxFr;
    private CheckBox checkBoxSat;
    private CheckBox checkBoxSun;
    private Switch switchBluetooth;
    private Switch switchRing;
    boolean isMon;
    boolean isTue;
    boolean isWen;
    boolean isThu;
    boolean isFr;
    boolean isSat;
    boolean isSun;
    boolean isBt;
    boolean isRing;
    boolean isActive;
    private String hourOfReminderActivate;
    private String minuteOfReminderActivate;

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
        switchBluetooth = findViewById(R.id.switchBluetooth);
        switchRing = findViewById(R.id.switchRing);
        chooseTimeOfReminder = findViewById(R.id.chooseTimeButton);

        pack = getIntent().getExtras();
        if(pack !=null){
            id = pack.getLong("id");
            name = pack.getString("name");
            isMon = pack.getBoolean("mon");
            isTue = pack.getBoolean("tue");
            isWen = pack.getBoolean("wed");
            isThu = pack.getBoolean("thu");
            isFr = pack.getBoolean("fri");
            isSat = pack.getBoolean("sat");
            isSun = pack.getBoolean("sun");
            isBt = pack.getBoolean("bt");
            isRing = pack.getBoolean("speaker");
            isActive = pack.getBoolean("active");
            nameOfNewReminder.setText(name);
            checkBoxMon.setChecked(isMon);
            checkBoxTue.setChecked(isTue);
            checkBoxWen.setChecked(isWen);
            checkBoxThu.setChecked(isThu);
            checkBoxFr.setChecked(isFr);
            checkBoxSat.setChecked(isSat);
            checkBoxSun.setChecked(isSun);
            switchBluetooth.setChecked(isBt);
            switchRing.setChecked(isRing);
        }

        if(isBt)
            chooseTimeOfReminder.setVisibility(View.INVISIBLE);
        else
            chooseTimeOfReminder.setVisibility(View.VISIBLE);

        addNewReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCheckboxesAndSwitches();
                String nameOfRemidner = nameOfNewReminder.getText().toString();

                if(nameOfRemidner.trim().length() == 0)
                    Toast.makeText(NewReminder.this, "Nazwa nie moze byc pusta", Toast.LENGTH_SHORT).show();
                else {
                    Intent updateIntent = new Intent();
                    updateIntent.putExtra("id", id);
                    updateIntent.putExtra("name", nameOfRemidner);
                    updateIntent.putExtra("mon", isMon);
                    updateIntent.putExtra("tue", isTue);
                    updateIntent.putExtra("wed", isWen);
                    updateIntent.putExtra("thu", isThu);
                    updateIntent.putExtra("fri", isFr);
                    updateIntent.putExtra("sat", isSat);
                    updateIntent.putExtra("sun", isSun);
                    updateIntent.putExtra("bt", isBt);
                    updateIntent.putExtra("speaker", isRing);
                    updateIntent.putExtra("active", isActive);
                    updateIntent.putExtra("hour", hourOfReminderActivate);
                    updateIntent.putExtra("minute", minuteOfReminderActivate);
                    setResult(RESULT_OK, updateIntent);
                    finish();
                }
            }
        });

        chooseTimeOfReminder.setOnClickListener(v -> {
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
        });

        switchBluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    chooseTimeOfReminder.setVisibility(View.INVISIBLE);
                }
                else {
                    chooseTimeOfReminder.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    public void checkCheckboxesAndSwitches(){

        isMon = checkBoxMon.isChecked();
        isTue = checkBoxTue.isChecked();
        isWen = checkBoxWen.isChecked();
        isThu = checkBoxThu.isChecked();
        isFr = checkBoxFr.isChecked();
        isSat = checkBoxSat.isChecked();
        isSun = checkBoxSun.isChecked();
        isBt = switchBluetooth.isChecked();
        isRing = switchRing.isChecked();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        hourOfReminderActivate = String.valueOf(hourOfDay);
        minuteOfReminderActivate = String.valueOf(minute);
    }
}
