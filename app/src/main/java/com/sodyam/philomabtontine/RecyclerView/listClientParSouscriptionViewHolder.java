package com.sodyam.philomabtontine.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sodyam.philomabtontine.R;

public class listClientParSouscriptionViewHolder  extends RecyclerView.ViewHolder {

    /**
     *     Declaration d'une variable de type TextView
     *     qui contiendra les texte à afficher
     */

    private final TextView listClientParSouscription;

    /**
     * Methode qui construit le ViewHolder
     * listClientView est type de la classe courante
     * UnClient est id du textView qui sera dupliqué
     * @param itemView
     */

    private listClientParSouscriptionViewHolder(View itemView) {
        super(itemView);
        listClientParSouscription= itemView.findViewById(R.id.Unclient_souscription);
    }

    public void bind(String text) {
        listClientParSouscription.setText(text);
    }

    /**
     * Ici "content_adapter" est l'activité sur laquelle se trouve le TextView qui sera dupliqué
     * @param parent
     * @return
     */
    static listClientParSouscriptionViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_par_sousription_single_item, parent, false);
        return new listClientParSouscriptionViewHolder(view);
    }
}
