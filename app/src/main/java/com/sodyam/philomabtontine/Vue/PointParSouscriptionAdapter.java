package com.sodyam.philomabtontine.Vue;

import static com.sodyam.philomabtontine.utils.AllConstants.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Carnet;

import java.util.List;

public class PointParSouscriptionAdapter extends RecyclerView.Adapter<PointParSouscriptionAdapter.ExampleViewHolder> {
    private Context mContext;
    private List<T_Carnet> mExampleList;
    private PointParSouscriptionAdapter.OnItemClickListenner mListenner;
    private Databasephilomabtontine database;

    public interface OnItemClickListenner {
        void onItemClick(int position);
    }

    public void setOnClickListenner(PointParSouscriptionAdapter.OnItemClickListenner listenner) {
        mListenner = listenner;
    }
    public PointParSouscriptionAdapter(Context context, List<T_Carnet> example_categoryList) {
        mContext = context;
        mExampleList = example_categoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PointParSouscriptionAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.point_par_souscription_single_item, parent, false);
        return new PointParSouscriptionAdapter.ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PointParSouscriptionAdapter.ExampleViewHolder holder, int position) {
   database=Databasephilomabtontine.getInstance(mContext.getApplicationContext());
       T_Carnet currentItem = mExampleList.get(position);
       int nombreClient=database.CarnetDao().getListeCarnets().size();
        //int nombreClient2=database.);
       Log.e(TAG," Le montant en cours : ------------------------------------"+currentItem.getIdLot());
       // Log.e(TAG," Le montant en cours : ------------------------------------"+nombreClient2);
        Log.e(TAG," Les Clients LOts en cours : ------------------------------------"+currentItem.toString());
        Log.e(TAG,"Nombre de Clients : "+database.CarnetDao().getNombreClientBySouscription(currentItem.getIdLot()));

        holder.souscription_valeur.setText(currentItem.getIdLot()+" F CFA ");
        holder.nbre_client.setText(database.CarnetDao().getNombreClientBySouscription(currentItem.getIdLot())+" ");
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{

        private TextView nbre_client;
        private TextView souscription_valeur;

        public ExampleViewHolder(View itemView) {
            super(itemView);
           nbre_client=itemView.findViewById(R.id.nbre_client);
           souscription_valeur=itemView.findViewById(R.id.souscription_valeur);
        }
    }
}
