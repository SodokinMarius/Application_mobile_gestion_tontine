package com.sodyam.philomabtontine;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.sodyam.philomabtontine.Database.Dao.AgentDao;
import com.sodyam.philomabtontine.Database.Dao.CLientLotDao;
import com.sodyam.philomabtontine.Database.Dao.CarnetDao;
import com.sodyam.philomabtontine.Database.Dao.ClientDao;
import com.sodyam.philomabtontine.Database.Dao.LotDao;
import com.sodyam.philomabtontine.Database.Dao.PaiementDao;
import com.sodyam.philomabtontine.Outils.DateConverter;
import com.sodyam.philomabtontine.model.T_Agent;
import com.sodyam.philomabtontine.model.T_Carnet;
import com.sodyam.philomabtontine.model.T_Client;
import com.sodyam.philomabtontine.model.T_Client_Lot;
import com.sodyam.philomabtontine.model.T_Lot;
import com.sodyam.philomabtontine.model.T_Paiement;

@Database(entities = {T_Agent.class, T_Client.class, T_Carnet.class, T_Lot.class, T_Paiement.class, T_Client_Lot.class}, version = 30, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class Databasephilomabtontine  extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile Databasephilomabtontine INSTANCE;

    // ========= DAO =======
    public abstract AgentDao AgentDao();
    public abstract ClientDao ClientDao();

    public abstract PaiementDao PaiementDao();

    public abstract LotDao LotDao();

    public abstract CLientLotDao Client_LotDao();

    public abstract CarnetDao CarnetDao();

    // --- INSTANCE ---
    public static Databasephilomabtontine getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (Databasephilomabtontine.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Databasephilomabtontine.class, "philomabtontine.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            //.addCallback(prepopulateDatebade())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

//    //TEST
//    public static  Callback prepopulateDatebade()
//    {
//        return  new Callback() {
//            @Override
//            public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                super.onCreate(db);
//                ContentValues contentValues=new ContentValues();
//
//                contentValues.put("Poste","SUPERVISEUR");
//                contentValues.put("NomAgent","TATA");
//                contentValues.put("PrenomAgent","Béatrice");
//                contentValues.put("Telephone","66562771");
//                contentValues.put("Adresse","GODOMEY");
//                contentValues.put("mot_de_passe","SUP2022");
//                contentValues.put("DateDeDebut","01/12/2021");
//                db.insert("T_Agent", OnConflictStrategy.IGNORE,contentValues);
//            }
//        };
//    }
    }

