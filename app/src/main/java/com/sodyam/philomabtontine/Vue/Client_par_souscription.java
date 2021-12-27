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

import java.sql.Date;

public class Client_par_souscription extends AppCompatActivity {
    private Button quiter_client_par_souscription,btn_actualiser_client_par_souscription;
    private EditText zone_recherche_souscription;
    private int souscription_recherche;
    private TextView nombre_total_souscription,montant_dernier_paiement,date_dernier_paiement,nom_cli,prenom_cli,
        telephone_cli,montant_paye,reste_paye,valeur_souscript,date_actuelle;
    private Databasephilomabtontine database;
    private RecyclerView recyclerView;
    private int montant_recherche;
    private  ClientParSouscriptionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_par_souscription);
        this.zone_recherche_souscription=findViewById(R.id.zone_recherche_lot);
//        souscription_recherche=zone_recherche_souscription.getInputType();
        this.nombre_total_souscription=findViewById(R.id.nombre_total_souscription);
        this.btn_actualiser_client_par_souscription=(Button) findViewById(R.id.btn_actualiser_client_par_souscription);
        this.date_actuelle=(TextView) findViewById(R.id.date_actuelle);
        /**
         * CONVERSION DE LA DATE
         */

        Date date=new Date(System.currentTimeMillis());
        String anne=date.toString().substring(0,4);
        String mois=date.toString().substring(5,7);
        String jour=date.toString().substring(8);
        String dates=jour+" "+mois+" "+anne;
        date_actuelle.setText(DateConverter.ConvertNumericNewDatetoAllLetter());

        /**
         * INITIALISONS LA BASE DE DONNEES
         */
     database=Databasephilomabtontine.getInstance(getApplicationContext());

        /**
         * AFFICHAGE DES INFORMATIONS DE LA BASE DE DONNEES
         */
        Log.e(TAG,"CLIENT PAR SOUSCRIPTION : ................................."+database.PaiementDao().getClientBySouscription().size());

            recyclerView = findViewById(R.id.Unclient_souscription);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ClientParSouscriptionAdapter(getApplicationContext(), database.CarnetDao().getListeOrdonneCarnets());
            recyclerView.setAdapter(adapter);
            Log.e(TAG,"CLIENT PAR SOUSCRIPTION : ................................."+database.PaiementDao().getClientBySouscription().size());


        nombre_total_souscription.setText("Total : " +database.LotDao().getListesLots().size());


        /**
         * ACTION APRES CLIC SUR CLIENT PAR SOUSCRIPTION
         */
         btn_actualiser_client_par_souscription.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(!zone_recherche_souscription.getText().toString().trim().isEmpty())
                 {
                     montant_recherche=Integer.parseInt(zone_recherche_souscription.getText().toString());
                     if(database.LotDao().getListesMontantExistants().contains(montant_recherche))
                     {
//                recyclerView = findViewById(R.id.UneSouscription);
                         recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                         adapter = new ClientParSouscriptionAdapter(getApplicationContext(), database.CarnetDao().getListeCarnetByLot (montant_recherche));
                         recyclerView.setAdapter(adapter);
                     }
                     else
                     {
                         AlertDialog.Builder boiteDialogue=new AlertDialog.Builder(Client_par_souscription.this);
                         boiteDialogue.setTitle("RESULTAT DE RECHERCHE");
                         boiteDialogue.setMessage("LE MONTANT  "+montant_recherche + " F CFA "+
                                            "\n   N'EST PAS ENCORE MIS !!! ");
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
                     adapter = new ClientParSouscriptionAdapter(getApplicationContext(), database.CarnetDao().getListeOrdonneCarnets());
                     recyclerView.setAdapter(adapter);
                 }
             }
         });


        this.quiter_client_par_souscription=findViewById(R.id.btn_quiter_client_par_souscription);

       quiter_client_par_souscription.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent VersAjout_nouveau_Agent = new Intent(getApplicationContext(), com.sodyam.philomabtontine.Vue.Menu.class);
               startActivity(VersAjout_nouveau_Agent);
               finish();
           }
       });
    }
}
