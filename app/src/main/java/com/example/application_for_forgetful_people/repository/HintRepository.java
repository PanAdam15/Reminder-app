package com.example.application_for_forgetful_people.repository;

import android.app.Application;
import com.example.application_for_forgetful_people.AppRoomDatabase;
import com.example.application_for_forgetful_people.dao.HintDao;
import com.example.application_for_forgetful_people.entity.Hint;

import java.util.List;

public class HintRepository {

    private final HintDao hintDao;
    private final List<String> listOfHints;

    public HintRepository(Application application) {
        AppRoomDatabase appRoomDatabase = AppRoomDatabase.getDatabase(application);
        hintDao = appRoomDatabase.hintDao();
        listOfHints = hintDao.getAllHints();
    }

    public List<String> getAllHints() {
        return listOfHints;
    }

    public void insert(Hint hint) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            hintDao.insert(hint);
        });
    }
}
