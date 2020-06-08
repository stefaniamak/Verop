package com.example.simpleeshop.ui.administrator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simpleeshop.R;
import com.example.simpleeshop.ui.account.UserDetails;
import com.example.simpleeshop.ui.account.UserOrders;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AdministratorProfile extends Fragment {

    View root;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public AdministratorProfile() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment AdministratorProfile.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static AdministratorProfile newInstance(String param1, String param2) {
//        AdministratorProfile fragment = new AdministratorProfile();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_administrator_profile, container, false);

        ViewPager2 pager = root.findViewById(R.id.pagerAdmin);

        pager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch(position) {
                    case 0:
                        return new UsersList();
                    case 1:
                        return new ProductsList();
                    case 2:
                        return new OrdersList();
                }
                return null;
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });

        TabLayout tabLayout = root.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, pager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch(position) {
                            case 0:
                                tab.setText("Users List");
                                break;
                            case 1:
                                tab.setText("Products List");
                                break;
                            case 2:
                                tab.setText("Orders List");
                                break;
                        }
                    }
                }
        ).attach();

        return root;
    }
}