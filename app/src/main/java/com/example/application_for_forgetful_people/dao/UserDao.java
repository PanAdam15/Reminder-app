package com.example.application_for_forgetful_people.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.application_for_forgetful_people.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(User user);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();
}
