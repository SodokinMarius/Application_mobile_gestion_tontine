//package com.sodyam.philomabtontine.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.sodyam.philomabtontine.R;
//
//public class listAgentViewHolder extends RecyclerView.ViewHolder {
//
//    /**
//     *     Declaration d'une variable de type TextView
//     *     qui contiendra les texte à afficher
//     */
//
//    private final TextView listAgentView;
//
//    /**
//     * Methode qui construit le ViewHolder
//     * listAgentView est type de la classe courante
//     * UnAgent est id du textView qui sera dupliqué
//     * @param itemView
//     */
//
//    private listAgentViewHolder(View itemView) {
//        super(itemView);
//        listAgentView= itemView.findViewById(R.id);
//    }
//
//    public void bind(String text) {
//        listAgentView.setText(text);
//    }
//
//    /**
//     * Ici "content_adapter" est l'activité sur laquelle se trouve le TextView qui sera dupliqué
//     * @param parent
//     * @return
//     */
//    static listAgentViewHolder create(ViewGroup parent) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.simple_selectable_list_item_agent, parent, false);
//        return new listAgentViewHolder(view);
//    }
//}
