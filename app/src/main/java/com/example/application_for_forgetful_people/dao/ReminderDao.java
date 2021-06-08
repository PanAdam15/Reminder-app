package com.example.application_for_forgetful_people.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.application_for_forgetful_people.entity.Reminder;

import java.util.List;

@Dao
public interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Reminder reminder);

    @Query("DELETE FROM reminder")
    void deleteAll();

    @Query("SELECT * FROM reminder")
    LiveData<List<Reminder>> getAllReminders();

    @Delete
    void deleteReminder(Reminder reminder);

    @Query("DELETE FROM reminder WHERE id = :id")
    void deleteReminderById(Long id);


}
