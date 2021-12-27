package com.sodyam.philomabtontine.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.Vue.Authentification;


public class SpinnerActivity extends Authentification implements AdapterView.OnItemSelectedListener {


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) findViewById(R.id.poste_entre);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
