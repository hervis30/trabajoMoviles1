package com.example.myproyecto.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproyecto.DbHelper;
import com.example.myproyecto.ProfileProductActivity;
import com.example.myproyecto.databinding.ProductItemBinding;
import com.example.myproyecto.databinding.UserItemBinding;
import com.example.myproyecto.entities.ProductEntity;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ProductItemBinding productItemBinding;
    private Context context;
    private DbHelper dbHelper;
    private ArrayList<ProductEntity> productEntityArrayList;
    public ProductAdapter(Context context, ArrayList<ProductEntity>productEntityArrayList) {
        this.context = context;
        this.productEntityArrayList = productEntityArrayList;
        dbHelper=new DbHelper(context);

    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        productItemBinding = ProductItemBinding.inflate(LayoutInflater.from(context));
        return new ProductViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        ProductEntity product = productEntityArrayList.get(position);
        holder.itemBinding.tvProductName.setText(product.getProductName());
        holder.itemBinding.tvStock.setText(String.valueOf(product.getStock()));
        holder.itemBinding.tvCategory.setText(product.getCategory());
        holder.itemBinding.tvAuthor.setText(product.getAuthor());
        holder.itemBinding.tvValue.setText(String.valueOf(product.getValue()));
        holder.itemBinding.tvStoreName.setText(String.valueOf(product.getStoreName()));
        holder.itemBinding.tvStoreEmail.setText(String.valueOf(product.getEmailStore()));
        holder.itemBinding.btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("products","id="+product.getId(),null);
                for(int i=0;i<productEntityArrayList.size();i++){
                    if(productEntityArrayList.get(i).getId()==product.getId()){
                        productEntityArrayList.remove(i);
                        break;
                    }
                }
                notifyDataSetChanged();
            }
        });
        holder.itemBinding.btnEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProfileProductActivity.class);
                intent.putExtra("productData",product);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productEntityArrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ProductItemBinding itemBinding;
        public ProductViewHolder(@NonNull ProductItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }
    }
}
