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

    @Query("SELECT * FROM reminder where isActive = 1 and ((SELECT strftime('%w','now') = '0' and ifSunday = 1) or " +
            "(SELECT strftime('%w','now') = '1' and ifMonday = 1) or " +
            "(SELECT strftime('%w','now') = '2' and ifTuesday = 1) or " +
            "(SELECT strftime('%w','now') = '3' and ifWednesday = 1) or " +
            "(SELECT strftime('%w','now') = '4' and ifThursday = 1) or " +
            "(SELECT strftime('%w','now') = '5' and ifFriday = 1) or " +
            "(SELECT strftime('%w','now') = '6' and ifSaturday = 1))")
    LiveData<List<Reminder>> getRemindersWhoseAreActive();

    @Delete
    void deleteReminder(Reminder reminder);

    @Query("DELETE FROM reminder WHERE id = :id")
    void deleteReminderById(Long id);

    @Query("UPDATE reminder set isActive = :isActive where id = :id")
    void updateIsActiveStatus(boolean isActive, long id);

    @Update
    void update(Reminder reminder);
}
