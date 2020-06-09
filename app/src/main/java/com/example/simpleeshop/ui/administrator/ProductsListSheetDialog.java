package com.example.simpleeshop.ui.administrator;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simpleeshop.MyApplication;
import com.example.simpleeshop.R;
import com.example.simpleeshop.UiRefresher;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.Products;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import static com.example.simpleeshop.MyApplication.getImageId;


public class ProductsListSheetDialog extends BottomSheetDialogFragment {

    View root;
    int productId, reserve, imgId;
    double price;
    TableLayout productsListTable;
    Spinner imageSpinner;
    EditText nameEditView;
    TextView priceTextView, reserveTextView;
    Button delete, update, increasePrice, decreasePrice, increaseReserve, decreaseReserve, insertProduct;
    ImageView spinnerImage;
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
        buttonsVisibility();

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

        imageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setImage(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
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
        // ImageView
        spinnerImage = root.findViewById(R.id.spinnerImage);
    }

    private void buttonsVisibility() {
        if(productId == -1){
            delete.setVisibility(View.GONE);
            update.setVisibility(View.GONE);
            insertProduct.setVisibility(View.VISIBLE);
        } else {
            delete.setVisibility(View.VISIBLE);
            update.setVisibility(View.VISIBLE);
            insertProduct.setVisibility(View.GONE);
        }
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
        setImage(imageId);
        loadSpinnerData();
        imageSpinner.setSelection(imageId - 1);
        nameEditView.setText(name);
        priceTextView.setText(price + "€");
        reserveTextView.setText(Integer.toString(reserve));
    }

    private void setImage(int imageId){
        MyAppDatabase db = MyAppDatabase.Instance();
        String imagePath = db.myDao().getImagePathByImgId(imageId);
        spinnerImage.setImageResource(getImageId(imagePath));
        imgId = imageId;
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

    private Products getShowcasedVariables(){
        MyAppDatabase db = MyAppDatabase.Instance();
        Products product = new Products();

        product.setName(nameEditView.getText().toString());
        product.setPrice(price);
        product.setImageId(imgId);
        product.setReserve(reserve);

        return product;
    }

    private void deleteProduct(){
        MyAppDatabase db = MyAppDatabase.Instance();
        Products product = db.myDao().getProduct(productId);
        db.myDao().deleteProduct(product);
        doDismissAndRefresh("Product deleted.");
    }

    private void updateProduct(){
        MyAppDatabase db = MyAppDatabase.Instance();
        Products product = getShowcasedVariables();
        product.setId(productId);
        db.myDao().updateProduct(product);
        doDismissAndRefresh("Product updated.");
    }

    private void insertProduct(){
        MyAppDatabase db = MyAppDatabase.Instance();
        db.myDao().insertProduct(getShowcasedVariables());
        doDismissAndRefresh("Product inserted.");
    }

    public void doDismissAndRefresh(String message){
        // Refresh
        UiRefresher.Instance().refreshUis();
        // Dismiss
        this.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}