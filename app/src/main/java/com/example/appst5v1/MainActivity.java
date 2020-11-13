package com.example.appst5v1;

import android.os.Bundle;

import com.example.appst5v1.ui.ActivityManager;

import org.json.JSONObject;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static ActivityManager activityManager;
    public static int id_user = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager = new ActivityManager(this);
        activityManager.launchPagePrincipale(1);
    }

    public static JSONObject[] getMesures(){
        return LoadJson.getJsonArrayFromUrl("http://webprog-dev.com/getInfos/recupMesure.php?id_patient="+id_user);
    }

    public static JSONObject getInfoPatient(){
        return LoadJson.getJsonArrayFromUrl("http://webprog-dev.com/getInfos/infoPatient.php?id_patient="+id_user)[0];
    }

    public static JSONObject[] getinfoProche(){
        return LoadJson.getJsonArrayFromUrl("http://webprog-dev.com/getInfos/recupProche.php?id_patient"+id_user);
    }

    public static JSONObject getinfomedecin(){
        return LoadJson.getJsonArrayFromUrl("http://webprog-dev.com/getInfos/donneeMedecinPatient.php?id_patient="+id_user)[0];
    }



}
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_profile,R.id.nav_statistiques)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}*/