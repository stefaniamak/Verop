package com.example.simpleeshop.ui.account;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simpleeshop.MainActivity;
import com.example.simpleeshop.MyApplication;
import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.OrderedItems;
import com.example.simpleeshop.database.Orders;

import java.util.List;

public class UserOrders extends Fragment {

    TableLayout ordersTable;
    View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_user_orders, container, false);
        ordersTable = root.findViewById(R.id.userOrdersTable);

        initializeOrdersTable();

        return root;
    }

    private void initializeOrdersTable(){
        MyAppDatabase db = MyAppDatabase.Instance();

        List<Orders> ordersList = db.myDao().getUserOrders(MyApplication.Instance().getSharedPreferenceConfig().readUserId());
        for(Orders order : ordersList) {
            addRow(order.getId(), "random date");
        }
    }

    public void resetOrdersTable() {
        clearUserOrdersTable();
        initializeOrdersTable();
    }

    private void clearUserOrdersTable(){
        // Remove all table rows except the first one
        int childCount = ordersTable.getChildCount();
        if (childCount > 1) {
            ordersTable.removeViews(1, childCount - 1);
        }
    }

    private void addRow(final int orderId, String date) {
        final Context context = MyApplication.Context();

        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TableRow orderTableRow = new TableRow(context);
        orderTableRow.setLayoutParams(rowParams);
        orderTableRow.setGravity(Gravity.CENTER);

        TextView orderIdText = new TextView(context);
        orderIdText.setGravity(Gravity.CENTER);
        orderIdText.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.5f));
        orderIdText.setText(Integer.toString(orderId));
        orderTableRow.addView(orderIdText);

        TextView orderDateText = new TextView(context);
        orderDateText.setGravity(Gravity.CENTER);
        orderDateText.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f));
        orderDateText.setText(date);
        orderTableRow.addView(orderDateText);


        Button editOrder = new Button(context);
        editOrder.setText("DETAILS");
        editOrder.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.25f));

        final UserOrders that = this;
        editOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).openOrderDetailsSheetDialog(that, orderId);
                addItems(orderId);
            }
        });

        orderTableRow.addView(editOrder);

        ordersTable.addView(orderTableRow);
    }

    private void addItems(int orderId){
        MyAppDatabase db = MyAppDatabase.Instance();
        List<OrderedItems> orderedProducts = db.myDao().getOrderedProductsIds(orderId);
        for(OrderedItems product : orderedProducts){
            DetailsMap.Instance().ShowOrderedProducts(product.getPid(), product.getQuantity());
        }
    }

}
