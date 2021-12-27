package com.sodyam.philomabtontine.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sodyam.philomabtontine.R;

public class listClientsViewHolder extends RecyclerView.ViewHolder {

    /**
     *     Declaration d'une variable de type TextView
     *     qui contiendra les texte à afficher
     */

    private final TextView listClientView;

    /**
     * Methode qui construit le ViewHolder
     * listClientView est type de la classe courante
     * UnClient est id du textView qui sera dupliqué
     * @param itemView
     */

    private listClientsViewHolder(View itemView) {
        super(itemView);
        listClientView= itemView.findViewById(R.id.Unclient);
    }

    public void bind(String text) {
       listClientView.setText(text);
    }

    /**
     * Ici "content_adapter" est l'activité sur laquelle se trouve le TextView qui sera dupliqué
     * @param parent
     * @return
     */
    static listClientsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_adapter, parent, false);
        return new listClientsViewHolder(view);
    }


}
