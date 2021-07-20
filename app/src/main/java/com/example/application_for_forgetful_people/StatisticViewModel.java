package com.example.application_for_forgetful_people;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.application_for_forgetful_people.entity.Statistics;
import com.example.application_for_forgetful_people.repository.StatisticsRepository;

public class StatisticViewModel extends AndroidViewModel {

    private final StatisticsRepository statisticsRepository;

    public StatisticViewModel(@NonNull Application application) {
        super(application);
        statisticsRepository = new StatisticsRepository(application);
    }

    public void insert(Statistics statistics){
        statisticsRepository.insert(statistics);
    }
}
