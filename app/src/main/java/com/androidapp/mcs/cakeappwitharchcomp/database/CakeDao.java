package com.androidapp.mcs.cakeappwitharchcomp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.androidapp.mcs.cakeappwitharchcomp.model.Cakes;

import java.util.List;

@Dao
public interface CakeDao {
//    @Insert
//    void insert(Cakes cakes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cakes cakes);

    @Query("SELECT * from Cakes")
    LiveData<List<Cakes>> getCakes();


}
