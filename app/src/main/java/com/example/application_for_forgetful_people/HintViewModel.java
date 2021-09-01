package com.example.application_for_forgetful_people;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.application_for_forgetful_people.entity.Hint;
import com.example.application_for_forgetful_people.repository.HintRepository;

import java.util.List;

public class HintViewModel extends AndroidViewModel {

    private final HintRepository hintRepository;
    private final List<String> listOfHints;

    public HintViewModel(@NonNull Application application) {
        super(application);
        hintRepository = new HintRepository(application);
        listOfHints = hintRepository.getAllHints();
    }

    public List<String> getAllHints() {
        return listOfHints;
    }

    public void insert(Hint hint) {
        hintRepository.insert(hint);
    }
}
