//package com.sodyam.philomabtontine.Repositories;
//
//import com.sodyam.philomabtontine.Database.Dao.CLientLotDao;
//import com.sodyam.philomabtontine.Database.Dao.CarnetDao;
//import com.sodyam.philomabtontine.Database.Dao.LotDao;
//import com.sodyam.philomabtontine.Database.Dao.PaiementDao;
//import com.sodyam.philomabtontine.Outils.Liste_paiements;
//import com.sodyam.philomabtontine.Outils.Point_general_activities;
//import com.sodyam.philomabtontine.Outils.Point_jour;
//import com.sodyam.philomabtontine.Outils.Point_par_souscription;
//import com.sodyam.philomabtontine.Outils.dernierPaiementInfos;
//import com.sodyam.philomabtontine.Outils.infosPaiements;
//import com.sodyam.philomabtontine.Outils.pointParTypeLot;
//import com.sodyam.philomabtontine.model.T_Carnet;
//import com.sodyam.philomabtontine.model.T_Client_Lot;
//import com.sodyam.philomabtontine.model.T_Lot;
//import com.sodyam.philomabtontine.model.T_Paiement;
//
//import java.util.List;
//
//public class EncaissementRepository {
//    private final PaiementDao mPaiementDao;
//    private final CarnetDao mCarnetDao;
//    private final CLientLotDao mCLientLotDao;
//    private final LotDao mLotDao;
//
//
//
//    public EncaissementRepository(PaiementDao paiementDao, CarnetDao carnetDao, CLientLotDao CLientLotDao, LotDao lotDao) {
//        mPaiementDao = paiementDao;
//        mCarnetDao = carnetDao;
//        mCLientLotDao = CLientLotDao;
//        mLotDao = lotDao;
//    }
//
//    public int getMontantPayeByNumCarnet(Integer idCarnet){
//        return mPaiementDao.getMontantPayeByNumCarnet(idCarnet);
//
//    }
//    //Point general des activités
//    public Point_general_activities getPoinGeneralActivities()
//    {
//        return mPaiementDao.getPointGeneral();
//    }
//
//    public void insertPaiement(T_Paiement newPaiement){
//        mPaiementDao.insertPaiement(newPaiement);
//    }
//
//    public List<Liste_paiements> getListePaiements(){
//        return  mPaiementDao.getListePaiements();
//    }
//
//    //Methode qui retourne le point du jour
//    public Point_jour pointDuJour(Integer date_ajout)
//    {
//        return  mPaiementDao.getPointDuJour();
//    }
//
//    //METHODE RETOURNANT LE NOMBRE DE CARNET VENDU LE DERNIER JOUR
//    public  Long nombreCarnetVenduDernierJour()
//    {
//        return mPaiementDao.getNombreCarnet_Vendu_By_Jour();
//    }
//
//    // NOMBRE DE CARNET VENDU AU TOTAL
//    public Integer getNombreCarnetVenduTotal()
//    {
//        return  mCarnetDao.getNombre_General_Carnet_Vendu();
//    }
//
//    /**
//     * RECUPERATION DE LA LISTE DES LOTS
//     * @return
//     */
//
//    public List<Long> getListesMontantEnregistrees()
//    {
//        return  mLotDao.getListesMontantExistants();
//    }
//
//    /**
//     * RECUPERATION DE LLA DATE AJOUT D'UN LOT
//     * @return
//     */
//    public Integer getDateAjoutLot(Integer montant)
//    {
//        return mLotDao.getDateAjoutKnownLot(montant);
//    }
//
//    //MONTANT COLLECTE LE DERNIER JOUR
//
//    public Long montantCollecteDernierJour()
//    {
//        return  mPaiementDao.get_Montant_collecte_Jour();
//    }
//
//    //DATE DU PREMIER ENREGISTREMENT
//
//    public Integer getDateFirstCarnetSAving()
//    {
//        return mPaiementDao.getDatePremierEnregistrement();
//    }
//
//    //METHODE RETOURNANT LES INFORMATIONS DU DERNIER PAIEMENT D'UN CLIENT CONNAISSANT SON MATRICULE
//    public dernierPaiementInfos getDateEtMontantDernierPaiement(Integer IdClient)
//    {
//        return mPaiementDao.getDernierPaiementGeneralById(IdClient);
//    }
//
//    //METHODE RETOURNANT LE POINT PAR CLIENT
//    public infosPaiements getPointByClientKnownId(Integer numCarnet)
//    {
//        return  mPaiementDao.getInfosPaiementClientById(numCarnet);
//    }
//
//    /**
//     * POINT POAR SOUSCRIPTION
//     * @param
//     * @return
//     */
//    public List <Point_par_souscription> getPointParSouscription()
//    {
//        return  mCLientLotDao.getPointParSouscription();
//    }
//
////METHODE RETOURNANT LE POINT PAR TYPE DE LOT
//    public pointParTypeLot getPointParTypeLot(Integer IdClient)
//    {
//        return mPaiementDao.getInfosPaiementClientTypelotById(IdClient);
//    }
//
//    /**
//     * RECUPERER LE NOMBRE DE CLIENT ENREGISTRE PAR UN AGENT ENCONNAISSANT SON POSTE
//     */
//    public Integer getNombreCarnetEnregistreByPoste(String poste)
//    {
//        return  mPaiementDao.getNombreClientEnregistreByPoste(poste);
//    }
//
//    /**
//     * //DERNIER  CLIENT_LOT ENREGISTRE
//     * @return
//     */
//
//    public  int getLastClientLotEnregistre(){
//        return mCLientLotDao.getDernierClientLotEnregistre();
//    }
//    /**
//     * RECUPERATION DES NUMEROS DES CARNETS
//     */
//    public  List getNumerosDesCarnets()
//    {
//        return  mCarnetDao.getNumerosDesCarnets();
//    }
//
//    /**
//     * RECUPERATION DE L'ID DU DERNIER CARNET ENREGISTRE
//     */
//    public int getDernierCarnetEnregistre()
//    {
//        return mCarnetDao.getIdDernierCarnet();
//    }
//    /**
//     * ======== LES METHODES D'INSERTION =============
//     */
//     //insertion PAIEMENT
//    public void CreatePaiement(T_Paiement paiement)
//    {
//        mPaiementDao.insertPaiement(paiement);
//    }
//
//    //INSERTION CARNET
//    public void  CreateCarnet(T_Carnet carnet)
//    {
//         mCarnetDao.createCarnet(carnet);
//    }
//
//    //INSERTION CLIENT_LOT
//    public  void CreateClientlot(T_Client_Lot client_lot)
//    {
//       mCLientLotDao.insertCient_lot(client_lot);
//    }
//
//    //INSERTION D'UN LOT
//    public  void Createlot(T_Lot lot)
//    {
//        mLotDao.insertLot(lot);
//    }
//}
