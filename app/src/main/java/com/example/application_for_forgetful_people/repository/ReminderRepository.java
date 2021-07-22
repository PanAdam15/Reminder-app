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
    private final LiveData<List<Reminder>> listOfRemindersWhoseStatusIsActive;

    public ReminderRepository(Application application) {
        AppRoomDatabase appRoomDatabase =
                AppRoomDatabase.getDatabase(application);
        reminderDao = appRoomDatabase.reminderDao();
        listOfReminders = reminderDao.getAllReminders();
        listOfRemindersWhoseStatusIsActive = reminderDao.getRemindersWhoseAreActive();

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

    public void updateIsActiveStatus(boolean isActive, long id){
        AppRoomDatabase.databaseWriteExecutor.execute(() ->{
            reminderDao.updateIsActiveStatus(isActive,id);
        });
    }

    public LiveData<List<Reminder>> getRemindersWhoseStatusIsActive(){
        return listOfRemindersWhoseStatusIsActive;
    }

    public void update(Reminder reminder){
        AppRoomDatabase.databaseWriteExecutor.execute(() ->{
            reminderDao.update(reminder);
        });
    }
}
