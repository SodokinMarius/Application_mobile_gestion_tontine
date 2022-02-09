package com.sodyam.philomabtontine.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sodyam.philomabtontine.Outils.Point_par_souscription;
import com.sodyam.philomabtontine.model.T_Client_Lot;

import java.util.List;

@Dao
public interface CLientLotDao {
    @Query("SELECT * FROM T_Client_Lot")
   List<T_Client_Lot> getListeClients_lots();

    //Point par souscription
    @Query("SELECT id_Lot as souscription," +
            "COUNT(IdClient) as nombreDeClient " +
            "FROM T_Client_Lot" +
            " GROUP BY id_Lot " +
            " ORDER BY id_Lot ASC ")
    List<Point_par_souscription> getPointParSouscription();

    @Query("SELECT COUNT(IdClient) FROM T_Client_Lot  WHERE id_Lot=:idlot GROUP BY id_Lot")
    int getNombreClientByLot(int idlot);

    @Query("SELECT * FROM T_Client_Lot  WHERE id_Lot=:idlot  GROUP BY  id_Lot ")
    List<T_Client_Lot> getListClientByLot(int idlot);


    @Query("SELECT MAX(Id_Client_lot) FROM T_Client_Lot")
    int getDernierClientLotEnregistre();

   @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCient_lot(T_Client_Lot client_lot);

    @Update
    int updateClient_Lot(T_Client_Lot client_lot);

    @Query("DELETE FROM T_Client_Lot where Id_Client_Lot= :numClientLot")
   void deleteClient_Lot(long numClientLot);
}
