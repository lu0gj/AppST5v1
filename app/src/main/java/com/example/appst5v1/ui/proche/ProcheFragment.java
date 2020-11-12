package com.example.appst5v1.ui.proche;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appst5v1.MainActivity;
import com.example.appst5v1.R;
import com.example.appst5v1.ui.profile.ProfileViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class ProcheFragment extends Fragment {
    private ProcheViewModel mViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(ProcheViewModel.class);
        View root = inflater.inflate(R.layout.proche_fragment, container, false);

        LinearLayout rootViewContainer = (LinearLayout) root.findViewById(R.id.container);
        rootViewContainer.removeAllViews();

        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction;





        fragTransaction = fragMan.beginTransaction();
        fragTransaction.add(rootViewContainer.getId(), new ProcheElemFragment("Gégé", "Grand père", "0385904877") , "fragment1");
        fragTransaction.commit();


        fragTransaction = fragMan.beginTransaction();
        fragTransaction.add(rootViewContainer.getId(), new ProcheElemFragment("Bilal","Ami (++)","0627171945") , "fragment2");
        fragTransaction.commit();

        return root;
    }
}
