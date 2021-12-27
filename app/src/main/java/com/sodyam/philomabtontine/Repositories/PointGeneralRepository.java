package com.sodyam.philomabtontine.Repositories;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.sodyam.philomabtontine.Database.Dao.CarnetDao;
import com.sodyam.philomabtontine.Database.Dao.PaiementDao;
import com.sodyam.philomabtontine.Outils.Point_general_activities;

import java.util.Date;

public class PointGeneralRepository {

    private final CarnetDao mCarnetDao;
    private final PaiementDao mPaiementDao;

    public PointGeneralRepository(CarnetDao carnetDao, PaiementDao paiementDao) {

        mCarnetDao = carnetDao;
        mPaiementDao = paiementDao;
    }

    public Integer getNombreCarnet_Vendu_General(){
       return mCarnetDao.getNombre_General_Carnet_Vendu();
    }

    public Long get_Montant_Collecte_General(){
       return mPaiementDao.getMontant_Total_General();
    }

    public Point_general_activities getPointGeneraleActivities()
    {
        return mPaiementDao.getPointGeneral();
    }

}
