package com.sodyam.philomabtontine.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sodyam.philomabtontine.Outils.Point_general_activities;
import com.sodyam.philomabtontine.Outils.Point_jour;
import com.sodyam.philomabtontine.Outils.Point_par_Client;
import com.sodyam.philomabtontine.Outils.client_par_souscription;
import com.sodyam.philomabtontine.Outils.dernierPaiementInfos;
import com.sodyam.philomabtontine.Outils.infosPaiements;
import com.sodyam.philomabtontine.Outils.pointParTypeLot;
import com.sodyam.philomabtontine.model.T_Carnet;
import com.sodyam.philomabtontine.model.T_Paiement;

import java.util.Date;
import java.util.List;

@Dao
public interface PaiementDao {
    long montant_carnet=0;
    long chiffre_Affaire_jour=0;
    Date date_ajout = new Date();

    @Query("SELECT * FROM T_Paiement GROUP BY numCarnet ORDER BY date_paiement DESC")
   List<T_Paiement> getListePaiementsUnique();


    /**
     * LISTRE DES PAIEMENTS D'UN CARNET
     */

    @Query("SELECT * FROM T_Paiement  WHERE  numCarnet=:numero ORDER BY date_paiement DESC")
    List<T_Paiement> getListePaiementsByNumCarnet(int numero);

    //String date_convert= date_ajout.toString();
    /**
     * Les methoses consernant le point general de l'activité
     */
    //montant total des quotes parts en general
    @Query("SELECT sum(montant_paye)  FROM T_Paiement")
    Long getMontant_Total_General();


        //Nombre de Carnet Vendu le dernier jour
//        @Query("SELECT  COUNT(NumeroCarnet) FROM T_Carnet WHERE date_ajout=:date")
//        Long getNombreCarnet_Vendu_By_Jour(Integer date);

    @Query("SELECT  * FROM T_Carnet WHERE date_ajout=:date")
   List<T_Carnet> getNombreCarnet_Vendu_By_Jour(long date);



    //Calcul du montant payé le dernier jour
    @Query("SELECT sum(montant_paye) from T_Paiement ")
    Long get_Montant_collecte_Jour();

    /**
     * LISTE DES PAIELMENTS DU DERNIER JOUR
     */
    @Query("SELECT SUM(montant_paye) FROM T_Paiement WHERE " +
            "date_paiement_recherche=:date_paiement")
    long getMontantDernierJour(long date_paiement);

    @Query("SELECT * FROM  T_Paiement WHERE date_paiement_recherche=:date ")
     T_Paiement getPaiementDernierJour(long date);

    /**
     * Methode qui rtourne le point general du Jour dans un interval de date
     * @return
     */
    @Query("SELECT  count(T_Carnet.NumeroCarnet) as Nombre_Carnet_Vendu," +
            " (count(T_Carnet.NumeroCarnet)*500) as montant_carnet, " +
            " SUM(T_Paiement.montant_paye) as montant_du_jour, " +
            " ((count(T_Carnet.NumeroCarnet)*500)+SUM(T_Paiement.montant_paye))as chiffre_Affaire_jour " +
            "FROM T_Carnet inner join T_Paiement on T_Carnet.NumeroCarnet=T_Paiement.numCarnet " +
            "where T_Carnet.date_ajout=:date_convert" +
            " AND  T_Paiement.date_paiement=:date_convert")
    Point_jour getPointDuJourBetweenDate(Integer date_convert);


    /**
     * POINT DU JOUR
     * @return
     */
    @Query("SELECT  count(T_Carnet.NumeroCarnet) as Nombre_Carnet_Vendu," +
            " (count(T_Carnet.NumeroCarnet)* 500) as montant_carnet, "+
            " SUM(T_Paiement.montant_paye) as montant_du_jour, " +
            " ((count(T_Carnet.NumeroCarnet)* 500)+ SUM(T_Paiement.montant_paye))as chiffre_Affaire_jour " +
            "FROM T_Carnet,T_Paiement  WHERE T_Carnet.NumeroCarnet=T_Paiement.numCarnet " +
            "AND T_Paiement.date_paiement=(SELECT MAX(T_Paiement.date_paiement) FROM T_Paiement) ")
    Point_jour getPointDuJour();

