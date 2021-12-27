//package com.sodyam.philomabtontine.Injection;
//
//import android.content.Context;
//
//import com.sodyam.philomabtontine.ContentViewModel;
//import com.sodyam.philomabtontine.Database.Dao.AgentDao;
//import com.sodyam.philomabtontine.Database.Dao.ClientDao;
//import com.sodyam.philomabtontine.Databasephilomabtontine;
//import com.sodyam.philomabtontine.Repositories.AgentRepository;
//import com.sodyam.philomabtontine.Repositories.ClientRepository;
//import com.sodyam.philomabtontine.Repositories.EncaissementRepository;
//import com.sodyam.philomabtontine.Repositories.PointGeneralRepository;
//import com.sodyam.philomabtontine.Repositories.PointParSouscriptionRepository;
//import com.sodyam.philomabtontine.Repositories.PointjourRepository;
//
//import java.util.Date;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
//public class Injection {
//    public static AgentRepository getAgentDataSource(Context context) {
//        Databasephilomabtontine database = Databasephilomabtontine.getInstance(context);
//        return new AgentRepository(database.AgentDao());
//    }
//
//    public static ClientRepository getClientDataSource(Context context) {
//        Databasephilomabtontine database = Databasephilomabtontine.getInstance(context);
//        return new ClientRepository(database.ClientDao(),database.PaiementDao());
//    }
//
//    public static EncaissementRepository getEncaissementDataSource(Context context) {
//        Databasephilomabtontine database = Databasephilomabtontine.getInstance(context);
//        return new EncaissementRepository(database.PaiementDao(),database.CarnetDao(),database.Client_LotDao(),database.LotDao());
//    }
//
//    public static PointGeneralRepository getPointGeneralDataSource(/*Date datebegin,Date dateEnd,*/Context context) {
//        Databasephilomabtontine database = Databasephilomabtontine.getInstance(context);
//        return new PointGeneralRepository(database.CarnetDao(),database.PaiementDao());
//    }
//
//    public static PointjourRepository getPointJourDataSource(Context context) {
//        Databasephilomabtontine database = Databasephilomabtontine.getInstance(context);
//        return new PointjourRepository(database.CarnetDao(),database.PaiementDao());
//    }
//
//    public static PointParSouscriptionRepository getPointParSouscriptionDataSource(Context context) {
//        Databasephilomabtontine database = Databasephilomabtontine.getInstance(context);
//        return new PointParSouscriptionRepository(database.Client_LotDao());
//    }
//
//    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }
//
//    public static viewModelFactory getViewModelFactory(Context context/*, Date dateBegin, Date dateEnd*/){
//        AgentRepository mAgentdataSource = getAgentDataSource(context);
//        ClientRepository mClientRepository= getClientDataSource(context);
//        EncaissementRepository mEncaissementRepository= getEncaissementDataSource(context);
//        PointGeneralRepository mPointGeneralRepository= getPointGeneralDataSource(/*dateBegin,dateEnd,*/context);
//        PointjourRepository mPointjourRepository= getPointJourDataSource(context);
//        PointParSouscriptionRepository mPointParSouscriptionRepository =getPointParSouscriptionDataSource(context);
//        Executor executor = provideExecutor();
//        return new viewModelFactory(mAgentdataSource,
//                mClientRepository,mEncaissementRepository,
//                mPointGeneralRepository,mPointjourRepository,
//                mPointParSouscriptionRepository,executor);
//    }
//}
//
