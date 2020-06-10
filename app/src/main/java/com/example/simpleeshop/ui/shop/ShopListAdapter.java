package com.example.simpleeshop.ui.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.Products;

import java.util.ArrayList;

import static com.example.simpleeshop.MyApplication.getImageId;

public class ShopListAdapter extends ArrayAdapter<Products> {

//    List<String> Items = null;
    Context mContext;
    int mResource;


    public ShopListAdapter(Context context, int resource, ArrayList<Products> productsArray) {
        super(context, resource, productsArray);
        this.mContext = context;
        this.mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the products information

        Products product = getItem(position);

        MyAppDatabase db = MyAppDatabase.Instance();
        String imageString = db.myDao().getImagePathByProductId(product.getId());

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false); // This can potentially cause problems, because it loads every item.

        TextView prName = (TextView) convertView.findViewById(R.id.productNameText);
        TextView prPrice = (TextView) convertView.findViewById(R.id.productPriceText);
        TextView prReserve = (TextView) convertView.findViewById(R.id.productReservedText);
        ImageView prImage = (ImageView) convertView.findViewById(R.id.productImageView);

        prImage.setImageResource(getImageId(imageString));
        prName.setText(product.getName());
        String priceText = String.format("%.1f €", product.getPrice());
        prPrice.setText(priceText);
        prReserve.setText(String.valueOf(product.getReserve()));


        return  convertView;

    }


}
