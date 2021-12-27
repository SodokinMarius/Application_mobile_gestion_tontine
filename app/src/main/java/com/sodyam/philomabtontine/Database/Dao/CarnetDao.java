package com.sodyam.philomabtontine.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sodyam.philomabtontine.model.T_Carnet;

import java.util.List;


@Dao
public interface CarnetDao {
    @Query("SELECT * FROM T_Carnet ORDER BY NumeroCarnet ASC ")
    List<T_Carnet> getListeCarnets();

    @Query("SELECT * FROM T_Carnet  GROUP BY IdLot ORDER BY NumeroCarnet ASC ")
    List<T_Carnet> getListeCarnetsPointSouscription();


    @Query("SELECT * FROM T_Carnet WHERE date_ajout BETWEEN :date_debut AND :date_fin ")
    List<T_Carnet> getListeCarnetsBetweenPeriod(long date_debut,long date_fin);


    /**
     * LISTE DES CARNETS TRIEES
     */
    @Query("SELECT * FROM T_Carnet ORDER BY IdLot")
    List<T_Carnet> getListeOrdonneCarnets();

    /**
     * RECUPERATION DES NUMEROS DES CARNETS
     */
    @Query("SELECT NumeroCarnet FROM T_Carnet ")
   List<Integer> getNumerosDesCarnets();


    @Query("SELECT IdClient FROM T_Carnet ")
    List<Integer> getNumerosDesClientsDesCarnets();

    @Query("SELECT * FROM T_Carnet WHERE IdClient=:matricule ")
    T_Carnet getNumCarnetByMatriculeClient(int matricule);
    /**
 * RECUPERATION DU NUMERO DU DERNIER CARNET
 */
    @Query("SELECT MAX(NumeroCarnet) FROM T_Carnet ")
    int getIdDernierCarnet();

    @Query("SELECT DISTINCT  COUNT(IdClient) FROM T_Carnet WHERE IdLot=:montant  GROUP BY IdLot")
    int getNombreClientBySouscription(int montant);
    /**
     * Le nombre total de carnet Vendu genenralement
     * @return
     */
    @Query("SELECT COUNT(*) FROM T_Carnet ")
    Integer getNombre_General_Carnet_Vendu();

    @Query("SELECT COUNT(*) FROM T_Carnet WHERE date_ajout BETWEEN :date_debut AND :date_fin")
    Integer getNombre_General_Carnet_VenduBetweenPeriod(long date_debut,long date_fin);

    @Query("SELECT COUNT(*) FROM T_Carnet WHERE date_ajout BETWEEN :date_debut AND :date_fin")
    Integer getNombre_General_Carnet_VenduBetweenPerod(long date_debut,long date_fin);

    @Query("SELECT T_Carnet.* FROM T_Carnet WHERE T_Carnet.NumeroCarnet=:numcarnet   GROUP BY T_Carnet.NumeroCarnet")
    List<T_Carnet> getListeCarnetByNumCarnet(int numcarnet);

    @Query("SELECT T_Carnet.* FROM T_Carnet WHERE IdLot =:montant GROUP BY IdLot")
    List<T_Carnet> getListeCarnetByLot(int montant);

    /**
     * LISTE DES CARNETS PAR TYPE LOT
     * @param typelot
     * @return
     */
    @Query("SELECT T_Carnet.* FROM T_Lot,T_Carnet " +
            "WHERE  T_Carnet.IdLot=T_Lot.montant_lot AND  T_Lot.type_lot LIKE :typelot ")
    List<T_Carnet> getListeCarnetByTypeLot(String typelot);

    @Query("SELECT T_Carnet.* FROM T_Carnet WHERE T_Carnet.NumeroCarnet == :numcarnet ")
   T_Carnet getCarnetByNumCarnet(int numcarnet);
    @Insert
    void  createCarnet(T_Carnet carnet);

    @Update
    int updateCarnet(T_Carnet carnet);

    @Query("DELETE FROM T_Carnet where NumeroCarnet=:numCarnet")
    void  deleteCarnet(long numCarnet);
}
