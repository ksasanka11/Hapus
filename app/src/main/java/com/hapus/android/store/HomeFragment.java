package com.hapus.android.store;


import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

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
        categoryModelList.add(new CategoryModel("Link", "Organic"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        //categoryRecyclerView.addItemDecoration(new SpacingItemDecoration(getContext(), 16));
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        return view;
    }

    public class SpacingItemDecoration extends RecyclerView.ItemDecoration{
        private int spacing;

        public SpacingItemDecoration(Context context, int padding) {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            spacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, padding, metrics);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            if (position != state.getItemCount() - 1) {
                outRect.bottom = spacing;
            }
        }
    }

}
