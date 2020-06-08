package com.example.simpleeshop.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.Products;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment  { // implements AdapterView.OnItemSelectedListener

    private static ShopFragment instance;
    private ShopViewModel shopViewModel;
    ListView listView;
    ShopListAdapter shopListAdapter;
    TextView selectedItem;

    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shopViewModel =
                ViewModelProviders.of(this).get(ShopViewModel.class);

        root = inflater.inflate(R.layout.fragment_shop, container, false);
        initializeList();

        instance = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Item Clicked", Toast.LENGTH_SHORT).show();
//                Toast.makeText(parent.getContext(),
//                        "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
//                        Toast.LENGTH_SHORT).show();
                // TODO : Call method to have items be added at cart table
                addItemToCart(position);
            }
        });

//        listView.setOnItemClickListener(this);

        return root;
    }

    public static ShopFragment getInstance() {
        return instance;
    }

    public void refresh() {
        clear();
        initializeList();
    }

    private void clear(){
        listView.setAdapter(null);
    }

    private void addItemToCart(int position){
        Products product = shopListAdapter.getItem(position);
        CartMap.Instance().AddProduct(product.getId());
    }

    private void initializeList() {
        listView = root.findViewById(R.id.shop_list_view);
        ArrayList<Products> productsArray = new ArrayList<>();

        MyAppDatabase db = MyAppDatabase.Instance();
        List< Products > products = db.myDao().getProducts();

        for (int i = 0; i < products.size(); i++){
            productsArray.add(products.get(i));
        }

        shopListAdapter = new ShopListAdapter(root.getContext(), R.layout.fragment_shop_item, productsArray);
        listView.setAdapter(shopListAdapter);
    }


}
