package com.sodyam.philomabtontine.Vue;

import static android.content.ContentValues.TAG;

import static com.sodyam.philomabtontine.Vue.Authentification.current_agentConnexion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.Outils.DateConverter;
import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Carnet;
import com.sodyam.philomabtontine.model.T_Client;
import com.sodyam.philomabtontine.model.T_Client_Lot;
import com.sodyam.philomabtontine.model.T_Lot;
import com.sodyam.philomabtontine.utils.Functions;

import java.util.Date;

//import com.sodyam.philomabtontine.ContentViewModel;
//import com.sodyam.philomabtontine.Injection.Injection;
//import com.sodyam.philomabtontine.Injection.viewModelFactory;

public class nouveau_client extends AppCompatActivity {
    private Button bouton_enregister_nouveau_client,btn_quiter_nouveau_client,btn_actualiser_client;
    private TextView date_actuelle;
    private EditText numcarnet,nom_client,prenom_client,adresse_client,
                        profession_client,telephone,agent_enregistreur;

    private EditText montant_souscrit;
    private Integer num_carnet;
    private String mdate;
    private Date date_souscription=new Date();
    private T_Client nouveau_client;
    private nouveau_client Nouveau_client;
    private T_Lot nouveau_Lot;
    private T_Client_Lot nouveau_Client_lot;
    private T_Carnet  nouveau_carnet;
    //private ContentViewModel mContentViewModel;
    private Databasephilomabtontine database;
    private Spinner poste_enregistreur,sexe_client, type_lot;

    /**
     *  Declaration des variables
     */
    Integer numero_carnet;
    String nomClient,prenomClient,lieuNaissance,sexeClient,adresseClient,professionClient,tel;
    String dates;
    private Integer dateActuelle, date_paiement;
    private long newDAte;
    private boolean verifier;

    Integer montant_souscription;




    Date date_du_lot_existant=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_client);
       // configureContentViewModel();

        /**
         * CONVERTION DE LA DATE ACTUELLE
         * @param
         */
        java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
        String anne=date.toString().substring(0,4);
        String mois=date.toString().substring(5,7);
        String jour=date.toString().substring(8);
        dates=jour+"-"+mois+"-"+anne;
        mdate=anne+mois+jour;
        Log.e(TAG,"La date d'Ajout en string: -------------------" +date.toString());
        newDAte=DateConverter.ConvertDateStringToIntegerAfterRemovingSpecialsCharacters(getApplicationContext(),anne+mois+jour);//Pour envoyer dans le Carnet


        /**
         * AFFICHAGE DE LA DATE ACTUELLE
         */
        date_actuelle=(TextView) findViewById(R.id.date_actuelle_new_client);
        date_actuelle.setText(DateConverter.ConvertNumericNewDatetoAllLetter());


        Log.e(TAG,"La date d'Ajout en long: -------------------" +newDAte);
  database=Databasephilomabtontine.getInstance(getApplicationContext());
        /**
         * IMPLEMENTATION DU SPINNER
         */
