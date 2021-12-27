package com.sodyam.philomabtontine.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sodyam.philomabtontine.model.T_Agent;

import java.util.List;

@Dao
public interface AgentDao {
    @Query("SELECT * FROM T_Agent")
     List<T_Agent> getListeAgents();



    /**
     * Rechercher un agent par son poste
     * @param
     * @return
     */
    @Query("SELECT * FROM T_Agent where Poste= :poste_agent")
    T_Agent getAgentByPoste(String poste_agent);

    @Query("SELECT Poste FROM T_Agent ")
    List<String> getPostesAgents();

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    long insertAgent(T_Agent agent);

   @Update
    int UpdateAgent(T_Agent agent);

    @Query("DELETE FROM T_Agent where Poste= :Poste")
    int deleteAgent(String Poste);

}
