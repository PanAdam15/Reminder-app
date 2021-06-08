package com.example.application_for_forgetful_people;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.application_for_forgetful_people.dao.ReminderDao;
import com.example.application_for_forgetful_people.entity.Reminder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Reminder.class}, version = 1, exportSchema = false)
public abstract class ReminderRoomDatabase extends RoomDatabase {

    public abstract ReminderDao reminderDao();

    private static volatile ReminderRoomDatabase INSTANCE;

    public static ReminderRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ReminderRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ReminderRoomDatabase.class,"ReminderDB")
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

            });
        }
    };
}
