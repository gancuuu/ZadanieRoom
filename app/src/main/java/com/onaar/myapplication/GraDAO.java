package com.onaar.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface GraDAO {

    @Insert
    void wstawGreDoBazy(Gra gra);

    @Query("SELECT COUNT(*) FROM gry")
    int policzGry();
}
