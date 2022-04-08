package com.example.application_for_forgetful_people.activities;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.application_for_forgetful_people.R;
import com.example.application_for_forgetful_people.TimePickerFragment;
import com.example.application_for_forgetful_people.entity.Hint;
import com.example.application_for_forgetful_people.viewModels.HintViewModel;
import com.example.application_for_forgetful_people.viewModels.ReminderViewModel;
import top.defaults.colorpicker.ColorPickerPopup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewReminderActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

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
    private Switch switchBluetooth, switchall;
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
    private List<String> listOfHints;
    private HintViewModel hintViewModel;
    private String[] tab;
    private Button mSetColorButton, mPickColorButton;
    private View mColorPreview;
    private int mDefaultColor;
    private TextView background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                hintViewModel = new ViewModelProvider(NewReminderActivity.this).get(HintViewModel.class);
                listOfHints = hintViewModel.getAllHints();
                tab = new String[listOfHints.size()];
                listOfHints.toArray(tab);
            }
        });

        t.start();

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
        mPickColorButton = findViewById(R.id.colorButton);
        mColorPreview = findViewById(R.id.colorPreview);
        background = findViewById(R.id.textView4);
        switchall = findViewById(R.id.switch1);
        mDefaultColor = -16711681;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nameOfNewReminder.setAdapter(new ArrayAdapter<String>(NewReminderActivity.this,R.layout.support_simple_spinner_dropdown_item,tab));

        assert getSupportActionBar() != null;
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

        nameOfNewReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameOfNewReminder.showDropDown();

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

        switchall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBoxMon.setChecked(true);
                    checkBoxTue.setChecked(true);
                    checkBoxWen.setChecked(true);
                    checkBoxThu.setChecked(true);
                    checkBoxFr.setChecked(true);
                    checkBoxSat.setChecked(true);
                    checkBoxSun.setChecked(true);
                }
                else{
                    checkBoxMon.setChecked(false);
                    checkBoxTue.setChecked(false);
                    checkBoxWen.setChecked(false);
                    checkBoxThu.setChecked(false);
                    checkBoxFr.setChecked(false);
                    checkBoxSat.setChecked(false);
                    checkBoxSun.setChecked(false);
                }

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
                        Toast.makeText(NewReminderActivity.this, "Proszę, wybierz czas", Toast.LENGTH_SHORT).show();
                    }
                    else if(days.isEmpty()){
                        Toast.makeText(NewReminderActivity.this, "Proszę, wybierz przynajmniej jeden dzień", Toast.LENGTH_SHORT).show();
                    }
                    else if(nameOfRemidner.isEmpty()){
                        Toast.makeText(NewReminderActivity.this, "Nazwa nie moze byc pusta", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if (!listOfHints.contains(nameOfRemidner)) {
                            hintViewModel.insert(new Hint(nameOfRemidner));
                        }
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
                    if(nameOfRemidner.length() > 45){
                        Toast.makeText(NewReminderActivity.this, "Nazwa nie moze zawierać więcej niż 45 znaków", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (!listOfHints.contains(nameOfRemidner)) {
                            hintViewModel.insert(new Hint(nameOfRemidner));
                        }
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
                        updateIntent.putExtra("color", mDefaultColor);
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
        mPickColorButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        new ColorPickerPopup.Builder(NewReminderActivity.this).initialColor(Color.RED)
                                .enableBrightness(true)
                                .enableAlpha(true)
                                .okTitle("Wybierz")
                                .cancelTitle("Wstecz")
                                .showIndicator(true)
                                .showValue(true)
                                .build()
                                .show(
                                        v,
                                        new ColorPickerPopup.ColorPickerObserver() {
                                            @Override
                                            public void
                                            onColorPicked(int color) {
                                                mDefaultColor = color;
                                                mColorPreview.setBackgroundColor(mDefaultColor);

                                            }
                                        });

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