    @Query("SELECT SUM(montant_paye) FROM T_Paiement WHERE date_paiement_recherche=:date ")
    Integer getSommeMontantPayeDernierJour(long date);


    //Methode qui renvoie la date d'enregistrement du tout premier Carnet
    //Cette date servira à initialier par defaut la date de debut
    //du point general au cas où l'agent ne le precisera pas
    @Query("SELECT date_ajout FROM T_Carnet where NumeroCarnet=1")
   String getDatePremierEnregistrement();


    /**
     * //Methode qui retourne le point general de l'activité
     * @return
     */
    @Query("SELECT  COUNT(T_Client.Matricule) as nombre_client_total, " +
            " count(T_Carnet.NumeroCarnet) as carnet_total_vendu," +
            " (count(T_Carnet.NumeroCarnet)*500) as montant_carnet, " +
            " sum(T_Paiement.montant_paye) as montant_total_collecte, " +
            " sum(T_Lot.montant_lot*12) as benefice_total, " +
            " (sum(T_Paiement.montant_paye) -sum(T_Lot.montant_lot*12)) as montant_rendu, " +
            " (sum(T_Paiement.montant_paye)+(count(T_Carnet.NumeroCarnet)*500))as chiffre_affaire_total " +
            " FROM T_Carnet inner join T_Client on T_Client.Matricule=T_Carnet.idClient " +
            " inner join T_Lot on T_Carnet.IdLot=T_Lot.montant_lot" +
            " inner join T_Paiement on T_Carnet.NumeroCarnet=T_Paiement.numCarnet")
            /*" where T_Carnet.date_ajout between :date_debut and :date_fin" +
            " AND T_Paiement.date_paiement between :date_debut and :date_fin")*/
            Point_general_activities getPointGeneral(/*String date_debut,String date_fin*/);

            @Query("SELECT SUM(montant_paye) FROM T_Paiement")
            Long getMontantGeneralDesCollectes();

    @Query("SELECT SUM(montant_paye) FROM T_Paiement WHERE date_paiement BETWEEN :date_debut AND :date_fin ")
    long getMontantGeneralDesCollectesBetweenPeriod(long date_debut,long date_fin);

            @Query("SELECT SUM( T_Carnet.IdLot*12) FROM T_Carnet")
            Long getBeneficesGeneral();

    @Query("SELECT SUM( T_Carnet.IdLot*12) FROM T_Carnet WHERE date_ajout BETWEEN :date_debut AND :date_fin ")
    long getBeneficesGeneralBetweenPeriod(long date_debut,long date_fin);

            @Query("SELECT SUM(T_Carnet.IdLot*372) FROM T_Carnet ")
            Long getMontantGeneralAttendu();



    //Methode retournant la liste des points DATE DERNIER ET DERNIER PAIEMENT client mais connaissant son Marticule
        @Query("SELECT T_Paiement.montant_paye as montant_dernier_paiement, " +
                "T_Paiement.date_paiement as date_dernier_paiement " +
                "FROM T_Carnet inner join T_Client on T_Client.Matricule=T_Carnet.idClient " +
                "inner join T_Paiement on T_Carnet.NumeroCarnet=T_Paiement.numCarnet " +
                "WHERE IdPaiement=(SELECT max(IdPaiement) FROM T_Paiement) " +
                "AND T_Client.Matricule=:numclient ")
        dernierPaiementInfos getDernierPaiementGeneralById(Integer numclient);

