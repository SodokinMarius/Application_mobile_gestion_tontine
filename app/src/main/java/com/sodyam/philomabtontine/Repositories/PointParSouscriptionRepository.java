package com.sodyam.philomabtontine.Repositories;

import androidx.lifecycle.LiveData;

import com.sodyam.philomabtontine.Database.Dao.CLientLotDao;
import com.sodyam.philomabtontine.Database.Dao.ClientDao;
import com.sodyam.philomabtontine.Outils.Point_par_souscription;

import java.util.List;

public class PointParSouscriptionRepository {
    private final CLientLotDao mCLientLotDao;

    public PointParSouscriptionRepository(CLientLotDao CLientLotDao) {
        mCLientLotDao = CLientLotDao;
    }

    public List<Point_par_souscription> getPointParSouscription(){
        return mCLientLotDao.getPointParSouscription();
    }
}
