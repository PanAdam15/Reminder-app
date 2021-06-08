package com.example.application_for_forgetful_people;

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

    public ReminderViewModel(@NonNull Application application) {
        super(application);
        reminderRepository = new ReminderRepository(application);
        listOfReminders = reminderRepository.getAllReminders();
    }

    LiveData<List<Reminder>> getAllReminders(){
        return listOfReminders;
    }

    public void deleteAll(){
        reminderRepository.deleteAll();
    }

    public void insert(Reminder reminder){
        reminderRepository.insert(reminder);
    }

    public void deleteReminderById(Long id){
        reminderRepository.deleteReminderById(id);
    }
}
