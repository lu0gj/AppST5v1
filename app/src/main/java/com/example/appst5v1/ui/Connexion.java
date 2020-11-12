package com.example.appst5v1.ui;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appst5v1.LoadJson;
import com.example.appst5v1.MainActivity;
import com.example.appst5v1.R;

import org.json.JSONObject;
import org.w3c.dom.Text;


public class Connexion {
    private final AppCompatActivity appCompatActivity;
    private boolean isDoctor;
    public Connexion(AppCompatActivity appCompatActivity){
        this.appCompatActivity=appCompatActivity;
    }
    public void init(boolean isDoctor) {
        this.isDoctor = isDoctor;
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
            int tmp = tentative(pseudo,mdp);
            TextView textError = (TextView) appCompatActivity.findViewById(R.id.error_text);
            if(tmp == -1){
                textError.setText(R.string.errorCon);
            }else if(tmp == -2){
                textError.setText(R.string.wrongEmail);
            }
        });
    }

    public int tentative(String pseudo, String mdp){
        String str = LoadJson.getStringFromUrl(String.format("http://webprog-dev.com/getInfos/connexion.php?email=%s&mdp=%s",pseudo,mdp));
        str = str.substring(1, str.length() - 1);
        int id = -1;
        try{
            id = Integer.parseInt(str);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return -1;
        }
        if(id > 0){
            MainActivity.id_user = id;
            MainActivity.activityManager.launchPagePrincipale(id);
        }
        return -2;
    }
}
