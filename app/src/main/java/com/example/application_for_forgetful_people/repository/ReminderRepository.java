package com.example.application_for_forgetful_people.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.application_for_forgetful_people.AppRoomDatabase;
import com.example.application_for_forgetful_people.dao.ReminderDao;
import com.example.application_for_forgetful_people.entity.Reminder;

import java.util.List;

public class ReminderRepository {

    private final ReminderDao reminderDao;
    private final LiveData<List<Reminder>> listOfReminders;

    public ReminderRepository(Application application) {
        AppRoomDatabase appRoomDatabase =
                AppRoomDatabase.getDatabase(application);
        reminderDao = appRoomDatabase.reminderDao();
        listOfReminders = reminderDao.getAllReminders();
    }

    public LiveData<List<Reminder>> getAllReminders(){
        return listOfReminders;
    }

    public void deleteAll(){
        AppRoomDatabase.databaseWriteExecutor.execute(() ->{
            reminderDao.deleteAll();
        });
    }

    public void insert(Reminder reminder){
        AppRoomDatabase.databaseWriteExecutor.execute(() ->{
            reminderDao.insert(reminder);
        });
    }

    public void deleteReminder(Reminder reminder){
        AppRoomDatabase.databaseWriteExecutor.execute(() ->{
            reminderDao.deleteReminder(reminder);
        });
    }

    public void deleteReminderById(Long id){
        AppRoomDatabase.databaseWriteExecutor.execute(() ->{
            reminderDao.deleteReminderById(id);
        });
    }
}
