package com.example.application_for_forgetful_people.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "Statistics")
public class Statistics {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    @NonNull
    private long reminderId;

    @NonNull
    private boolean wasForgotten;

    @NonNull
    private String dayOfForgettingActivity;

    @NonNull
    private String monthOfForgettingActivity;

    @NonNull
    private String yearOfForgettingActivity;

    public Statistics(long id, long reminderId, boolean wasForgotten) {
        this.id = id;
        this.reminderId = reminderId;
        this.wasForgotten = wasForgotten;
    }

    @Ignore
    public Statistics(long reminderId, boolean wasForgotten) {
        this.reminderId = reminderId;
        this.wasForgotten = wasForgotten;
    }

    @Ignore
    public Statistics(long reminderId, boolean wasForgotten, @NonNull String dayOfForgettingActivity, @NonNull String monthOfForgettingActivity, @NonNull String yearOfForgettingActivity) {
        this.reminderId = reminderId;
        this.wasForgotten = wasForgotten;
        this.dayOfForgettingActivity = dayOfForgettingActivity;
        this.monthOfForgettingActivity = monthOfForgettingActivity;
        this.yearOfForgettingActivity = yearOfForgettingActivity;
    }
@Ignore
    public Statistics(long id, long reminderId, boolean wasForgotten, @NonNull String dayOfForgettingActivity, @NonNull String monthOfForgettingActivity, @NonNull String yearOfForgettingActivity) {
        this.id = id;
        this.reminderId = reminderId;
        this.wasForgotten = wasForgotten;
        this.dayOfForgettingActivity = dayOfForgettingActivity;
        this.monthOfForgettingActivity = monthOfForgettingActivity;
        this.yearOfForgettingActivity = yearOfForgettingActivity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getReminderId() {
        return reminderId;
    }

    public void setReminderId(long reminderId) {
        this.reminderId = reminderId;
    }

    public boolean isWasForgotten() {
        return wasForgotten;
    }

    public void setWasForgotten(boolean wasForgotten) {
        this.wasForgotten = wasForgotten;
    }

    @NonNull
    public String getDayOfForgettingActivity() {
        return dayOfForgettingActivity;
    }

    public void setDayOfForgettingActivity(@NonNull String dayOfForgettingActivity) {
        this.dayOfForgettingActivity = dayOfForgettingActivity;
    }

    @NonNull
    public String getMonthOfForgettingActivity() {
        return monthOfForgettingActivity;
    }

    public void setMonthOfForgettingActivity(@NonNull String monthOfForgettingActivity) {
        this.monthOfForgettingActivity = monthOfForgettingActivity;
    }

    @NonNull
    public String getYearOfForgettingActivity() {
        return yearOfForgettingActivity;
    }

    public void setYearOfForgettingActivity(@NonNull String yearOfForgettingActivity) {
        this.yearOfForgettingActivity = yearOfForgettingActivity;
    }
}
