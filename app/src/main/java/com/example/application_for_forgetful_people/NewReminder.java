package com.example.application_for_forgetful_people;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Calendar;

public class NewReminder extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private Bundle pack;
    private Long id;
    private String name;
    private ReminderViewModel reminderViewModel;
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
    private AutoCompleteTextView nameOfNewReminder;
    private String[] tab = new String[]{"Pranie","Żelazko","Gaz","Zamknąć drzwi","Nakarmić zwierzęta","Klucze","Portfel"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
        ArrayList<Integer> days = new ArrayList<>();
        reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);

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
        nameOfNewReminder = findViewById(R.id.nameOfNewActivity);

        nameOfNewReminder.setAdapter(new ArrayAdapter<String>(NewReminder.this,R.layout.support_simple_spinner_dropdown_item,tab));

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nowe przypomnienie");
        isActive = true;

        pack = getIntent().getExtras();
        if (pack != null) {
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

        if (isBt)
            chooseTimeOfReminder.setVisibility(View.INVISIBLE);
        else
            chooseTimeOfReminder.setVisibility(View.VISIBLE);

        checkBoxMon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    days.add(Calendar.MONDAY);
                else
                    days.remove((Integer) Calendar.MONDAY);
            }
        });

        checkBoxTue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    days.add(Calendar.TUESDAY);
                else
                    days.remove((Integer) Calendar.TUESDAY);
            }
        });

        checkBoxWen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    days.add(Calendar.WEDNESDAY);
                else
                    days.remove((Integer) Calendar.WEDNESDAY);
            }
        });

        checkBoxThu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    days.add(Calendar.THURSDAY);
                else
                    days.remove((Integer) Calendar.THURSDAY);
            }
        });

        checkBoxFr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    days.add(Calendar.FRIDAY);
                else
                    days.remove((Integer) Calendar.FRIDAY);
            }
        });

        checkBoxSat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    days.add(Calendar.SATURDAY);
                else
                    days.remove((Integer) Calendar.SATURDAY);
            }
        });

        checkBoxSun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    days.add(Calendar.SUNDAY);
                else
                    days.remove((Integer) Calendar.SUNDAY);
            }
        });

        addNewReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCheckboxesAndSwitches();
                checkAlreadyCheckedCheckboxes(days);
                String nameOfRemidner = nameOfNewReminder.getText().toString();
                if (!switchBluetooth.isChecked()) {
                    if ((hourOfReminderActivate == null && minuteOfReminderActivate == null)) {
                        Toast.makeText(NewReminder.this, "Proszę, wybierz czas", Toast.LENGTH_SHORT).show();
                    }
                    else if(days.isEmpty()){
                        Toast.makeText(NewReminder.this, "Proszę, wybierz przynajmniej jeden dzień", Toast.LENGTH_SHORT).show();
                    }
                    else if(nameOfRemidner.isEmpty()){
                        Toast.makeText(NewReminder.this, "Nazwa nie moze byc pusta", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                        if(id != null)
                            reminderViewModel.deleteReminderById(id);
                        intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(hourOfReminderActivate));
                        intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(minuteOfReminderActivate));
                        intent.putExtra(AlarmClock.EXTRA_MESSAGE, nameOfRemidner);
                        intent.putExtra(AlarmClock.EXTRA_DAYS, days);
                        startActivity(intent);
                        finish();
                    }

                } else {

                    if (nameOfRemidner.trim().length() == 0)
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
                        setResult(RESULT_OK, updateIntent);
                        finish();
                    }
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

                if (isChecked) {
                    chooseTimeOfReminder.setVisibility(View.INVISIBLE);
                } else {
                    chooseTimeOfReminder.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    public void checkCheckboxesAndSwitches() {

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

    public void checkAlreadyCheckedCheckboxes(ArrayList<Integer> days){
        if(checkBoxMon.isChecked())
            days.add(Calendar.MONDAY);
        if(checkBoxTue.isChecked())
            days.add(Calendar.TUESDAY);
        if(checkBoxWen.isChecked())
            days.add(Calendar.WEDNESDAY);
        if(checkBoxThu.isChecked())
            days.add(Calendar.THURSDAY);
        if(checkBoxFr.isChecked())
            days.add(Calendar.FRIDAY);
        if(checkBoxSat.isChecked())
            days.add(Calendar.SATURDAY);
        if(checkBoxSun.isChecked())
            days.add(Calendar.SUNDAY);
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
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
