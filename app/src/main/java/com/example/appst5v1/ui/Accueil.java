package com.example.appst5v1.ui;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appst5v1.R;

public class Accueil extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);
        Button patient = (Button) findViewById(R.id.patient);
        AppCompatActivity appCompatActivity = this;
        patient.setOnClickListener(v -> {
            Connexion connexion= new Connexion(appCompatActivity);
            connexion.init(false);
        });
        Button doctor = (Button) findViewById(R.id.doctor);
        doctor.setOnClickListener(v -> {
            Connexion connexion= new Connexion(appCompatActivity);
            connexion.init(true);
        });
    }
}
