package com.example.appst5v1.ui.proche;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appst5v1.MainActivity;
import com.example.appst5v1.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import org.json.JSONException;
import org.json.JSONObject;

public class ProcheFragment extends Fragment {
    private ProcheViewModel mViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(ProcheViewModel.class);

        JSONObject[] info_proches = MainActivity.getinfoProche();

        if(info_proches == null) {
            View root2 = inflater.inflate(R.layout.layout_error, container, false);
            ((TextView)root2.findViewById(R.id.text_layout_error)).setText(R.string.aucun_proche);
            return root2;
        }

        View root = inflater.inflate(R.layout.proche_fragment, container, false);


        LinearLayout rootViewContainer = (LinearLayout) root.findViewById(R.id.container);
        rootViewContainer.removeAllViews();

        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction;


        for (JSONObject js :info_proches) {
            try {
                String name = js.getString("nom");
                String lien = js.getString("lien");
                String phone = js.getString("tel");
                fragTransaction = fragMan.beginTransaction();
                fragTransaction.add(rootViewContainer.getId(), new ProcheElemFragment(name, lien, phone), "fragment");
                fragTransaction.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return root;
    }
}
