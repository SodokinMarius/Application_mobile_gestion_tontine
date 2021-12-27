package com.sodyam.philomabtontine.Repositories;

import com.sodyam.philomabtontine.Database.Dao.CarnetDao;
import com.sodyam.philomabtontine.Database.Dao.PaiementDao;

public class PointjourRepository {
    private final CarnetDao mCarnetDao;
    private final PaiementDao mPaiementDao;

    public PointjourRepository(CarnetDao carnetDao, PaiementDao paiementDao) {
        mCarnetDao = carnetDao;
        mPaiementDao = paiementDao;
    }

//    public Long getNombreCarnet_Vendu_By_Jour(){
//        return mPaiementDao.getNombreCarnet_Vendu_By_Jour();
//    }

    public Long get_Montant_Collecte_Jour(){
        return mPaiementDao.get_Montant_collecte_Jour();
    }

}
