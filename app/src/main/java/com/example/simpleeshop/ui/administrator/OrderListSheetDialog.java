package com.example.simpleeshop.ui.administrator;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;


public class OrderListSheetDialog extends BottomSheetDialogFragment {

    View root;
    OrdersList parent;
    TableLayout orderTable;
    int orderId;
    double totalCost;
    TextView quantityTextView, totalCostTextView;
    Button delete, update;
    List<OrderedItems> totalProductsOrdered;

    public OrderListSheetDialog(OrdersList parent, int orderId){
        this.parent = parent;
        this.orderId = orderId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_order_list_sheet_dialog, container, false);

        orderTable = root.findViewById(R.id.productListDetailsTable);
        delete = root.findViewById(R.id.deleteListOrder);
        update = root.findViewById(R.id.editListOrder);
        totalCostTextView = root.findViewById(R.id.totalListCost);

        initializeCartTable();
        layoutVisibility();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOrder();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOrder();
            }
        });

        return root;
    }

    private void layoutVisibility(){
        MyAppDatabase db = MyAppDatabase.Instance();
        totalProductsOrdered = db.myDao().getOrderedProductsIds(orderId);
        if(!totalProductsOrdered.isEmpty()){
            totalCostTextView.setVisibility(View.VISIBLE);
            totalCostTextView.setText("Total: " + totalCost + "€");
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        clearBottomSheetMap();
    }

    private void clearBottomSheetMap(){
        // Remove all table rows except the first one
        int childCount = orderTable.getChildCount();
        if (childCount > 1) {
            orderTable.removeViews(1, childCount - 1);
        }
    }

    private void initializeCartTable(){
        MyAppDatabase db = MyAppDatabase.Instance();
        totalProductsOrdered = db.myDao().getOrderedProductsIds(orderId);
        totalCost = 0;
        int listItem = 0;

        for(OrderedItems order : totalProductsOrdered) {
            Products product = db.myDao().getProduct(order.getPid());
            int q = order.getQuantity();
            addRow(product.getName(), product.getPrice(), q, listItem);
            totalCost += q * product.getPrice();
            listItem ++;
        }
    }

    private void updateOrder(){
        MyAppDatabase db = MyAppDatabase.Instance();
        totalProductsOrdered = db.myDao().getOrderedProductsIds(orderId);


        Toast.makeText(getActivity(), "Order updated!", Toast.LENGTH_SHORT).show();

    }

    private void deleteOrder(){

        MyAppDatabase db = MyAppDatabase.Instance();

        // Updates Product Reserve
        OrderedItems orderedItems = new OrderedItems();
        List<OrderedItems> orderedItemsList = db.myDao().getOrderedProductsIds(orderId);

        for(OrderedItems orderedItem : orderedItemsList){
            // Gets Ordered quantity
            int productQuantity = orderedItem.getQuantity();

            // Updates Products
            Products product;
            int originalReserve = db.myDao().getProductReserve(orderedItem.getPid());
            db.myDao().updateProductReserve(orderedItem.getPid(), originalReserve + productQuantity);

        }

        // Deletes order
        Orders order = new Orders();
        order.setId(orderId);
        db.myDao().deleteOrder(order);

        parent.resetOrdersTable();

        // Closes Button Sheet Dialog
        this.dismiss();
        Toast.makeText(getActivity(), "Order canceled.", Toast.LENGTH_SHORT).show();
    }

    private void addRow(String product, double price, final int count, final int listItem) {
        Context context = MyApplication.Context();

        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TableRow.LayoutParams textParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f);

        TableRow.LayoutParams innerLayoutParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f);
        innerLayoutParams.width = 35;
        innerLayoutParams.height = 100;

        TableRow orderTableRow = new TableRow(context);
        orderTableRow.setLayoutParams(rowParams);

        orderTableRow.setGravity(Gravity.CENTER);

        TextView clickedProduct = new TextView(context);
        TextView clickedPrice  = new TextView(context);
        LinearLayout quantityLinearLayout = new LinearLayout(context);
            Button decreaseQuantity = new Button(context);
            final TextView clickedQuantity = new TextView(context);
            Button increaseQuantity = new Button(context);
        TextView clickedTotal = new TextView(context);

        clickedProduct.setGravity(Gravity.CENTER);
        clickedPrice.setGravity(Gravity.CENTER);
        quantityLinearLayout.setGravity(Gravity.CENTER);
            decreaseQuantity.setGravity(Gravity.CENTER);
            clickedQuantity.setGravity(Gravity.CENTER);
            increaseQuantity.setGravity(Gravity.CENTER);
        clickedTotal.setGravity(Gravity.CENTER);

        clickedProduct.setLayoutParams(textParams);
        clickedPrice.setLayoutParams(textParams);
        quantityLinearLayout.setLayoutParams(textParams);
            decreaseQuantity.setLayoutParams(innerLayoutParams);
            clickedQuantity.setLayoutParams(innerLayoutParams);
            increaseQuantity.setLayoutParams(innerLayoutParams);
        clickedTotal.setLayoutParams(textParams);

        clickedProduct.setText(product);
        clickedPrice.setText(price + "€");
            decreaseQuantity.setText("-");
            clickedQuantity.setText(Integer.toString(count));
            increaseQuantity.setText("+");
        price = price * count;
        clickedTotal.setText(price + "€");

        quantityLinearLayout.addView(decreaseQuantity);
        quantityLinearLayout.addView(clickedQuantity);
        quantityLinearLayout.addView(increaseQuantity);

        orderTableRow.addView(clickedProduct);
        orderTableRow.addView(clickedPrice);
        orderTableRow.addView(quantityLinearLayout);
        orderTableRow.addView(clickedTotal);

        orderTable.addView(orderTableRow);

        // Increase - Decrease buttons listeners

        increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedQuantity.setText(updateQuantity(1, listItem));
            }
        });
        decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalProductsOrdered.get(listItem).getQuantity() > 0){
                    clickedQuantity.setText(updateQuantity(-1, listItem));
                }
            }
        });
//        listView.setAdapter(cartListAdapter);
    }

    private String updateQuantity(int modifier, int listItem){
        int modifiedQuantity = totalProductsOrdered.get(listItem).getQuantity() + modifier;
        totalProductsOrdered.get(listItem).setQuantity(modifiedQuantity);
        return Integer.toString(modifiedQuantity);
    }
}