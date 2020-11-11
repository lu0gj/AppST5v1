package com.example.appst5v1.ui;

import android.app.Activity;
import android.content.Intent;


public class ActivityManager {
    private Activity actualActivity;
    private final Class accueil = Accueil.class;
    private final Class pageprincipale = PagePrincipale.class;

    public ActivityManager(Activity activity){
        this.actualActivity=activity;
    }

    private void launchActivity(Class myclass){
        Intent intent = new Intent(actualActivity,myclass);
        actualActivity.finish();
        actualActivity.startActivity(intent);

    }

    public void launchPagePrincipale(){
        launchActivity(pageprincipale);
    }
    public void launchAccueil(){
        launchActivity(accueil);
    }
}
