package com.example.appst5v1.ui.proche;

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

public class ProcheFragment extends Fragment {
    private ProcheViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(ProcheViewModel.class);
        View root = inflater.inflate(R.layout.profile_fragment, container, false);


        return root;
    }
}
