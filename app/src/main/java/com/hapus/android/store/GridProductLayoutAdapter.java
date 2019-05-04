package com.hapus.android.store;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public GridProductLayoutAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final View view;

        if(convertView == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout, null);
            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            ImageView productImage = view.findViewById(R.id.h_s_product_image);
            TextView productTitle = view.findViewById(R.id.h_s_product_title);
            TextView productDescription = view.findViewById(R.id.h_s_product_description);
            TextView productPrice = view.findViewById(R.id.h_s_product_price);

            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(view.getContext(), Product_details_activity.class);
                    view.getContext().startActivity(productDetailsIntent);
                }
            });

            productImage.setImageResource(horizontalProductScrollModelList.get(i).getProductImage());
            productTitle.setText(horizontalProductScrollModelList.get(i).getProductTitle());
            productDescription.setText(horizontalProductScrollModelList.get(i).getProductDescription());
            productPrice.setText(horizontalProductScrollModelList.get(i).getProductPrice());
        }else{
            view = convertView;
        }

        return view;
    }
}
