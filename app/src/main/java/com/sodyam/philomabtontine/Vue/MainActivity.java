package com.sodyam.philomabtontine.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.sodyam.philomabtontine.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Runnable executable = new Runnable() {
            @Override
            public void run() {
                Intent VersAutentification = new Intent(MainActivity.this, Authentification.class);
                startActivity(VersAutentification);
                finish();
            }
        };

        /**
         *  android:backgroundTint="#4CAF50"
         */
        //Handler post deplayed
        new Handler().postDelayed(executable, 3000);
    }
}