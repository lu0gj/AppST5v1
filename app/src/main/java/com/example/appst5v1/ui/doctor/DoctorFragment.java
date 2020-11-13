package com.example.appst5v1.ui.doctor;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.appst5v1.MainActivity;
import com.example.appst5v1.R;
import com.example.appst5v1.ui.profile.ProfileViewModel;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class DoctorFragment extends Fragment {

    private DoctorViewModel mViewModel;
    private String str;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(DoctorViewModel.class);



        JSONObject info_user = MainActivity.getinfomedecin();
        if(info_user == null) {
            View root2 = inflater.inflate(R.layout.layout_error, container, false);
            ((TextView)root2.findViewById(R.id.text_layout_error)).setText(R.string.doctor_dont_load);
            return root2;
        }
        View root = inflater.inflate(R.layout.medecin_fragment, container, false);
        try {
            ((TextView) root.findViewById(R.id.firstname_profile)).setText(info_user.getString("prenom"));
            ((TextView) root.findViewById(R.id.lastname_profile)).setText(info_user.getString("nom"));
            ((TextView) root.findViewById(R.id.email_profile)).setText(info_user.getString("email"));
            ((TextView) root.findViewById(R.id.phone_profile)).setText(info_user.getString("tel"));
            ((TextView) root.findViewById(R.id.location_profile)).setText(info_user.getString("adresse"));
        }catch (JSONException e) {
            e.printStackTrace();
        }

        Button butCall = (Button) root.findViewById(R.id.action_call);
        butCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MainActivity.activityManager.callNumber( ((TextView) root.findViewById(R.id.phone_profile)).getText().toString());
            }

        });
        return root;

    }
}