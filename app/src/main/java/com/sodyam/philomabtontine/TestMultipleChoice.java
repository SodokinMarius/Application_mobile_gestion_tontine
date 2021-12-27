package com.sodyam.philomabtontine;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class TestMultipleChoice extends AppCompatActivity {
    private TextInputLayout mTextInputLayout;
    private AutoCompleteTextView mAutoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_multiple_choice);
        mAutoCompleteTextView=(AutoCompleteTextView) findViewById(R.id.aChoicePoste);
        mTextInputLayout=(TextInputLayout) findViewById(R.id.postes);

        String [] postes_dispo={"SUPERVISEUR","SECRETAIRE","TRESORIER(E)","AGENT DE COLLECT"};
        ArrayAdapter <String> adapterItem=new ArrayAdapter<>(TestMultipleChoice.this,R.layout.test_multiple_item,postes_dispo);
        mAutoCompleteTextView.setAdapter(adapterItem);
        mAutoCompleteTextView.setThreshold(1);
    }
}