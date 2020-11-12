package com.example.appst5v1.ui.proche;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.appst5v1.MainActivity;
import com.example.appst5v1.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ProcheElemFragment extends Fragment {
    private ProcheViewModel mViewModel;
    private String name;
    private String lien;
    private String phone_number;

    public ProcheElemFragment(String name, String lien, String phone_number){
        super();
        this.name=name;
        this.lien=lien;
        this.phone_number=phone_number;

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(ProcheViewModel.class);
        View root = inflater.inflate(R.layout.proche_elem_fragment, container, false);
        TextView nameView = (TextView) root.findViewById(R.id.proche_elem_name);
        nameView.setText(name);
        TextView lienView = (TextView) root.findViewById(R.id.proche_elem_lien);
        lienView.setText(lien);

        Button butCall = (Button) root.findViewById(R.id.proche_elem_call_button);
        butCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.activityManager.callNumber(phone_number);
            }
        });
        return root;
    }
}
