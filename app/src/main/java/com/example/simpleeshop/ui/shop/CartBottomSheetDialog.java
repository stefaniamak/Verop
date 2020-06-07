package com.example.simpleeshop.ui.shop;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    TextView cartEmpty, totalCostTextView;
    Button clear, confirm;
    double totalCost;

    //TextView clickedProduct;

    TextView clickedTotal;
    
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_cart, container, false);

        cartEmpty = root.findViewById(R.id.cartEmpty);
        clear = root.findViewById(R.id.clear);
        confirm = root.findViewById(R.id.confirm);
        totalCostTextView = root.findViewById(R.id.totalCost);


        initializeCartTable();
        layoutVisibility();

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCart();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPurchase();
            }
        });

        return root;
    }

    private void layoutVisibility(){
        Hashtable<Integer,Integer> totalProducts = Cart.Instance().TotalProducts();
        if(!totalProducts.isEmpty()){
            cartEmpty.setVisibility(View.GONE);
            clear.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.VISIBLE);
            totalCostTextView.setVisibility(View.VISIBLE);
            totalCostTextView.setText("Total: " + totalCost + "€");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initializeCartTable(){
        Hashtable<Integer,Integer> totalProducts = Cart.Instance().TotalProducts();
        MyAppDatabase db = MyAppDatabase.Instance();
        totalCost = 0;

        for(int id : totalProducts.keySet()) {
            product = db.myDao().getProduct(id);
            addRow(product.getName(), product.getPrice(), totalProducts.get(id));
            totalCost += totalProducts.get(id) * product.getPrice();
        }

    }

    private void confirmPurchase(){
        Hashtable<Integer,Integer> totalProducts = Cart.Instance().TotalProducts();

    }

    private void clearCart(){
        // Remove all table rows except the first one
        int childCount = cartTable.getChildCount();
        if (childCount > 1) {
            cartTable.removeViews(1, childCount - 1);
        }
        Cart.Instance().ClearProducts();

        // Layout visibility reverse
        cartEmpty.setVisibility(View.VISIBLE);
        clear.setVisibility(View.GONE);
        confirm.setVisibility(View.GONE);
        totalCostTextView.setVisibility(View.GONE);
    }

    private void addRow(String product, double price, int count) {
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
