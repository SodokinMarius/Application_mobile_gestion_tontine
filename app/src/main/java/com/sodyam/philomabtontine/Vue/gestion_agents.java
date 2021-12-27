package com.sodyam.philomabtontine.Vue;

import static com.sodyam.philomabtontine.Vue.Authentification.current_agentConnexion;
import static com.sodyam.philomabtontine.utils.AllConstants.TAG;

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
import com.sodyam.philomabtontine.model.T_Agent;
import com.sodyam.philomabtontine.utils.Functions;

import java.sql.Date;

public class gestion_agents extends AppCompatActivity {

    private EditText nom_agent,prenom_agent,date_naissance,lieu_naissance,telephone,adreese_agent,
            mot_passe,mot_passe_confirm;

    private String nomagent,prenomagent,lieunaissance,telephon,adreeseagent,posteagent,
            motpasse,motpasse_confirm;
    private TextView date_actuelle;

    private Button enregistrer_nouveau_agent, btn_quiter_nouveau_agent,btn_actualier_nouveau_agent;
    private gestion_agents NouveauAgent;
    private T_Agent nouveau_agent;
    private Databasephilomabtontine database;
    private  Spinner poste_agent;



    /**
     * CONVERTION DE LA DATE ACTUELLE
     * @param
     */
    /**
     * CONVERSION DE LA DATE
     */

    Date date=new Date(System.currentTimeMillis());
    String anne=date.toString().substring(0,4);
    String mois=date.toString().substring(5,7);
    String jour=date.toString().substring(8);
    String dates=jour+"-"+mois+"-"+anne;
    String date_debut=dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_agents);
        init();
        database=Databasephilomabtontine.getInstance(getApplicationContext());
        this.date_actuelle=(TextView)findViewById(R.id.date_actuelle_agent);

        Log.e(TAG,"AGENT QUI SE CONNECTE : ========================================"+current_agentConnexion);

        enregistrer_nouveau_agent=(Button) findViewById(R.id.btn_enregistre_agent);
        btn_quiter_nouveau_agent=(Button) findViewById(R.id.btn_quiter_nouveau_agent);
    date_actuelle.setText(DateConverter.ConvertNumericNewDatetoAllLetter());

        /**
         * FOR POSTE
         */
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterForPoste = ArrayAdapter.createFromResource(this,
                R.array.postes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterForPoste.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        poste_agent.setAdapter(adapterForPoste);

        this.btn_actualier_nouveau_agent=(Button) findViewById(R.id.btn_actualier_nouveau_agent);


        btn_actualier_nouveau_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ConvertToString();
                nom_agent.setText("");
                prenom_agent.setText("");
                telephone.setText("");
                adreese_agent.setText("");
                mot_passe.setText("");
                mot_passe_confirm.setText("");
            }
        });


        enregistrer_nouveau_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            ConvertToString();
                if(ChampAgentEstVide()){
                    if(database.AgentDao().getPostesAgents().toString().contains(poste_agent.getSelectedItem().toString()))
                    {
                        Toast.makeText(gestion_agents.this, "CET AGENT EST DEJA ENREGISTRE ! ", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(mot_passe.getText().toString().trim().equals(mot_passe_confirm.getText().toString().trim())){
                            ConvertToString();
                            AlertDialog.Builder BoiteDeConfirmation=new AlertDialog.Builder(gestion_agents.this);
                            BoiteDeConfirmation.setTitle("CONFIRMATION DE L'AJOUT ");
                            BoiteDeConfirmation.setMessage("Vous voulez enregistrer l'agent " + nomagent +
                                    " " + prenomagent +" \nVenant de : "+ adreeseagent+
                                    " \n et de Contact : "+telephon+" \n Mot de Passe : "+motpasse+
                                    ", \nVoulez vous continuer ? ");
                            BoiteDeConfirmation.setPositiveButton("CONTINUER", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    database=Databasephilomabtontine.getInstance(getApplicationContext());

                                    T_Agent newAgent= new T_Agent(poste_agent.getSelectedItem().toString(), nomagent, prenomagent,telephon,adreeseagent,motpasse,date_debut);
                                    database.AgentDao().insertAgent(newAgent);
                                    Log.e(TAG,"Nombre total d'Agent : ======================================"+database.AgentDao().getListeAgents().size()+"");
                                    Toast.makeText(getApplicationContext(), getString(R.string.confirmAgentInsertion), Toast.LENGTH_SHORT).show();

                                    /**
                                     * RETOUR A LA LISTE DES AGENTS */

                                    Intent ClientActivity= new Intent(gestion_agents.this,liste_clients.class);
                                    startActivity(ClientActivity);
                                    finish();
                                }
                            });
                            BoiteDeConfirmation.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(gestion_agents.this, "Enregeristrement Annulé ! ", Toast.LENGTH_LONG).show();
                                }
                            });
                            BoiteDeConfirmation.show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "MOTS DE PASSE NON CONFORMES !! ", Toast.LENGTH_LONG).show();
                            mot_passe.setText("");
                            mot_passe_confirm.setText("");

                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les Champs svp !!", Toast.LENGTH_LONG).show();
                }
            }
        });

        /**
         * ============== ACTION APRES CLIC SUR LE BOUTON QUITTER
         */
        btn_quiter_nouveau_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VersMenu=new Intent(getApplicationContext(),Menu.class);
                startActivity(VersMenu);
                finish();

            }
        });
    }

    private void init(){
        this.nom_agent=(EditText) findViewById(R.id.nom_agent);
        this.prenom_agent=(EditText)findViewById(R.id.prenom_agent);
        this.telephone=(EditText)findViewById(R.id.telepnone_agent);
        this.adreese_agent=(EditText)findViewById(R.id.adresse_agent);
        this.poste_agent=(Spinner) findViewById(R.id.poste_agent);
        this.mot_passe=(EditText)findViewById(R.id.passwd_agent);
        this.mot_passe_confirm=(EditText)findViewById(R.id.confirm_passwd);
    }


    /**
     * Convertion des variables captées en type String
     */
    public void ConvertToString(){
        init();
        nomagent=nom_agent.getText().toString();
        prenomagent=prenom_agent.getText().toString();
        telephon=telephone.getText().toString();
        adreeseagent=adreese_agent.getText().toString();
        motpasse=mot_passe.getText().toString();
        motpasse_confirm=mot_passe_confirm.getText().toString();
    }

    /**
     * Faire une écoute sur le bouton enregistrer un nouveau client
     */


    /**
     * Verifions si tous les champs sont renseignés
     */
    private boolean ChampAgentEstVide()
    {

        return (Functions.checkIsEmpty(this,nom_agent) &&
                Functions.checkIsEmpty(this,prenom_agent) &&
                Functions.checkIsEmpty(this,telephone)&&
                Functions.checkIsEmpty(this,adreese_agent)&&
                Functions.checkIsEmpty(this,mot_passe) &&
                Functions.checkIsEmpty(this,mot_passe_confirm));
            }

    /**
     * Verification de la conformité des mot de passe
     */
    private boolean MotDePasseEstConforme()
    {
        Boolean mot_de_passe_conforme=false;
        try
        {
            if(mot_passe.getText().toString().trim().equals(mot_passe_confirm.getText().toString().trim()))
            {
                Toast.makeText(gestion_agents.this, "Les mot de passes ne sont pas conformes ! ", Toast.LENGTH_SHORT).show();
                mot_de_passe_conforme=true;
            }
        }catch (Exception error){ }
        return mot_de_passe_conforme;
    }

}
