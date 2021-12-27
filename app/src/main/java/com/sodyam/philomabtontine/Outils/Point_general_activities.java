package com.sodyam.philomabtontine.Outils;

public class Point_general_activities {
    private  int nombre_client_total;
    private  int carnet_total_vendu;
    private  int montant_carnet;
    private int montant_total_collecte;
    private int benefice_total;
    private  int montant_rendu;
    private int chiffre_affaire_total;

    /*public Point_general_activities(int nombre_client_total, int carnet_total_vendu, int montant_carnet, int montant_total_collecte, int benefice_total, int montant_rendu, int chiffre_affaire_total) {
        this.nombre_client_total = nombre_client_total;
        this.carnet_total_vendu = carnet_total_vendu;
        this.montant_carnet = montant_carnet;
        this.montant_total_collecte = montant_total_collecte;
        this.benefice_total = benefice_total;
        this.montant_rendu = montant_rendu;
        this.chiffre_affaire_total = chiffre_affaire_total;
    }*/

    public int getNombre_client_total() {
        return nombre_client_total;
    }

    public void setNombre_client_total(int nombre_client_total) {
        this.nombre_client_total = nombre_client_total;
    }

    public int getCarnet_total_vendu() {
        return carnet_total_vendu;
    }

    public void setCarnet_total_vendu(int carnet_total_vendu) {
        this.carnet_total_vendu = carnet_total_vendu;
    }

    public int getMontant_carnet() {
        return montant_carnet;
    }

    public void setMontant_carnet(int montant_carnet) {
        this.montant_carnet = montant_carnet;
    }

    public int getMontant_total_collecte() {
        return montant_total_collecte;
    }

    public void setMontant_total_collecte(int montant_total_collecte) {
        this.montant_total_collecte = montant_total_collecte;
    }

    public int getBenefice_total() {
        return benefice_total;
    }

    public void setBenefice_total(int benefice_total) {
        this.benefice_total = benefice_total;
    }

    public int getMontant_rendu() {
        return montant_rendu;
    }

    public void setMontant_rendu(int montant_rendu) {
        this.montant_rendu = montant_rendu;
    }

    public int getChiffre_affaire_total() {
        return chiffre_affaire_total;
    }

    public void setChiffre_affaire_total(int chiffre_affaire_total) {
        this.chiffre_affaire_total = chiffre_affaire_total;
    }
}
