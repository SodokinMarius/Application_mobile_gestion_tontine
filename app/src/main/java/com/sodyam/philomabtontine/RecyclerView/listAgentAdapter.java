//package com.sodyam.philomabtontine.RecyclerView;
//
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.DiffUtil;
//import androidx.recyclerview.widget.ListAdapter;
//
//import com.sodyam.philomabtontine.model.T_Agent;
//
//public class listAgentAdapter extends ListAdapter<T_Agent, listAgentViewHolder> {
//    /**
//     * ListAgentAdapter est le nom de la classe Adapter courante
//     * T_Agent est le type de l'objet à dupliquer
//     * @param diffCallback
//     */
//    public  listAgentAdapter(@NonNull DiffUtil.ItemCallback<T_Agent> diffCallback) {
//        super(diffCallback);
//    }
//
//    @Override
//    public listAgentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return listAgentViewHolder.create(parent);
//    }
//
//    /**
//     * Methode chargée de mettre à jour chaque fois la vue,
//     * c'est à  dire prendre les informations de la BD et les envoyer
//     * dans le recyclerView
//     * @param holder
//     * @param position
//     */
//    @Override
//    public  void onBindViewHolder(@NonNull listAgentViewHolder holder, int position) {
//        T_Agent current=getItem(position);
//        holder.bind(current.getNomAgent());
//        holder.bind(current.getPrenomAgent());
//        holder.bind(String.valueOf(current.getTelephone()));
//        holder.bind(current.getPoste());
//        holder.bind(current.getDateDeDebut().toString());
//
//    }
//
//    public static class WordDiff extends DiffUtil.ItemCallback<T_Agent> {
//
//        @Override
//        public boolean areItemsTheSame(@NonNull T_Agent oldItem, @NonNull T_Agent newItem) {
//            return oldItem == newItem;
//        }
//
//        @Override
//        public boolean areContentsTheSame(@NonNull T_Agent oldItem, @NonNull T_Agent newItem) {
//            return (oldItem.getNomAgent().equals(newItem.getNomAgent())
//                    && oldItem.getPrenomAgent().equals(newItem.getPrenomAgent()) &&
//                    oldItem.getPoste().equals(newItem.getPoste()) &&
//                    oldItem.getTelephone().equals(newItem.getTelephone()) &&
//                    oldItem.getDateDeDebut().equals(newItem.getDateDeDebut()));
//        }
//        }
//        /**
//         * Cette methode sera chargé de verifier chaque fois si la liste client
//         * a été mise à jour ou pas : ceci en comparant lee valeurs
//         * des attributs et enfin envoyer true s'ils sont égaux
//         * et false sinon
//         * @param oldItem
//         * @param newItem
//         * @return
//         */
//}
