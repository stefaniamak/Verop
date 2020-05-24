package com.example.simpleeshop.ui.shop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.simpleeshop.R;

import java.util.ArrayList;
import java.util.List;

public class ShopListAdapter extends ArrayAdapter<String> {

    List<String> Items = new ArrayList<>();
    Context context;

    public ShopListAdapter(@NonNull Context context, List<String> Items) {
        super(context, R.layout.item_layout, Items);
        this.context = context;
        this.Items = Items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(R.layout.item_layout, parent, false);
        TextView ItemName = row.findViewById(R.id.shop_items);
        ItemName.setText(Items.get(position));
        return row;
    }
}
