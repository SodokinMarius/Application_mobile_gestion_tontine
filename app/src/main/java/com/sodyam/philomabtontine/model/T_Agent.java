package com.sodyam.philomabtontine.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//@TypeConverters(DateConverter.class)
//@Entity(tableName = "T_Agent", indices = {@Index(value = {"Poste"}, unique = true)})
@Entity(tableName = "T_Agent")

public class T_Agent {


    @PrimaryKey
    @ColumnInfo(name = "Poste")
    @NonNull
    private String Poste;

    @ColumnInfo(name ="NomAgent")
    private String NomAgent;

    @ColumnInfo(name ="PrenomAgent")
    private String PrenomAgent;


    @ColumnInfo(name ="Telephone")
    private String Telephone;

    @ColumnInfo(name ="Adresse")
    private String Adresse;

    @ColumnInfo(name ="mot_de_passe")
    private String mot_de_passe;

    @ColumnInfo(name ="DateDeDebut")
    private String  DateDeDebut;


    public T_Agent() {
    }

    public T_Agent(@NonNull String poste, String nomAgent, String prenomAgent, String telephone, String adresse, String mot_de_passe, String dateDeDebut) {
        Poste = poste;
        NomAgent = nomAgent;
        PrenomAgent = prenomAgent;
        Telephone = telephone;
        Adresse = adresse;
        this.mot_de_passe = mot_de_passe;
        DateDeDebut = dateDeDebut;
    }

    public String getPoste() {
        return Poste;
    }

    public void setPoste(String poste) {
        Poste = poste;
    }

    public String getNomAgent() {
        return NomAgent;
    }

    public void setNomAgent(String nomAgent) {
        NomAgent = nomAgent;
    }

    public String getPrenomAgent() {
        return PrenomAgent;
    }

    public void setPrenomAgent(String prenomAgent) {
        PrenomAgent = prenomAgent;
    }



    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }


    public String getDateDeDebut() {
        return DateDeDebut;
    }

    public void setDateDeDebut(String dateDeDebut) {
        DateDeDebut = dateDeDebut;
    }

    @Override
    public String toString() {
        return "T_Agent{" +
                "Poste='" + Poste + '\'' +
                ", NomAgent='" + NomAgent + '\'' +
                ", PrenomAgent='" + PrenomAgent + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", DateDeDebut=" + DateDeDebut +
                '}';
    }
}