package com.example.application_for_forgetful_people.viewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.application_for_forgetful_people.entity.Reminder;
import com.example.application_for_forgetful_people.repository.ReminderRepository;

import java.util.List;

public class ReminderViewModel extends AndroidViewModel {

    private final ReminderRepository reminderRepository;
    private final LiveData<List<Reminder>> listOfReminders;
    private final LiveData<List<Reminder>> listOfRemindersWhoseStatusIsActive;

    public ReminderViewModel(@NonNull Application application) {
        super(application);
        reminderRepository = new ReminderRepository(application);
        listOfReminders = reminderRepository.getAllReminders();
        listOfRemindersWhoseStatusIsActive = reminderRepository.getRemindersWhoseStatusIsActive();
    }

    public LiveData<List<Reminder>> getAllReminders(){
        return listOfReminders;
    }

    public void deleteAll(){
        reminderRepository.deleteAll();
    }

    public void insert(Reminder reminder){
        reminderRepository.insert(reminder);
    }

    public void deleteReminder(Reminder reminder){
        reminderRepository.deleteReminder(reminder);
    }

    public void deleteReminderById(Long id){
        reminderRepository.deleteReminderById(id);
    }

    public void updateIsActiveStatus(boolean isActive, long id) {
        reminderRepository.updateIsActiveStatus(isActive,id);
    }

    public LiveData<List<Reminder>> getListOfRemindersWhoseStatusIsActive(){
        return listOfRemindersWhoseStatusIsActive;
    }

    public void update(Reminder reminder){ reminderRepository.update(reminder);}
}
