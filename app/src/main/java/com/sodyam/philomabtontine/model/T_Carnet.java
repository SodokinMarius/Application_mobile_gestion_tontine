package com.sodyam.philomabtontine.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "T_Carnet"
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
                      childColumns = "IdLot"
              )
      }*/)
        /*indices = {@Index(value = {"NumeroCarnet"}, unique = true),@Index(value = {"IdClient"}, unique = false),
                @Index(value = {"IdLot"}, unique = false)})*/
public class T_Carnet {

        @PrimaryKey
        @ColumnInfo(name ="NumeroCarnet")
        private int NumeroCarnet;


        //@ForeignKey(entity = T_Client.class, parentColumns = "Matricule", childColumns = "IdClient")
        @ColumnInfo(name ="IdClient")
        private int IdClient;

        //@ForeignKey(entity = T_Lot.class,parentColumns = "montant_lot",childColumns = "IdLot")
        @ColumnInfo(name ="IdLot")
        private int IdLot;

    @ColumnInfo(name ="date_ajout")
        private long date_ajout;


    public T_Carnet() {

    }

    public T_Carnet(int numeroCarnet, int idClient, int idLot, long date_ajout) {
        NumeroCarnet = numeroCarnet;
        IdClient = idClient;
        IdLot = idLot;
        this.date_ajout = date_ajout;
    }

    public int getNumeroCarnet() {
        return NumeroCarnet;
    }

    public void setNumeroCarnet(int numeroCarnet) {
        NumeroCarnet = numeroCarnet;
    }

    public int getIdClient() {
        return IdClient;
    }

    public void setIdClient(int idClient) {
        IdClient = idClient;
    }

    public int getIdLot() {
        return IdLot;
    }

    public void setIdLot(int idLot) {
        IdLot = idLot;
    }

    public long getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(long date_ajout) {
        this.date_ajout = date_ajout;
    }

    @Override
    public String toString() {
        return "T_Carnet{" +
                "NumeroCarnet=" + NumeroCarnet +
                ", IdClient=" + IdClient +
                ", IdLot=" + IdLot +
                ", date_ajout=" + date_ajout +
                '}';
    }
}


