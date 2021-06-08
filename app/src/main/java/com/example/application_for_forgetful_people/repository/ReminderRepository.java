package com.example.application_for_forgetful_people.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.application_for_forgetful_people.ReminderRoomDatabase;
import com.example.application_for_forgetful_people.dao.ReminderDao;
import com.example.application_for_forgetful_people.entity.Reminder;

import java.util.List;

public class ReminderRepository {

    private final ReminderDao reminderDao;
    private final LiveData<List<Reminder>> listOfReminders;

    public ReminderRepository(Application application) {
        ReminderRoomDatabase reminderRoomDatabase =
                ReminderRoomDatabase.getDatabase(application);
        reminderDao = reminderRoomDatabase.reminderDao();
        listOfReminders = reminderDao.getAllReminders();
    }

    public LiveData<List<Reminder>> getAllReminders(){
        return listOfReminders;
    }

    public void deleteAll(){
        ReminderRoomDatabase.databaseWriteExecutor.execute(() ->{
            reminderDao.deleteAll();
        });
    }

    public void insert(Reminder reminder){
        ReminderRoomDatabase.databaseWriteExecutor.execute(() ->{
            reminderDao.insert(reminder);
        });
    }

    public void deleteReminder(Reminder reminder){
        ReminderRoomDatabase.databaseWriteExecutor.execute(() ->{
            reminderDao.deleteReminder(reminder);
        });
    }
}
