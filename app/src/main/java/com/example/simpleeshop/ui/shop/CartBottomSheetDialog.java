package com.example.simpleeshop.ui.shop;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.simpleeshop.MyApplication;
import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.Products;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.function.BiConsumer;

public class CartBottomSheetDialog extends BottomSheetDialogFragment {

    TableLayout cartTable;
    View root;
    ListView listView;
    ShopListAdapter cartListAdapter;
    Products product;

    //TextView clickedProduct;

    TextView clickedTotal;
    
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_cart, container, false);

//        View convertView;
//        cartTable = (TableLayout) convertView.findViewById(R.id.cartTable);

        initializeCartTable();

        return root;
    }

    public void addNewItemToTable(Products product){

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initializeCartTable(){
        Hashtable<Integer,Integer> totalProducts = Cart.Instance().TotalProducts();
        MyAppDatabase db = MyAppDatabase.Instance();


        for(int id : totalProducts.keySet()) {
            product = db.myDao().getProduct(id);
            addRow(product.getName(), product.getPrice(), totalProducts.get(id));
        }

    }



    public void addRow(String product, double price, int count) {
        cartTable = root.findViewById(R.id.cartTable);
        Context context = MyApplication.Context();

        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TableRow.LayoutParams textParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f);

        TableRow cartTableRow = new TableRow(context);
        cartTableRow.setLayoutParams(rowParams);

        cartTableRow.setGravity(Gravity.CENTER);

        TextView clickedProduct = new TextView(context);
        TextView clickedPrice  = new TextView(context);
        TextView clickedQuantity = new TextView(context);
        TextView clickedTotal = new TextView(context);

        clickedProduct.setGravity(Gravity.CENTER);
        clickedPrice.setGravity(Gravity.CENTER);
        clickedQuantity.setGravity(Gravity.CENTER);
        clickedTotal.setGravity(Gravity.CENTER);

        clickedProduct.setLayoutParams(textParams);
        clickedPrice.setLayoutParams(textParams);
        clickedQuantity.setLayoutParams(textParams);
        clickedTotal.setLayoutParams(textParams);

        clickedProduct.setText(product);
        clickedPrice.setText(price + "€");
        clickedQuantity.setText(Integer.toString(count));
        price = price * count;
        clickedTotal.setText(price + "€");

        cartTableRow.addView(clickedProduct);
        cartTableRow.addView(clickedPrice);
        cartTableRow.addView(clickedQuantity);
        cartTableRow.addView(clickedTotal);

        cartTable.addView(cartTableRow);
//        listView.setAdapter(cartListAdapter);
    }
}
