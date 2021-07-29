package com.example.application_for_forgetful_people;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class NewReminder extends AppCompatActivity {

    private Bundle pack;
    private Long id;
    private String name;
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

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

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
                    updateIntent.putExtra("active",isActive);
                    setResult(RESULT_OK, updateIntent);
                    finish();

//                    if(id==null){
//                        Reminder reminderAdd = new Reminder(nameOfRemidner,isMon,isTue,isWen,isThu,isFr,isSat,isSun,isBt,isRing,true);
//                        reminderViewModel.insert(reminderAdd);
//                    }
//                    else {
//                        Reminder reminderUpdate = new Reminder(id, nameOfRemidner, isMon, isTue, isWen, isThu, isFr, isSat, isSun, isBt, isRing, true);
//                        reminderViewModel.update(reminderUpdate);
//                    }
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

    @Override // back button in nav bar
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
