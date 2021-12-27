package com.sodyam.philomabtontine.Vue;

import static android.content.ContentValues.TAG;
import static com.sodyam.philomabtontine.Vue.Authentification.current_agentConnexion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.Outils.DateConverter;
import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Agent;

import java.sql.Date;
import java.util.List;

public class liste_agents extends AppCompatActivity {
    private SearchView zone_recherche_agent;
    private TextView date_actuelle;
     private Button  btn_ajout_agent,btn_quiter_liste_agent,btn_administrer_agent;
     private TextView nom_agent, prenom_agent,poste_occupe,contact,

    nombre_enregistre, date_debut;
     private List<T_Agent> mT_agent;
     RecyclerView mRecyclerView;
     MainAdapter mMainAdapter;
     private Databasephilomabtontine database;
     private  RecyclerView recyclerView;
     private Example_agents_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_agents);

        /**
         * INITIALISATION DU RECYCLERVIEW8 DE LA LISTE DES CLIENTS
         */
        database=Databasephilomabtontine.getInstance(this.getApplicationContext());
        Log.e(TAG,"Le Nombre total d'Agent ......................: "+ database.ClientDao().getListeClients().size());
        if(database.AgentDao().getListeAgents().size()==0)
        {
          Log.e(TAG,"LA liste est vide -----------------------------------");
        }
        else
        {
            Integer i=0;
           for( T_Agent agent : database.AgentDao().getListeAgents())
           {
               recyclerView = findViewById(R.id.recyclerViewForAgent);
               recyclerView.setLayoutManager(new LinearLayoutManager(this));
               adapter = new Example_agents_adapter(getApplicationContext(), database.AgentDao().getListeAgents());
               recyclerView.setAdapter(adapter);
           }

        }

        init();
        this.btn_quiter_liste_agent=findViewById(R.id.btn_quiter_liste_agent);
        this.btn_ajout_agent= (Button) findViewById(R.id.btn_ajout_nouveau_agent);

        /**
         * AFFICHAGE DI NOMBRE D'AGENT ENREGISTRE
         */
        nombre_enregistre.setText(database.AgentDao().getListeAgents().size()+" agents");
        //Rediection vers le formulaire d'Ajout d'un nouveau agent

        /**
         * CONVERSION DE LA DATE
         */

        Date date=new Date(System.currentTimeMillis());
        String anne=date.toString().substring(0,4);
        String mois=date.toString().substring(5,7);
        String jour=date.toString().substring(8);
        String dates=jour+" "+mois+" "+anne;
        date_actuelle.setText(DateConverter.ConvertNumericNewDatetoAllLetter());
        btn_ajout_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!current_agentConnexion.equals("SUPERVISEUR"))
                {
                    AlertDialog.Builder avertissement=new AlertDialog.Builder(liste_agents.this);
                    avertissement.setTitle("AVERTISSEMENT");
                    avertissement.setMessage("CETTE PAGE EST ADMINISTREE !! \nVOUS NE POSEDEZ PAS LES DROITS D'ACCES ! ");

                    avertissement.setPositiveButton("FERMER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
//                   avertissement.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                       @Override
//                       public void onClick(DialogInterface dialogInterface, int i) {
//
//                       }
//                   });
                   avertissement.show();
                }
                else
                {
                    Intent VersAjout_nouveau_Agent=new Intent(getApplicationContext(),com.sodyam.philomabtontine.Vue.gestion_agents.class);
                    startActivity(VersAjout_nouveau_Agent);
                    finish();
                }
            }
        });

        btn_quiter_liste_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VersMenu=new Intent(getApplicationContext(),Menu.class);
                startActivity(VersMenu);
                finish();
            }
        });
//        //Assign variable
//        mRecyclerView=findViewById(R.id.recycler_view);
//
//        //Set Layout manager
//        mRecyclerView.setLayoutManager(new LinearLayoutManaguer(this));
//
//        //Initialise Adapter
//        mMainAdapter=new MainAdapter(this,mT_agent);
//
//        //Set adapter
//        mRecyclerView.setAdapter(mMainAdapter);
    }


    //Methode d'initialisation et de recuperation
    private void init()

    {
        this.date_actuelle=(TextView) findViewById(R.id.date_actuelle);
        this.nom_agent=(TextView) findViewById(R.id.nom_agent);
        this.prenom_agent=(TextView) findViewById(R.id.prenom_agent);
        this.poste_occupe=(TextView) findViewById(R.id.poste_agent);
        this.contact=(TextView) findViewById(R.id.telepnone_agent);
        this.nombre_enregistre=(TextView) findViewById(R.id.nombre_total_agent);
        this.date_debut=(TextView) findViewById(R.id.date_debut);
//        this.btn_administrer_agent=(Button) findViewById(R.id.btn_administrer_liste_agent);
        //this.zone_recherche_agent= (SearchView) findViewById(R.id.zone_recherche_agent);
    }
}