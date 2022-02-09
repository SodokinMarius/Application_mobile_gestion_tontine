package com.sodyam.philomabtontine.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "T_Client_Lot"
        /*,
        foreignKeys = {
        @ForeignKey(
                entity = T_Client.class,
                parentColumns = "Matricule",
                childColumns = "IdClient"
        ),
                @ForeignKey(
                        entity = T_Lot.class,
                        parentColumns = "montant_lot",
                        childColumns = "id_Lot"
                )
        }*/)
       // indices = {@Index(value = {"Id_Client_Lot"}, unique = false)})
public class T_Client_Lot {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="Id_Client_Lot")
    @NonNull
        private int Id_Client_lot;


    @ColumnInfo(name ="IdClient")
       private  int  IdClient;

   @ColumnInfo(name ="id_Lot")
       private int id_Lot;


    public T_Client_Lot() {

    }

    public T_Client_Lot(int idClient, int id_Lot) {
        IdClient = idClient;
        this.id_Lot = id_Lot;
    }

    public int getId_Client_lot() {
        return Id_Client_lot;
    }

    public void setId_Client_lot(int id_Client_lot) {
        Id_Client_lot = id_Client_lot;
    }

    public int getIdClient() {
        return IdClient;
    }

    public void setIdClient(int idClient) {
        IdClient = idClient;
    }

    public int getId_Lot() {
        return id_Lot;
    }

    public void setId_Lot(int id_Lot) {
        this.id_Lot = id_Lot;
    }

    @Override
    public String toString() {
        return "T_Client_Lot{" +
                "Id_Client_lot=" + Id_Client_lot +
                ", IdClient=" + IdClient +
                ", id_Lot=" + id_Lot +
                '}';
    }
}




