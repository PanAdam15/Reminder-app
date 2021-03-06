package com.example.application_for_forgetful_people;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.application_for_forgetful_people.dao.CuriosityDao;
import com.example.application_for_forgetful_people.dao.HintDao;
import com.example.application_for_forgetful_people.dao.ReminderDao;
import com.example.application_for_forgetful_people.dao.StatisticsDao;
import com.example.application_for_forgetful_people.entity.Curiosity;
import com.example.application_for_forgetful_people.entity.Hint;
import com.example.application_for_forgetful_people.entity.Reminder;
import com.example.application_for_forgetful_people.entity.Statistics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Reminder.class, Statistics.class, Curiosity.class, Hint.class}, version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {

    public abstract ReminderDao reminderDao();
    public abstract StatisticsDao statisticsDao();
    public abstract CuriosityDao curiosityDao();
    public abstract HintDao hintDao();

    private static volatile AppRoomDatabase INSTANCE;

    public static AppRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class,"ReminderDST")
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
                StatisticsDao statisticsDao = INSTANCE.statisticsDao();
                CuriosityDao curiosityDao = INSTANCE.curiosityDao();
                HintDao hintDao = INSTANCE.hintDao();
                curiosityDao.insert(new Curiosity("curosity_1","W m??zgu jest 100 miliard??w neuron??w."));
                curiosityDao.insert(new Curiosity("curosity_2","Naczynia krwiono??ne obecne w m??zgu maj?? prawie 160 000 kilometr??w d??ugo??ci."));
                curiosityDao.insert(new Curiosity("curosity_3","M??zg w prawie 80% to woda."));
                curiosityDao.insert(new Curiosity("curosity_4","Tw??j m??zg, kiedy nie ??pimy generuje oko??o 25 wat??w energii."));
                curiosityDao.insert(new Curiosity("curosity_5","Dopiero oko??o 25 roku ??ycia ludzki m??zg osi??ga pe??n?? dojrza??o????"));
                curiosityDao.insert(new Curiosity("curosity_6","Ludzki m??zg wa??y oko??o 1,5 kg, co stanowi zaledwie 2% masy cia??a."));
                curiosityDao.insert(new Curiosity("curosity_7","Pr??dko???? impulsu nerwowego to ok. 400 km/godz."));
                curiosityDao.insert(new Curiosity("curosity_8","Cz??owiek do??wiadcza oko??o 70 tysi??cy my??li dziennie."));
                curiosityDao.insert(new Curiosity("curosity_9","90% decyzji podejmowanych jest pod??wiadomie."));
                curiosityDao.insert(new Curiosity("curosity_10","Ilo???? informacji docieraj??ca do naszego m??zgu wynosi oko??o 100 megabajt??w na sekund??."));
                curiosityDao.insert(new Curiosity("curosity_11","Picie alkoholu nie powoduje, ??e zapomina o rzeczach i wydarzeniach."));
                curiosityDao.insert(new Curiosity("curosity_12","Kiedy pijesz alkohol, Tw??j m??zg chwilowo traci zdolno???? tworzenia wspomnie??."));
                curiosityDao.insert(new Curiosity("curosity_13","Oko??o 25% procent cholesterolu cz??owieka znajduje si?? w m??zgu."));
                curiosityDao.insert(new Curiosity("curosity_14","Jedna trzecia ca??ego m??zgu zajmuje si?? przetwarzaniem bod??c??w wzrokowych."));
                curiosityDao.insert(new Curiosity("curosity_15","Dzi??ki intensywnemu kr????eniu 90 % energii cieplnej na zimnie ucieka przez g??ow??."));
                curiosityDao.insert(new Curiosity("curosity_16","Epilepsja to rodzaj ???zwarcia???, do kt??rego dochodzi w m??zgu."));
                curiosityDao.insert(new Curiosity("curosity_17","Jedynym stanem w kt??rym m??zg jest aktywny prawie w 100% jest stan epilepsji."));
                curiosityDao.insert(new Curiosity("curosity_18","Je??li jaki?? obszar m??zgu jest uszkodzony, inny potrafi przej???? jego funkcje."));
                curiosityDao.insert(new Curiosity("curosity_19","Muzyka wyzwala aktywno???? w tej samej cz????ci m??zgu, kt??ra uwalnia dopamin??."));
                curiosityDao.insert(new Curiosity("curosity_20","To, ??e wykorzystujemy tylko 10% naszego m??zgu to mit."));
                curiosityDao.insert(new Curiosity("curosity_21","Im bardziej stymulujemy m??zg do pracy tym lepiej on dzia??a."));
                curiosityDao.insert(new Curiosity("curosity_22","Aktywny m??zg jest bardziej odporny na starzenie si?? i Alzheimera."));
                curiosityDao.insert(new Curiosity("curosity_23","Ka??da cz?????? m??zgu ma przypisan?? konkretn?? funkcj??."));
                curiosityDao.insert(new Curiosity("curosity_24","Nasz m??zg ma ogromny potencja?? i mo??emy si?? nauczy?? jak efektywniej go u??ywa??."));
                hintDao.insert(new Hint("Pranie"));
                hintDao.insert(new Hint("??elazko"));
                hintDao.insert(new Hint("Gaz"));
                hintDao.insert(new Hint("Zamkn???? drzwi"));
                hintDao.insert(new Hint("Nakarwmi?? zwierz??ta"));
                hintDao.insert(new Hint("Klucze"));
                hintDao.insert(new Hint("Portfel"));

            });
        }
    };
}
