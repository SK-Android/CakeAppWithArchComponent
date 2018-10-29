package com.androidapp.mcs.cakeappwitharchcomp.MyTests;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.androidapp.mcs.cakeappwitharchcomp.database.CakeDao;
import com.androidapp.mcs.cakeappwitharchcomp.database.CakeDatabase;
import com.androidapp.mcs.cakeappwitharchcomp.model.SampleDataProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    public static final String TAG = "Junit"; //For Logcat output
    //References to database and dao objects
    private CakeDatabase cakeDatabase;
    private CakeDao mcakeDao;

    @Before
    public void createDb(){
        //Don't use applications context use a special context from Instrumentation registry for testing
        Context context = InstrumentationRegistry.getTargetContext();     //This is an instance of the android context class
        //Create a Database reference
        cakeDatabase = Room.inMemoryDatabaseBuilder(context,CakeDatabase.class).build();  //(This create a copy of Database in memory that won't be saved persistently in storage)
                                                                                        //& I can create and destroy the database for each test

        mcakeDao = cakeDatabase.cakeDao(); //Call the actual implementation of CakeDao
        Log.i(TAG, "createDb");
    }

    @After
    public void closeDb(){
        cakeDatabase.close();
        Log.i(TAG, "closeDb");
    }

    @Test  //These methods should return void and not have any arguments
    public void createAndRetrieveNotes(){
        mcakeDao.insertAll(SampleDataProvider.getCakes());
        int count = SampleDataProvider.getCount();

        Log.i(TAG, "createAndRetrieveNotes:count= "+count);
        assertEquals(SampleDataProvider.getCakes().size(),count);
    }
}
