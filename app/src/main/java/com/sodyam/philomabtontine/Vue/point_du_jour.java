package com.sodyam.philomabtontine.Vue;

import static com.sodyam.philomabtontine.utils.AllConstants.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.Outils.DateConverter;
import com.sodyam.philomabtontine.R;


//import com.sodyam.philomabtontine.ContentViewModel;
//import com.sodyam.philomabtontine.Injection.Injection;
//import com.sodyam.philomabtontine.Injection.viewModelFactory;

public class point_du_jour<date_paiement> extends AppCompatActivity {
    private TextView date_actuelle;
    private TextView nombre_carnet_vendu,montant_carnet_jour,montant_total_collecte,chiffre_affaire_jour;
    private Button bouton_quiter_point_jour,  btn_actualiser_point_jour;
    private EditText zone_recherche_point_jour;
    //private ContentViewModel mContentViewModel;
    private  long date_paiement,date_recherche;
    private Databasephilomabtontine database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_du_jour);
        init_poin_jour();

        java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
        String anne=date.toString().substring(0,4);
        String mois=date.toString().substring(5,7);
        String jour=date.toString().substring(8);

        String dates=jour+"-"+mois+"-"+anne;
        /**
         * DATE ACTUELLE
         */
        this.date_actuelle=(TextView) findViewById(R.id.date_actuelle_point_jour);
        this.zone_recherche_point_jour=(EditText) findViewById(R.id.zone_recherche_point_jour) ;
        this. btn_actualiser_point_jour=(Button) findViewById(R.id. btn_actualiser_point_jour) ;
        date_actuelle.setText(DateConverter.ConvertNumericNewDatetoAllLetter());

        date_paiement=DateConverter.ConvertDateStringToIntegerAfterRemovingSpecialsCharacters(getApplicationContext(),
                DateConverter.deleteSpecialCharacters(getApplicationContext(), date.toString()));

        PointDuJourParDate(date_paiement);



   btn_actualiser_point_jour.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           if(zone_recherche_point_jour.getText().toString().trim().isEmpty())
           {
               Toast.makeText(getApplicationContext(), "VEUILLEZ ENTRER UNE DATE", Toast.LENGTH_LONG).show();
           }
           else
           {
               try{

               date_recherche=DateConverter.ConvertDateStringToIntegerAfterRemovingSpecialsCharacters(getApplicationContext(),
                       DateConverter.deleteSpecialCharacters( getApplicationContext(),zone_recherche_point_jour.getText().toString()));
                               //DateConverter.deleteSpecialCharacters(getApplicationContext(),));
               Log.e(TAG,"Date recherche =========================================="+date_recherche);
               PointDuJourParDate(date_recherche);
             }
               catch(Exception e){

                    Toast.makeText(getApplicationContext(),"Veuillez entrer une date correcte",Toast.LENGTH_LONG).show();
               }

           }

       }
   });


        /**
         * AFFICHAGE DES INFORMATIONS RECUPREES DE LA BD
         */

        bouton_quiter_point_jour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VersMenuPrincipal=new Intent(getApplicationContext(),Menu.class);
                startActivity(VersMenuPrincipal);
                finish();
            }
        });
    }
//    private void configureContentViewModel()
//    {
//        viewModelFactory mviewModelFactory= Injection.getViewModelFactory(this);
//        this.mContentViewModel= ViewModelProviders.of(this, (ViewModelProvider.Factory) mviewModelFactory)
//                .get(ContentViewModel.class);
//    }
    //Recuperation et valorisation des variables
    private void init_poin_jour()
    {
        this.bouton_quiter_point_jour=(Button) findViewById(R.id.btn_quitter_point_jour);
        this.nombre_carnet_vendu=(TextView)findViewById(R.id.nombre_carnet_vendu);
        this.montant_carnet_jour=(TextView)findViewById(R.id.montant_carnet);
        this.montant_total_collecte=(TextView)findViewById(R.id.total_quote_part_jour);
        this.chiffre_affaire_jour=(TextView) findViewById(R.id.chiffre_jour);
    }

    private void  PointDuJourParDate(long  date)
    {

        database=Databasephilomabtontine.getInstance(getApplicationContext());

//        Log.e(TAG,"Le chiffre du Jour : "+ date.getDay());

        /**
         * AFFICHAGE DU NOMBRE DE CARNET ET LE MONTANT CARNET
         */
        nombre_carnet_vendu.setText(database.PaiementDao().getNombreCarnet_Vendu_By_Jour(date).size()+"");
        montant_carnet_jour.setText(database.PaiementDao().getNombreCarnet_Vendu_By_Jour(date).size()* 500 +" F CFA");
        montant_total_collecte.setText("0 F CFA");
        chiffre_affaire_jour.setText("0 F CFA");

        if(database.ClientDao().getListeClients().size()==0 || database.CarnetDao().getListeCarnets().size()==0
                || database.PaiementDao().getListePaiementsUnique().size()==0)
        {
            Toast.makeText(getApplicationContext(), "Aucun paiement n'est effectué !!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Log.e(TAG,"Nombre de Carnet : --------------------------"+database.PaiementDao().getNombreCarnet_Vendu_By_Jour(date).size()+"");
            Log.e(TAG,"Montant carnet Jour : --------------------------"+database.PaiementDao().getNombreCarnet_Vendu_By_Jour(date).size()*500);
            Log.e(TAG,"Montant collecte : --------------------------"+database.PaiementDao().getSommeMontantPayeDernierJour(date)+" F CFA");
            // Log.e(TAG,"Chiffre  : --------------------------"+database.PaiementDao().getPointDuJour().getChiffre_Affaire_jour()+"");

            Log.e(TAG,"date Actuelle  : --------------------------"+date+"");
            Log.e(TAG,"date Actuelle  : --------------------------"+date+"");
            //Log.e(TAG,"date_paiement  : --------------------------"+database.PaiementDao().getListePaiementsUnique().get(0).getDate_paiement()+"");
            montant_total_collecte.setText(database.PaiementDao().getMontantDernierJour(date)+" F CFA ");

            long montantCollete=database.PaiementDao().getMontantDernierJour(date);
            Log.e(TAG,"Dernier paiement : ========================="+database.PaiementDao().getMontantDernierJour(date));
            long montant_carnet=database.PaiementDao().getNombreCarnet_Vendu_By_Jour(date).size()* 500;

            Log.e(TAG,"Montant payer========================="+ montantCollete);
            Log.e(TAG,"montant carnet========================="+ montant_carnet);
            long chiffre=montantCollete+montant_carnet;
            chiffre_affaire_jour.setText(chiffre+" F CFA");

        }
    }


}