        @Query("SELECT  T_Client.Nom,T_Client.Prenom," +
                "T_Carnet.NumeroCarnet as numero_carnet," +
                "T_Carnet.IdLot as lot_choisi, " +
                "SUM(T_Paiement.montant_paye) as montant_paye," +
                " ((T_Carnet.IdLot*372)-montant_paye) as reste_paye " +
                "FROM T_Client inner join T_Carnet on " +
                "T_Client.Matricule=T_Carnet.IdClient " +
                "inner join T_Lot on T_Carnet.IdLot=T_Lot.montant_lot " +
                "inner join T_Paiement on T_Carnet.NumeroCarnet=T_Paiement.numCarnet " +
                "WHERE T_Carnet.NumeroCarnet=:numcarnet")
        infosPaiements getInfosPaiementClientById(Integer numcarnet);

    /**
     * METHODE DE LA LISTE DES CLIENTS OAR SOUSCRIPTION
     * @return
     */

    @Query("SELECT  T_Client.Nom,T_Client.Prenom, " +
            "T_Carnet.NumeroCarnet as numero_carnet, " +
            "T_Carnet.IdLot as lot_souscript, " +
            "SUM(T_Paiement.montant_paye) as montant_paye," +
            " ((T_Carnet.IdLot*372)-SUM(T_Paiement.montant_paye)) as reste_paye " +
            "FROM T_Client inner join T_Carnet on " +
            "T_Client.Matricule=T_Carnet.IdClient " +
            "inner join T_Lot on T_Carnet.IdLot=T_Lot.montant_lot " +
            "inner join T_Paiement on T_Carnet.NumeroCarnet=T_Paiement.numCarnet "+
            "GROUP BY lot_souscript  ORDER BY lot_souscript ")
    List<client_par_souscription> getClientBySouscription();

    /**
     * METHODE DE LA LISTE DES POINTS PAR CLIENTS
     * @return
     */

    @Query("SELECT  T_Client.Nom,T_Client.Prenom, " +
            "T_Carnet.NumeroCarnet as numero_carnet, " +
            "T_Carnet.IdLot as lot_souscript, " +
            "SUM(T_Paiement.montant_paye) as montant_paye," +
            " ((T_Carnet.IdLot* 372)- SUM(T_Paiement.montant_paye)) as reste_paye " +
            "FROM T_Client inner join T_Carnet on " +
            "T_Client.Matricule=T_Carnet.IdClient " +
            "inner join T_Lot on T_Carnet.IdLot=T_Lot.montant_lot " +
            "inner join T_Paiement on T_Carnet.NumeroCarnet=T_Paiement.numCarnet "+
            " GROUP BY T_Lot.montant_lot" +
            " ORDER BY T_Carnet.NumeroCarnet"
            )
    List<Point_par_Client> getPointByClients();

    /**
     * POINT PAR SOUSCRIPTION
     */
   /* @Query("SELECT montant_paye, COUNT(montant_paye) FROM T_Paiement " +
            "GROUP BY montant_paye ORDER BY montant_paye")
    List<Point_par_souscription> getPointBySouscription();*/

    /**
     * RECUPERER LE NOMBRE DE CLIENT ENREGISTRE CONNAISSANT LE POSTE DE L'AGENT
     */
        @Query("SELECT COUNT(T_Carnet.NumeroCarnet) as nombreCarnet FROM T_Carnet,T_Client  " +
                "WHERE T_Carnet.IdClient=T_Client.Matricule  AND T_Client.agent_enregistreur=:poste ")
        Integer getNombreClientEnregistreByPoste(String poste);

    /**
     *DUPLICATA DE LA REQUETE
     */
    @Query("SELECT COUNT(*) FROM T_Client  " +
            "WHERE agent_enregistreur=:poste ")
    Integer getNombreClientEnregistreParPoste(String poste);



    /**
     * RETOURNER LE DERNIER PAIEMENT CONNAISSANT LE NUMERO DU CARNET
     */

