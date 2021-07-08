package com.example.application_for_forgetful_people.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    @NonNull
    private String name;

    private String bluetoothNetworkName;

    public User(long id, @NonNull String name, String bluetoothNetworkName) {
        this.id = id;
        this.name = name;
        this.bluetoothNetworkName = bluetoothNetworkName;
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

    public String getBluetoothNetworkName() {
        return bluetoothNetworkName;
    }

    public void setBluetoothNetworkName(String bluetoothNetworkName) {
        this.bluetoothNetworkName = bluetoothNetworkName;
    }

    @Ignore
    public User() {
    }
}
