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
import com.sodyam.philomabtontine.model.T_Client;

import java.util.List;

public class listClientAdapter extends RecyclerView.Adapter<listClientAdapter.ExampleViewHolder> {
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
    public listClientAdapter(Context context, List<T_Carnet> example_categoryList) {
        mContext = context;
        mExampleList = example_categoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public listClientAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.content_adapter, parent, false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        T_Carnet currentItem = mExampleList.get(position);
        database=Databasephilomabtontine.getInstance(mContext.getApplicationContext());
        //T_Carnet carnet=database.CarnetDao().getNumCarnetByMatriculeClient(currentItem.getMatricule());
        //List<Integer> lesNumCar
        T_Client client=database.ClientDao().getClientByNumCarnet(currentItem.getNumeroCarnet());

        Log.e(TAG,"  Tous les Clients  Numeros: ----------------------------------"+database.ClientDao().getLesMatriculesClients().toString());
        Log.e(TAG,"  Tous les Carnets  Numeros: ----------------------------------"+database.CarnetDao().getNumerosDesCarnets().toString());
        Log.e(TAG,"  Tous les des Clients Des Carnets: ----------------------------------"+database.CarnetDao().getNumerosDesClientsDesCarnets().toString());
       Log.e(TAG,"  Tous les carnets : ----------------------------------"+client.toString());
        //Log.e(TAG,"  Tous les carnets : ----------------------------------"+ liste.get(position));
        Log.e(TAG," Le numero du Client en cours : ----------------------------------"+client.getMatricule());
      //Log.e(TAG,"  Numero du Carnet 1 : ----------------------------------"+carnet.getNumeroCarnet());

      holder.numCarnet.setText(" Carnet N° "+ currentItem.getNumeroCarnet()) ;

     //Log.e(TAG,"  Numero du Carnet autres : ----------------------------------"+carnet.getNumeroCarnet());


        holder.nom_client.setText(client.getNom());
        holder.prenom_client.setText(client.getPrenom());
        holder.contactclient.setText(client.getTelephone());
        holder.lot_souscrit.setText("Lot "+ currentItem.getIdLot());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{

        private TextView nom_client;
        private TextView prenom_client;
        private TextView numCarnet;
        private TextView contactclient;
        private TextView lot_souscrit;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            contactclient = itemView.findViewById(R.id.contactclient);
            nom_client= itemView.findViewById(R.id.nom_client);
            prenom_client=itemView.findViewById(R.id.prenom_client);
            numCarnet=itemView.findViewById(R.id.numCarnet);
            lot_souscrit=itemView.findViewById(R.id.lot_souscrit);
        }
    }
}