    @Query("SELECT T_Paiement.* FROM T_Paiement,T_Carnet " +
            "WHERE T_Paiement.numCarnet=T_Carnet.NumeroCarnet " +
            "AND  T_Carnet.NumeroCarnet=:numcarnet " +
            " AND T_Paiement.IdPaiement = (SELECT MAX(T_Paiement.IdPaiement) FROM T_Paiement WHERE numCarnet=:numcarnet) ")
    T_Paiement getDernierPaiementByNumCarnet(int numcarnet);


//    @Query("SELECT T_Paiement.* FROM T_Paiement,T_Carnet " +
//            "WHERE T_Paiement.numCarnet=T_Carnet.NumeroCarnet " +
//            "AND  T_Carnet.NumeroCarnet=:numcarnet ")
//   T_Paiement getPaiementByNumCarnet(int numcarnet);

    @Query("SELECT T_Paiement.* FROM T_Paiement inner join T_Carnet " +
            "on T_Paiement.numCarnet=T_Carnet.NumeroCarnet " +
            "WHERE  T_Carnet.NumeroCarnet=:numcarnet ")
    T_Paiement getPaiementByNumCarnet(int numcarnet);

    /**
     * Methode renvoyant le point par type de lot
     * @param idClient
     * @return
     */
    @Query("SELECT T_Client.Matricule, T_Client.Nom,T_Client.Prenom, " +
            " T_Client.Telephone as telephone," +
            " T_Lot.type_lot as type_lot," +
            " sum(T_Paiement.montant_paye) as montant_paye," +
            " ((T_Carnet.IdLot*372)-montant_paye) as reste_paye " +
            "FROM T_Client inner join T_Carnet on " +
            " T_Client.Matricule=T_Carnet.IdClient" +
            " inner join T_Lot on T_Carnet.IdLot=T_Lot.montant_lot" +
            " inner join T_Paiement on T_Carnet.NumeroCarnet=T_Paiement.numCarnet" +
            " WHERE T_Client.Matricule=:idClient")
   pointParTypeLot getInfosPaiementClientTypelotById(Integer idClient);

    /**
     * TOUT LE POINT PAR TYPE LOT
     */

    @Query("SELECT T_Client.Nom,T_Client.Prenom, " +
            " T_Client.Telephone as telephone," +
            " T_Lot.type_lot as type_lot," +
            " sum(T_Paiement.montant_paye) as montant_paye," +
            " ((T_Carnet.IdLot*372)-montant_paye) as reste_paye " +
            "FROM T_Client inner join T_Carnet on " +
            " T_Client.Matricule=T_Carnet.IdClient" +
            " inner join T_Lot on T_Carnet.IdLot=T_Lot.montant_lot" +
            " inner join T_Paiement on T_Carnet.NumeroCarnet=T_Paiement.numCarnet"
           )
    List<pointParTypeLot> getInfosPaiementClientTypelotById();


    @Query("SELECT T_Client.Nom,T_Client.Prenom, " +
            " T_Client.Telephone as telephone," +
            " T_Lot.type_lot as type_lot," +
            " sum(T_Paiement.montant_paye) as montant_paye," +
            " ((T_Carnet.IdLot*372)-montant_paye) as reste_paye " +
            "FROM T_Client inner join T_Carnet on " +
            " T_Client.Matricule=T_Carnet.IdClient" +
            " inner join T_Lot on T_Carnet.IdLot=T_Lot.montant_lot" +
            " inner join T_Paiement on T_Carnet.NumeroCarnet=T_Paiement.numCarnet" +
            " WHERE T_Client.Matricule=:idClient")
    pointParTypeLot getInfoPaiementClientTypelotById(Integer idClient);

