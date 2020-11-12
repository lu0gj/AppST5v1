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
import com.example.appst5v1.R;
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
    private final int id = ((PagePrincipale)getActivity()).getId();
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
        series = new LineGraphSeries<>();
        GraphView graph = (GraphView) rootView.findViewById(R.id.graphstatistique);

        graph.addSeries(series);
        // customize a little bit viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(10);
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
                myAct.runOnUiThread(() -> addEntry(id));
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
    private void addEntry(int id) {
        // here, we choose to display max 10 points on the viewport and we scroll to end
        JSONObject jsonObject= LoadJson.Jsonr(String.format("http://webprog-dev.com/getInfos/recupMesure.php?id_patient=%d",id));
        try {
            JSONObject js=jsonObject.getJSONObject(String.valueOf(id));
            series.appendData(new DataPoint(lastX++, RANDOM.nextDouble() * 10d), false, 10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}