package com.sodyam.philomabtontine.Outils;

public class Liste_paiements {

    private String Nom, Prenom, poste_agent;
    private Integer numero_carnet,
            montant_dernier_paiement, montant_paye, reste_paye;
    private Integer date_paiement;

    public Liste_paiements() {
    }

    public Liste_paiements(String nom, String prenom, String poste_agent, Integer numero_carnet, Integer montant_dernier_paiement, Integer montant_paye, Integer reste_paye, Integer date_paiement) {
        Nom = nom;
        Prenom = prenom;
        this.poste_agent = poste_agent;
        this.numero_carnet = numero_carnet;
        this.montant_dernier_paiement = montant_dernier_paiement;
        this.montant_paye = montant_paye;
        this.reste_paye = reste_paye;
        this.date_paiement = date_paiement;
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

    public String getPoste_agent() {
        return poste_agent;
    }

    public void setPoste_agent(String poste_agent) {
        this.poste_agent = poste_agent;
    }

    public Integer getNumero_carnet() {
        return numero_carnet;
    }

    public void setNumero_carnet(Integer numero_carnet) {
        this.numero_carnet = numero_carnet;
    }

    public Integer getMontant_dernier_paiement() {
        return montant_dernier_paiement;
    }

    public void setMontant_dernier_paiement(Integer montant_dernier_paiement) {
        this.montant_dernier_paiement = montant_dernier_paiement;
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

    public Integer getDate_paiement() {
        return date_paiement;
    }

    public void setDate_paiement(Integer date_paiement) {
        this.date_paiement = date_paiement;
    }
}