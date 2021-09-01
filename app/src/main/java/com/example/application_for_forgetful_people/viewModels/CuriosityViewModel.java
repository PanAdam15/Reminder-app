package com.example.application_for_forgetful_people.viewModels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.example.application_for_forgetful_people.entity.Curiosity;
import com.example.application_for_forgetful_people.repository.CuriosityRepository;

import java.util.List;

public class CuriosityViewModel extends AndroidViewModel {

    private final CuriosityRepository curiosityRepository;
    private final List<Curiosity> listOfCuriosities;

    public CuriosityViewModel(Application application) {
        super(application);

        curiosityRepository = new CuriosityRepository(application);
        listOfCuriosities = curiosityRepository.getAllCuriosities();


    }
    public List<Curiosity> getAllCuriosities(){
        return listOfCuriosities;
    }
    public void insert(Curiosity curiosity){
        curiosityRepository.insert(curiosity);
    }

}
