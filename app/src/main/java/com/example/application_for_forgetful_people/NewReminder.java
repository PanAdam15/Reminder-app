package com.example.application_for_forgetful_people;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class NewReminder extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);

    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NewReminder.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
