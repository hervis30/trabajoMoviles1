package com.example.myproyecto.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproyecto.MainActivity;
import com.example.myproyecto.R;
import com.example.myproyecto.databinding.UserProductItemBinding;
import com.example.myproyecto.entities.UserProductEntity;

import java.util.ArrayList;

public class UserProductAdapter extends RecyclerView.Adapter<UserProductAdapter.UserProductViewHolder> {
    private UserProductItemBinding userProductItemBinding;
    private Context context;
    private ArrayList<UserProductEntity>userProductEntityArrayList;
    public UserProductAdapter(Context context, ArrayList<UserProductEntity> userProductEntityArrayList) {
        this.context=context;
        this.userProductEntityArrayList=userProductEntityArrayList;

    }

    @NonNull
    @Override
    public UserProductAdapter.UserProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        userProductItemBinding=UserProductItemBinding.inflate(LayoutInflater.from(context));
        return new UserProductViewHolder(userProductItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserProductAdapter.UserProductViewHolder holder, int position) {
        UserProductEntity userProduct = userProductEntityArrayList.get(position);
        holder.itemBinding.tvProductName.setText(userProduct.getProductName());
        holder.itemBinding.tvStock.setText(String.valueOf(userProduct.getStock()));
        holder.itemBinding.tvCategory.setText(userProduct.getCategory());
        holder.itemBinding.tvAuthor.setText(userProduct.getAuthor());
        holder.itemBinding.tvValue.setText(String.valueOf(userProduct.getValue()));
        holder.itemBinding.tvStoreName.setText(String.valueOf(userProduct.getStoreName()));
        holder.itemBinding.tvStoreEmail.setText(String.valueOf(userProduct.getEmailStore()));
    }

    @Override
    public int getItemCount() {
        return userProductEntityArrayList.size();
    }

    public class UserProductViewHolder extends RecyclerView.ViewHolder {
        UserProductItemBinding itemBinding;
        public UserProductViewHolder(@NonNull UserProductItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding=itemView;

        }
    }

}
