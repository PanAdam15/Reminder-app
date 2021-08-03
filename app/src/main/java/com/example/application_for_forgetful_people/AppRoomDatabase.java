package com.example.application_for_forgetful_people;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.application_for_forgetful_people.dao.CuriosityDao;
import com.example.application_for_forgetful_people.dao.ReminderDao;
import com.example.application_for_forgetful_people.dao.StatisticsDao;
import com.example.application_for_forgetful_people.dao.UserDao;
import com.example.application_for_forgetful_people.entity.Curiosity;
import com.example.application_for_forgetful_people.entity.Reminder;
import com.example.application_for_forgetful_people.entity.User;
import com.example.application_for_forgetful_people.entity.Statistics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Reminder.class, User.class, Statistics.class, Curiosity.class}, version = 11, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {

    public abstract ReminderDao reminderDao();
    public abstract UserDao userDao();
    public abstract StatisticsDao statisticsDao();
    public abstract CuriosityDao curiosityDao();

    private static volatile AppRoomDatabase INSTANCE;

    public static AppRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class,"ReminderDB")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                ReminderDao dao = INSTANCE.reminderDao();
                UserDao userDao = INSTANCE.userDao();
                StatisticsDao statisticsDao = INSTANCE.statisticsDao();
                CuriosityDao curiosityDao = INSTANCE.curiosityDao();
            });
        }
    };
}
