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
import com.example.simpleeshop.database.User;
import com.example.simpleeshop.ui.account.UserOrders;

import java.util.List;

public class UsersList extends Fragment {

    TableLayout usersListTable;
    View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_users_list, container, false);

        usersListTable = root.findViewById(R.id.users_list_table);

        initializeUsersListTable();

        return root;
    }

    private void initializeUsersListTable(){
        MyAppDatabase db = MyAppDatabase.Instance();

        List< User > usersList = db.myDao().getUsers();
        for(User user : usersList) {
            addRow(user.getId(), user.getUsername());
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

    private void addRow(int orderId, String username) {
        Context context = MyApplication.Context();

        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TableRow.LayoutParams textParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f);

        TableRow userListTableRow = new TableRow(context);
        userListTableRow.setLayoutParams(rowParams);

        userListTableRow.setGravity(Gravity.CENTER);

        TextView userIdListItem = new TextView(context);
        TextView usernameListItem  = new TextView(context);

        userIdListItem.setGravity(Gravity.CENTER);
        usernameListItem.setGravity(Gravity.CENTER);

        userIdListItem.setLayoutParams(textParams);
        usernameListItem.setLayoutParams(textParams);

        userIdListItem.setText(Integer.toString(orderId));
        usernameListItem.setText(username);

        userListTableRow.addView(userIdListItem);
        userListTableRow.addView(usernameListItem);

        usersListTable.addView(userListTableRow);

    }
}