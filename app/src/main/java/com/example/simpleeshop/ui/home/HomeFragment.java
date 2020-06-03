package com.example.simpleeshop.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    int donationsNumber = 0;
    int purchasesNumber = 0;
    TextView donationsText;
    TextView purchasesText;

    View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        donationsText = root.findViewById(R.id.text_home_donations_number);
        purchasesText = root.findViewById(R.id.text_home_purchases_number);

        MyAppDatabase db = MyAppDatabase.Instance();
        List<Integer> purchases = db.myDao().getTotalPurchases();
        List<Integer> donations = db.myDao().getTotalDonations();

        for (int item:purchases){
            purchasesNumber = purchasesNumber + item ;
        }

        for (int item:donations){
            donationsNumber = donationsNumber + item ;
        }

        donationsText.setText(String.valueOf(donationsNumber));
        purchasesText.setText(String.valueOf(purchasesNumber));

        //initializeVariables();

        return root;
    }
/*
    private void initializeVariables() {
        donationsList = root.findViewById(R.id.text_home_donations_number);
        purchasesList = root.findViewById(R.id.text_home_purchases_number);
        donationsNumber = 0;
        purchasesNumber = 0;

        MyAppDatabase db = MyAppDatabase.Instance();
        List<Integer> purchases = db.myDao().getTotalPurchases();
        for (int item:purchases){
            purchasesNumber = purchasesNumber + item ;
        }

        List<Integer> donations = db.myDao().getTotalDonations();
        for (int item:donations){
            donationsNumber = donationsNumber + item ;
        }

        donationsList.setValue(donationsNumber);
        purchasesList.settext

        //adapter = new ShopListAdapter(root.getContext(), itemList);

        //listView.setAdapter(adapter);
    }
*/
}
