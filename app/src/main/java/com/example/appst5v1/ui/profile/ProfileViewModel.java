package com.example.appst5v1.ui.profile;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ProfileViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<String> mText;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        String titre = new String("test");
        mText.setValue(titre);
    }

    public LiveData<String> getText() {
        return mText;
    }
}