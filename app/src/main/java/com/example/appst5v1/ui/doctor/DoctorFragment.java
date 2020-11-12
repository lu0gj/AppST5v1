package com.example.appst5v1.ui.doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.appst5v1.MainActivity;
import com.example.appst5v1.R;
import com.example.appst5v1.ui.profile.ProfileViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class DoctorFragment extends Fragment {

    private DoctorViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(DoctorViewModel.class);
        View root = inflater.inflate(R.layout.profile_fragment, container, false);

        ((TextView) root.findViewById(R.id.firstname_profile)).setText("Médecin");

        Button butCall = (Button) root.findViewById(R.id.action_call);
        butCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MainActivity.activityManager.callNumber( ((TextView) root.findViewById(R.id.phone_profile)).getText().toString());
            }

        });
        return root;

    }}