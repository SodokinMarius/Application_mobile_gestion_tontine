package com.sodyam.philomabtontine.Vue;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.Outils.DateConverter;
import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Client;

import java.sql.Date;
import java.util.ArrayList;

//import com.sodyam.philomabtontine.ContentViewModel;
//import com.sodyam.philomabtontine.Injection.Injection;
//import com.sodyam.philomabtontine.Injection.viewModelFactory;

//import com.sodyam.philomabtontine.model.AccessDb_Client;

public class liste_clients extends AppCompatActivity {
    private EditText zone_recherche_client;
    private Button bouton_ajout_client,btn_quitter_liste_client, btn_actualiser_liste_client;
    private TextView date_actuelle;
    private  TextView nombre_total_client,contact_cli,numcarnet,nom_cli,prenom_cli,lot_souscrit;
    private RecyclerView unClient;
    ArrayList <T_Client> liste_Clients;
   // private  ContentViewModel mContentViewModel;
    private  Databasephilomabtontine database;
    private com.sodyam.philomabtontine.Vue.listClientAdapter adapter;
    private RecyclerView recyclerView;
    int num_client_recherche;

     public liste_clients()
     {

     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_clients);

        init_clients();
        /**
         * INITIALISATION DU RECYCLERVIEW8 DE LA LISTE DES CLIENTS
         */
        database=Databasephilomabtontine.getInstance(this.getApplicationContext());
        if(database.ClientDao().getListeClients().size()==0)
        {
           Log.e(TAG,"La liste des Client est vide----------------------");
        }
        else
        {
            Log.e(TAG,"le nombre total des clients----------------------"+ database.ClientDao().getListeClients().size());
            Log.e(TAG,"le nombre total des Carnets----------------------"+ database.CarnetDao().getNombre_General_Carnet_Vendu());
            Log.e(TAG,"le nombre total des Lots----------------------"+ database.LotDao().getListesLots().size());
            Log.e(TAG,"le nombre total des Clients_Lots----------------------"+ database.Client_LotDao().getListeClients_lots().size());


            /**
             * AFFICHAGE DES INFORMATIONS DE LA BASE DE DONNEES
             */

                recyclerView = findViewById(R.id.Unclient);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new listClientAdapter(getApplicationContext(), database.CarnetDao().getListeCarnets());
                recyclerView.setAdapter(adapter);
        }

        btn_actualiser_liste_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!zone_recherche_client.getText().toString().trim().isEmpty())
                {
                    num_client_recherche=Integer.parseInt(zone_recherche_client.getText().toString());
                    if(database.CarnetDao().getNumerosDesCarnets().contains(num_client_recherche))
                    {
                        recyclerView = findViewById(R.id.Unclient);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new listClientAdapter(getApplicationContext(), database.CarnetDao().getListeCarnetByNumCarnet(num_client_recherche));
                        recyclerView.setAdapter(adapter);
                    }
                    else
                    {
                        AlertDialog.Builder boiteDialogue=new AlertDialog.Builder(liste_clients.this);
                        boiteDialogue.setTitle("RESULTAT DE RECHERCHE");
                        boiteDialogue.setMessage("CE CLIENT DE NUMERO "+num_client_recherche +
                                "\n N'EST PAS ENREGISTRE !!! ");
                        boiteDialogue.setPositiveButton("FERMER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "VEUILLEZ ACTUALISER LA PAGE!!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        boiteDialogue.show();

                    }

                }
                else
                {
                    recyclerView = findViewById(R.id.Unclient);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new listClientAdapter(getApplicationContext(), database.CarnetDao().getListeCarnets());
                    recyclerView.setAdapter(adapter);
                }
            }
        });




//            init_clients();
        nombre_total_client.setText("Nbre Client : "+database.CarnetDao().getListeCarnets().size());

        /**
         * CONVERSION DE LA DATE
         */

        Date date=new Date(System.currentTimeMillis());
        String anne=date.toString().substring(0,4);
        String mois=date.toString().substring(5,7);
        String jour=date.toString().substring(8);
        String dates=jour+" "+mois+" "+anne;
        date_actuelle.setText(DateConverter.ConvertNumericNewDatetoAllLetter());

        btn_quitter_liste_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VersMenu=new Intent(getApplicationContext(),Menu.class);
                startActivity(VersMenu);
                finish();
            }
        });

        bouton_ajout_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VersAjout_nouveau_client=new   Intent(getApplicationContext(),nouveau_client.class);
                startActivity(VersAjout_nouveau_client);
//                finish();
            }

//                Menu.mfragmentManager.beginTransaction().replace(R.id.fragment_container,new Menu(), null).addToBackStack(null).commit();

        });
    }


    /**
     * CONFIGURATON DU CONTENT VIEWMODEL
     */
//    private void configureContentViewModel()
//    {
//        viewModelFactory mviewModelFactory= Injection.getViewModelFactory(this);
//        this.mContentViewModel= ViewModelProviders.of(this, (ViewModelProvider.Factory) mviewModelFactory)
//                .get(ContentViewModel.class);
//    }
    private void init_clients()
    {
        this.zone_recherche_client= (EditText) findViewById(R.id.zone_recherche_client);
        this.date_actuelle=(TextView) findViewById(R.id.date_actuelle);
        this.nombre_total_client=(TextView)findViewById(R.id.nombre_total_client);
        this.contact_cli=(TextView)findViewById(R.id.contactclient);
        this.numcarnet=(TextView)findViewById(R.id.numCarnet);
        this.nom_cli=(TextView)findViewById(R.id.nom_client);
        this.prenom_cli=(TextView)findViewById(R.id.prenom_client);
        this.lot_souscrit=(TextView)findViewById(R.id.lot_souscrit);
        this.unClient= (RecyclerView) findViewById(R.id.Unclient);
        this.bouton_ajout_client=(Button) findViewById(R.id.btn_ajout_nouveau_client);
        this.btn_quitter_liste_client=findViewById(R.id.btn_quiter_liste_client);
        this.btn_actualiser_liste_client=(Button)findViewById(R.id.btn_actualiser_liste_client);

    }
}