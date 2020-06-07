package com.example.simpleeshop.ui.shop;

import android.app.Activity;
import android.app.Person;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.simpleeshop.R;
import com.example.simpleeshop.database.Products;

import java.util.ArrayList;
import java.util.List;

public class ShopListAdapter extends ArrayAdapter<Products> {

//    List<String> Items = null;
    Context mContext;
    int mResource;


    public ShopListAdapter(Context context, int resource, ArrayList<Products> productsArray) {
        super(context, resource, productsArray);
        this.mContext = context;
        this.mResource = resource;
    }

//    public ShopListAdapter(@NonNull Context context, List<String> Items) {
//        super(context, R.layout.item_layout, Items);
//        this.context = context;
//        this.Items = Items;
//    }
//
//    public ShopListAdapter(@NonNull Context context) {
//        super(context, R.layout.item_layout);
//        this.context = context;
//    }
//
//    public ShopListAdapter(ShopFragment shopFragment, int fragment_shop_item, ArrayList<Products> productsArray) {
//        super();
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the products information
        int productId = getItem(position).getId();
        String name = getItem(position).getName();
        double price = getItem(position).getPrice();
        int imageId = getItem(position).getImageId();
        int reserve = getItem(position).getReserve();

        // Create the product object with the information
        Products product = new Products();
        product.setId(productId);
        product.setName(name);
        product.setPrice(price);
        product.setImageId(imageId);
        product.setReserve(reserve);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false); // This can potentially cause problems, because it loads every item.

        TextView prName = (TextView) convertView.findViewById(R.id.productNameText);
        TextView prPrice = (TextView) convertView.findViewById(R.id.productPriceText);
        TextView prReserve = (TextView) convertView.findViewById(R.id.productReservedText);
        ImageView prImage = (ImageView) convertView.findViewById(R.id.productImageView);
        // TODO: ADD IMAGE TOO
//        String prImagePath = getURLForResource(R.drawable.ic_stay_home_color);
        prImage.setImageResource(R.drawable.ic_stay_home_color);


        prName.setText(name);
        prPrice.setText(price + "€");
        prReserve.setText("Items left: " + reserve);


//        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
//        prImage.setImageResource(prImagePath);


        return  convertView;

//        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//        View row = inflater.inflate(R.layout.item_layout, parent, false);
//        TextView ItemName = row.findViewById(R.id.shop_items);
//        ItemName.setText(Items.get(position));
//        return row;
    }

    public String getURLForResource (int resourceId) {
        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +resourceId).toString();
    }
}
