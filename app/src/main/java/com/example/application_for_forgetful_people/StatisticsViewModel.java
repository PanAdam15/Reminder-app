package com.example.application_for_forgetful_people;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.application_for_forgetful_people.entity.Statistics;
import com.example.application_for_forgetful_people.repository.StatisticsRepository;

import java.util.List;

public class StatisticsViewModel extends AndroidViewModel {

    private final StatisticsRepository statisticsRepository;
    private final LiveData<List<Statistics>> listOfStatistics;

    public StatisticsViewModel(@NonNull Application application) {
        super(application);

        statisticsRepository = new StatisticsRepository(application);
        listOfStatistics = statisticsRepository.getAllStatistics();
    }
    public LiveData<List<Statistics>> getAllStatistics(){
        return listOfStatistics;
    }
    public int getForgottenCount() {return statisticsRepository.getForgottenCount();}
    public void insert(Statistics statistics){
        statisticsRepository.insert(statistics);
    }
}
