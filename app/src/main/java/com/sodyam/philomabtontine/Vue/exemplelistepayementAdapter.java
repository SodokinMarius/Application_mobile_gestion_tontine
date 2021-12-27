package com.sodyam.philomabtontine.Vue;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Carnet;
import com.sodyam.philomabtontine.model.T_Client;
import com.sodyam.philomabtontine.model.T_Paiement;

import java.sql.Date;
import java.util.List;

public class exemplelistepayementAdapter extends RecyclerView.Adapter<exemplelistepayementAdapter.ExampleViewHolderListepayement> {
    private Context mContext;
    private List<T_Paiement> mExampleList;
    private OnItemClickListenner mListenner;
  private Databasephilomabtontine database;

    public interface OnItemClickListenner {
        void onItemClick(int position);
    }

    public void setOnClickListenner(OnItemClickListenner listenner) {
        mListenner = listenner;
    }


    public exemplelistepayementAdapter (Context context, List<T_Paiement> example_categoryList) {
        mContext = context;
        mExampleList = example_categoryList;
        notifyDataSetChanged();
    }

    @Override
    public ExampleViewHolderListepayement onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.exemplairelistepayement, parent, false);
        return new ExampleViewHolderListepayement(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ExampleViewHolderListepayement holder, int position) {
        database=Databasephilomabtontine.getInstance(mContext.getApplicationContext());
        if(database.ClientDao().getListeClients().size()==0 || database.CarnetDao().getListeCarnets().size()==0
                || database.PaiementDao().getListePaiementsUnique().size()==0 || database.AgentDao().getListeAgents().size()==0)
        {
            Toast.makeText(mContext.getApplicationContext(), "Pas de Paiementys pour le moment !!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            T_Paiement currentItem = mExampleList.get(position);
            T_Carnet  carnet = database.CarnetDao().getCarnetByNumCarnet(currentItem.getNumCarnet());
            T_Client client = database.ClientDao().getClientByNumCarnet(currentItem.getNumCarnet());
            Log.e(TAG," Le poste de l'Agent en cours -----------------------"+ currentItem.getPoste_agent());

            Log.e(TAG," PAiement en cours  -----------------------"+ currentItem.toString());
            Log.e(TAG," Le -----------------------"+ currentItem.getPoste_agent());
            //T_Agent agent = database.AgentDao().getAgentByPoste(paiement.getPoste_agent());

            holder.montant_dernier_paye.setText(currentItem.getMontant_paye() + " F CFA");
            holder.date_dernier_paye.setText(currentItem.getDate_paiement()+ "");
            Log.e(TAG, "Numero Carnet passé : --------------------------------- " + position);
            Date date = new Date(System.currentTimeMillis());
            Log.e(TAG, "Numero Carnet passé : ---------------------------------------------" + date);
            Log.e(TAG, "lient en cours : ---------------------------------------------" + client.toString());
            holder.agent_operateur.setText(currentItem.getPoste_agent());
            holder.numcarnet.setText("Carnet N° " + currentItem.getNumCarnet());
            holder.nom_cli.setText(client.getNom());

            int montant_payer = database.PaiementDao().getMontantPayeByNumCarnet(currentItem.getNumCarnet());
            Log.e(TAG, "Date dernier paiement : --------------------------------" + database.PaiementDao().getListePaiementsUnique().size());
            // String dateLastPaiement=Long.toString(database.PaiementDao().getDateDernierMontantPayerDuClient(currentItem.getNumCarnet()));
            int montant_lot = carnet.getIdLot();

            int resteAPayer = (montant_lot * 372) - montant_payer;
            Log.e(TAG, "Lot : ---------------------------------" + montant_lot);
            Log.e(TAG, "Montant restant : ---------------------------------" + resteAPayer);

            holder.montant_payer.setText(montant_payer + " F CFA");

            holder.prenom_cli.setText(client.getPrenom());
            holder.reste_a_payer.setText(resteAPayer + " F CFA");
        }
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    //ok valide bas
        public class ExampleViewHolderListepayement extends RecyclerView.ViewHolder{

        private Button bouton_ajout_nouveau_paiement;
        private TextView date_actuelle;
        private  TextView montant_total_encaisse,agent_operateur,numcarnet,nom_cli,prenom_cli,reste_a_payer,montant_dernier_paye,date_dernier_paye,montant_payer;


        public ExampleViewHolderListepayement(View itemView){
            super(itemView);

            this.agent_operateur=(TextView)itemView.findViewById(R.id.agent_enregistreur);
            this.numcarnet=(TextView)itemView.findViewById(R.id.numCarnet);
            this.montant_payer=(TextView) itemView.findViewById(R.id.montant_paye);
            this.nom_cli=(TextView)itemView.findViewById(R.id.nom_client);
            this.prenom_cli=(TextView)itemView.findViewById(R.id.prenom_client);
            this.reste_a_payer=(TextView)itemView.findViewById(R.id.reste_paye);
            this.montant_dernier_paye=(TextView) itemView.findViewById(R.id.mont_dernier_paye_paiement);
            this.date_dernier_paye=(TextView) itemView.findViewById(R.id.date_dernier_paye);
        }

    }
}