    /**
     * Methode qui se charge recuperer la liste des Paiements
     * @return
     */
//    //Methode renvoyant la liste des Paiements
//    @Query("SELECT  T_Client.Nom,T_Client.Prenom," +
//            "T_Carnet.NumeroCarnet as numero_carnet, " +
//            "T_Paiement.montant_paye as montant_dernier_paiement,"+
//            "T_Paiement.date_paiement as date_paiement," +
//            "T_Agent.Poste as poste_agent, " +
//            "SUM(T_Paiement.montant_paye) as montant_paye," +
//            " ((T_Carnet.IdLot*372)-montant_paye) as reste_paye " +
//            "FROM T_Client inner join T_Carnet on " +
//            "T_Client.Matricule=T_Carnet.IdClient " +
//            "inner join T_Lot on T_Carnet.IdLot =T_Lot.montant_lot " +
//            "inner join T_Paiement on T_Carnet.NumeroCarnet=T_Paiement.numCarnet " +
//            "inner join T_Agent on T_Agent.Poste=T_Paiement.poste_agent " +
//            "ORDER BY T_Paiement.date_paiement  ASC")
//    List<Liste_paiements> getListePaiements();


    /**
     * MONTANT et date du DERNIER PAIEMENT
     * @param
     * @return
     */
    @Query("SELECT montant_paye FROM T_Paiement " +
            "inner join T_Carnet on T_Paiement.numCarnet=T_Carnet.NumeroCarnet " +
            "inner join T_Client on T_Carnet.IdClient=T_Client.Matricule " +
            "WHERE IdPaiement= (SELECT MAX(IdPaiement) FROM T_Paiement )" +
            "AND T_Paiement.numCarnet=:numcarnet")
    Integer getDernierPaiementDuClient(Integer numcarnet);


    @Query("SELECT date_paiement FROM T_Paiement " +
            "inner join T_Carnet on T_Paiement.numCarnet=T_Carnet.NumeroCarnet " +
            "inner join T_Client on T_Carnet.IdClient=T_Client.Matricule " +
            "WHERE IdPaiement= (SELECT MAX(IdPaiement) FROM T_Paiement )" +
            "AND  T_Carnet.NumeroCarnet=:numcarnet")
    long getDateDernierPaiementDuClient(Integer numcarnet);

    //Montant total payer par un client connaissant son numero de Carnet
//    @Query("SELECT SUM(T_Paiement.montant_paye) FROM T_Paiement WHERE numCarnet=:numCarnet")
//    int getMontantPayeByNumCarnet(Integer numCarnet);

    //Montant total payer par un client connaissant son numero de Carnet
    @Query("SELECT SUM(T_Paiement.montant_paye) FROM T_Paiement WHERE numCarnet=:numCarnet")
    int getMontantPayeByNumCarnet(Integer numCarnet);




    /**
     * =========================================== NOUVELLES REQUETES =============================
     * @param idClient
     */
    @Query("SELECT * FROM T_Carnet WHERE IdClient= :idClient ")
    T_Carnet getCarnetByNumClient(int idClient);


    @Query("SELECT IdLot FROM T_Carnet,T_Paiement " +
            "WHERE T_Paiement.numCarnet=T_Carnet.NumeroCarnet " +
            "AND T_Carnet.NumeroCarnet=:numcarnet ")
    int getMontLotByNumCarnet(int numcarnet);

    @Query("SELECT montant_paye FROM T_Paiement " +
            "WHERE IdPaiement= (SELECT MAX(IdPaiement) FROM T_Paiement )" +
            "AND T_Paiement.numCarnet=:numcarnet")
    Integer getDernierMontantPayerDuClient(int numcarnet);



    @Query("SELECT date_paiement FROM T_Paiement " +
            "WHERE IdPaiement= (SELECT MAX(date_paiement) FROM T_Paiement )" +
            "AND  numCarnet=:numcarnet")
    String getDateDernierMontantPayerDuClient(int numcarnet);




    //Creation d'un paiement
        @Insert
        void insertPaiement (T_Paiement paiement);

        @Update
        int updatePaiement (T_Paiement paiement);

        @Query("DELETE FROM T_Paiement where IdPaiement= :numPaiement")
        int deletePaiement ( long numPaiement);

}
