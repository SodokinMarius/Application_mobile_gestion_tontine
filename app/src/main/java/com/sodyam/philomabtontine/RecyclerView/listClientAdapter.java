package com.sodyam.philomabtontine.RecyclerView;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.sodyam.philomabtontine.Outils.List_client;

public class listClientAdapter extends ListAdapter<List_client, listClientsViewHolder> {

    /**
     * ListClientAdapter est le nom de la classe Adapter courante
     * liste_Clients est le type de l'objet à dupliquer
     * @param diffCallback
     */
    public listClientAdapter(@NonNull DiffUtil.ItemCallback<List_client> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public listClientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return listClientsViewHolder.create(parent);
    }

    /**
     * Methode chargée de mettre à jour chaque fois la vue,
     * c'est à  dire prendre les informations de la BD et les envoyer
     * dans le recyclerView
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull listClientsViewHolder holder, int position) {
     List_client current=getItem(position);
     holder.bind(current.getNom());
     holder.bind(current.getPrenom());
     holder.bind(String.valueOf(current.getTelephone()));
     holder.bind(String.valueOf((int)current.getIdLot()));
     holder.bind(String.valueOf((int)current.getNumeroCarnet()));

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
