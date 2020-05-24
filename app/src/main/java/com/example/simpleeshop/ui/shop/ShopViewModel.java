package com.example.simpleeshop.ui.shop;

import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simpleeshop.MainActivity;
import com.example.simpleeshop.R;

import java.util.ArrayList;
import java.util.List;

public class ShopViewModel extends ViewModel {


    private MutableLiveData<String> mText;

    public ShopViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");


    }



    public LiveData<String> getText() {
        return mText;
    }
}