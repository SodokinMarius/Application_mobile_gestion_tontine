package com.sodyam.philomabtontine.Repositories;

import com.sodyam.philomabtontine.Database.Dao.AgentDao;
import com.sodyam.philomabtontine.model.T_Agent;

import java.util.List;

public class AgentRepository {

    private final AgentDao mAgentDao;

    public AgentRepository(AgentDao agentDao) {
        mAgentDao = agentDao;
    }

    /**
     * GET TOUS LES AGENTS
     */
    public List<T_Agent> getListeAgents(){
        return mAgentDao.getListeAgents();
    }
    /**
     * Retourner le mot de passe de l'utilisateur
     */
    public T_Agent getAgentByPoste(String AgentPoste){
        return mAgentDao.getAgentByPoste(AgentPoste);
    }

    /**
     * enregistrer un agent
     */

    public void insertAgent(T_Agent agent){
        mAgentDao.insertAgent(agent);
    }

}
