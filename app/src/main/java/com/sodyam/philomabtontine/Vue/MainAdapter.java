package com.sodyam.philomabtontine.Vue;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Agent;

import java.util.List;

public class MainAdapter  extends RecyclerView.Adapter<com.sodyam.philomabtontine.Vue.MainAdapter.ViewHolder>{
        //Initialise variable
        int lastPosition=-1;

        Activity mActivity;
        List<T_Agent> mListe_agents;
        //Create a constructor
        public MainAdapter(Activity activity, List<T_Agent> mListe_agents){
            this.mActivity=activity;
            this.mListe_agents=mListe_agents;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //Initialise view
            View view= LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_selectable_list_item,parent,false);
            //Return view
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull com.sodyam.philomabtontine.Vue.MainAdapter.ViewHolder holder, int position) {

            //Set text on view
            T_Agent agent=mListe_agents.get(position);

            holder.nomUser.setText(agent.getNomAgent());
            holder.contactUser.setText(agent.getTelephone());
            holder.prenomUser.setText(agent.getPrenomAgent());
            holder.posteUser.setText(agent.getPoste());
            holder.numberClientSaved.setText('1');
            holder.DateOfBeginning.setText(agent.getDateDeDebut());
        }

        @Override
        public int getItemCount()
        {
           return mListe_agents.size();
        }
        public static class ViewHolder extends RecyclerView.ViewHolder{
            private TextView nomUser;
            private TextView contactUser;
            private TextView prenomUser;
            private TextView posteUser;
            private TextView numberClientSaved;
            private TextView DateOfBeginning;
            public ViewHolder(@NonNull View itemView){
                super(itemView);
                //Assign value to variable
                nomUser= (TextView) itemView.findViewById(R.id.NomPrenomUtilisataur);
                contactUser= (TextView) itemView.findViewById(R.id.contactUtilisateur);
                prenomUser=(TextView) itemView.findViewById(R.id.prenom_agent);
                posteUser=(TextView) itemView.findViewById(R.id.poste_agent);
                numberClientSaved=(TextView) itemView.findViewById(R.id.nombre_client_enregistre);
                DateOfBeginning=(TextView) itemView.findViewById(R.id.date_debut);
            }
        }

}
