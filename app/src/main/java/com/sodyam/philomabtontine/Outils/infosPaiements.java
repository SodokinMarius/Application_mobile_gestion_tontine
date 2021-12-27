package com.sodyam.philomabtontine.Outils;

public class infosPaiements {

    private String nomClient,prenomClient;
    private Integer numero_carnet, lot_choisi;
    private Long montant_paye,reste_paye;

    public infosPaiements(String nomClient, String prenomClient, Integer numero_carnet, Integer lot_choisi, Long montant_paye, Long reste_paye) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.numero_carnet = numero_carnet;
        this.lot_choisi = lot_choisi;
        this.montant_paye = montant_paye;
        this.reste_paye = reste_paye;
    }

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

    public Integer getNumero_carnet() {
        return numero_carnet;
    }

    public void setNumero_carnet(Integer numero_carnet) {
        this.numero_carnet = numero_carnet;
    }

    public Integer getLot_choisi() {
        return lot_choisi;
    }

    public void setLot_choisi(Integer lot_choisi) {
        this.lot_choisi = lot_choisi;
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

    @Override
    public String toString() {
        return "infosPaiements{" +
                "nomClient='" + nomClient + '\'' +
                ", prenomClient='" + prenomClient + '\'' +
                ", numero_carnet=" + numero_carnet +
                ", lot_choisi=" + lot_choisi +
                ", montant_paye=" + montant_paye +
                ", reste_paye=" + reste_paye +
                '}';
    }
}
