package com.onaar.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gry")
public class Gra {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nazwa;
    public String producent;
    public int rokWydania;
    public String gatunek;

    public Gra(String nazwa, String producent, int rokWydania, String gatunek) {
        this.nazwa = nazwa;
        this.producent = producent;
        this.rokWydania = rokWydania;
        this.gatunek = gatunek;
    }

    @Override
    public String toString() {
        return "Gra{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", producent='" + producent + '\'' +
                ", rokWydania=" + rokWydania +
                ", gatunek='" + gatunek + '\'' +
                '}';
    }
}
