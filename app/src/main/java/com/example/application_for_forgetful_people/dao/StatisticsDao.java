package com.example.application_for_forgetful_people.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.application_for_forgetful_people.entity.Statistics;

import java.util.List;

@Dao
public interface StatisticsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Statistics statistics);

    @Query("SELECT * FROM statistics")
    LiveData<List<Statistics>> getAllStatistics();

    @Query("SELECT COUNT(id) FROM statistics WHERE wasForgotten = 1")
    int getForgottenCount();

    @Query("SELECT * FROM statistics")
    List<Statistics> getStatisticAllToList();
}
