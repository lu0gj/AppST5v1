package com.example.appst5v1;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import androidx.lifecycle.MutableLiveData;

public class LoadJson {


    public static MutableLiveData<String> loadJson(String url){
            MutableLiveData<String> strResult= new MutableLiveData<>();
            final AsyncTask<Void, Void, Void> mTask = new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        strResult.setValue(getJsonFromServer(url));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);
                }

            };

        mTask.execute();
        return strResult;
    }

    public static String getJsonFromServer(String url) throws IOException {

        BufferedReader inputStream = null;

        URL jsonUrl = new URL(url);
        URLConnection dc = jsonUrl.openConnection();

        dc.setConnectTimeout(5000);
        dc.setReadTimeout(5000);

        inputStream = new BufferedReader(new InputStreamReader(
                dc.getInputStream()));

        // read the JSON results into a string
        String jsonResult = inputStream.readLine();
        for(int i = 0; i<10;i++){
            jsonResult+=inputStream.readLine();
        }

        return jsonResult;
    }
}
