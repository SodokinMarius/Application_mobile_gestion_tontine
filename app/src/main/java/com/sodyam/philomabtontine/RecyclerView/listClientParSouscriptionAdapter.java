package com.sodyam.philomabtontine.RecyclerView;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.sodyam.philomabtontine.Outils.List_client;
import com.sodyam.philomabtontine.Outils.client_par_souscription;

public class listClientParSouscriptionAdapter extends ListAdapter<client_par_souscription, listClientParSouscriptionViewHolder> {

    /**
     * ListClientAdapter est le nom de la classe Adapter courante
     * liste_Clients est le type de l'objet à dupliquer
     * @param diffCallback
     */
    public listClientParSouscriptionAdapter(@NonNull DiffUtil.ItemCallback<client_par_souscription> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public listClientParSouscriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return listClientParSouscriptionViewHolder.create(parent);
    }

    /**
     * Methode chargée de mettre à jour chaque fois la vue,
     * c'est à  dire prendre les informations de la BD et les envoyer
     * dans le recyclerView
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull listClientParSouscriptionViewHolder holder, int position) {
        client_par_souscription current=getItem(position);
        holder.bind(current.getNom_cli());
        holder.bind(current.getPrenom_cli());
        holder.bind(current.getTelephone_cli().toString()+ "F CFA");
        holder.bind(current.getValeur_souscript().toString()+" F CFA");
        holder.bind(current.getMontant_dernier_paiement().toString()+" F CFA");
        holder.bind(current.getDate_dernier_paiement());
        holder.bind(current.getMontant_paye().toString()+" F CFA");
        holder.bind(current.getReste_paye().toString()+" F CFA");

    }

    public  static class WordDiff extends DiffUtil.ItemCallback<List_client> {

        @Override
        public boolean areItemsTheSame(@NonNull List_client oldItem, @NonNull List_client newItem) {
            return oldItem==newItem;
        }

        /**
         * Cette methode sera chargé de verifier chaque fois si la liste client
         * a été mise à jour ou pas : ceci en comparant lee valeurs
         * des attributs et enfin envoyer true s'ils sont égaux
         * et false sinon
         * @param oldItem
         * @param newItem
         * @return
         */
        @Override
        public boolean areContentsTheSame(@NonNull List_client oldItem, @NonNull List_client newItem) {
            return (oldItem.getNom().equals(newItem.getNom())
                    && oldItem.getPrenom().equals(newItem.getPrenom()) &&
                    oldItem.getIdLot()==newItem.getIdLot() &&
                    oldItem.getNumeroCarnet()== newItem.getNumeroCarnet() &&
                    oldItem.getTelephone()== newItem.getTelephone()
            );
        }
    }
}
