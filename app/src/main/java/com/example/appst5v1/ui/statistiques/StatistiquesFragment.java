package com.example.appst5v1.ui.statistiques;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appst5v1.LoadJson;
import com.example.appst5v1.MainActivity;
import com.example.appst5v1.R;
import com.example.appst5v1.ui.ActivityManager;
import com.example.appst5v1.ui.PagePrincipale;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class StatistiquesFragment extends Fragment {

    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    private StatistiquesViewModel mViewModel;
    private int id = MainActivity.id_user;
    private int i =0;

    public StatistiquesFragment(){
        super();
    }
    public static StatistiquesFragment newInstance() {
        return new StatistiquesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.statistiques_fragment, container, false);
        // data

        System.out.println(MainActivity.getMesures()+" ----------------- ");
        series = new LineGraphSeries<>();
        GraphView graph = (GraphView) rootView.findViewById(R.id.graphstatistique);

        graph.addSeries(series);
        // customize a little bit viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(30);
        viewport.setMaxY(200);
        viewport.setScrollable(true);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // we're going to simulate real time with thread that append data to the graph
        new Thread(() -> {
            // we add 100 new entries
            for(Activity myAct = getActivity();myAct!=null;myAct=getActivity()){
                myAct.runOnUiThread(() -> addEntry());
                // sleep to slow down the add of entries
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // manage error ...
                }
            }
        }).start();
    }
    // add random data to graph
    private void addEntry() {
        // here, we choose to display max 10 points on the viewport and we scroll to end
        System.out.println(i+"zaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        JSONObject jsonObject= MainActivity.getMesures()[i];
        try {
            String date = jsonObject.getString("date_prise");
            int x = (((int) date.charAt(11))*10 +((int) date.charAt(12)))*3600+(((int) date.charAt(14))*10+((int) date.charAt(15)))*60 + ((int) date.charAt(17))*10+(int) date.charAt(18);
            int value = Integer.parseInt(jsonObject.getString("niveau_glycemine"));
            series.appendData(new DataPoint(x, value),false, 20);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        i++;
    }
}