package com.example.application_for_forgetful_people.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.application_for_forgetful_people.entity.Curiosity;

import java.util.List;

@Dao
public interface CuriosityDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Curiosity curiosity);

    @Query("SELECT * FROM curiosity")
    List<Curiosity> getAllCuriosities();
}
