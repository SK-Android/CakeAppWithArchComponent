package com.androidapp.mcs.cakeappwitharchcomp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.androidapp.mcs.cakeappwitharchcomp.model.Cakes;

@Database(entities = {Cakes.class},version = 1)
public abstract class CakeDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "CakeDatabase.db";
    private static volatile CakeDatabase instance; //Singleton object //volatile-> Can be accessed from main memory

    private static final Object LOCK = new Object(); // object to synchronise on

    public abstract CakeDao cakeDao();

    public static CakeDatabase getInstance(Context context) {
       if(instance == null) {
           synchronized (LOCK) {
               if (instance == null) {
                   instance = Room.databaseBuilder(context.getApplicationContext(),
                           CakeDatabase.class, DATABASE_NAME).build();
               }
           }
       }

        return instance;
    }
}
