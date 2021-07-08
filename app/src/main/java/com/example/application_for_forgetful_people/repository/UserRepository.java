package com.example.application_for_forgetful_people.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.application_for_forgetful_people.AppRoomDatabase;
import com.example.application_for_forgetful_people.dao.UserDao;
import com.example.application_for_forgetful_people.entity.User;

import java.util.List;

public class UserRepository {

    private final UserDao userDao;
    private final LiveData<List<User>> listOfUsers;

    public UserRepository(Application application) {
        AppRoomDatabase appRoomDatabase =
                AppRoomDatabase.getDatabase(application);
        userDao = appRoomDatabase.userDao();
        listOfUsers = userDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers(){
        return listOfUsers;
    }

    public void insert(User user){
        AppRoomDatabase.databaseWriteExecutor.execute(() ->
                userDao.insert(user));
    }
}
