package com.sodyam.philomabtontine.Vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Agent;

import java.util.List;

public class Example_agents_adapter extends RecyclerView.Adapter<Example_agents_adapter.ExampleViewHolder>{
    private Context mContext;
    private List<T_Agent> mExampleList;
    private OnItemClickListenner mListenner;



    public interface OnItemClickListenner {
        void onItemClick(int position);
    }

    public void setOnClickListenner(OnItemClickListenner listenner) {
        mListenner = listenner;
    }

    public Example_agents_adapter(Context context, List<T_Agent> example_categoryList) {
        mContext = context;
        mExampleList = example_categoryList;
        notifyDataSetChanged();
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.simple_selectable_list_item_agent, parent, false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {

        final Databasephilomabtontine database=Databasephilomabtontine.getInstance(mContext.getApplicationContext());
        T_Agent currentItem = mExampleList.get(position);
        //T_Paiement paiement=database.PaiementDao().getNombreClientEnregistreByPoste()
        holder.contactUtilisateur.setText(currentItem.getTelephone());
        holder.poste_agent.setText(currentItem.getPoste());
        holder.UnNomPrenomUtilisateur.setText(currentItem.getNomAgent());
        holder.prenom_agent.setText(currentItem.getPrenomAgent());
        holder.date_debut.setText(currentItem.getDateDeDebut().toString());
       holder.nombre_client_enregistre.setText(database.PaiementDao().getNombreClientEnregistreParPoste(currentItem.getPoste())+" clients");
    }


    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        private TextView contactUtilisateur;
        private TextView poste_agent;
        private TextView UnNomPrenomUtilisateur;
        private TextView prenom_agent;
        private TextView date_debut;
        private TextView nombre_client_enregistre;


        public ExampleViewHolder(View itemView) {
            super(itemView);

            contactUtilisateur = itemView.findViewById(R.id.contactUtilisateur);
            poste_agent = itemView.findViewById(R.id.poste_agent);
            UnNomPrenomUtilisateur=itemView.findViewById(R.id.NomPrenomUtilisateur);
            prenom_agent=itemView.findViewById(R.id.prenom_agent);
            date_debut=itemView.findViewById(R.id.date_debutAgent);
            nombre_client_enregistre=itemView.findViewById(R.id.nombre_client_enregistre);
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListenner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListenner.onItemClick(position);
                        }
                    }
                }
            });*/
        }
    }
}
