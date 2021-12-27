package com.sodyam.philomabtontine.Vue;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Carnet;
import com.sodyam.philomabtontine.model.T_Client;
import com.sodyam.philomabtontine.model.T_Lot;

import java.util.List;

public class ClientParSouscriptionAdapter extends RecyclerView.Adapter<ClientParSouscriptionAdapter.ExampleViewHolder> {

    private Context mContext;
    private List<T_Carnet> mExampleList;
    private ClientParSouscriptionAdapter.OnItemClickListenner mListenner;
    private  Databasephilomabtontine database;


    public interface OnItemClickListenner {
        void onItemClick(int position);
    }

    public void setOnClickListenner(ClientParSouscriptionAdapter.OnItemClickListenner listenner) {
        mListenner = listenner;
    }

    public ClientParSouscriptionAdapter(Context context, List<T_Carnet> example_categoryList) {
        mContext = context;
        mExampleList = example_categoryList;
        notifyDataSetChanged();
    }

    @Override
    public ClientParSouscriptionAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.client_par_sousription_single_item, parent, false);
        return new ClientParSouscriptionAdapter.ExampleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ClientParSouscriptionAdapter.ExampleViewHolder holder, int position) {
        final Databasephilomabtontine database=Databasephilomabtontine.getInstance(mContext.getApplicationContext());
        T_Carnet currentItem = mExampleList.get(position);

           T_Client client=database.ClientDao().getClientByNumCarnet(currentItem.getNumeroCarnet());
           T_Lot lot=database.LotDao().getLotSouscritByMontantLot(currentItem.getIdLot());
           //T_Paiement paiement=database.PaiementDao().getDernierPaiementByNumCarnet(currentItem.getNumeroCarnet());
                Log.e(TAG,"Numero du Carnet en cours : ------------"+ currentItem.getNumeroCarnet());
        int reste_payer=(currentItem.getIdLot()*372)-(database.PaiementDao().getMontantPayeByNumCarnet(currentItem.getNumeroCarnet()));

      Log.e(TAG,"CLIENT PAR SOUSCRIPTION : ................................."+database.PaiementDao().getClientBySouscription().size());
        holder.nom_client.setText(client.getNom());
        holder.prenom_client.setText(client.getPrenom());
        Log.e(TAG,"CLIENT PAR SOUSCRIPTION : ................................."+client.getMatricule());
        holder.telephone_client.setText(client.getTelephone());
        holder.valeur_souscription.setText(("Lot " +currentItem.getIdLot())+" F CFA");
        holder.montant_paye.setText(database.PaiementDao().getMontantPayeByNumCarnet(currentItem.getNumeroCarnet())+" F CFA ");
        holder.reste_paye.setText(reste_payer+" F CFA");
//        holder.mont_dernier_paye.setText( paiement.getMontant_paye()+" F CFA ");
//        holder.date_dernier_paye.setText(paiement.getDate_paiement()+"");
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{

        private TextView mont_dernier_paye;
        private TextView date_dernier_paye;
        private TextView valeur_souscription;
        private TextView nom_client;
        private TextView prenom_client;
        private TextView telephone_client;
        private TextView montant_paye;
        private TextView reste_paye;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            //mont_dernier_paye=itemView.findViewById(R.id.mont_dernier_paye_souscription);
            date_dernier_paye=itemView.findViewById(R.id.date_dernier_paye);
            valeur_souscription=itemView.findViewById(R.id.valeur_souscription);
            nom_client=itemView.findViewById(R.id.nom_client);
            prenom_client=itemView.findViewById(R.id.prenom_client);
            telephone_client=itemView.findViewById(R.id.telephone_client);
            montant_paye=itemView.findViewById(R.id.montant_paye);
            reste_paye=itemView.findViewById(R.id.reste_paye);
        }
    }
}
