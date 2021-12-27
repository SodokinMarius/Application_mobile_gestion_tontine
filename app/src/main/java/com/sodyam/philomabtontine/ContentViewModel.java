//package com.sodyam.philomabtontine;
//import androidx.annotation.Nullable;
//import androidx.lifecycle.ViewModel;
//
//import com.sodyam.philomabtontine.Outils.Liste_paiements;
//import com.sodyam.philomabtontine.Outils.Point_general_activities;
//import com.sodyam.philomabtontine.Outils.Point_jour;
//import com.sodyam.philomabtontine.Outils.Point_par_souscription;
//import com.sodyam.philomabtontine.Outils.client_par_souscription;
//import com.sodyam.philomabtontine.Outils.dernierPaiementInfos;
//import com.sodyam.philomabtontine.Outils.infosPaiements;
//import com.sodyam.philomabtontine.Outils.pointParTypeLot;
//import com.sodyam.philomabtontine.Repositories.AgentRepository;
//import com.sodyam.philomabtontine.Repositories.ClientRepository;
////import com.sodyam.philomabtontine.Repositories.EncaissementRepository;
//import com.sodyam.philomabtontine.Repositories.PointGeneralRepository;
//import com.sodyam.philomabtontine.Repositories.PointParSouscriptionRepository;
//import com.sodyam.philomabtontine.Repositories.PointjourRepository;
//import com.sodyam.philomabtontine.model.T_Agent;
//import com.sodyam.philomabtontine.model.T_Carnet;
//import com.sodyam.philomabtontine.model.T_Client;
//import com.sodyam.philomabtontine.model.T_Client_Lot;
//import com.sodyam.philomabtontine.model.T_Lot;
//import com.sodyam.philomabtontine.model.T_Paiement;
//
//import java.util.List;
//import java.util.concurrent.Executor;
//
//public class ContentViewModel extends ViewModel {
//    private final AgentRepository agentDataSource;
//    private final ClientRepository clientDataSource;
//    private final EncaissementRepository paiementDataSource;
//    private final PointGeneralRepository pointGeneralDataSource;
//    private final PointjourRepository pointJourDataSource;
//    private final PointParSouscriptionRepository pointParSouscriptionDataSource;
//    private final Executor executor;
//
//    //DATA
//    @Nullable
//    private T_Agent currentAgent;
//
//    public ContentViewModel(AgentRepository agentDataSource,
//                            ClientRepository clientDataSource,
//                            EncaissementRepository paiementDataSource,
//                            PointGeneralRepository pointGeneralDataSource,
//                            PointjourRepository pointJourDataSource,
//                            PointParSouscriptionRepository pointParSouscriptionDataSource,
//                            Executor executor) {
//        this.agentDataSource = agentDataSource;
//        this.clientDataSource = clientDataSource;
//        this.paiementDataSource = paiementDataSource;
//        this.pointGeneralDataSource = pointGeneralDataSource;
//        this.pointJourDataSource = pointJourDataSource;
//        this.pointParSouscriptionDataSource = pointParSouscriptionDataSource;
//        this.executor = executor;
//    }
//
//    public void init(String posteAgent) {
//        if (this.currentAgent != null) {
//            return;
//        }
//        currentAgent = agentDataSource.getAgentByPoste(posteAgent);
//    }
//
//    //============
//    //FOR AGENT
//    //=============
//    public T_Agent getAgentByPoste(String poste_agent) {
//        return this.currentAgent;
//    }
//
//    //============
//    //OTHER DATA
//    //=============
//
//    //============COTE AGENT ET SES METHODES ============
//    public List<T_Agent> getListeAgents() {
//        return agentDataSource.getListeAgents();
//    }
//
//    /**
//     * RECUPERER LE NOMBRE DE CLIENT ENREGISTRE PAR UN AGENT EN CABONNAISSANT SON POSTE
//     * @param
//     */
//    public  void getNombreDeClientEnregistreByPosteKnown(String poste)
//    {
//        paiementDataSource.getNombreCarnetEnregistreByPoste(poste);
//    }
//
//    //INSERER NOUVEAU AGENT
//    public void  inserNewAgent(T_Agent agent)
//    {
//        executor.execute(()->{
//            agentDataSource.insertAgent(agent);
//        });
//    }
//
//    public T_Agent getAgentByHerPoste(String poste_agent) {
//        return agentDataSource.getAgentByPoste(poste_agent);
//
//    }
//    /**
//     * LE NOMBRE TOTAL DE CLIENT
//     */
//
//    public Integer getNombreTotalClient()
//    {
//        return clientDataSource.getNombreClient();
//    }
//    /**
//     *  ============COTE CLIENT ET SES METHODES ============
//     * @return
//     */
//
//  //Liste des Clients
//    public List<T_Client> getListeDesClient() {
//        return clientDataSource.getListeClient();
//    }
//
//    /**
//     *   RECUPERER UN CLIENT CONNAISSANT SON NUMERO CARNET
//     * @param numCarnet
//     * @return
//     */
//    public T_Client getClientByNumCarnet(Integer numCarnet) {
//        return clientDataSource.getClientByNumCarnet(numCarnet);
//    }
//
//    /**
//     * ===== LISTE DES CLIENTS PAR SOUSCRIPTION ====
//     * @return
//     */
//    public List<client_par_souscription> getClientBySouscription() {
//        return clientDataSource.getClientBySouscription();
//    }
//
//    /**
//     * INFORMATIONS DU CLIENT CONNAISSANT SON CARNET
//     */
//    public  infosPaiements getInfosPaiementClientByNumcarnet(Integer numcarnet)
//    {
//        return paiementDataSource.getPointByClientKnownId(numcarnet);
//    }
//
//    /**
//     * =====  INSERTION D'UN NOUVEAU CLIENT ========
//     * @param client
//     */
//    public void insertClient(T_Client client) {
//        executor.execute(() -> {
//            clientDataSource.insertClient(client);
//        });
//    }
//
//    /**
//     * ============COTE PAIEMENT ET SES METHODES ============
//     */
//
//    //LISTE DES PAIEMENTS
//    public List<Liste_paiements> getListeDesPaiements() {
//        return paiementDataSource.getListePaiements();
//    }
//
//    public int getMontantCarnetByTheNumcarnet(Integer numcarnet) {
//        return paiementDataSource.getMontantPayeByNumCarnet(numcarnet);
//    }
//
//    //NOUVEAU PAIEMENT
//    public void insertNewPaiement(T_Paiement paiement) {
//        executor.execute(() -> {
//            paiementDataSource.insertPaiement(paiement);
//        });
//    }
//
//
//    /**
//     * =============Point General de l'Activité
//     * @return
//     */
//   public Point_general_activities getPoint_General_Activities() {
//      return  paiementDataSource.getPoinGeneralActivities();
//    }
//
//    /**
//     * POINT PAR SOUSCRIPTION
//     */
//    public List <Point_par_souscription> getPointParSouscription()
//    {
//        return paiementDataSource.getPointParSouscription();
//    }
//
//    /**
//     *  ============ POINT DU JOUR =========
//     * @param date
//     * @return
//     */
//
//    public Point_jour getPointDuJour(Integer date)
//    {
//        return paiementDataSource.pointDuJour(date);
//    }
//
//    /**
//     * ========= Point Par souscription =========
//     */
//
//    public  List<Point_par_souscription> getPointBySouscription()
//    {
//        return  pointParSouscriptionDataSource.getPointParSouscription();
//    }
//    /**
//     * =========Point par Client=========
//     */
//     public infosPaiements getPointByclientKnownId(Integer IdClient)
//     {
//         return paiementDataSource.getPointByClientKnownId(IdClient);
//     }
//
//
//
//    /**
//     * ========= POINT PAR TYPE DE LOT ===========
//     */
//         public  pointParTypeLot getPointParTypeLotKnownId(Integer IdClient)
//         {
//             return paiementDataSource.getPointParTypeLot(IdClient);
//         }
//
//    /**
//     * RECUPERATION DE LA LISTE DES NUMEROS DES CARNETS
//     */
//    public  List getListesNumerosCarnets()
//    {
//        return paiementDataSource.getNumerosDesCarnets();
//    }
//
//    /**
//     * RECUPERATION DE LA DATE AJOUT D'UN LOT
//     */
//
//    public Integer getDateAjoutDuLot(Integer montant)
//    {
//       return  paiementDataSource.getDateAjoutLot(montant);
//    }
//    /**
//     * NOMBRE TOTAL DE CARNET VENDU
//     */
//    public Integer getNombreTotalCarnetVendu()
//    {
//        return paiementDataSource.getNombreCarnetVenduTotal();
//    }
//    /**
//     * RECUPERER LES MONTANT DES LOTS EXISTANTS
//     */
//    public  List<Long> getListesMontantLotsExistant()
//    {
//        return paiementDataSource.getListesMontantEnregistrees();
//    }
//
//    /**
//     * ========= INFOS SUR LE DERNIER PAIEMENT(DATE ET  MONTANT) ===========
//     */
//    public  dernierPaiementInfos getDateEtMontantDuDernierPaiement(Integer IdClient)
//    {
//        return paiementDataSource.getDateEtMontantDernierPaiement(IdClient);
//    }
//
//    /**
//     *  ======    METHODE RETOURNANT LE NOMBRE DE CARNET VENDU LE DERNIER JOUR,
//     * LE MONTANT TOTAL COLLECTE ET
//     * DATE DU PREMIER ENREGISTREMENT (point envoyer en paramètre de la methode point general)====
//     */
//
//    public Long getNombreCarnetVenduDernierJour()
//    {
//        return paiementDataSource.nombreCarnetVenduDernierJour();
//    }
//
//    public  Long getMontantCollecteDernierJour()
//    {
//        return paiementDataSource.montantCollecteDernierJour();
//    }
//
//    public Integer getDateDuPremierEnregistrementCarnet()
//    {
//        return  paiementDataSource.getDateFirstCarnetSAving();
//    }
//
//    /**
//     * ======== MATRICULE DU DERNIER CLIENT ENREGISTRE =========
//     */
//    public  int getDernierClientEnregistre()
//    {
//        return clientDataSource.getMatriculeDernierClient();
//    }
//
//    /**
//     * ========= ID DU DERNIER CARNET ENREGISTRE ======
//     */
//    public int getNumeroDernierCarnetEnregistre()
//    {
//        return paiementDataSource.getDernierCarnetEnregistre();
//    }
//
//    /**
//     * ========= DERNIER CLIENT_LOT ENREGISTRE ========
//     */
//
//    public  int getLastClientLotCreated()
//    {
//        return  paiementDataSource.getLastClientLotEnregistre();
//    }
//
//    /**
//     * ========== METHODES D'INSERTION ===========
//     */
//
//    //====== NOUVEAU CARNET ===============
//    public  void createNewCarnet(T_Carnet carnet)
//    {
//        executor.execute(()->{
//          paiementDataSource.CreateCarnet(carnet);
//        });
//
//    }
//    //====== NOUVEAU LOT ===============
//    public void createNewLot(T_Lot lot)
//    {
//        executor.execute(()->{
//            paiementDataSource.Createlot(lot);
//        });
//
//    }
//    //====== NOUVEAU PAIEMENT ===============
//    public void createNewPaiement(T_Paiement paiement)
//    {
//        executor.execute(()->{
//            paiementDataSource.insertPaiement(paiement);
//        });
//    }
//
//    //====== NOUVEAU CLIENT_LOT ===============
//    public void CreateNewClientlot(T_Client_Lot client_lot)
//    {
//        executor.execute(()->{
//            paiementDataSource.CreateClientlot(client_lot);
//        });
//    }
//
//
//}
