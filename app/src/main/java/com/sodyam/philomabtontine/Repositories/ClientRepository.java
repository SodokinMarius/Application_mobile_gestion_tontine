package com.sodyam.philomabtontine.Repositories;

import com.sodyam.philomabtontine.Database.Dao.ClientDao;
import com.sodyam.philomabtontine.Database.Dao.PaiementDao;
import com.sodyam.philomabtontine.Outils.client_par_souscription;
import com.sodyam.philomabtontine.model.T_Client;

import java.util.List;

public class ClientRepository {
    private final ClientDao mClientDao;
    private final PaiementDao mPaiementDao;

    public ClientRepository(ClientDao clientDao, PaiementDao paiementDao) {
        mClientDao = clientDao;
        mPaiementDao = paiementDao;
    }

    /**
     * inserer un nouveau client dans la base de donnee
     * @param client
     * recoit un nouveau client qu'on insere dans la base de donnee
     */
    public void insertClient(T_Client client){
        mClientDao.insertClient(client);
    }

    /**
     * Liste des clients enregistrer dans la base de donnee
     */
    public List<T_Client> getListeClient(){
        return mClientDao.getListeClients();
    }


    /**
     * MATRICULE DU DERNIER CLIENT ENREGISTRE
     */
    public int getMatriculeDernierClient()
    {
       return mClientDao.getDernierClientEnregistre();
    }

    /**
     * Rechercher un client specifique dans la base de donne,encaissement egalement
     * @param numcarnet
     * @return
     */

    public T_Client getClientByNumCarnet(Integer numcarnet){
        return mClientDao.getClientByNumCarnet(numcarnet);
    }

    public List<client_par_souscription> getClientBySouscription() {
        return mPaiementDao.getClientBySouscription();
    }

    public Integer getNombreClient()
    {
        return  mClientDao.getNombreClient();
    }
}
