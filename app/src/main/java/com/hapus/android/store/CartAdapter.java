package com.hapus.android.store;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch(cartItemModelList.get(position).getType()){
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;
                default:
                    return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case CartItemModel.CART_ITEM:
                View cartItemView=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_layout,viewGroup,false);
                return new CartItemViewholder(cartItemView);
            case CartItemModel.TOTAL_AMOUNT:
                View cartTotalView=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_total_amount_layout,viewGroup,false);
                return new CartTotalAmountViewholder(cartTotalView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch(cartItemModelList.get(position).getType()){
            case CartItemModel.CART_ITEM:
                int resource=cartItemModelList.get(position).getProductImage();
                String title=cartItemModelList.get(position).getProductTitle();
                String productPrice=cartItemModelList.get(position).getProductPrice();
                String cuttedPrice=cartItemModelList.get(position).getCuttedPrice();
                int offersApplied=cartItemModelList.get(position).getOffersApplied();

                ((CartItemViewholder)viewHolder).setItemDetails(resource,title,productPrice,cuttedPrice,offersApplied);
                break;
            case CartItemModel.TOTAL_AMOUNT:
                String totalItems=cartItemModelList.get(position).getTotalItems();
                String totalItemPrice=cartItemModelList.get(position).getTotalItemPrice();
                String deliveryPrice=cartItemModelList.get(position).getDeliveryPrice();
                String totalAmount=cartItemModelList.get(position).getTotalAmount();
                String savedAmount=cartItemModelList.get(position).getSavedAmount();

                ((CartTotalAmountViewholder)viewHolder).setTotalAmount(totalItems,totalItemPrice,deliveryPrice,totalAmount,savedAmount);
                break;
            default:
                return;

        }

    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewholder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView offersApplied;
        private TextView productQuantity;



        public CartItemViewholder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_image);
            productTitle=itemView.findViewById(R.id.product_title);
            productPrice=itemView.findViewById(R.id.product_price);
            cuttedPrice=itemView.findViewById(R.id.cutted_price);
            offersApplied=itemView.findViewById(R.id.offers_applied);
            productQuantity=itemView.findViewById(R.id.product_quantity);
        }
        private void setItemDetails(int resource,String title,String productPriceText,String cuttedPriceText,int offersAppliedNo){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            productPrice.setText(productPriceText);
            cuttedPrice.setText(cuttedPriceText);
            if(offersAppliedNo>0)
            {
                offersApplied.setVisibility(View.VISIBLE);
                offersApplied.setText(offersAppliedNo+"Offers Applied");
            }
            else
            {
                offersApplied.setVisibility(View.INVISIBLE);
            }


        }
    }

    class CartTotalAmountViewholder extends RecyclerView.ViewHolder{
        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;



        public CartTotalAmountViewholder(@NonNull View itemView) {
            super(itemView);
            totalItems = itemView.findViewById(R.id.total_items);
            totalItemPrice = itemView.findViewById(R.id.total_items_price);
            deliveryPrice = itemView.findViewById(R.id.delivery_price);
            totalAmount = itemView.findViewById(R.id.total_price);
            savedAmount = itemView.findViewById(R.id.saved_amount);
        }
            private void setTotalAmount(String totalItemText,String totalItemPriceText,String deliveryPriceText,String totalAmountText,String savedAmountText)
            {
                totalItems.setText(totalItemText);
                totalItemPrice.setText(totalItemPriceText);
                deliveryPrice.setText(deliveryPriceText);
                totalAmount.setText(totalAmountText);
                savedAmount.setText(savedAmountText);
            }

    }
    }

