package com.example.simpleeshop.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.simpleeshop.R;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    private ShopViewModel shopViewModel;
    ListView listView;
    ListAdapter adapter;

    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shopViewModel =
                ViewModelProviders.of(this).get(ShopViewModel.class);
        root = inflater.inflate(R.layout.fragment_shop, container, false);

        //        getShopItems();


        /*
        final TextView textView = root.findViewById(R.id.text_gallery);
        shopViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */

        initializeList();


        return root;
    }

    private void initializeList() {
        listView = root.findViewById(R.id.shop_list_view);


        List<String> itemList = new ArrayList<>();

        String[] items = getResources().getStringArray(R.array.item_test_list);
            for (String item:items){
                itemList.add(item);
            }

        adapter = new ShopListAdapter(root.getContext(), itemList);

        listView.setAdapter(adapter);
    }
}
