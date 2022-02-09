package com.sodyam.philomabtontine.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sodyam.philomabtontine.model.T_Lot;

import java.util.List;

@Dao
public interface LotDao {
    @Query("SELECT * FROM T_Lot GROUP BY montant_lot ORDER BY montant_lot ASC")
    List<T_Lot> getListesLots();


    @Query("SELECT  T_Lot.* FROM T_Lot,T_Carnet WHERE T_Carnet.IdLot=T_Lot.montant_lot " +
            "AND T_Carnet.IdLot=:montant_lot ORDER BY T_Lot.type_lot ASC ")
    T_Lot getLotSouscritByMontantLot(int montant_lot);


    @Query("SELECT  type_lot FROM T_Lot")
    List<String> getListeTypeLots();

    //Nombre total de souscription
    @Query("SELECT COUNT(T_Lot.montant_lot) FROM T_Lot")
   Integer getNombreTotalDeLot();

    @Query("SELECT montant_lot FROM T_Lot ")
    List<Integer> getListesMontantExistants();

     //RECUPERATION DE LA DATE D'AJOUT D'UN LOT CONNAISSANT SA VALEUR
    @Query("SELECT date_ajout FROM T_Lot where montant_lot=:montant")
    String getDateAjoutKnownLot(Integer montant);

    //Nombre total de Type Lot
    @Query("SELECT COUNT(type_lot) FROM T_Lot GROUP BY type_lot")
    Integer getNombreTotalTypeLot();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
  void insertLot(T_Lot Lot);

    @Update
    int updateLot(T_Lot Lot);

    @Query("DELETE FROM T_Paiement where IdPaiement= :numLot")
    int deleteLot(long numLot);

}
