package com.example.appst5v1.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;


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

    public void launchPagePrincipale(int id){
        launchActivity(pageprincipale);
        ((PagePrincipale) actualActivity).setId(id);
    }
    public void launchAccueil(){
        launchActivity(accueil);
    }

    public void  callNumber(String phone_number){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+phone_number));
        actualActivity.startActivity(callIntent);
    }
}
