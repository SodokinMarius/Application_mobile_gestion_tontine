package com.sodyam.philomabtontine.Outils;

public class pointParTypeLot {
    private String nomClient,prenomClient,telephone,type_lot;
    private Long montant_paye, reste_paye;
    //int Matricule;

    public pointParTypeLot( String nomClient, String prenomClient, String telephone, String type_lot, Long montant_paye, Long reste_paye) {

        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.telephone = telephone;
        this.type_lot = type_lot;
        this.montant_paye = montant_paye;
        this.reste_paye = reste_paye;
    }

//    public int getMatricule() {
//        return Matricule;
//    }
//
//    public void setMatricule(int matricule) {
//        this.Matricule = matricule;
//    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getType_lot() {
        return type_lot;
    }

    public void setType_lot(String type_lot) {
        this.type_lot = type_lot;
    }

    public Long getMontant_paye() {
        return montant_paye;
    }

    public void setMontant_paye(Long montant_paye) {
        this.montant_paye = montant_paye;
    }

    public Long getReste_paye() {
        return reste_paye;
    }

    public void setReste_paye(Long reste_paye) {
        this.reste_paye = reste_paye;
    }

}
