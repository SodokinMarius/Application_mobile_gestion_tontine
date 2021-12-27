package com.sodyam.philomabtontine.Vue;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Agent;
import com.sodyam.philomabtontine.utils.AllConstants;
import com.sodyam.philomabtontine.utils.Functions;

import java.util.ArrayList;

public class Authentification extends AppCompatActivity {

    private Button valider_connexion;
    private EditText mot_de_passe;
    public static String current_agentConnexion;


    /**
     * IMPLEMENTATION DU SPINNER
     */
    public Spinner poste_occupe ;
    private String mot_de_passe_C,poste_occ_C;
    private T_Agent Current_Agent;
    //private ContentViewModel mContentViewModel;
    private Databasephilomabtontine database;


  TextInputLayout choiscePostes;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        checkPermissionStorage();
        init();

        poste_occupe = (Spinner) findViewById(R.id.poste_entre);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.postes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
       poste_occupe.setAdapter(adapter);
  database=Databasephilomabtontine.getInstance(getApplicationContext());

//        poste_occupe=spinner;
//
            valider_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //poste_occ_C = poste_occupe.getText().toString();
                mot_de_passe_C = mot_de_passe.getText().toString();
                poste_occupe.getSelectedItem().toString();
                Log.e(TAG,"Poste de l'Agent Courrent :"+poste_occupe.getSelectedItem().toString());
                if (InfosEstNonVide()) {
                    current_agentConnexion=poste_occupe.getSelectedItem().toString();

                    //Log.d(AllConstants.TAG,"poste : "+poste_occ_C);
                    Functions.initDatabase(Authentification.this);

                    Log.e(AllConstants.TAG, "fjfjf");

                   if (database.AgentDao().getPostesAgents().contains(poste_occupe.getSelectedItem().toString())) {
                       //Log.e(TAG,"AGENT EN CONNEXION : ----------------------"+Current_Agent.toString());
                       Current_Agent = database.AgentDao().getAgentByPoste(poste_occupe.getSelectedItem().toString());

                       Log.e(TAG,"Agent ========================================================="+Current_Agent.getPoste());
                       Log.e(TAG,"MOT DE ¨PASSE BASE AV ========================================================="+Current_Agent.getMot_de_passe());
                       Log.e(TAG,"MOT DE PASSE SAISI  AV========================================================="+mot_de_passe.getText().toString());

                       if (!Current_Agent.getMot_de_passe().equals(mot_de_passe.getText().toString())) {
                           Toast.makeText(Authentification.this, "Mot de Passe Incorrect ! ", Toast.LENGTH_LONG).show();
                           Log.e(TAG,"MOT DE ¨PASSE BASE ========================================================="+Current_Agent.getMot_de_passe());
                           Log.e(TAG,"MOT DE ¨PASSE SAISI ========================================================="+mot_de_passe.getText().toString());
                           mot_de_passe.setText("");

                       }

                       else {
                           Toast.makeText(Authentification.this, "Connecté avec succès !", Toast.LENGTH_LONG).show();
                           Intent mainActivity=new Intent(getApplicationContext(), Menu.class);
                           startActivity(mainActivity);
                           mot_de_passe.setText("");
//                            finish();
                       }
                   }
                   else/* if (!database.AgentDao().getPostesAgents().contains(poste_occupe.getSelectedItem().toString()))*/
                   {
                       Toast.makeText(Authentification.this, "Ce Poste n'existe pas ! ", Toast.LENGTH_LONG).show();

                   }

                }
            }
        });
        /**
         * MISE EN PLACE DE L'ADAPTER
         */
//        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.test_multiple_item,poste_disponibles);
//        poste.setAdapter(adapter);
//
    }

    public void init() {
        database = Databasephilomabtontine.getInstance(Authentification.this);
        init_Infos_Connexion();
    }

    /**
     * Recuperation des Informationssaisie par  l'Utilisateur
     * Et Convertion en Chaine de Caractère
     */

    public  void init_Infos_Connexion()
    {
        try
        {
           //this.poste_occupe= (AutoCompleteTextView) findViewById(R.id.postes);
           //this.choiscePostes=(TextInputLayout) findViewById(R.id.aChoicePoste);
            this.poste_occupe =(Spinner) findViewById(R.id.poste_entre);
            this.mot_de_passe = (EditText) findViewById(R.id.passwd_entre);
            this.valider_connexion= (Button) findViewById(R.id.btn_connecter);
        }catch(Exception error) { }
    }

    public boolean InfosEstNonVide(){
        return  Functions.checkIsEmpty(this, mot_de_passe);
    }

    public void checkPermissionStorage() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Permissions.check(this/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onGranted() {
                init();
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                checkPermissionStorage();
                Toast.makeText(getApplicationContext(), getString(R.string.acceptautorisation), Toast.LENGTH_SHORT).show();
            }
        });

    }

}