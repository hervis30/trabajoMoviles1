package com.example.myproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myproyecto.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityAdminBinding adminBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adminBinding=ActivityAdminBinding.inflate(getLayoutInflater());
        View view = adminBinding.getRoot();
        setContentView(view);
        adminBinding.btnProductList.setOnClickListener(this);
        adminBinding.btnUserList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnProductList:
                Intent intent=new Intent(this,ListProductActivity.class);
                startActivity(intent);
                break;

            case R.id.btnUserList:
                Intent intent1 = new Intent(this,ListUsersActivity.class);
                startActivity(intent1);
                break;

        }

    }
}