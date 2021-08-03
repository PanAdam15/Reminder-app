package com.example.application_for_forgetful_people.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalTime;

@Entity(tableName = "Reminder")
public class Reminder {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    @NonNull
    private String name;

    @NonNull
    private boolean ifMonday;

    @NonNull
    private boolean ifTuesday;

    @NonNull
    private boolean ifWednesday;

    @NonNull
    private boolean ifThursday;

    @NonNull
    private boolean ifFriday;

    @NonNull
    private boolean ifSaturday;

    @NonNull
    private boolean ifSunday;

    @NonNull
    private boolean ifBluetooth;

    @NonNull
    private boolean ifRing;

    @NonNull
    private boolean isActive;

    public Reminder(long id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }
    @Ignore
    public Reminder(long id, @NonNull String name, boolean ifMonday, boolean ifTuesday, boolean ifWednesday, boolean ifThursday, boolean ifFriday, boolean ifSaturday, boolean ifSunday, boolean ifBluetooth, boolean ifRing, boolean isActive) {
        this.id = id;
        this.name = name;
        this.ifMonday = ifMonday;
        this.ifTuesday = ifTuesday;
        this.ifWednesday = ifWednesday;
        this.ifThursday = ifThursday;
        this.ifFriday = ifFriday;
        this.ifSaturday = ifSaturday;
        this.ifSunday = ifSunday;
        this.ifBluetooth = ifBluetooth;
        this.ifRing = ifRing;
        this.isActive = isActive;
    }

    @Ignore
    public Reminder(@NonNull String name, boolean ifMonday, boolean ifTuesday, boolean ifWednesday, boolean ifThursday, boolean ifFriday, boolean ifSaturday, boolean ifSunday, boolean ifBluetooth, boolean ifRing, boolean isActive) {
        this.name = name;
        this.ifMonday = ifMonday;
        this.ifTuesday = ifTuesday;
        this.ifWednesday = ifWednesday;
        this.ifThursday = ifThursday;
        this.ifFriday = ifFriday;
        this.ifSaturday = ifSaturday;
        this.ifSunday = ifSunday;
        this.ifBluetooth = ifBluetooth;
        this.ifRing = ifRing;
        this.isActive = isActive;
    }
   @Ignore
    public Reminder(@NonNull String name, boolean ifMonday, boolean ifTuesday, boolean ifWednesday, boolean ifThursday, boolean ifFriday, boolean ifSaturday, boolean ifSunday, boolean ifBluetooth, boolean ifRing) {
        this.name = name;
        this.ifMonday = ifMonday;
        this.ifTuesday = ifTuesday;
        this.ifWednesday = ifWednesday;
        this.ifThursday = ifThursday;
        this.ifFriday = ifFriday;
        this.ifSaturday = ifSaturday;
        this.ifSunday = ifSunday;
        this.ifBluetooth = ifBluetooth;
        this.ifRing = ifRing;
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

    public boolean isIfMonday() {
        return ifMonday;
    }

    public void setIfMonday(boolean ifMonday) {
        this.ifMonday = ifMonday;
    }

    public boolean isIfTuesday() {
        return ifTuesday;
    }

    public void setIfTuesday(boolean ifTuesday) {
        this.ifTuesday = ifTuesday;
    }

    public boolean isIfWednesday() {
        return ifWednesday;
    }

    public void setIfWednesday(boolean ifWednesday) {
        this.ifWednesday = ifWednesday;
    }

    public boolean isIfThursday() {
        return ifThursday;
    }

    public void setIfThursday(boolean ifThursday) {
        this.ifThursday = ifThursday;
    }

    public boolean isIfFriday() {
        return ifFriday;
    }

    public void setIfFriday(boolean ifFriday) {
        this.ifFriday = ifFriday;
    }

    public boolean isIfSaturday() {
        return ifSaturday;
    }

    public void setIfSaturday(boolean ifSaturday) {
        this.ifSaturday = ifSaturday;
    }

    public boolean isIfSunday() {
        return ifSunday;
    }

    public void setIfSunday(boolean ifSunday) {
        this.ifSunday = ifSunday;
    }

    public boolean isIfBluetooth() {
        return ifBluetooth;
    }

    public void setIfBluetooth(boolean ifBluetooth) {
        this.ifBluetooth = ifBluetooth;
    }

    public boolean isIfRing() {
        return ifRing;
    }

    public void setIfRing(boolean ifRing) {
        this.ifRing = ifRing;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
