package com.sodyam.philomabtontine.Outils;

public class Point_par_Client {
    private String nomClient,prenomclient;
    private Integer numero_carnet,montant_dernier_paiement,
            lot_choisi,montant_paye,reste_paye,valeur_souscript;

    private Integer date_actuelle;
    private Integer  date_dernier_paiement;



    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomclient() {
        return prenomclient;
    }

    public void setPrenomclient(String prenomclient) {
        this.prenomclient = prenomclient;
    }

    public Integer getMontant_dernier_paiement() {
        return montant_dernier_paiement;
    }

    public void setMontant_dernier_paiement(Integer montant_dernier_paiement) {
        this.montant_dernier_paiement = montant_dernier_paiement;
    }

    public Integer getLot_choisi() {
        return lot_choisi;
    }

    public void setLot_choisi(Integer lot_choisi) {
        this.lot_choisi = lot_choisi;
    }

    public Integer getMontant_paye() {
        return montant_paye;
    }

    public void setMontant_paye(Integer montant_paye) {
        this.montant_paye = montant_paye;
    }

    public Integer getReste_paye() {
        return reste_paye;
    }

    public void setReste_paye(Integer reste_paye) {
        this.reste_paye = reste_paye;
    }

    public Integer getValeur_souscript() {
        return valeur_souscript;
    }

    public void setDate_actuelle(Integer date_actuelle) {
        this.date_actuelle = date_actuelle;
    }

    public Integer getDate_dernier_paiement() {
        return date_dernier_paiement;
    }

    public void setDate_dernier_paiement(Integer date_dernier_paiement) {
        this.date_dernier_paiement = date_dernier_paiement;
    }

    public void setValeur_souscript(Integer valeur_souscript) {
        this.valeur_souscript = valeur_souscript;
    }

    public void setNumero_carnet(Integer numero_carnet) {
        this.numero_carnet = numero_carnet;
    }

    public Integer getDate_actuelle() {
        return date_actuelle;
    }

    public Integer getNumero_carnet() {
        return numero_carnet;
    }
}
