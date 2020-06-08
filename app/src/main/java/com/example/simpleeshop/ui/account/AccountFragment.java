package com.example.simpleeshop.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.simpleeshop.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;

    private RadioButton radioButtonDetails;
    private RadioButton radioButtonOrders;
    ListView listView;
    ListAdapter adapter;
    View root;
    AccountFragment.OnButtonSelectedListener onButtonSelectedListener;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        accountViewModel =
//                ViewModelProviders.of(this).get(AccountViewModel.class);
        root = inflater.inflate(R.layout.fragment_account, container, false);
//        final TextView textView = root.findViewById(R.id.text_account_title);
//        accountViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        ViewPager2 pager = root.findViewById(R.id.pager);

        pager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch(position) {
                    case 0:
                        return new UserDetails();
                    case 1:
                        return new UserOrders();
                }
                return null;
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        });

        TabLayout tabLayout = root.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, pager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch(position) {
                            case 0:
                                tab.setText("Details");
                                break;
                            case 1:
                                tab.setText("Orders");
                                break;
                        }
                    }
                }
        ).attach();


//        initializeList();
//        radioButtonDetails = root.findViewById(R.id.radioButton_profile_details);
//        radioButtonOrders = root.findViewById(R.id.radioButton_orders);
//
//
//        radioButtonDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // ToDo: Code for ON CLICK
////                textView.setText("DetailsMap radio button");
//            }
//        });
//
//        radioButtonOrders.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // ToDo: Code for ON CLICK
////                textView.setText("Orders radio button");
//
//            }
//        });

        return root;
    }

//    private void initializeList() {
//        listView = root.findViewById(R.id.shop_list_view);
//        adapter = new ShopListAdapter(root.getContext());
//        listView.setAdapter(adapter);
//    }
//
//    private void setChosenList(String listName){
//        List<String> itemList = new ArrayList<>();
//        MyAppDatabase db = MyAppDatabase.Instance();
//        List< String > products = db.myDao().getProductsName();
//        for (String item:products){
//            itemList.add(item);
//        }
//
//    }

    public interface OnButtonSelectedListener {
        public void onButtonSelected(String message);
    }
}
