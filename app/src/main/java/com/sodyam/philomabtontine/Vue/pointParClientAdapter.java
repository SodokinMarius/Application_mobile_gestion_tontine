package com.sodyam.philomabtontine.Vue;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Carnet;
import com.sodyam.philomabtontine.model.T_Client;

import java.util.List;

public class pointParClientAdapter extends RecyclerView.Adapter<pointParClientAdapter.ExampleViewHolder> {
    private Context mContext;
    private List<T_Carnet> mExampleList;
    private  pointParClientAdapter.OnItemClickListenner mListenner;
    private Databasephilomabtontine database;

    public interface OnItemClickListenner {
        void onItemClick(int position);
    }

    public void setOnClickListenner(OnItemClickListenner listenner) {
        mListenner = listenner;
    }
    public pointParClientAdapter(Context context, List<T_Carnet> example_categoryList) {
        mContext = context;
        mExampleList = example_categoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public pointParClientAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.point_par_client_single_item, parent, false);
        return new pointParClientAdapter.ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(pointParClientAdapter.ExampleViewHolder holder, int position) {
        database = Databasephilomabtontine.getInstance(mContext.getApplicationContext());
        if(database.CarnetDao().getListeCarnets().size()==0)
        {
            Toast.makeText(mContext.getApplicationContext(), "PAS DE CLIENT ENREGISTRE", Toast.LENGTH_SHORT).show();
        }

       else
       {
           T_Carnet currentItem = mExampleList.get(position);

           T_Client client=database.ClientDao().getClientByNumCarnet(currentItem.getNumeroCarnet());
//           Log.e(TAG,"Matrivcule du Client en cours : ============"+ carnet.getNumeroCarnet());
//           Log.e(TAG,"Montant Payer : ============================"+database.PaiementDao().getMontantPayeByNumCarnet(carnet.getNumeroCarnet()));
           holder.nom_client.setText(client.getNom());
           holder.prenom_client.setText(client.getPrenom());
            holder.montant_souscript.setText("Lot : "+currentItem.getIdLot()+" F CFA");
           Log.e(TAG,"Matrivcule du Client en cours : ============"+ client.getMatricule());
           Log.e(TAG,"Matrivcule du Client en cours  NUM CARNET: ============"+ currentItem.getNumeroCarnet());
           Log.e(TAG,"Montant Payer : ============================"+database.PaiementDao().getMontantPayeByNumCarnet(currentItem.getNumeroCarnet()));

           holder.montant_paye.setText((database.PaiementDao().getMontantPayeByNumCarnet(currentItem.getNumeroCarnet())) + " F CFA");
           int reste_payer=(currentItem.getIdLot()*372)-(database.PaiementDao().getMontantPayeByNumCarnet(currentItem.getNumeroCarnet()));
           holder.reste_paye.setText(reste_payer+ " F CFA");
           holder.numCarnet.setText("Carnet N° "+currentItem.getNumeroCarnet()+"");
           Log.e(TAG, "Reste a payer : -------------- " +reste_payer);
           Log.e(TAG, "Montant dernier payerr : -------------- " +database.PaiementDao().getDernierPaiementDuClient(currentItem.getNumeroCarnet()));
           // holder.mont_dernier_paye.setText(database.PaiementDao().getDernierPaiementDuClient(carnet.getNumeroCarnet()) + " F CFA");

           Log.e(TAG, "Numero Carnet retourné : -------------- " + currentItem.getNumeroCarnet());
       }

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{

      private  TextView nom_client;
      private  TextView prenom_client;
      private  TextView montant_souscript;
      private  TextView montant_paye;
      private  TextView reste_paye;
        private  TextView numCarnet;
        private  TextView mont_dernier_paye;
        private  TextView date_dernier_paye;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            nom_client=itemView.findViewById(R.id.nom_client);
            prenom_client=itemView.findViewById(R.id.prenom_client);
            montant_souscript=itemView.findViewById(R.id.montant_souscript);
            montant_paye=itemView.findViewById(R.id.montant_paye_ppc);
            reste_paye=itemView.findViewById(R.id.reste_paye);
            numCarnet=itemView.findViewById(R.id.numCarnet);
           // mont_dernier_paye=itemView.findViewById(R.id.mont_dernier_paye);
            date_dernier_paye=itemView.findViewById(R.id.date_dernier_paye);
        }
    }
}
