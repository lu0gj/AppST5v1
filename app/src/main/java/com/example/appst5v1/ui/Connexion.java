package com.example.appst5v1.ui;

import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appst5v1.R;


public class Connexion {
    private final AppCompatActivity appCompatActivity;
    public Connexion(AppCompatActivity appCompatActivity){
        this.appCompatActivity=appCompatActivity;
    }
    public void init(boolean b) {
        appCompatActivity.setContentView(R.layout.connexion);
        recup();
    }
    private  void recup(){
        Button validate = (Button) appCompatActivity.findViewById(R.id.validate);
        EditText pseudoText = (EditText) appCompatActivity.findViewById(R.id.pseudo);
        EditText mdpText = (EditText) appCompatActivity.findViewById(R.id.Pseudo);

        validate.setOnClickListener(v -> {
            String pseudo = pseudoText.getText().toString();
            String mdp = mdpText.getText().toString();
            tentative(pseudo,mdp);
        });
    }
    public void tentative(String pseudo, String mdp){
        ActivityManager activityManager = new ActivityManager(appCompatActivity);
        activityManager.launchPagePrincipale();
    }
}
