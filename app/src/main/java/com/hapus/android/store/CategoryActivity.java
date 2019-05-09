package com.hapus.android.store;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.hapus.android.store.DBqueries.categoryPageModelList;
import static com.hapus.android.store.DBqueries.homePageModelList;
import static com.hapus.android.store.DBqueries.loadCategoryData;
import static com.hapus.android.store.DBqueries.loadFragmentData;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recyclerview);



        ////////// Horizontal Product Layout
        /*horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllButton = view.findViewById(R.id.horizontal_scroll_view_all_button);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerview);*/

        /*List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.korralu, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.app_icon, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.my_account, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ic_mail_outline_24px, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.add_profile_pic, "Korralu", "Foxtail millets", "Rs.100/kg"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.close_cross, "Korralu", "Foxtail millets", "Rs.100/kg"));*/

        /*HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);
        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();*/

        ////////// Horizontal Product Layout

        ///////////////////////

        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        //List<HomePageModel> homePageModelList = new ArrayList<>();
        HomePageAdapter adapter = new HomePageAdapter(categoryPageModelList);
        categoryRecyclerView.setAdapter(adapter);

        if(categoryPageModelList.size() == 0){
            loadCategoryData(adapter, CategoryActivity.this);
        }else{
            adapter.notifyDataSetChanged();
        }

        //////////////////////

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_search_icon) {
            //todo: search
            return true;
        }else if(id == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
