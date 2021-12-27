package com.sodyam.philomabtontine.Vue;

import android.content.Context;
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

public class exemplePointParTypeDeLotAdapter extends RecyclerView.Adapter<exemplePointParTypeDeLotAdapter.PointParTypeDeLotViewHolder>{
    private Context mContext;
    private List<T_Carnet> mExampleList;
    private OnItemClickListenner mListenner;
    private Databasephilomabtontine database;
    public interface OnItemClickListenner {
        void onItemClick(int position);
    }
    public void setOnClickListenner(OnItemClickListenner listenner) {
        mListenner = listenner;
    }

    public exemplePointParTypeDeLotAdapter(Context context, List<T_Carnet> example_categoryList) {
        mContext = context;
        mExampleList = example_categoryList;
        notifyDataSetChanged();
    }

    @Override
    public PointParTypeDeLotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.exemplairepointpartypedelot, parent, false);
        return new PointParTypeDeLotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PointParTypeDeLotViewHolder holder,int position) {
        database=Databasephilomabtontine.getInstance(mContext.getApplicationContext());
        T_Carnet currentItem = mExampleList.get(position);
        T_Client client=database.ClientDao().getClientByNumCarnet(currentItem.getNumeroCarnet());
        T_Lot lot=database.LotDao().getLotSouscritByMontantLot(currentItem.getIdLot());
        //T_Paiement paiement=database.PaiementDao().getDernierPaiementByNumCarnet(currentItem.getNumeroCarnet());

//        Log.e(TAG,"Paiement recuperé ======================================"+currentItem.getNumeroCarnet());
//        Log.e(TAG,"Paiement recuperé ======================================"+client.getMatricule());
//        Log.e(TAG,"Paiement recuperé ======================================"+paiement.getNumCarnet());


        holder.montant_paye.setText((database.PaiementDao().getMontantPayeByNumCarnet(currentItem.getNumeroCarnet())) + " F CFA");
        //holder.mont_dernier_paye.setText(database.PaiementDao().getDernierMontantPayerDuClient(carnet.getNumeroCarnet())+" F CFA ");
        int reste_payer=(currentItem.getIdLot()*372)-(database.PaiementDao().getMontantPayeByNumCarnet(currentItem.getNumeroCarnet()));
        holder.reste_paye.setText(reste_payer + " F CFA");

        holder.telephone.setText(client.getTelephone());
        holder.typeLot.setText(lot.getType_lot());
        holder.nom_client.setText(client.getNom());
        holder.prenom_client.setText(client.getPrenom());
//        Log.e(TAG,"Dernier Montant : ---------------"+paiement.getMontant_paye());
//        Log.e(TAG,"Date du denier paiement : ---------------"+paiement.getDate_paiement());

//        holder.mont_dernier_paye.setText(paiement.getMontant_paye() + " F CFA");
//        holder.date_dernier_paye.setText(paiement.getDate_paiement()+"");


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class PointParTypeDeLotViewHolder extends RecyclerView.ViewHolder{

        private TextView mont_dernier_paye,date_dernier_paye,nom_client,prenom_client,typeLot,reste_paye,montant_paye,telephone;
        public PointParTypeDeLotViewHolder(View itemView){
        super(itemView);

        //this.mont_dernier_paye=itemView.findViewById(R.id.mont_dernier_paye);
        this.date_dernier_paye=itemView.findViewById(R.id.date_dernier_paye);
        this.typeLot=itemView.findViewById(R.id.typeLot);
        this.nom_client=itemView.findViewById(R.id.nom_client);
        this.prenom_client=itemView.findViewById(R.id.prenom_client);
        this.reste_paye=itemView.findViewById(R.id.reste_paye);
        this.montant_paye=itemView.findViewById(R.id.montant_paye);
        this.telephone=itemView.findViewById(R.id.telephone);

        }
    }
}