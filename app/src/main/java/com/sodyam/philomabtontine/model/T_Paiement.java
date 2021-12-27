package com.sodyam.philomabtontine.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "T_Paiement"
        /*,
        foreignKeys ={
        @ForeignKey(
                entity = T_Carnet.class,
                parentColumns = "NumeroCarnet",
                childColumns = "numCarnet"
        ),
        @ForeignKey(
                entity = T_Agent.class,
                parentColumns = "Poste",
                childColumns = "poste_agent"
        )
        }*/)
       //indices = {@Index(value = {"IdPaiement"}, unique = true)})
public class T_Paiement {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="IdPaiement")
    @NonNull
    private int IdPaiement;

   @ColumnInfo(name ="numCarnet")
    private int numCarnet;

  //@ForeignKey(entity = T_Agent.class,parentColumns = "Poste",childColumns = "poste_agent")
  @ColumnInfo(name ="poste_agent")
    private  String poste_agent;

    @ColumnInfo(name ="montant_paye")
    private int montant_paye;

    @ColumnInfo(name ="date_paiement")
    private String date_paiement;

    @ColumnInfo(name ="date_paiement_recherche")
    private long date_paiement_recherche;


    public T_Paiement() {
    }

    public T_Paiement(int numCarnet, String poste_agent, int montant_paye, String date_paiement, long date_paiement_recherche) {
        this.numCarnet = numCarnet;
        this.poste_agent = poste_agent;
        this.montant_paye = montant_paye;
        this.date_paiement = date_paiement;
        this.date_paiement_recherche = date_paiement_recherche;
    }

    public int getIdPaiement() {
        return IdPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        IdPaiement = idPaiement;
    }

    public int getNumCarnet() {
        return numCarnet;
    }

    public void setNumCarnet(int numCarnet) {
        this.numCarnet = numCarnet;
    }

    public String getPoste_agent() {
        return poste_agent;
    }

    public void setPoste_agent(String poste_agent) {
        this.poste_agent = poste_agent;
    }

    public int getMontant_paye() {
        return montant_paye;
    }

    public void setMontant_paye(int montant_paye) {
        this.montant_paye = montant_paye;
    }

    public String getDate_paiement() {
        return date_paiement;
    }

    public void setDate_paiement(String date_paiement) {
        this.date_paiement = date_paiement;
    }

    public long getDate_paiement_recherche() {
        return date_paiement_recherche;
    }

    public void setDate_paiement_recherche(long date_paiement_recherche) {
        this.date_paiement_recherche = date_paiement_recherche;
    }

    @Override
    public String toString() {
        return "T_Paiement{" +
                "IdPaiement=" + IdPaiement +
                ", numCarnet=" + numCarnet +
                ", poste_agent='" + poste_agent + '\'' +
                ", montant_paye=" + montant_paye +
                ", date_paiement='" + date_paiement + '\'' +
                ", date_paiement_recherche=" + date_paiement_recherche +
                '}';
    }
}
