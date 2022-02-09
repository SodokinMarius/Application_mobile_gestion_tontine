package com.sodyam.philomabtontine.Outils;

public class List_client {
    private String Nom;
    private String Prenom;
    private long Telephone;
    private long IdLot;
    private  int NumeroCarnet;



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

    public long getTelephone() {
        return Telephone;
    }

    public void setTelephone(long telephone) {
        Telephone = telephone;
    }

    public long getIdLot() {
        return IdLot;
    }

    public void setIdLot(long idLot) {
        IdLot = idLot;
    }

    public int getNumeroCarnet() {
        return NumeroCarnet;
    }

    public void setNumeroCarnet(int numeroCarnet) {
        NumeroCarnet = numeroCarnet;
    }
}
