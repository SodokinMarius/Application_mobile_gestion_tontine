package com.sodyam.philomabtontine.Vue;

import static com.sodyam.philomabtontine.Vue.Authentification.current_agentConnexion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.sodyam.philomabtontine.R;

/**
 * FragmentManager manager = getFragmentManager();
 * FragmentTransaction transaction = manager.beginTransaction();
 * transaction.replace(R.id.bodyfragment, AnotherFragment.newInstance()); //newInstance() is a static factory method.
 * transaction.commit();
 */


public class Menu extends AppCompatActivity {
    private Button gestion_client,client_par_souscriptionId,gestion_paiement,point_par_client;
    private Button point_par_type_lot,point_par_souscriptionId,gestion_agents,point_jour;
    private Button point_general_activities;
   public static String Current_agent;

    public Menu()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.client_par_souscriptionId=(Button) findViewById(R.id.client_par_souscriptionId);
        this.point_par_souscriptionId=(Button) findViewById(R.id.point_par_sousciptionId);
        this.gestion_client=(Button) findViewById(R.id.customer_management);

        this.gestion_paiement=(Button)findViewById(R.id.paiement_management);
        this.point_par_client=(Button)findViewById(R.id.point_by_customer);
        this.point_par_type_lot=(Button)findViewById(R.id.point_by_lot_type);

        this.gestion_agents=(Button)findViewById(R.id.agent_management);
        this.point_jour=(Button)findViewById(R.id.day_point);
        this.point_general_activities=(Button)findViewById(R.id.point_general);
        //init();

        //Redirection vers Gestion des clients
        gestion_client.setOnClickListener(new View.OnClickListener() {
//
            @Override
            public void onClick(View view) {
                Intent ClientActivity= new Intent(Menu.this,liste_clients.class);
                startActivity(ClientActivity);
            }
        });

        //Redirection vers gestion agents(Liste des agents
        gestion_agents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gestion_agent=new Intent(Menu.this,liste_agents.class);
                startActivity(gestion_agent);
            }
        });
        //Redirection vers client par souscription
        client_par_souscriptionId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!current_agentConnexion.equals("SUPERVISEUR"))
                {
                    AlertDialog.Builder avertissement=new AlertDialog.Builder(Menu.this);
                    avertissement.setTitle("AVERTISSEMENT");
                    avertissement.setMessage("CETTE PAGE EST ADMINISTREE !! \nVOUS NE POSEDEZ PAS LES DROITS D'ACCES ! ");

                    avertissement.setPositiveButton("FERMER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
//                   avertissement.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                       @Override
//                       public void onClick(DialogInterface dialogInterface, int i) {
//
//                       }
//                   });
                    avertissement.show();
                }
                else
                {
                    Intent Client_souscription=new Intent(Menu.this, com.sodyam.philomabtontine.Vue.Client_par_souscription.class);
                    startActivity(Client_souscription);
                }
            }
        });
        //Redirection vers gestion des paiements
        gestion_paiement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gestion_paiement=new Intent(Menu.this,liste_paiements.class);
                startActivity(gestion_paiement);
            }
        });

        //Redirection vers point par client(Liste des clients)
        point_par_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!current_agentConnexion.equals("SUPERVISEUR"))
                {
                    AlertDialog.Builder avertissement=new AlertDialog.Builder(Menu.this);
                    avertissement.setTitle("AVERTISSEMENT");
                    avertissement.setMessage("CETTE PAGE EST ADMINISTREE !! \nVOUS NE POSEDEZ PAS LES DROITS D'ACCES ! ");
                    avertissement.setPositiveButton("FERMER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
//                   avertissement.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                       @Override
//                       public void onClick(DialogInterface dialogInterface, int i) {
//
//                       }
//                   });
                    avertissement.show();
                }
                else
                {
                    Intent p_par_client=new Intent(Menu.this,point_par_client.class);
                    startActivity(p_par_client);
                }

            }
        });

        //Redirection vers point par type de lot
        point_par_type_lot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!current_agentConnexion.equals("SUPERVISEUR"))
                {
                    AlertDialog.Builder avertissement=new AlertDialog.Builder(Menu.this);
                    avertissement.setTitle("AVERTISSEMENT");
                    avertissement.setMessage("CETTE PAGE EST ADMINISTREE !! \nVOUS NE POSEDEZ PAS LES DROITS D'ACCES ! ");

                    avertissement.setPositiveButton("FERMER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
//                   avertissement.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                       @Override
//                       public void onClick(DialogInterface dialogInterface, int i) {
//
//                       }
//                   });
                    avertissement.show();
                }
                else
                {
                    Intent point_p_type_lot=new Intent(Menu.this, point_par_type_lot.class);
                    startActivity(point_p_type_lot);

                }

            }
        });

        //redirection vers point par souscription
        point_par_souscriptionId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!current_agentConnexion.equals("SUPERVISEUR"))
                {
                    AlertDialog.Builder avertissement=new AlertDialog.Builder(Menu.this);
                    avertissement.setTitle("AVERTISSEMENT");
                    avertissement.setMessage("CETTE PAGE EST ADMINISTREE !! \nVOUS NE POSEDEZ PAS LES DROITS D'ACCES ! ");

                    avertissement.setPositiveButton("FERMER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
//                   avertissement.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                       @Override
//                       public void onClick(DialogInterface dialogInterface, int i) {
//
//                       }
//                   });
                    avertissement.show();
                }
                else
                {
                    Intent p_p_souscription=new Intent(Menu.this,point_par_souscription.class);
                    startActivity(p_p_souscription);

                }
            }
        });

        //Redirection vers Point du jour
        point_jour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!current_agentConnexion.equals("SUPERVISEUR") && !current_agentConnexion.equals("SECRETAIRE"))
                {
                    AlertDialog.Builder avertissement=new AlertDialog.Builder(Menu.this);
                    avertissement.setTitle("AVERTISSEMENT");
                    avertissement.setMessage("CETTE PAGE EST ADMINISTREE !! \nVOUS NE POSEDEZ PAS LES DROITS D'ACCES ! ");

                    avertissement.setPositiveButton("FERMER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
//                   avertissement.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                       @Override
//                       public void onClick(DialogInterface dialogInterface, int i) {
//
//                       }
//                   });
                    avertissement.show();
                }
                else
                {
                    Intent VersPoin_jour=new Intent(getApplicationContext(),com.sodyam.philomabtontine.Vue.point_du_jour.class);
                    startActivity(VersPoin_jour);

                }

            }
        });

        //Redirection vers point general de l'activite
        point_general_activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!current_agentConnexion.equals("SUPERVISEUR"))
                {
                    AlertDialog.Builder avertissement=new AlertDialog.Builder(Menu.this);
                    avertissement.setTitle("AVERTISSEMENT");
                    avertissement.setMessage("CETTE PAGE EST ADMINISTREE !! \nVOUS NE POSEDEZ PAS LES DROITS D'ACCES ! ");

                    avertissement.setPositiveButton("FERMER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
//                   avertissement.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                       @Override
//                       public void onClick(DialogInterface dialogInterface, int i) {
//
//                       }
//                   });
                    avertissement.show();
                }
                else
                {
                    Intent point_general=new Intent(getApplicationContext(), point_general_activities.class);
                    startActivity(point_general);

                }

            }


        });

    }

    }

