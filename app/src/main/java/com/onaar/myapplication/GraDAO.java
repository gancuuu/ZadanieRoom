package com.onaar.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GraDAO {

    @Insert
    void wstawGreDoBazy(Gra gra);

    @Query("SELECT COUNT(*) FROM gry")
    int policzGry();

    @Query("SELECT * FROM gry")
    List<Gra> zwrocWszystkieGry();
}
