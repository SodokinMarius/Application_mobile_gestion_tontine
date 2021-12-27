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

import java.sql.Date;

//import com.sodyam.philomabtontine.ContentViewModel;
//import com.sodyam.philomabtontine.Injection.Injection;
//import com.sodyam.philomabtontine.Injection.viewModelFactory;


public class point_general_activities extends AppCompatActivity {
    private EditText date_debut,date_fin;
    private TextView nombre_gobal_client,nombre_global_carnet_vendu,chiffre_global_carte,quote_part_global,
    benefices_global,montant_gobal_attendu,chiffre_global_activities,date_actuelle;
    private Button btn_executer_recherche_point_global,btn_quitter_recherche,btn_quiter_point_general;
    //private ContentViewModel mContentViewModel;
    private Databasephilomabtontine database;
    private  long date_deb,date_fini;

   // private Point_general general_point;

    //Transformation des variables datas en Type chh=
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_general_activities);
        init_point_global();



        /**
         * INITIALISATION DE LA BASE DE DONNEES
         */
        nombre_gobal_client=(TextView) findViewById(R.id.nombre_client_enregistre) ;
        date_actuelle=(TextView) findViewById(R.id.date_actuelle);
        database=Databasephilomabtontine.getInstance(getApplicationContext());
        nombre_gobal_client.setText(database.CarnetDao().getListeCarnets().size() + " clients");
        nombre_global_carnet_vendu.setText(database.CarnetDao().getNombre_General_Carnet_Vendu() + " carnets");
        chiffre_global_carte.setText(database.CarnetDao().getNombre_General_Carnet_Vendu() * 500 + " F CFA");
        quote_part_global.setText(" 0 F CFA");
        benefices_global.setText("0 F CFA");
        montant_gobal_attendu.setText("0 F CFA ");
        chiffre_global_activities.setText("0 F CFA");

        if(database.ClientDao().getListeClients().size()==0 || database.CarnetDao().getListeCarnets().size()==0
                || database.PaiementDao().getListePaiementsUnique().size()==0)
        {
            Toast.makeText(getApplicationContext(), "Aucun paiement n'est effectué !!", Toast.LENGTH_SHORT).show();
        }
        else {
            quote_part_global.setText(database.PaiementDao().getMontantGeneralDesCollectes() + " F CFA");
            benefices_global.setText(database.PaiementDao().getBeneficesGeneral() + " F CFA");
            Long benefice = database.PaiementDao().getBeneficesGeneral();
            Long montPaye = database.PaiementDao().getMontantGeneralDesCollectes();
            int montant_carnet = database.CarnetDao().getNombre_General_Carnet_Vendu() * 500;
            montant_gobal_attendu.setText(database.PaiementDao().getMontantGeneralAttendu()+" F CFA ");
            chiffre_global_activities.setText(montPaye + montant_carnet + " F CFA");
        }


        /**
         * ACTION APRES CLIC SUR LE BOUTON ACTUALISER RECHERCHE
         */

        btn_executer_recherche_point_global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (date_debut.getText().toString().trim().isEmpty() && date_fin.getText().toString().trim().isEmpty()
                        || date_fin.getText().toString().trim().isEmpty() || date_debut.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "VEUILLEZ REMPLIR LES CHAMPS DE RECHERCHE AVANT D'ACTUALISER !", Toast.LENGTH_SHORT).show();
                } else {

                    String date_start = date_debut.getText().toString();
                    String date_end = date_fin.getText().toString();
                    try{

                        Log.e(TAG, "DATE DEBUT EN LETTRE =========================" + date_start);
                        Log.e(TAG, "DATE FIN EN LETTRE =========================" + date_end);


                        if (date_debut.getText().toString().trim().length() >10 || date_fin.getText().toString().trim().length() > 10)
                        {
                            Toast.makeText(getApplicationContext(), "LE FORMAT DES DATES EST INCORRECT !", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            /**
                             * CONVERTION DE LA DATE EN TYPE LONG
                             */
                            date_deb = DateConverter.ConvertDateStringToIntegerAfterRemovingSpecialsCharacters(getApplicationContext(),
                                    DateConverter.deleteSpecialCharacters(getApplicationContext(), date_start));
                            date_fini = DateConverter.ConvertDateStringToIntegerAfterRemovingSpecialsCharacters(getApplicationContext(),
                                    DateConverter.deleteSpecialCharacters(getApplicationContext(), date_end));

                            if (date_fini < date_deb) {
                                Toast.makeText(getApplicationContext(), "DATES INCORRECTS ! VEUILLEZ LES REVOIR !", Toast.LENGTH_SHORT).show();
                            }
                            else {


                                Log.e(TAG, "DATE DEBUT EN CHIFFRE =========================" + date_deb);
                                Log.e(TAG, "DATE FIN EN CHIFFRE =========================" + date_fini);

                                quote_part_global.setText(database.PaiementDao().getMontantGeneralDesCollectesBetweenPeriod(date_deb, date_fini) + " F CFA");
                                benefices_global.setText(database.PaiementDao().getBeneficesGeneralBetweenPeriod(date_deb, date_fini) + " F CFA");
                                long benefice = database.PaiementDao().getBeneficesGeneralBetweenPeriod(date_deb, date_fini);
                                long montPaye = database.PaiementDao().getMontantGeneralDesCollectesBetweenPeriod(date_deb, date_fini);
                                int montant_carnet = database.CarnetDao().getNombre_General_Carnet_VenduBetweenPerod(date_deb, date_fini) * 500;
                                montant_gobal_attendu.setText(montPaye - benefice + " F CFA ");
                                chiffre_global_activities.setText(montPaye + montant_carnet + " F CFA");

                                nombre_gobal_client.setText(database.CarnetDao().getListeCarnetsBetweenPeriod(date_deb, date_fini).size() + " clients");
                                nombre_global_carnet_vendu.setText(database.CarnetDao().getNombre_General_Carnet_VenduBetweenPeriod(date_deb, date_fini) + " carnets");
                                chiffre_global_carte.setText((database.CarnetDao().getNombre_General_Carnet_VenduBetweenPeriod(date_deb, date_fini) * 500)+ " F CFA");
                            }
                        }
                    }
                    catch(Exception e){
                        Toast.makeText(getApplicationContext(), "VEUILLEZ RESPECTER LE FORMAT INDIQUE !!", Toast.LENGTH_SHORT).show();
                    }

                }

            }

        });

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
         * ACTION APRES AVOIR CLIQUER SUR LE BOUTON QUITTER POINT GENERALE
         */
        btn_quitter_recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VersMenu=new Intent(getApplicationContext(),Menu.class);
                startActivity(VersMenu);
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

    //Recuperation et Valorisation des variables
    private  void init_point_global()
    {
        this.btn_executer_recherche_point_global=(Button) findViewById(R.id.btn_actualiser_recherche);
        this.btn_quitter_recherche=(Button) findViewById(R.id.btn_quitter_point_general);
        this.date_debut=(EditText) findViewById(R.id.saisi_date_debut);
        this.date_fin=(EditText) findViewById(R.id.saisi_date_fin);
        this.nombre_gobal_client=(TextView) findViewById(R.id.nombre_client_enregistre);
        this.nombre_global_carnet_vendu=(TextView) findViewById(R.id.nombre_carnet_vendu);
        this.chiffre_global_carte= (TextView)findViewById(R.id.montant_carnet);
        this.quote_part_global=(TextView)findViewById(R.id.total_quote_part_general);
        this.benefices_global=(TextView)findViewById(R.id.benefices);
        this.montant_gobal_attendu=(TextView)findViewById(R.id.montant_attendu);
        this.chiffre_global_activities=(TextView)findViewById(R.id.chiffre_affaire_global);
    }

//    private void configureContentViewModel()
//    {
//        viewModelFactory mviewModelFactory= Injection.getViewModelFactory(this);
//        this.mContentViewModel= ViewModelProviders.of(this, (ViewModelProvider.Factory) mviewModelFactory)
//                .get(ContentViewModel.class);
//    }
}