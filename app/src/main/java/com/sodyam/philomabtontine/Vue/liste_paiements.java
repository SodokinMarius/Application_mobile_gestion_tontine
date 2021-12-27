package com.sodyam.philomabtontine.Vue;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.Outils.DateConverter;
import com.sodyam.philomabtontine.R;

import java.sql.Date;

public class liste_paiements extends AppCompatActivity {


    private Button bouton_ajout_nouveau_paiement,btn_actualier_liste_paiement;
    private TextView date_actuelle;
    private  TextView montant_total_encaisse,agent_operateur,numcarnet,nom_cli,prenom_cli,reste_a_payer,
     montant_dernier_paye,date_dernier_paye;

    private RecyclerView recyclerView;
    private exemplelistepayementAdapter adapter;
    private EditText numero;
    private  int numCarnet;

    private Databasephilomabtontine database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_paiements);


        /**
         * CONVERSION DE LA DATE
         */
            this.date_actuelle=(TextView) findViewById(R.id.date_actuelle);
        Date date=new Date(System.currentTimeMillis());
        String anne=date.toString().substring(0,4);
        String mois=date.toString().substring(5,7);
        String jour=date.toString().substring(8);
        String dates=jour+" "+mois+" "+anne;
        date_actuelle.setText(DateConverter.ConvertNumericNewDatetoAllLetter());

        /**
         * INITIALISATION DU RECYCLERVIEW8 DE LA LISTE DES PAIEMENTS
         */
        database= Databasephilomabtontine.getInstance(this.getApplicationContext());
        Log.e(TAG,"Le Nombre total de paiement ......................: ");
        if(database.PaiementDao().getListePaiementsUnique().size()==0)
        {
            Log.e(TAG,"LA liste est vide -----------------------------------");
        }
//        else
//        {
//            for (Liste_paiements liste:database.PaiementDao().getListePaiementsUnique()){
                recyclerView = findViewById(R.id.recyclerViewForPayement);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new exemplelistepayementAdapter(getApplicationContext(), database.PaiementDao().getListePaiementsUnique());
                recyclerView.setAdapter(adapter);
            //}
        //}


        init();
        this.montant_total_encaisse=(TextView) findViewById(R.id.montant_total_encaisse);
        montant_total_encaisse.setText(database.PaiementDao().getMontantGeneralDesCollectes()+" F CFA");
        this.bouton_ajout_nouveau_paiement=(Button) findViewById(R.id.btn_ajout_nouveau_paiement);
        this.btn_actualier_liste_paiement=findViewById(R.id.btn_actualiser_recherche);
        //Action de redirection vers le fomulaire de nouveau paiment
        bouton_ajout_nouveau_paiement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VersAjout_paiement=new Intent(liste_paiements.this, com.sodyam.philomabtontine.Vue.nouveau_paiement.class);
                startActivity(VersAjout_paiement);
//                finish();

            }
        });
    this.numero=(EditText)findViewById(R.id.zone_recherche_paiement);


        btn_actualier_liste_paiement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    numCarnet=Integer.parseInt(numero.getText().toString());
                    recyclerView = findViewById(R.id.recyclerViewForPayement);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new exemplelistepayementAdapter(getApplicationContext(), database.PaiementDao().getListePaiementsByNumCarnet(numCarnet));
                    recyclerView.setAdapter(adapter);
                    //}
                }
               catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "VERIFIEZ BIEN LE NUMERO SAISI", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void init()
    {
        this.date_actuelle=(TextView) findViewById(R.id.date_actuelle);
        this.montant_total_encaisse=(TextView) findViewById(R.id.montant_total_encaisse);
        this.agent_operateur=(TextView)findViewById(R.id.agent_enregistreur);
        this.numcarnet=(TextView)findViewById(R.id.numCarnet);
        this.nom_cli=(TextView)findViewById(R.id.nom_client);
        this.prenom_cli=(TextView)findViewById(R.id.prenom_client);
        this.reste_a_payer=(TextView)findViewById(R.id.reste_paye);

    }
}