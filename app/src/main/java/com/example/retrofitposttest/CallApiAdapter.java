package com.example.retrofitposttest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitposttest.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CallApiAdapter extends RecyclerView.Adapter<CallApiAdapter.CallApiViewHolder>{

    private List<Product> list = new ArrayList<>();
    private Context context;

    public CallApiAdapter(List<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CallApiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new CallApiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallApiViewHolder holder, int position) {
        Product product = list.get(position);

        if (product == null) {
            return;
        }

        holder.tvTitle.setText(product.getTitle());
        holder.tvUrl.setText(product.getUrl());
        holder.tvThumnailUrl.setText(product.getThumbnailUrl());
        Glide.with(context).load(product.getUrl()).centerCrop().into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    class CallApiViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvUrl, tvThumnailUrl;
        private ImageView imgProduct;

        public CallApiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvThumnailUrl = itemView.findViewById(R.id.tv_thumbnailUrl);
            tvUrl = itemView.findViewById(R.id.tv_url);
            tvTitle = itemView.findViewById(R.id.tv_title);
            imgProduct = itemView.findViewById(R.id.img_product);
        }
    }
}
