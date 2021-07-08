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
    private String name;

    @NonNull
    private String disconnected;

    @NonNull
    private Integer amountOfForgotten;

    public Statistics(long id, @NonNull String name, @NonNull String disconnected, @NonNull Integer amountOfForgotten) {
        this.id = id;
        this.name = name;
        this.disconnected = disconnected;
        this.amountOfForgotten = amountOfForgotten;
    }
    @Ignore
    public Statistics(@NonNull String name, @NonNull String disconnected, @NonNull Integer amountOfForgotten) {
        this.name = name;
        this.disconnected = disconnected;
        this.amountOfForgotten = amountOfForgotten;
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

    @NonNull
    public String getDisconnected() {
        return disconnected;
    }

    public void setDisconnected(@NonNull String disconnected) {
        this.disconnected = disconnected;
    }

    @NonNull
    public Integer getAmountOfForgotten() {
        return amountOfForgotten;
    }

    public void setAmountOfForgotten(@NonNull Integer amountOfForgotten) {
        this.amountOfForgotten = amountOfForgotten;
    }
}
