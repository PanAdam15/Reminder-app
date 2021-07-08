package com.example.application_for_forgetful_people.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.application_for_forgetful_people.entity.Reminder;
import com.example.application_for_forgetful_people.entity.Statistics;
import com.example.application_for_forgetful_people.entity.User;

import java.util.List;

@Dao
public interface StatisticsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Statistics statistics);

    @Query("SELECT * FROM statistics")
    LiveData<List<Statistics>> getAllStatistics();


}
