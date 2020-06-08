package com.example.simpleeshop.ui.administrator;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simpleeshop.MyApplication;
import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.OrderedItems;
import com.example.simpleeshop.database.Orders;
import com.example.simpleeshop.database.Products;

import java.util.List;

import static com.example.simpleeshop.MyApplication.getImageId;


public class ProductsList extends Fragment {

    TableLayout productsListTable;
    View root;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_products_list, container, false);
        productsListTable = root.findViewById(R.id.products_list_table);

        initializeProductsListTable();

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initializeProductsListTable(){
        MyAppDatabase db = MyAppDatabase.Instance();

        // todo call getProductsSales

        List<Products> productsList = db.myDao().getProducts();


        for(Products product : productsList) {
            String imagePath = db.myDao().getImagePath(product.getId());
            List<Integer> orderedItemQuantityListList = db.myDao().getProductSales(product.getId());
            int soldItems = 0;
            for(Integer i : orderedItemQuantityListList) {
                soldItems += i;
            }
            addRow(imagePath, product.getName(), product.getPrice(), product.getReserve(), soldItems);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void resetOrdersTable() {
        clearUserOrdersTable();
        initializeProductsListTable();
    }

    private void clearUserOrdersTable(){
        // Remove all table rows except the first one
        int childCount = productsListTable.getChildCount();
        if (childCount > 1) {
            productsListTable.removeViews(1, childCount - 1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addRow(String imagePath, final String name, double price, int reserve, int sales) {
        final Context context = MyApplication.Context();

        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TableRow.LayoutParams textParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f);

        TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.5f);

        TableRow.LayoutParams imgParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f);

        buttonParams.width = 80;

        imgParams.width = 100;
        imgParams.height = 100;

        TableRow productListTableRow = new TableRow(context);
        productListTableRow.setLayoutParams(rowParams);

        productListTableRow.setGravity(Gravity.CENTER);

        ImageView imagePathText = new ImageView(context);
        TextView nameText  = new TextView(context);
        TextView priceText = new TextView(context);
        TextView reserveText = new TextView(context);
        TextView salesTest = new TextView(context);
        Button editButton = new Button(context);

        imagePathText.setForegroundGravity(Gravity.CENTER);
        nameText.setGravity(Gravity.CENTER);
        priceText.setGravity(Gravity.CENTER);
        reserveText.setGravity(Gravity.CENTER);
        salesTest.setGravity(Gravity.CENTER);
        editButton.setGravity(Gravity.CENTER);

        imagePathText.setLayoutParams(imgParams);
        nameText.setLayoutParams(textParams);
        priceText.setLayoutParams(textParams);
        reserveText.setLayoutParams(textParams);
        salesTest.setLayoutParams(textParams);
        editButton.setLayoutParams(buttonParams);

        imagePathText.setImageResource(getImageId(imagePath));
        nameText.setText(name);
        priceText.setText(price + "€");
        reserveText.setText(Integer.toString(reserve));
        salesTest.setText(Integer.toString(sales));
        editButton.setText(">");

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Product: " + name, Toast.LENGTH_SHORT).show();
            }
        });

        productListTableRow.addView(imagePathText);
        productListTableRow.addView(nameText);
        productListTableRow.addView(priceText);
        productListTableRow.addView(reserveText);
        productListTableRow.addView(salesTest);
        productListTableRow.addView(editButton);

        productsListTable.addView(productListTableRow);
//        listView.setAdapter(cartListAdapter);
    }
}