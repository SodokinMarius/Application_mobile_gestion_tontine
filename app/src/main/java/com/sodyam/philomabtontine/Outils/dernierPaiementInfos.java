package com.sodyam.philomabtontine.Outils;

public class dernierPaiementInfos {
private Long montant_dernierPaiement;
private Integer date_dernier_paiement;

    public dernierPaiementInfos(Long montant_dernierPaiement, Integer date_dernier_paiement) {
        this.montant_dernierPaiement = montant_dernierPaiement;
        this.date_dernier_paiement = date_dernier_paiement;
    }

    public Long getMontant_dernierPaiement() {
        return montant_dernierPaiement;
    }

    public void setMontant_dernierPaiement(Long montant_dernierPaiement) {
        this.montant_dernierPaiement = montant_dernierPaiement;
    }

    public Integer getDate_dernier_paiement() {
        return date_dernier_paiement;
    }

    public void setDate_dernier_paiement(Integer date_dernier_paiement) {
        this.date_dernier_paiement = date_dernier_paiement;
    }
}

