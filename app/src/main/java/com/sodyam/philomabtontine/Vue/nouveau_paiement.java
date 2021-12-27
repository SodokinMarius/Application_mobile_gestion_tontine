package com.sodyam.philomabtontine.Vue;

import static android.content.ContentValues.TAG;
import static com.sodyam.philomabtontine.Vue.Authentification.current_agentConnexion;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.Outils.DateConverter;
import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Client;
import com.sodyam.philomabtontine.model.T_Paiement;
import com.sodyam.philomabtontine.utils.Functions;

import java.util.Date;

//import com.sodyam.philomabtontine.ContentViewModel;
//import com.sodyam.philomabtontine.Injection.Injection;
//import com.sodyam.philomabtontine.Injection.viewModelFactory;


public class nouveau_paiement extends AppCompatActivity {
    private Button bouton_actualiser_paiement,bouton_enregister_paiement,btn_quiter_paiement;
    private TextView date_actuelle;

    private EditText numcarnet,quote_part;
    private  Spinner poste_agentE;
    private  TextView nom_client,prenom_client,telephone,montant_payer,reste_payer,montant_souscrit;
    private  T_Paiement nouveau_paiement;
    private T_Client Client_Conserne;
    //private ContentViewModel mContentViewModel;
    private T_Client mInfosPaiementsDuClient;
     private  long date_paiement;
     private Databasephilomabtontine database;
         Date Date_actu=new Date();
    Integer num_carnet;
    Integer montant;
    private String dates;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_paiement);
        init();

         this.bouton_actualiser_paiement=(Button) findViewById(R.id.btn_actualiser_paiement);
         this.bouton_enregister_paiement=(Button) findViewById(R.id.btn_enregistre_paiement);
         this.btn_quiter_paiement=(Button) findViewById(R.id.btn_quiter_paiement);
         this.quote_part=(EditText) findViewById(R.id.quote_part);

         /**
          * IMPLEMENTATION DU SPINNER
          */
//         poste = (Spinner) findViewById(R.id.agent_enregistreur);
//         // Create an ArrayAdapter using the string array and a default spinner layout
//         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                 R.array.postes, android.R.layout.simple_spinner_item);
//         // Specify the layout to use when the list of choices appears
//         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//         // Apply the adapter to the spinner
//         poste.setAdapter(adapter);
////        poste_occupe=spinner;



         /**
          * CONVERTION DE LA DATE ACTUELLE
          * @param
          */

         java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
         String anne=date.toString().substring(0,4);
         String mois=date.toString().substring(5,7);
         String jour=date.toString().substring(8);
         dates=jour+"-"+mois+"-"+anne;


         /**
          * CONVERTION DE LA DATE EN TYPE LONG
          */

//         date_actuelle.setText("Date de : "+dates);
         date_actuelle.setText(DateConverter.ConvertNumericNewDatetoAllLetter());
//         date_paiement=Integer.parseInt(anne)+Integer.parseInt(mois)+Integer.parseInt(jour);
         date_paiement=DateConverter.ConvertDateStringToIntegerAfterRemovingSpecialsCharacters(getApplicationContext(),anne+mois+jour);
         Log.e(TAG,"Date en chiffre : ---------------------------------"+date_paiement);
         Log.e(TAG,"Date en paiement : ---------------------------------"+Integer.parseInt(jour));
         Log.e(TAG,"Mois : ---------------------------------"+Integer.parseInt(mois));
         Log.e(TAG,"Annee : ---------------------------------"+Integer.parseInt(anne));
         Log.e(TAG,"Date en lettres : ---------------------------------"+dates);
