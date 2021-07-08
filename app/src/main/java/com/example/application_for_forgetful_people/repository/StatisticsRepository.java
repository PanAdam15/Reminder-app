package com.example.application_for_forgetful_people.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.application_for_forgetful_people.AppRoomDatabase;
import com.example.application_for_forgetful_people.dao.StatisticsDao;
import com.example.application_for_forgetful_people.dao.UserDao;
import com.example.application_for_forgetful_people.entity.Statistics;
import com.example.application_for_forgetful_people.entity.User;

import java.util.List;

public class StatisticsRepository {

    private final StatisticsDao statisticsDao;
    private final LiveData<List<Statistics>> listOfStatistics;

    public StatisticsRepository(Application application) {
        AppRoomDatabase appRoomDatabase =
                AppRoomDatabase.getDatabase(application);
        statisticsDao = appRoomDatabase.statisticsDao();
        listOfStatistics = statisticsDao.getAllStatistics();
    }

    public LiveData<List<Statistics>> getAllStatistics(){
        return listOfStatistics;
    }

    public void insert(Statistics statistics){
        AppRoomDatabase.databaseWriteExecutor.execute(() ->
                statisticsDao.insert(statistics));
    }
}
