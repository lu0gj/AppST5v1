package com.example.appst5v1.ui.statistiques;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.appst5v1.LoadJson;
import com.example.appst5v1.MainActivity;
import com.example.appst5v1.R;
import com.example.appst5v1.ui.ActivityManager;
import com.example.appst5v1.ui.PagePrincipale;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        JSONObject[] list_mesures = MainActivity.getMesures();

        if(list_mesures == null) {
            View root2 = inflater.inflate(R.layout.layout_error, container, false);
            ((TextView)root2.findViewById(R.id.text_layout_error)).setText(R.string.stats_dont_load);
            return root2;
        }


        View rootView = inflater.inflate(R.layout.statistiques_fragment, container, false);
        // data


        GraphView graph = (GraphView) rootView.findViewById(R.id.graphstatistique);
        ArrayList<MyDataPoint> datas = new ArrayList<>();


        for(int i = 0; i<list_mesures.length;i++){
            try {
                String[] date = list_mesures[i].getString("date_prise").split("-");
                Date datePrise = new Date(Integer.parseInt(date[2])-1900, Integer.parseInt(date[1]), Integer.parseInt(date[0]), Integer.parseInt(date[3]),Integer.parseInt(date[4]), Integer.parseInt(date[5]));
                int niv_gly = list_mesures[i].getInt("niveau_glycemine");
                datas.add(new MyDataPoint(datePrise,niv_gly));
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        datas.sort(new Comparator<MyDataPoint>() {

            public int compare(MyDataPoint s1, MyDataPoint s2) {

                //ascending order
                return s1.compareTo(s2);

                //descending order
                //return StudentName2.compareTo(StudentName1);
            }});

        MyDataPoint[] mydata = new MyDataPoint[datas.size()];
        for(int i = 0; i < mydata.length;i++){
            mydata[i] = datas.get(i);
        }

        series = new LineGraphSeries<DataPoint>(mydata);
        graph.addSeries(series);

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));



        // customize a little bit viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        //viewport.setMinY(30);
        //viewport.setMaxY(200);

        viewport.setScrollable(true);
        viewport.setScalable(true);
        //viewport.setScalableY(true);
        //viewport.setScrollableY(true);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // we're going to simulate real time with thread that append data to the graph
        /*new Thread(() -> {
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
        }).start();*/
    }

    class MyDataPoint extends DataPoint implements Comparable{
        private Date d;
        private int value;

        private MyDataPoint(Date d, int value){
            super(d,value);
            this.d = d;
            this. value = value;
        }


        @Override
        public int compareTo(Object o) {
            MyDataPoint toCompare = (MyDataPoint)o;
            return this.d.compareTo(toCompare.d);
        }
    }
}