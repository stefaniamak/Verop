package com.example.simpleeshop.ui.administrator_profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdministratorProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdministratorProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is your profile");
    }

    public LiveData<String> getText() {
        return mText;
    }
}