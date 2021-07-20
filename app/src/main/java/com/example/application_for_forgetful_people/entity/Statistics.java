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
}
