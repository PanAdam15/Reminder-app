package com.example.application_for_forgetful_people.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.application_for_forgetful_people.entity.Hint;

import java.util.List;

@Dao
public interface HintDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Hint hint);

    @Query("SELECT content FROM hint")
    List<String> getAllHints();
}
