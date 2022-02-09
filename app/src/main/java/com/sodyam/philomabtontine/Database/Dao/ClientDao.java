package com.sodyam.philomabtontine.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sodyam.philomabtontine.Outils.List_client;
import com.sodyam.philomabtontine.model.T_Client;

import java.util.List;

@Dao
public interface ClientDao {

    /**
     *  //Liste des clients
     * @return
     */

  /*  @Query("SELECT T_Client.Nom,Prenom,Telephone,T_Carnet.NumeroCarnet,IdLot FROM T_Client" +
            " inner join T_Carnet on T_Client.Matricule=T_Carnet.IdClient")
    List<List_client> getListeClients();*/

    @Query("SELECT * FROM T_Client")
    List<T_Client> getListeClients();

    @Query("SELECT * FROM T_Client WHERE Matricule>=:matricule ")
    List<T_Client> getListeClientsByMatricule(int matricule);

//    @Query("SELECT T_Client.Nom,Prenom,Telephone,T_Carnet.NumeroCarnet,IdLot " +
//            "FROM T_Client,T_Carnet" +
//            " WHERE  T_Client.Matricule=T_Carnet.IdClient")
//    List<List_client> getListeClients();

    /**
     *   =========== Le nombre total de Client ==========
     * @return
     */

    @Query("SELECT count(*) FROM T_Client")
    Integer getNombreClient();


    /**
     * ==== Rechercher un client par l'Id de son carnet =========
     * @param numcarnet
     * @return
     */
    @Query("SELECT T_Client.Nom,Prenom,Telephone,T_Carnet.NumeroCarnet,IdLot FROM T_Client" +
            " inner join T_Carnet on T_Client.Matricule=T_Carnet.IdClient where T_Carnet.NumeroCarnet=:numcarnet")
    List<List_client> getListeClients_By_Carnet(Integer numcarnet);


    /**
     * ======= Reception d'un client connaissant son numero de carnet ======
     * @param numcarnet
     * @return
     */


    @Query("SELECT T_Client.* from T_Client inner join T_Carnet on T_Client.Matricule=T_Carnet.IdClient where T_Carnet.NumeroCarnet=:numcarnet")
     T_Client getClientByNumCarnet(int numcarnet);

    /**
     *  ======= RECUPERATION DU MATRICULE DU DERNIER CLIENT(pour pouvoir créer les Client_lot) ====
     * @return
     */
    @Query("SELECT MAX(Matricule) FROM T_Client ")
    int getDernierClientEnregistre();

    @Query("SELECT Matricule FROM T_Client")
    List<Integer> getLesMatriculesClients();

//    @Query("SELECT T_Client.* FROM T_Client,T_Carnet WHERE T_Carnet.IdClient=:matricule ")
//    T_Client getClientByNumCarnet(int matricule);
    @Insert
    void insertClient(T_Client client);

    @Update
    int updateClients(T_Client client);

    @Query("DELETE FROM T_Client where Matricule= :Matricule")
    int deleteClient(int Matricule);




}
