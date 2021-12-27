package com.sodyam.philomabtontine.Vue;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import java.sql.Date;

public class point_par_client extends AppCompatActivity {

    private Button quiter_point_par_client, actualiser_point_par_client;
    private EditText zone_recherche_client;
    private TextView nombre_total_clients,montant_dernier_paiement,date_dernier_paiement,nom_cli,prenom_cli,
            lot_choisi,montant_paye,reste_paye,valeur_souscript,date_actuelle;
    private Databasephilomabtontine database;
    private RecyclerView recyclerView;
    private  pointParClientAdapter adapter;
    int num_carnet_recherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_par_client);
        init();
        database=Databasephilomabtontine.getInstance(this.getApplicationContext());

        /**
         * AFFICHAGE DES INFORMATIONS DE LA BASE DE DONNEES
         */

        recyclerView = findViewById(R.id.UnclientPoint);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new pointParClientAdapter(getApplicationContext(), database.CarnetDao().getListeCarnets());
        recyclerView.setAdapter(adapter);


        /**
         * ACTION APRES CLIC SUR LE BOUTON RECHERCHER CLIENT
         */
           actualiser_point_par_client.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   if(!zone_recherche_client.getText().toString().trim().isEmpty())
                   {
                       num_carnet_recherche=Integer.parseInt(zone_recherche_client.getText().toString());
                       if(database.CarnetDao().getNumerosDesCarnets().contains(num_carnet_recherche))
                       {
                           recyclerView = findViewById(R.id.UnclientPoint);
                           recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                           adapter = new pointParClientAdapter(getApplicationContext(), database.CarnetDao().getListeCarnetByNumCarnet(num_carnet_recherche));
                           recyclerView.setAdapter(adapter);
                       }
                       else
                       {
                           AlertDialog.Builder boiteDialogue=new AlertDialog.Builder(point_par_client.this);
                           boiteDialogue.setTitle("RESULTAT DE RECHERCHE");
                           boiteDialogue.setMessage("CE CLIENT DE NUMERO "+num_carnet_recherche+
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
                       recyclerView = findViewById(R.id.UnclientPoint);
                       recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                       adapter = new pointParClientAdapter(getApplicationContext(), database.CarnetDao().getListeCarnets());
                       recyclerView.setAdapter(adapter);
                   }
               }
           });


//        }

        /**
         * CONVERSION DE LA DATE
         */

        Date date=new Date(System.currentTimeMillis());
        String anne=date.toString().substring(0,4);
        String mois=date.toString().substring(5,7);
        String jour=date.toString().substring(8);
        String dates=jour+" "+mois+" "+anne;
        date_actuelle.setText(DateConverter.ConvertNumericNewDatetoAllLetter());
        nombre_total_clients.setText(database.CarnetDao().getListeCarnets().size()+" clients");

        /**
         * ACTION APRES CLIC SUR LE BOUTON QUITTER POINT PAR CLIENT
         */
        quiter_point_par_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent versMenu=new Intent(getApplicationContext(),Menu.class);
                startActivity(versMenu);
                finish();
            }
        });

    }

    private void init()
    {
        this.date_actuelle=findViewById(R.id.date_actuelle);
        //this.montant_dernier_paiement= (TextView) findViewById(R.id.mont_dernier_paye);
        this.date_dernier_paiement=(TextView) findViewById(R.id.date_dernier_paye);
        this.nom_cli=(TextView) findViewById(R.id.nom_client);
        this.prenom_cli=(TextView) findViewById(R.id.prenom_client);
        this.actualiser_point_par_client=(Button)findViewById(R.id.btn_actualiser_point_par_client);
        this.zone_recherche_client=(EditText) findViewById(R.id.zone_recherche_client);
        this.nombre_total_clients=(TextView) findViewById(R.id.nombre_total_client);

        this.quiter_point_par_client=(Button) findViewById(R.id.btn_quiter_point_par_client);
    }
}