//         Log.e(TAG,"Date en lettres : ---------------------------------"+Long.toString(date_paiement));

         /**
          * INITIALISATION DE LA BASE DE DONNEES
          */
         database=Databasephilomabtontine.getInstance(getApplicationContext());
         /**

          *
          * ============== ACTION APRES CLIC SUR LE BOUTON ACTUALISER
          */

   bouton_actualiser_paiement.setOnClickListener(new Button.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.O)
             @SuppressLint("WrongViewCast")
             @Override
             public void onClick(View view) {
                 init();

                 //configureContentViewModel();
                 if(numcarnet.getText().toString().trim().isEmpty())
                 {
                     Toast.makeText(com.sodyam.philomabtontine.Vue.nouveau_paiement.this,"Aucune données à actualiser !", Toast.LENGTH_SHORT).show();
                 }
                 else
                 {
                     num_carnet=Integer.parseInt(numcarnet.getText().toString());
                     Log.e(TAG,"NUMERO DU CLIENT RECHERCHE : -------------------------------"+ num_carnet);
                     Log.e(TAG,"LISTE DES PAIEMENTS : -------------------------------"+ database.PaiementDao().getListePaiementsUnique());

                     if(database.CarnetDao().getNumerosDesCarnets().contains(num_carnet))
                     {
                         nom_client.setText(database.ClientDao().getClientByNumCarnet(num_carnet).getNom());
                         montant_souscrit.setText(database.CarnetDao().getCarnetByNumCarnet(num_carnet).getIdLot()+" F CFA");
                         prenom_client.setText(database.ClientDao().getClientByNumCarnet(num_carnet).getPrenom());
                         telephone.setText(database.ClientDao().getClientByNumCarnet(num_carnet).getTelephone());
                         Log.e(TAG,"CARNET EN COURS DE PA======================="+num_carnet);
                         Log.e(TAG,"MONTANT======================="+database.PaiementDao().getMontantPayeByNumCarnet(num_carnet));
                         Log.e(TAG,"CARNET======================="+database.CarnetDao().getCarnetByNumCarnet(num_carnet).toString());

                         montant_payer.setText(database.PaiementDao().getMontantPayeByNumCarnet(num_carnet)+" F CFA");

                         reste_payer.setText((database.CarnetDao().getCarnetByNumCarnet(num_carnet).getIdLot()*372-database.PaiementDao().getMontantPayeByNumCarnet(num_carnet))+" F CFA");

                         if(quote_part.getText().toString().isEmpty())
                         {
                             montant=0;
                         }
                         else
                         {
                             montant=Integer.parseInt(quote_part.getText().toString());

                         }

                     }
                     else
                     {
                         montant_payer.setText(" ");
                         nom_client.setText(" ");
                         prenom_client.setText(" ");
                         telephone.setText(" ");

                         montant_payer.setText(" ");
                         reste_payer.setText( " ");
                         montant_souscrit.setText(" ");

                         Toast.makeText(com.sodyam.philomabtontine.Vue.nouveau_paiement.this,"CE CARNET N'EXISTE PAS ENCORE !!", Toast.LENGTH_LONG).show();
                     }
                 }


             }
         });

         /**
          * ============== ACTION APRES CLIC SUR LE BOUTON QUITTER
          */
         btn_quiter_paiement.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent VersMenu=new Intent(getApplicationContext(),Menu.class);
                 startActivity(VersMenu);
                 finish();

             }
         });

         /**
          * ======== ACTION APRES CLIC SUR LE BOUTON ENREGISTRER ======
          */
         bouton_enregister_paiement.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  init();
                  if (quote_part.getText().toString().trim().isEmpty()  ||  numcarnet.getText().toString().trim().isEmpty()) {
                      if(quote_part.getText().toString().trim().isEmpty())
                      {
                          Toast.makeText(getApplicationContext(), "Veuillez renseigner le montant à payer !!!", Toast.LENGTH_SHORT).show();
                      }
                      else if(numcarnet.getText().toString().trim().isEmpty())
                      {
                          Toast.makeText(getApplicationContext(), "Saisir le numéro du Carnet svp !!!", Toast.LENGTH_SHORT).show();
                      }
                      else if(quote_part.getText().toString().trim().isEmpty()  && numcarnet.getText().toString().trim().isEmpty())
                      {
                          Toast.makeText(getApplicationContext(), "Vous n'avez pas renseigner les champs !!!", Toast.LENGTH_SHORT).show();
                      }

                  }

                  else
                  {
                      AlertDialog.Builder boite_confirmation = new AlertDialog.Builder(com.sodyam.philomabtontine.Vue.nouveau_paiement.this);
                      boite_confirmation.setTitle("CONFIRMATION DE PAIEMENT");
                      boite_confirmation.setMessage("Vous voulez enregistrer un paiement de " + quote_part.getText().toString() +
                              "F CFA  dans  le Carnet N° " + numcarnet.getText().toString() + ", Voulez vous continuer ? ");

                      boite_confirmation.setPositiveButton("CONTINUER", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                              /**
                               * Insertion du paiement dans la Base de données:Table Paiement
                               */
                              Log.e(TAG,"Le nombre de paiement ------------------: "+ database.PaiementDao().getListePaiementsUnique().size());

                              if(CartnetExiste(num_carnet))
                              {
                                  SaveNewPaiement();
                                  Log.e(TAG,"Le nombre de paiement --------------------: "+ database.PaiementDao().getListePaiementsUnique().size());
                                  Log.e(TAG,"INFOS BIEN ENREGISTREES");

                                  Toast.makeText(com.sodyam.philomabtontine.Vue.nouveau_paiement.this, "Paiement Bien Enregistré !", Toast.LENGTH_SHORT).show();
                                  Intent gestion_paiement=new Intent(nouveau_paiement.this,liste_paiements.class);
                                  startActivity(gestion_paiement);
                                  finish();
                              }
                              else
                              {
                                  Toast.makeText(com.sodyam.philomabtontine.Vue.nouveau_paiement.this,"Ce Carnet n'existe pas !!", Toast.LENGTH_SHORT).show();
                              }
                          }
                      });

                      boite_confirmation.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                              Toast.makeText(com.sodyam.philomabtontine.Vue.nouveau_paiement.this, "Paiement Annulé ! ", Toast.LENGTH_SHORT).show();
                          }
                      });
                      boite_confirmation.show();
                  }
              }
          });
    }
    /**
     * CREER UN CLIENT_LOT
     */
   /* private  void  CreateNewClientLot()
    {
        T_Client_Lot nouveau_Client_Lot=new T_Client_Lot(mContentViewModel.getDernierClientEnregistre()+1,
                mContentViewModel.getLastClientLotCreated()+1);
        mContentViewModel.CreateNewClientlot(nouveau_Client_Lot);
    }*/


    /**
     * ENREGISTREMENT D'UN NOUVEAU PAIEMENT
     */

        public void  SaveNewPaiement()
        {
            init();
            T_Paiement newPaiement=new T_Paiement(num_carnet,current_agentConnexion,montant,dates,date_paiement);
            database.PaiementDao().insertPaiement(newPaiement);
        }

    /**
     *Recuperation et valorisation des variables
     */

    @SuppressLint("WrongViewCast")
    private void init()
    {
        this.date_actuelle=(TextView) findViewById(R.id.date_actuelle);
        this.numcarnet=(EditText) findViewById(R.id.numCarnet);
        this.nom_client=(TextView)findViewById(R.id.nom_client);
        this.prenom_client=(TextView)findViewById(R.id.prenom_client);
        this.telephone=(TextView)findViewById(R.id.telephone_client);
        this.montant_payer= (TextView) findViewById(R.id.montant_paye);
        this.reste_payer=(TextView)findViewById(R.id.reste_paye);
        this.montant_souscrit=(TextView) findViewById(R.id.montant_souscrit);

//        if(numcarnet.getText().toString().trim().isEmpty())
////        (Functions.checkIsEmpty(this,numcarnet) && Functions.checkIsEmpty(this,quote_part))
//        {
//            Toast.makeText(nouveau_paiement.this, " VEUILLEZ SAISIR LE NUMERO DU CARNET ! ", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//
//
//        }
       try
        {
            num_carnet=Integer.parseInt(numcarnet.getText().toString());
            montant=Integer.parseInt(quote_part.getText().toString());
        }
       catch (Exception err)
       {

       }



    }
    /**
     * Verification si les entrées sont correcte
     * @return
     */
    public boolean ValeursEntreesCorrects()
    {
        Boolean EntresCorrect=true;
        try {
            if(!CartnetExiste(this.num_carnet))
            {
                EntresCorrect=false;
                Toast.makeText(com.sodyam.philomabtontine.Vue.nouveau_paiement.this, "Ce Carnet n'existe pas ! ", Toast.LENGTH_SHORT).show();
            }
            if(montant%25!=0 || montant<25)
            {
                EntresCorrect=false;
                Toast.makeText(com.sodyam.philomabtontine.Vue.nouveau_paiement.this, "Montant Invalide ! ", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception error){ }
        return  EntresCorrect;
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


    /**
     * VERIFICATION DE L'EXISTENCE DU NUMERO DU CARNET
     */
    private Boolean CartnetExiste(Integer num_carnet) {
        Boolean carnetExiste = true;
        try {
            if(!database.CarnetDao().getNumerosDesCarnets().contains(num_carnet))
            {
                carnetExiste=false;
            }

        } catch (Exception err) {
        }
        return carnetExiste;
    }
    /**
     * Methode Verifiant si tout les Champs sont renseignés
     * @return
     */

    public boolean InfosPaiementEstVide()
    {

        return (Functions.checkIsEmpty(this,numcarnet) && Functions.checkIsEmpty(this,quote_part));

        }

}