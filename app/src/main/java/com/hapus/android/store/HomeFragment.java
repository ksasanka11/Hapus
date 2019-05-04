package com.hapus.android.store;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView testing;


    ////////// Horizontal Product Layout
    /*private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllButton;
    private RecyclerView horizontalRecyclerView;*/
    ////////// Horizontal Product Layout

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("Link", "Home"));
        categoryModelList.add(new CategoryModel("Link", "Millets"));
        categoryModelList.add(new CategoryModel("Link", "Snacks"));
        categoryModelList.add(new CategoryModel("Link", "Recipes"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        //categoryRecyclerView.addItemDecoration(new SpacingItemDecoration(getContext(), 16));
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        ////////// Banner Slider

        List<SliderModel> sliderModelList = new ArrayList<>();

        sliderModelList.add(new SliderModel(R.drawable.ic_menu_gallery, "#5bc3c7"));
        sliderModelList.add(new SliderModel(R.drawable.home, "#5bc3c7"));
        sliderModelList.add(new SliderModel(R.mipmap.app_icon_round, "#5bc3c7"));

        sliderModelList.add(new SliderModel(R.drawable.cart_black, "#5bc3c7"));
        sliderModelList.add(new SliderModel(R.drawable.close_cross, "#5bc3c7"));
        sliderModelList.add(new SliderModel(R.drawable.add_profile_pic, "#5bc3c7"));
        sliderModelList.add(new SliderModel(R.drawable.ic_menu_gallery, "#5bc3c7"));

        sliderModelList.add(new SliderModel(R.drawable.home, "#5bc3c7"));
        sliderModelList.add(new SliderModel(R.mipmap.app_icon_round, "#5bc3c7"));
        sliderModelList.add(new SliderModel(R.drawable.cart_black, "#5bc3c7"));
        ////////// Banner Slider

        ////////// Horizontal Product Layout
        /*horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllButton = view.findViewById(R.id.horizontal_scroll_view_all_button);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerview);*/

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.korralu, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.app_icon, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.my_account, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ic_mail_outline_24px, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.add_profile_pic, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.close_cross, "Korralu", "Foxtail millets", "Rs.100/kg"));

        /*HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);
        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();*/

        ////////// Horizontal Product Layout

        /////////  Grid Product Layout

        TextView gridLayoutTitle = view.findViewById(R.id.grid_product_layout_title);
        Button gridLayoutBtn = view.findViewById(R.id.grid_product_lauout_viewall_btn);
        GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);

        gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));

        /////////  Grid Product Layout


        ///////////////////////

        testing = view.findViewById(R.id.testing);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        //homePageModelList.add(new HomePageModel(0, sliderModelList));
        //homePageModelList.add(new HomePageModel(2,"Deals of the day!", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the day!", horizontalProductScrollModelList));
        //homePageModelList.add(new HomePageModel(2,"Deals of the day!", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the day!", horizontalProductScrollModelList));
        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //////////////////////

        return view;
    }

}