//        poste_enregistreur = (Spinner) findViewById(R.id.agent_enregistreur);
        //this.sexe_client=(Spinner) findViewById(R.id.sexe_client);
        this.type_lot=(Spinner) findViewById(R.id.type_lot);


        /**
         * FOR TYPE LOT
         */

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterForTypeLot = ArrayAdapter.createFromResource(this,
                R.array.typeLot, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterForTypeLot.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        type_lot.setAdapter(adapterForTypeLot);


        init();
        this.bouton_enregister_nouveau_client=(Button)findViewById(R.id.btn_enregistre_client);
        this.btn_quiter_nouveau_client=findViewById(R.id.btn_quiter_nouveau_client);
        this.numcarnet= (EditText) findViewById(R.id.numCarnet);
        numcarnet.setText(database.CarnetDao().getIdDernierCarnet()+1+"");

        btn_actualiser_client=findViewById(R.id.btn_actualiser_client);
        btn_actualiser_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numcarnet.setText(database.CarnetDao().getIdDernierCarnet()+1+"");
                nom_client.setText("");
                prenom_client.setText("");
                telephone.setText("");
                montant_souscrit.setText("");
            }
        });


        /**
         * BOUTON CLIQUER QUITTER NOUVEAU CLIENT
         */
        btn_quiter_nouveau_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VersMenu=new Intent(getApplicationContext(),Menu.class);
                startActivity(VersMenu);
                finish();
            }
        });

        //Action après clic sur le boutton enregistrer
        bouton_enregister_nouveau_client.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
                if(numcarnet.getText().toString().trim().isEmpty() ||
               nom_client.getText().toString().trim().isEmpty() ||
               prenom_client.getText().toString().trim().isEmpty() ||
               telephone.getText().toString().trim().isEmpty() ||
               montant_souscrit.getText().toString().trim().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs svp !!", Toast.LENGTH_SHORT).show();
                }
                else {

                    /**
                     * INITIALISATION DES BOUTONS
                     */
                    num_carnet = Integer.parseInt(numcarnet.getText().toString());
                    nomClient = nom_client.getText().toString();
                    prenomClient = prenom_client.getText().toString();
                    tel = telephone.getText().toString();
                    montant_souscription = Integer.parseInt(montant_souscrit.getText().toString
                            ());

                    Log.e(TAG,"Liste des numeros carnets ------------------>"+database.CarnetDao().getNumerosDesCarnets().toString());
                    Log.e(TAG,"LES CARNETS DANS LA BASE :====================="+database.CarnetDao().getNumerosDesCarnets());
                    Log.e(TAG,"LES CARNETS DANS LA BASE :====================="+database.ClientDao().getLesMatriculesClients().toString());
                    Log.e(TAG,"LE CARNET SAISI :====================="+num_carnet+"");

                    if (database.CarnetDao().getNumerosDesCarnets().contains(num_carnet)) {
                        Toast.makeText(getApplicationContext(), "CE CARNET EST DEJA VENDU !!", Toast.LENGTH_SHORT).show();
                    }
                    else {
//                        Log.e(TAG, "Poste : ------------------------------" + poste_enregistreur.getSelectedItem().toString());
                        Log.e(TAG, "Lot : ------------------------------" + type_lot.getSelectedItem().toString());


//                    ConvertToString();
                        Databasephilomabtontine database = Databasephilomabtontine.getInstance(getApplicationContext());
                        Log.e(TAG, "Numero du Client : ----------------" + database.ClientDao().getDernierClientEnregistre());
                        int num_client = database.ClientDao().getDernierClientEnregistre() + 1;


                        AlertDialog.Builder maboite_confirmation = new AlertDialog.Builder(Nouveau_client);
                        maboite_confirmation.setTitle("CONFIRMATION D'ENREGISTREMENT");
                        maboite_confirmation.setMessage("Vous voulez ajouter le client : \n" + nomClient + " " + prenomClient +
                                "\n de Numero Carnet : " + num_carnet + "\n avec les informations ci après : " +
                                "\n Contact : " + tel + " \n " +
                                " \n Montant souscrit : " + montant_souscription + "\n" +
                                " Voulez vous continuer ? ");

                        maboite_confirmation.setPositiveButton("CONTINUER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                verifier=false;
                       try {
                           insertNewClient();
                           verifier=true;
                       }
                       catch (Exception e)
                       {
                           verifier=false;
                       }


                                num_carnet = Integer.parseInt(numcarnet.getText().toString());
                                Log.e(TAG, "LISTES DES CARNETS VENDU : ==============" + database.CarnetDao().getNumerosDesCarnets().toString());

                                Log.e(TAG, "NUMERO DU CARNET EN COURRS : ==============" + num_carnet.toString());

                                createNewcCarnet();

                                if (!CeLotExiste(montant_souscription) && verifier) {
                                    createNewLot();
                                }
                                CreateNewClientLot();

                                //Affichage d'un texte d'assurance
                                Toast.makeText(getApplicationContext(), "Informations  Bien enregistrées", Toast.LENGTH_SHORT).show();
                                /**========== RETOUR A LA LISTE DES CLIENTS ==========*/
                                Intent ClientActivity= new Intent(nouveau_client.this,liste_clients.class);
                                startActivity(ClientActivity);
                                finish();

                            }
                        });
                        maboite_confirmation.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Enregistrement Annulé", Toast.LENGTH_SHORT).show();
                            }
                        });
                        maboite_confirmation.show();
                    }

                }
                }

        });
}

    /**
     * CONFIGURATON DU CONTENT VIEWMODEL
     */
