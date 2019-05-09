package com.hapus.android.store;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {

    private List<HorizontalProductScrollModel> mHorizontalProductScrollModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        mHorizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder viewHolder, int position) {
        String resource = mHorizontalProductScrollModelList.get(position).getProductImage();
        String title = mHorizontalProductScrollModelList.get(position).getProductTitle();
        String description = mHorizontalProductScrollModelList.get(position).getProductDescription();
        String price = mHorizontalProductScrollModelList.get(position).getProductPrice();

        String id = mHorizontalProductScrollModelList.get(position).getProductID();

        viewHolder.setProductImage(resource);
        viewHolder.setProductTitle(title);
        viewHolder.setProductDesription(description);
        viewHolder.setProductPrice(price);
        viewHolder.setProductID(id);
    }

    @Override
    public int getItemCount() {

            return mHorizontalProductScrollModelList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;
        private String productID;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.h_s_product_image);
            productTitle = itemView.findViewById(R.id.h_s_product_title);
            productDescription = itemView.findViewById(R.id.h_s_product_description);
            productPrice = itemView.findViewById(R.id.h_s_product_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(), Product_details_activity.class);
                    productDetailsIntent.putExtra("productId", productID);

                    FirebaseFirestore.getInstance().collection("ADMIN_PHONE").document("nZHDmKKeN2ByVIeitiQR").get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if(task.isSuccessful()){
                                        SmsManager smsManager = SmsManager.getDefault();

                                        DocumentSnapshot documentSnapshot = task.getResult();

                                        String phone_number = documentSnapshot.get("phone_number").toString();

                                        smsManager.sendTextMessage(phone_number, null, "Hello", null, null);

                                    }
                                }
                            });

                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }

        private void setProductImage(String resource){
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.home)).into(productImage);
        }

        private void setProductTitle(String title){
            productTitle.setText(title);
        }

        private void setProductDesription(String desription){
            productDescription.setText(desription);
        }

        private void setProductPrice(String price){
            productPrice.setText("Rs. "+price+"/kg");
        }

        public void setProductID(String productID) {
            this.productID = productID;
        }
    }
}
