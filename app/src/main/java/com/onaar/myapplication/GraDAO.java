package com.onaar.myapplication;


import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface GraDAO {

    @Query("SELECT COUNT(*) FROM gry")
    int policzGry();
}
