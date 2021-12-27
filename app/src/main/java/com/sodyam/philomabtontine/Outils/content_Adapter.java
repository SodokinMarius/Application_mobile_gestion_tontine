package com.sodyam.philomabtontine.Outils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Client;

import java.util.List;
import java.util.zip.Inflater;

public class content_Adapter extends BaseAdapter {
    private Context context;
    private List <T_Client> listeClients;
    private LayoutInflater inflater;

    public content_Adapter(Context context, List<T_Client> listeClients) {
        this.context = context;
        this.listeClients = listeClients;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.listeClients.size();
    }

    @Override
    public T_Client getItem(int position) {
        return  listeClients.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.content_adapter,null);
        T_Client currentClient=getItem(i);
        String nomClient=currentClient.getNom();
        String prenomClient=currentClient.getPrenom();
        String telepnone=currentClient.getTelephone();


        return null;
    }
}
