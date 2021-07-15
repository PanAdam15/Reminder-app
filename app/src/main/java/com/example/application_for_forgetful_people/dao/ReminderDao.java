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

    @Query("SELECT * FROM reminder where isActive = 1")
    LiveData<List<Reminder>> getRemindersWhoseAreActive();

    @Delete
    void deleteReminder(Reminder reminder);

    @Query("DELETE FROM reminder WHERE id = :id")
    void deleteReminderById(Long id);

    @Query("UPDATE reminder set isActive = :isActive where id = :id")
    void updateIsActiveStatus(boolean isActive, long id);
}
