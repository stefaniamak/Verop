package com.example.simpleeshop.ui.account;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.simpleeshop.MyApplication;
import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.OrderedItems;
import com.example.simpleeshop.database.Orders;
import com.example.simpleeshop.database.Products;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Hashtable;
import java.util.List;

public class UserOrderEditBottomSheetDialog extends BottomSheetDialogFragment {

    View root;
    UserOrders parent;
    int orderId;

    TableLayout orderTable;
    Button delete, update;
    double totalCost;
    TextView totalCostTextView;
    Hashtable<Integer,Integer> totalProductsOrdered;

    public UserOrderEditBottomSheetDialog(UserOrders parent, int orderId){
        this.parent = parent;
        this.orderId = orderId;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_user_orders_dialog_sheet, container, false);

        orderTable = root.findViewById(R.id.productListDetailsTable);
        delete = root.findViewById(R.id.deleteOrder);
        totalCostTextView = root.findViewById(R.id.totalCost);

        initializeCartTable();
        layoutVisibility();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOrder();
            }
        });

        return root;
    }

    private void layoutVisibility(){
        totalProductsOrdered = DetailsMap.Instance().TotalProducts();
        if(!totalProductsOrdered.isEmpty()){
            totalCostTextView.setVisibility(View.VISIBLE);
            totalCostTextView.setText("Total: " + getPriceToString(totalCost));
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
        DetailsMap.Instance().ClearProducts();
    }

    private void initializeCartTable(){
        totalProductsOrdered = DetailsMap.Instance().TotalProducts();
        MyAppDatabase db = MyAppDatabase.Instance();
        totalCost = 0;

        for(int id : totalProductsOrdered.keySet()) {
            Products product = db.myDao().getProduct(id);
            addRow(product.getName(), product.getPrice(), totalProductsOrdered.get(id));
            totalCost += totalProductsOrdered.get(id) * product.getPrice();
        }
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

    private void addRow(String product, double price, int count) {
        Context context = MyApplication.Context();

        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TableRow.LayoutParams textParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f);

        TableRow.LayoutParams quantityParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.5f);

        TableRow orderTableRow = new TableRow(context);
        orderTableRow.setLayoutParams(rowParams);

        orderTableRow.setGravity(Gravity.CENTER);

        TextView clickedProduct = new TextView(context);
        TextView clickedPrice  = new TextView(context);
        TableLayout quantity = new TableLayout(context);
        TableRow quantityRow = new TableRow(context);
        TextView clickedQuantity = new TextView(context);
        Button increaseQuantity = new Button(context);
        Button decreaseQuantity = new Button(context);
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
        clickedPrice.setText(getPriceToString(price));
        clickedQuantity.setText(Integer.toString(count));
        price = price * count;
        clickedTotal.setText(getPriceToString(price));

        orderTableRow.addView(clickedProduct);
        orderTableRow.addView(clickedPrice);
        orderTableRow.addView(clickedQuantity);
        orderTableRow.addView(clickedTotal);

        orderTable.addView(orderTableRow);
//        listView.setAdapter(cartListAdapter);
    }

    private String getPriceToString(double price){
        return String.format("%.1f €", price);
    }
}
