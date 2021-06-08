package com.example.application_for_forgetful_people.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Reminder")
public class Reminder {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    @NonNull
    private String name;

    public Reminder(long id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public Reminder(@NonNull String name) {
        this.name = name;
    }

    @Ignore
    public Reminder() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
