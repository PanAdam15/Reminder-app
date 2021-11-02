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
                curiosityDao.insert(new Curiosity("curosity_1","W mózgu jest 100 miliardów neuronów."));
                curiosityDao.insert(new Curiosity("curosity_2","Naczynia krwionośne obecne w mózgu mają prawie 160 000 kilometrów długości."));
                curiosityDao.insert(new Curiosity("curosity_3","Mózg w prawie 80% to woda."));
                curiosityDao.insert(new Curiosity("curosity_4","Twój mózg, kiedy nie śpimy generuje około 25 watów energii."));
                curiosityDao.insert(new Curiosity("curosity_5","Dopiero około 25 roku życia ludzki mózg osiąga pełną dojrzałość"));
                curiosityDao.insert(new Curiosity("curosity_6","Ludzki mózg waży około 1,5 kg, co stanowi zaledwie 2% masy ciała."));
                curiosityDao.insert(new Curiosity("curosity_7","Prędkość impulsu nerwowego to ok. 400 km/godz."));
                curiosityDao.insert(new Curiosity("curosity_8","Człowiek doświadcza około 70 tysięcy myśli dziennie."));
                curiosityDao.insert(new Curiosity("curosity_9","90% decyzji podejmowanych jest podświadomie."));
                curiosityDao.insert(new Curiosity("curosity_10","Ilość informacji docierająca do naszego mózgu wynosi około 100 megabajtów na sekundę."));
                curiosityDao.insert(new Curiosity("curosity_11","Picie alkoholu nie powoduje, że zapomina o rzeczach i wydarzeniach."));
                curiosityDao.insert(new Curiosity("curosity_12","Kiedy pijesz alkohol, Twój mózg chwilowo traci zdolność tworzenia wspomnień."));
                curiosityDao.insert(new Curiosity("curosity_13","Około 25% procent cholesterolu człowieka znajduje się w mózgu."));
                curiosityDao.insert(new Curiosity("curosity_14","Jedna trzecia całego mózgu zajmuje się przetwarzaniem bodźców wzrokowych."));
                curiosityDao.insert(new Curiosity("curosity_15","Dzięki intensywnemu krążeniu 90 % energii cieplnej na zimnie ucieka przez głowę."));
                curiosityDao.insert(new Curiosity("curosity_16","Epilepsja to rodzaj „zwarcia”, do którego dochodzi w mózgu."));
                curiosityDao.insert(new Curiosity("curosity_17","Jedynym stanem w którym mózg jest aktywny prawie w 100% jest stan epilepsji."));
                curiosityDao.insert(new Curiosity("curosity_18","Jeśli jakiś obszar mózgu jest uszkodzony, inny potrafi przejąć jego funkcje."));
                curiosityDao.insert(new Curiosity("curosity_19","Muzyka wyzwala aktywność w tej samej części mózgu, która uwalnia dopaminę."));
                curiosityDao.insert(new Curiosity("curosity_20","To, że wykorzystujemy tylko 10% naszego mózgu to mit."));
                curiosityDao.insert(new Curiosity("curosity_21","Im bardziej stymulujemy mózg do pracy tym lepiej on działa."));
                curiosityDao.insert(new Curiosity("curosity_22","Aktywny mózg jest bardziej odporny na starzenie się i Alzheimera."));
                curiosityDao.insert(new Curiosity("curosity_23","Każda część mózgu ma przypisaną konkretną funkcję."));
                curiosityDao.insert(new Curiosity("curosity_24","Nasz mózg ma ogromny potencjał i możemy się nauczyć jak efektywniej go używać."));
                hintDao.insert(new Hint("Pranie"));
                hintDao.insert(new Hint("Żelazko"));
                hintDao.insert(new Hint("Gaz"));
                hintDao.insert(new Hint("Zamknąć drzwi"));
                hintDao.insert(new Hint("Nakarwmić zwierzęta"));
                hintDao.insert(new Hint("Klucze"));
                hintDao.insert(new Hint("Portfel"));

            });
        }
    };
}
