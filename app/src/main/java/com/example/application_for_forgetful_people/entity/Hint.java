package com.example.application_for_forgetful_people.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Hint")
public class Hint {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    private String content;

    public Hint(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @Ignore
    public Hint(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
