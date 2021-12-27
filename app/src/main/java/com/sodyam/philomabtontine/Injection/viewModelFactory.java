//package com.sodyam.philomabtontine.Injection;
//
//import androidx.lifecycle.ViewModel;
//import androidx.lifecycle.ViewModelProvider;
//
//import com.sodyam.philomabtontine.Database.Dao.AgentDao;
//import com.sodyam.philomabtontine.Database.Dao.CLientLotDao;
//import com.sodyam.philomabtontine.Database.Dao.CarnetDao;
//import com.sodyam.philomabtontine.Database.Dao.LotDao;
//import com.sodyam.philomabtontine.Database.Dao.PaiementDao;
//import com.sodyam.philomabtontine.Repositories.AgentRepository;
//import com.sodyam.philomabtontine.Repositories.ClientRepository;
//import com.sodyam.philomabtontine.Repositories.EncaissementRepository;
//import com.sodyam.philomabtontine.Repositories.PointGeneralRepository;
//import com.sodyam.philomabtontine.Repositories.PointParSouscriptionRepository;
//import com.sodyam.philomabtontine.Repositories.PointjourRepository;
//import com.sodyam.philomabtontine.ContentViewModel;
//
//import java.util.concurrent.Executor;
//public class viewModelFactory implements ViewModelProvider.Factory{
//    private final AgentRepository agentDao;
//    private final ClientRepository carnetDao;
//    private final EncaissementRepository cLientLotDao;
//    private final PointGeneralRepository gestion_agentsDao;
//    private final PointjourRepository lotDao;
//    private final PointParSouscriptionRepository paiementDao;
//    private final Executor executor;
//
//    public viewModelFactory(AgentRepository agentDao,
//                            ClientRepository carnetDao,
//                            EncaissementRepository cLientLotDao,
//                            PointGeneralRepository gestion_agentsDao,
//                            PointjourRepository lotDao,
//                            PointParSouscriptionRepository paiementDao,
//                            Executor executor) {
//        this.agentDao = agentDao;
//        this.carnetDao = carnetDao;
//        this.cLientLotDao = cLientLotDao;
//        this.gestion_agentsDao = gestion_agentsDao;
//        this.lotDao = lotDao;
//        this.paiementDao = paiementDao;
//        this.executor = executor;
//    }
//
//    @Override
//    public <T extends ViewModel> T create(Class<T> modelClass) {
//        if (modelClass.isAssignableFrom(ContentViewModel.class)) {
//            return (T) new ContentViewModel(this.agentDao,
//                    this.carnetDao,
//                    this.cLientLotDao,
//                    this.gestion_agentsDao,
//                    this.lotDao,
//                    this.paiementDao,
//                    this.executor);
//        }
//        throw new IllegalArgumentException("Unknown ViewModel class");
//    }
//}
