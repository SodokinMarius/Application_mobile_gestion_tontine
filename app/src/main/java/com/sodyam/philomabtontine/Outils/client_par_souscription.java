package com.sodyam.philomabtontine.Outils;

public class client_par_souscription {
    private Integer nombre_total_souscription,montant_dernier_paiement,
            telephone_cli,montant_paye,reste_paye,valeur_souscript,numCarnet;
            private String nom_cli,prenom_cli;
            private String date_dernier_paiement,date_actuelle;

    public client_par_souscription(Integer nombre_total_souscription, Integer montant_dernier_paiement, Integer telephone_cli, Integer montant_paye, Integer reste_paye, Integer valeur_souscript, Integer numCarnet, String nom_cli, String prenom_cli, String date_dernier_paiement, String date_actuelle) {
        this.nombre_total_souscription = nombre_total_souscription;
        this.montant_dernier_paiement = montant_dernier_paiement;
        this.telephone_cli = telephone_cli;
        this.montant_paye = montant_paye;
        this.reste_paye = reste_paye;
        this.valeur_souscript = valeur_souscript;
        this.numCarnet = numCarnet;
        this.nom_cli = nom_cli;
        this.prenom_cli = prenom_cli;
        this.date_dernier_paiement = date_dernier_paiement;
        this.date_actuelle = date_actuelle;
    }

   

    public Integer getNombre_total_souscription() {
        return nombre_total_souscription;
    }

    public Integer getMontant_dernier_paiement() {
        return montant_dernier_paiement;
    }

    public Integer getTelephone_cli() {
        return telephone_cli;
    }

    public Integer getMontant_paye() {
        return montant_paye;
    }

    public Integer getReste_paye() {
        return reste_paye;
    }

    public Integer getValeur_souscript() {
        return valeur_souscript;
    }

    public Integer getNumCarnet() {
        return numCarnet;
    }

    public String getNom_cli() {
        return nom_cli;
    }

    public String getPrenom_cli() {
        return prenom_cli;
    }

    public String getDate_dernier_paiement() {
        return date_dernier_paiement;
    }

    public String getDate_actuelle() {
        return date_actuelle;
    }
}
