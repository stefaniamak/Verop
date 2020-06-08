package com.example.simpleeshop.ui.administrator;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.simpleeshop.MainActivity;
import com.example.simpleeshop.MyApplication;
import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.Orders;
import com.example.simpleeshop.ui.account.UserOrders;

import java.util.List;

public class UsersList extends Fragment {

    TableLayout usersListTable;
    View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_user_orders, container, false);
        usersListTable = root.findViewById(R.id.users_list_table);

        initializeUsersListTable();

        return root;
    }

    private void initializeUsersListTable(){
        MyAppDatabase db = MyAppDatabase.Instance();

        List<Orders> usersList = db.myDao().getUserOrders(MyApplication.Instance().getSharedPreferenceConfig().readUserId());
        for(Orders order : usersList) {
            addRow(order.getId(), "random date");
        }
    }

    public void resetOrdersTable() {
        clearUserOrdersTable();
        initializeUsersListTable();
    }

    private void clearUserOrdersTable(){
        // Remove all table rows except the first one
        int childCount = usersListTable.getChildCount();
        if (childCount > 1) {
            usersListTable.removeViews(1, childCount - 1);
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

//        final UserOrders that = this;
//        editOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(context, "Order id: " + orderId, Toast.LENGTH_SHORT).show();
//                ((MainActivity)getActivity()).openOrderDetailsSheetDialog(that, orderId);
//                addItems(orderId);
//            }
//        });

//        orderTableRow.addView(editOrder);
//
//        usersListTable.addView(orderTableRow);
////        listView.setAdapter(cartListAdapter);
    }
}