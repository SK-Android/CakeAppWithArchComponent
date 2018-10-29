package com.androidapp.mcs.cakeappwitharchcomp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.androidapp.mcs.cakeappwitharchcomp.database.AppRepository;
import com.androidapp.mcs.cakeappwitharchcomp.model.Cakes;
import com.androidapp.mcs.cakeappwitharchcomp.model.SampleDataProvider;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public LiveData<List<Cakes>> mCakes;
    private AppRepository mRepository;
    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mCakes = mRepository.mCakes;
    }

    public void addSampleData(Cakes cakes) {
        mRepository.addSampleData(cakes);
    }
}
