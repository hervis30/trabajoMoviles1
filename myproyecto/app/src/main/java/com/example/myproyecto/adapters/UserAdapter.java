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
import com.example.myproyecto.ProfileActivity;
import com.example.myproyecto.databinding.UserItemBinding;
import com.example.myproyecto.entities.UserEntity;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private UserItemBinding userItemBinding;
    private Context context;
    private DbHelper dbHelper;
    private ArrayList<UserEntity> userArrayList;
    public UserAdapter(Context context, ArrayList<UserEntity> userArrayList) {
    this.context=context;
    this.userArrayList = userArrayList;
    dbHelper = new DbHelper(context);
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    userItemBinding = UserItemBinding.inflate(LayoutInflater.from(context));
        return new UserViewHolder(userItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
    UserEntity user = userArrayList.get(position);
    holder.itemBinding.tvName.setText(user.getName());
    holder.itemBinding.tvIdentification
            .setText(String.valueOf(user.getIdentification()));
    holder.itemBinding.tvEmail.setText(user.getEmail());
    holder.itemBinding.btnDeleteUser.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete("users","id="+user.getId(),null);
            for(int i=0;i<userArrayList.size();i++){
                if(userArrayList.get(i).getId()==user.getId()){
                    userArrayList.remove(i);
                    break;
                }
            }
            notifyDataSetChanged();
        }
    });
    holder.itemBinding.btnEditUser.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra("userData", user);
            context.startActivity(intent);
            //notifyDataSetChanged();
        }
    });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private UserItemBinding itemBinding;
        public UserViewHolder(@NonNull UserItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }
    }
}
