package com.sodyam.philomabtontine.Vue;

import static com.sodyam.philomabtontine.utils.AllConstants.TAG;

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


public class point_par_souscription extends AppCompatActivity {
    private Button boutton_quiter_point_par_souscrition,btn_actualiser_point_par_souscription;
    private TextView date_actuelle;
    private TextView nmbre_total_souscription,type_souscription,nmbre_de_client;
    private Databasephilomabtontine database;
    private PointParSouscriptionAdapter adapter;
    private RecyclerView recyclerView;
    private EditText zone_recherche_souscription;
    private int montant_recherche;


    public point_par_souscription()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_par_souscription);
        //init_point_par_souscrit();
        this.boutton_quiter_point_par_souscrition=findViewById(R.id.btn_quiter_point_souscription);
        this.date_actuelle=findViewById(R.id.date_actuelle);
        this.nmbre_total_souscription=(TextView) findViewById(R.id.nombre_total_souscrition);
        this.type_souscription=findViewById(R.id.souscription_valeur);
        this.nmbre_de_client=findViewById(R.id.nbre_client);
        this.zone_recherche_souscription=(EditText) findViewById(R.id.zone_recherche_souscription);
        recyclerView = (RecyclerView) findViewById(R.id.UneSouscription) ;
     this.btn_actualiser_point_par_souscription=(Button) findViewById(R.id.btn_actualiser_point_par_souscription);
        this.date_actuelle=(TextView) findViewById(R.id.date_actuelle);
        date_actuelle.setText(DateConverter.ConvertNumericNewDatetoAllLetter());


        /**
         * AFFICHAGE DES INFORMATIONS DE LA BASE DE DONNEES
         */
        //recyclerView = findViewById(R.id.Unclient);

        database = Databasephilomabtontine.getInstance(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PointParSouscriptionAdapter(getApplicationContext(), database.CarnetDao().getListeCarnetsPointSouscription());
        recyclerView.setAdapter(adapter);

       nmbre_total_souscription.setText("Total : "+database.LotDao().getListesLots().size());
       Log.e(TAG,"Nombre de Lots : ------------------"+database.LotDao().getListesLots().size());

        /**
         * ACTION APRES CLIC SUR LE BOUTON POINTPAR SOUSCRIPTION
         */
        btn_actualiser_point_par_souscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!zone_recherche_souscription.getText().toString().trim().isEmpty())
                {
                    montant_recherche=Integer.parseInt(zone_recherche_souscription.getText().toString());
                    if(database.LotDao().getListesMontantExistants().contains(montant_recherche))
                    {
//                recyclerView = findViewById(R.id.UneSouscription);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new PointParSouscriptionAdapter(getApplicationContext(), database.CarnetDao().getListeCarnetByLot (montant_recherche));
                        recyclerView.setAdapter(adapter);
                    }
                    else
                    {
                        AlertDialog.Builder boiteDialogue=new AlertDialog.Builder(point_par_souscription.this);
                        boiteDialogue.setTitle("RESULTAT DE RECHERCHE");
                        boiteDialogue.setMessage("LE MONTANT  "+montant_recherche +
                                "\n  N'EST PAS ENCORE MIS !!! ");
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
//            recyclerView = findViewById(R.id.UneSouscription);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new PointParSouscriptionAdapter(getApplicationContext(), database.CarnetDao().getListeCarnets());
                    recyclerView.setAdapter(adapter);
                }
            }
        });



    boutton_quiter_point_par_souscrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VersMenuPrincipal=new Intent(getApplicationContext(),Menu.class);
                startActivity(VersMenuPrincipal);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //Valorisation des atributs
    private void init_point_par_souscrit()
    {

    }
}