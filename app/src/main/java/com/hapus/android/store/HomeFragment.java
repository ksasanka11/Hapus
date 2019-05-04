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

    ////////// Banner Slider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer mTimer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    ////////// Banner Slider

    ////////// Horizontal Product Layout
    private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllButton;
    private RecyclerView horizontalRecyclerView;
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

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);
        sliderModelList = new ArrayList<>();

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

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);
        bannerSliderViewPager.setCurrentItem(currentPage);
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(i == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                pageLooper();
                stopBannerSlideShow();

                if(motionEvent.getAction() == MotionEvent.ACTION_UP);
                    startBannerSlideShow();
                return false;
            }
        });
        ////////// Banner Slider

        ////////// Horizontal Product Layout
        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllButton = view.findViewById(R.id.horizontal_scroll_view_all_button);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerview);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.korralu, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.app_icon, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.my_account, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ic_mail_outline_24px, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.add_profile_pic, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.close_cross, "Korralu", "Foxtail millets", "Rs.100/kg"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);
        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();

        ////////// Horizontal Product Layout

        /////////  Grid Product Layout

        TextView gridLayoutTitle = view.findViewById(R.id.grid_product_layout_title);
        Button gridLayoutBtn = view.findViewById(R.id.grid_product_lauout_viewall_btn);
        GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);

        gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));

        /////////  Grid Product Layout


        ///////////////////////

        RecyclerView testing = view.findViewById(R.id.testing);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        //////////////////////

        return view;
    }

    ////////// Banner Slider

    private void pageLooper(){
        if(currentPage == sliderModelList.size()-2){
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }

        if(currentPage == 1){
            currentPage = sliderModelList.size()-3;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
    }

    private void startBannerSlideShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size())
                    currentPage = 1;
                bannerSliderViewPager.setCurrentItem(currentPage++, true);
            }
        };

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    private void stopBannerSlideShow(){
        mTimer.cancel();
    }

    ////////// Banner Slider


}
