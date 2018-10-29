package com.androidapp.mcs.cakeappwitharchcomp.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.androidapp.mcs.cakeappwitharchcomp.model.Cakes;
import com.androidapp.mcs.cakeappwitharchcomp.model.SampleDataProvider;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;
    public LiveData<List<Cakes>> mCakes;
    private CakeDatabase mCakeDb;


    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if(ourInstance == null)
        {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
       // mCakes = SampleDataProvider.getCakes();
        mCakeDb = CakeDatabase.getInstance(context); //Reference to actual database
    }



    private LiveData<List<Cakes>> getAllCakes()
    {
        return mCakeDb.cakeDao().getCakes();
    }

    public void addSampleData(final Cakes cakes) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mCakeDb.cakeDao().insert(cakes);
            }
        });
    }
}