//    private void configureContentViewModel()
//    {
//        viewModelFactory mviewModelFactory=
//                Injection.getViewModelFactory(this);
//        this.mContentViewModel= ViewModelProviders.of(this, (ViewModelProvider.Factory) mviewModelFactory)
//                .get(ContentViewModel.class);
//    }

    /**
     * Initialisation et recuperation des variables
      */
    private void init()
    {
        this.date_actuelle=(TextView) findViewById(R.id.date_actuelle);
        this.numcarnet= (EditText) findViewById(R.id.numCarnet);
        this.nom_client=(EditText) findViewById(R.id.nom_client);
        this.prenom_client=(EditText) findViewById(R.id.prenom_client);
        this.telephone=(EditText) findViewById(R.id.telephone_client);
        this.montant_souscrit=(EditText) findViewById(R.id.souscription);
        this.Nouveau_client=this;
    }


    /**
     * INSERER UN NOUVEAU CLIENT
     */
    private boolean insertNewClient() {
         init();
        num_carnet=Integer.parseInt(numcarnet.getText().toString());
         database=Databasephilomabtontine.getInstance(getApplicationContext());
        T_Client client=new T_Client(num_carnet,nomClient,prenomClient , tel,dates,current_agentConnexion);
        database.ClientDao().insertClient(client);
        return true;
    }

    /**
     * CREER UN NOUVEAU CARNET
     */


    private void createNewcCarnet()
    {
        init();
        num_carnet=Integer.parseInt(numcarnet.getText().toString());
        montant_souscription=Integer.parseInt(montant_souscrit.getText().toString());
        Databasephilomabtontine database=Databasephilomabtontine.getInstance(this);
        Log.e(TAG,"Numero du Client : ----------------"+ database.ClientDao().getDernierClientEnregistre());
        Log.e(TAG,"Numero de tous la Carnets : ------------------------"+ database.CarnetDao().getNumerosDesCarnets().toString());
        Log.e(TAG,"Numero du Carnet inseré : --------------------------"+ num_carnet);
        Log.e(TAG,"Numero du Carnet inseré : --------------------------"+ date_paiement);
        int num_client=database.ClientDao().getListeClients().size();
            T_Carnet nouveau_carnet=new T_Carnet(num_carnet, num_carnet,montant_souscription,newDAte);
            database.CarnetDao().createCarnet(nouveau_carnet);

    }

    /**
     *CREER UN LOT
     */
    private void  createNewLot()
    {
        num_carnet=Integer.parseInt(numcarnet.getText().toString());
        montant_souscription=Integer.parseInt(montant_souscrit.getText().toString());
        Databasephilomabtontine database=Databasephilomabtontine.getInstance(getApplicationContext());
        T_Lot nouveau_lot=new T_Lot(montant_souscription,type_lot.getSelectedItem().toString(),dates,
              dates);
        database.LotDao().insertLot(nouveau_lot);
    }

    /**
     * CREER UN CLIENT_LOT
     */
    private  void  CreateNewClientLot()
    {
        num_carnet=Integer.parseInt(numcarnet.getText().toString());
        montant_souscription=Integer.parseInt(montant_souscrit.getText().toString());
        Databasephilomabtontine database=Databasephilomabtontine.getInstance(getApplicationContext());
        T_Client_Lot nouveau_Client_Lot=new T_Client_Lot(num_carnet,
                montant_souscription);

          database.Client_LotDao().insertCient_lot(nouveau_Client_Lot);
    }


    public Boolean EntreesEstCorrect()
    {

        Boolean correct=true;
        try {
            //Conversion du numero de Carnet et du montant lot  entré en type Entier
            numero_carnet=0;

            numero_carnet=Integer.parseInt(numcarnet.getText().toString());



            //Controle de l'existence ou non du numero de carnet
            Integer nombre_carnet=database.CarnetDao().getNombre_General_Carnet_Vendu();

                if(database.CarnetDao().getListeCarnets().contains(numero_carnet))
                {
                    correct=false;
                    Toast.makeText(nouveau_client.this, "Ce Carnet existe déjà ! ", Toast.LENGTH_SHORT).show();

                }
                if(montant_souscription%25!=0 || montant_souscription<25)
                {
                    correct=false;
                    Toast.makeText(nouveau_client.this, "Montant Invalide ! ", Toast.LENGTH_SHORT).show();
                }

            if(numero_carnet<=0)
            {
                correct=false;
                Toast.makeText(nouveau_client.this, "Veuillez saisir un numero correct", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception err){    }
        return  correct;

    }

    //Fonction qui verifie si les champs sont entrées
    public Boolean EstNonVide() {
        nomClient=nom_client.getText().toString();
        prenomClient= prenom_client.getText().toString();
        tel=telephone.getText().toString();
        return (
                    Functions.checkIsEmpty(this,numcarnet) &&
                    Functions.checkIsEmpty(this,nom_client) &&
                    Functions.checkIsEmpty(this,prenom_client) &&
                    Functions.checkIsEmpty(this,telephone) &&
                    Functions.checkIsEmpty(this,montant_souscrit));


    }
    /**
     * Avant de faire l'insertion du lot dans la BD, verifions si ce dernier n'existe pas encore.
     * Cas échéant, envoyons lui la date de son premier enregistrement
     */
//
    public Boolean CeLotExiste(Integer montant_verifier)
    {
        Boolean lot_Existe=false;

        try {
            if(database.LotDao().getListesMontantExistants().contains(montant_verifier))
            {
                lot_Existe=true;
            }

        }catch (Exception error){    }
        return lot_Existe;
    }

}