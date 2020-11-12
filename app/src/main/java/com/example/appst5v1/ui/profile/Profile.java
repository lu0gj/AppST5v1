package com.example.appst5v1.ui.profile;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.appst5v1.MainActivity;
import com.example.appst5v1.R;
import com.example.appst5v1.ui.ActivityManager;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends Fragment {

    private ProfileViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.profile_fragment, container, false);

        JSONObject info_user = MainActivity.getInfoPatient();
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

}}