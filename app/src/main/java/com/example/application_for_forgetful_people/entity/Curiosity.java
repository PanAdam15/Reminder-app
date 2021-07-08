package com.example.application_for_forgetful_people.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Curiosity")
public class Curiosity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    @NonNull
    private String contents;

    @Ignore
    public Curiosity() {
    }

    public Curiosity(long id, @NonNull String contents) {
        this.id = id;
        this.contents = contents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getContents() {
        return contents;
    }

    public void setContents(@NonNull String contents) {
        this.contents = contents;
    }
}
