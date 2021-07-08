package com.example.application_for_forgetful_people.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.application_for_forgetful_people.AppRoomDatabase;
import com.example.application_for_forgetful_people.dao.CuriosityDao;
import com.example.application_for_forgetful_people.entity.Curiosity;

import java.util.List;

public class CuriosityRepository {

    private final CuriosityDao curiosityDao;
    private final LiveData<List<Curiosity>> listOfCuriosities;

    public CuriosityRepository(Application application){
        AppRoomDatabase appRoomDatabase =
                AppRoomDatabase.getDatabase(application);
        curiosityDao = appRoomDatabase.curiosityDao();
        listOfCuriosities = curiosityDao.getAllCuriosities();
    }

    public LiveData<List<Curiosity>> getAllCuriosities(){
        return listOfCuriosities;
    }

    public void insert(Curiosity curiosity){
        AppRoomDatabase.databaseWriteExecutor.execute(() ->{
            curiosityDao.insert(curiosity);
        });
    }
}
