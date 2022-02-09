package com.sodyam.philomabtontine.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="T_Lot"/* indices = {@Index(value = {"montant_lot"}, unique = true)}*/)
public class T_Lot {


    @PrimaryKey
    @ColumnInfo(name ="montant_lot")
    @NonNull
    private int montant_lot;

    @ColumnInfo(name ="type_lot")
    private String type_lot;

    @ColumnInfo(name ="date_ajout")
    private String date_ajout;

    @ColumnInfo(name ="dernier_modification")
    private  String dernier_modification;


    public T_Lot() {
    }

    public T_Lot(int montant_lot, String type_lot, String date_ajout, String dernier_modification) {
        this.montant_lot = montant_lot;
        this.type_lot = type_lot;
        this.date_ajout = date_ajout;
        this.dernier_modification = dernier_modification;
    }

    public int getMontant_lot() {
        return montant_lot;
    }

    public void setMontant_lot(int montant_lot) {
        this.montant_lot = montant_lot;
    }

    public String getType_lot() {
        return type_lot;
    }

    public void setType_lot(String type_lot) {
        this.type_lot = type_lot;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }

    public String getDernier_modification() {
        return dernier_modification;
    }

    public void setDernier_modification(String dernier_modification) {
        this.dernier_modification = dernier_modification;
    }

    @Override
    public String toString() {
        return "T_Lot{" +
                "montant_lot=" + montant_lot +
                ", type_lot='" + type_lot + '\'' +
                ", date_ajout=" + date_ajout +
                ", dernier_modification=" + dernier_modification +
                '}';
    }
}


