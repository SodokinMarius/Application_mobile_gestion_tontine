package com.sodyam.philomabtontine.Outils;

public class Point_jour {
    private Integer Nombre_Carnet_Vendu,montant_carnet,montant_du_jour,chiffre_Affaire_jour;


    /*public Point_jour(Integer nombre_Carnet_Vendu, Integer montant_carnet, Integer montant_du_jour, Integer chiffre_Affaire_jour) {
        Nombre_Carnet_Vendu = nombre_Carnet_Vendu;
        this.montant_carnet = montant_carnet;
        this.montant_du_jour = montant_du_jour;
        this.chiffre_Affaire_jour = chiffre_Affaire_jour;
    }*/

    public Integer getNombre_Carnet_Vendu() {
        return Nombre_Carnet_Vendu;
    }

    public void setNombre_Carnet_Vendu(Integer nombre_Carnet_Vendu) {
        Nombre_Carnet_Vendu = nombre_Carnet_Vendu;
    }

    public Integer getMontant_carnet() {
        return montant_carnet;
    }

    public void setMontant_carnet(Integer montant_carnet) {
        this.montant_carnet = montant_carnet;
    }

    public Integer getMontant_du_jour() {
        return montant_du_jour;
    }

    public void setMontant_du_jour(Integer montant_du_jour) {
        this.montant_du_jour = montant_du_jour;
    }

    public Integer getChiffre_Affaire_jour() {
        return chiffre_Affaire_jour;
    }

    public void setChiffre_Affaire_jour(Integer chiffre_Affaire_jour) {
        this.chiffre_Affaire_jour = chiffre_Affaire_jour;
    }
}
