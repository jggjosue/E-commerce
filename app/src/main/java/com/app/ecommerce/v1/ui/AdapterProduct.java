package com.app.ecommerce.v1.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.app.ecommerce.R;
import com.app.ecommerce.v1.entities.ListProduct;
import com.app.ecommerce.v1.entities.Product;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ProductViewHolder> {

    private List<Product> listProduct;
    private Context mContext;

    public AdapterProduct(Context context, List<Product> listProduct) {
        this.mContext = context;
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_designer_view, viewGroup, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        productViewHolder.title.setText(listProduct.get(i).getTitle());
        productViewHolder.price.setText(listProduct.get(i).getPrice());
        Glide.with(mContext)
                .load(listProduct.get(i).getImage())
                .centerCrop()
                .circleCrop()
                .into(productViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView title, price;
        AppCompatImageView image;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);
        }
    }

    public void filter(ArrayList<Product> listProduct) {
        this.listProduct = listProduct;
        notifyDataSetChanged();
    }

}
