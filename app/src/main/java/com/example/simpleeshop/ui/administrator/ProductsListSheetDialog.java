package com.example.simpleeshop.ui.administrator;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.simpleeshop.MyApplication;
import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.Products;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;


public class ProductsListSheetDialog extends BottomSheetDialogFragment {

    View root;
//    UserOrders parent;
    int productId, reserve;
    double price;
    TableLayout productsListTable;
    Spinner imageSpinner;
    EditText nameEditView;
    TextView priceTextView, reserveTextView;
    Button delete, update, increasePrice, decreasePrice, increaseReserve, decreaseReserve, insertProduct;
//    double totalCost;
//    Hashtable<Integer,Integer> totalProductsOrdered;

    public ProductsListSheetDialog(int productId){
//        this.parent = parent;
        this.productId = productId;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_products_list_sheet_dialog, container, false);


        initializeVariables();

        if(productId == -1){
            setDefaultView();
        } else {
            showcaseProduct();
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct();
            }
        });

        insertProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertProduct();
            }
        });

        return root;
    }

    private void initializeVariables(){
        // Table
        productsListTable = root.findViewById(R.id.products_list_table);
        // Spinner
        imageSpinner = root.findViewById(R.id.imageSpinner);
        // EditText
        nameEditView = root.findViewById(R.id.productNameEditText);
        // TextViews
        priceTextView = root.findViewById(R.id.productPriceListText);
        reserveTextView = root.findViewById(R.id.productReservedListText);
        // Buttons
        delete = root.findViewById(R.id.deleteListProduct);
        update = root.findViewById(R.id.updateListProduct);
        increasePrice = root.findViewById(R.id.increasePriceButton);
        decreasePrice = root.findViewById(R.id.decreasePriceButton);
        increaseReserve = root.findViewById(R.id.increaseReserveButton);
        decreaseReserve = root.findViewById(R.id.decreaseReserveButton);
        insertProduct = root.findViewById(R.id.insertListProduct);

    }

    private void setDefaultView() {
        setShowcasedVariables(1,"", 0, 0);
    }

    private void showcaseProduct() {
        MyAppDatabase db = MyAppDatabase.Instance();
        Products product = db.myDao().getProduct(productId);

        int imageId = product.getImageId();
        String name = product.getName();
        price = product.getPrice();
        reserve = product.getReserve();

        setShowcasedVariables(imageId, name, price, reserve);
    }

    private void setShowcasedVariables(int imageId, String name, double price, int reserve){
        loadSpinnerData();
        nameEditView.setText(name);
        priceTextView.setText(price + "€");
        reserveTextView.setText(Integer.toString(reserve));
    }

    private void loadSpinnerData() {
        MyAppDatabase db = MyAppDatabase.Instance();
        Context context = MyApplication.Context();

        List<String> labels = db.myDao().getproductImagesTitles();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        imageSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    private void deleteProduct(){
        MyAppDatabase db = MyAppDatabase.Instance();
        Products product = db.myDao().getProduct(productId);
        db.myDao().deleteProduct(product);
    }

    private void updateProduct(){
        MyAppDatabase db = MyAppDatabase.Instance();
        Products product = getShowcasedVariables();
        product.setId(productId);
        db.myDao().updateProduct(product);
    }

    private void insertProduct(){
        MyAppDatabase db = MyAppDatabase.Instance();
        db.myDao().insertProduct(getShowcasedVariables());
    }

    private Products getShowcasedVariables(){
        MyAppDatabase db = MyAppDatabase.Instance();
        Products product = new Products();

        // todo pairnw string apo spinner (h position?)
        // todo vriskw image id apo to string

        product.setName(nameEditView.getText().toString());
        product.setPrice(price);
        product.setImageId(1); // todo set image id
        product.setReserve(reserve);

        return product;
    }

}