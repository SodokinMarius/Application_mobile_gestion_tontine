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
import com.sodyam.philomabtontine.Outils.pointParTypeLot;
import com.sodyam.philomabtontine.R;

import java.util.List;

public class point_par_type_lot extends AppCompatActivity {

    private Button quiter_point_par_type_lot,btn_actualiser_type_lot;
    private EditText zone_recherche_lot;
    private TextView nombre_total_lot,montant_dernier_paiement,date_dernier_paiement,nom_cli,prenom_cli,
            lot_choisi,montant_paye,reste_paye,valeur_souscript,date_actuelle;

    private RecyclerView recyclerView;
    private exemplePointParTypeDeLotAdapter adapter;
    private List<pointParTypeLot> mPoint_par_type_lot;
    private Databasephilomabtontine database;
    private  String lot_recherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_par_type_lot);
         init();

        date_actuelle.setText(DateConverter.ConvertNumericNewDatetoAllLetter());
        /**
         * INITIALISATION DU RECYCLERVIEW8 DE LA LISTE DES CLIENTS
         */
        database=Databasephilomabtontine.getInstance(this.getApplicationContext());
        Log.e(TAG,"Le Nombre total d'Agent ......................: ");
        if(database.PaiementDao().getInfosPaiementClientTypelotById().size()==0)
        {
            Log.e(TAG,"LA liste est vide -----------------------------------");
        }
        else {
            for (pointParTypeLot point : database.PaiementDao().getInfosPaiementClientTypelotById()){
                recyclerView = findViewById(R.id.recyclerViewForPointParTypeDeLot);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new exemplePointParTypeDeLotAdapter(getApplicationContext(), database.CarnetDao().getListeCarnets());
                recyclerView.setAdapter(adapter);
            }
        }

//        init();
        nombre_total_lot.setText("Total : "+database.LotDao().getNombreTotalTypeLot());
        Log.e(TAG,"Tous les lots ================="+database.LotDao().getListesLots().toString());


        /**
         * ACTION APRES CLIC SUR ACTUALISER LISTE
         */
        btn_actualiser_type_lot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!zone_recherche_lot.getText().toString().trim().isEmpty())
                {
                    lot_recherche=zone_recherche_lot.getText().toString().toUpperCase();
                    if(database.LotDao().getListeTypeLots().contains(lot_recherche))
                    {
                        recyclerView = findViewById(R.id.recyclerViewForPointParTypeDeLot);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new exemplePointParTypeDeLotAdapter(getApplicationContext(), database.CarnetDao().getListeCarnetByTypeLot(lot_recherche));
                        recyclerView.setAdapter(adapter);
                    }
                    else
                    {
                        AlertDialog.Builder boiteDialogue=new AlertDialog.Builder(point_par_type_lot.this);
                        boiteDialogue.setTitle("RESULTAT DE RECHERCHE");
                        boiteDialogue.setMessage("AUCUN CLIENT N'A ADHERE POUR LE LOT DE TYPE "+lot_recherche+ " !" );
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
                    recyclerView = findViewById(R.id.recyclerViewForPointParTypeDeLot);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new exemplePointParTypeDeLotAdapter(getApplicationContext(), database.CarnetDao().getListeCarnets());
                    recyclerView.setAdapter(adapter);
                }
            }
        });


        quiter_point_par_type_lot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent versMenu=new Intent(getApplicationContext(), com.sodyam.philomabtontine.Vue.Menu.class);
                startActivity(versMenu);
                finish();
            }
        });
    }

    private void init()
    {
        this.date_actuelle=(TextView) findViewById(R.id.date_actuelle);
        this.zone_recherche_lot =(EditText) findViewById(R.id.zone_recherche_type_lot);
        this.nombre_total_lot=findViewById(R.id.nombre_total_type_lot);
        this.quiter_point_par_type_lot=findViewById(R.id.btn_quitter_client_par_type_lot);
        this.btn_actualiser_type_lot=(Button) findViewById(R.id.btn_actualiser_type_lot);
    }
}