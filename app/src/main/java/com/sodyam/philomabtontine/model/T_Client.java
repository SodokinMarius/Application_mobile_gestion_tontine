package com.sodyam.philomabtontine.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName ="T_Client", indices = {@Index(value = {"Matricule"}, unique = true)})
public class T_Client{
        /**
         * @param args the command line arguments
         */

        @PrimaryKey
        @ColumnInfo(name ="Matricule")
        @NonNull
        private int Matricule;

    @ColumnInfo(name ="Nom")
        private String Nom;

    @ColumnInfo(name ="Prenom")
        private String Prenom;

//    @ColumnInfo(name ="DateNaissance")
//        private Integer DateNaissance;

//    @ColumnInfo(name ="LieuNaissance")
//        private String LieuNaissance;

//    @ColumnInfo(name ="Sexe")
//        private String Sexe;
//
//    @ColumnInfo(name ="Adresse")
//        private String Adresse;

    @ColumnInfo(name ="Telephone")
        private String Telephone;

    @ColumnInfo(name ="DateSouscription")
        private String DateSouscription;
//
//    @ColumnInfo(name ="Profession")
//        private  String Profession;

    @ColumnInfo(name ="agent_enregistreur")
    private  String agent_enregistreur;

    public T_Client() {

    }


    public T_Client(int matricule, String nom, String prenom, String telephone, String dateSouscription, String agent_enregistreur) {
        Matricule = matricule;
        Nom = nom;
        Prenom = prenom;
        Telephone = telephone;
        DateSouscription = dateSouscription;
        this.agent_enregistreur = agent_enregistreur;
    }

    public int getMatricule() {
        return Matricule;
    }

    public void setMatricule(int matricule) {
        Matricule = matricule;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getDateSouscription() {
        return DateSouscription;
    }

    public void setDateSouscription(String dateSouscription) {
        DateSouscription = dateSouscription;
    }

    public String getAgent_enregistreur() {
        return agent_enregistreur;
    }

    public void setAgent_enregistreur(String agent_enregistreur) {
        this.agent_enregistreur = agent_enregistreur;
    }


    @Override
    public String toString() {
        return "T_Client{" +
                "Matricule=" + Matricule +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", DateSouscription='" + DateSouscription + '\'' +
                ", agent_enregistreur='" + agent_enregistreur + '\'' +
                '}';
    }
}


