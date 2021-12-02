package com.example.myproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myproyecto.databinding.ActivityProfileBinding;
import com.example.myproyecto.entities.UserEntity;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding profileBinding;
    private  DbHelper dbHelper;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = profileBinding.getRoot();
        setContentView(view);
        dbHelper=new DbHelper(this);
        UserEntity userData =(UserEntity) getIntent().getSerializableExtra("userData");
        getUser(userData);

    }
    public void getUser(UserEntity user){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
            idUser = user.getId();

            profileBinding.etName.setText(user.getName());
            profileBinding.etEmail.setText(user.getEmail());
            profileBinding.etIdentification.setText(
                    String.valueOf(user.getIdentification()));
            profileBinding.etPassword.setText(user.getPassword());
            profileBinding.etRol.setText(user.getRol());
            //Log.d("cesdeshop: ",cursor.getString(indexRow));
        }
    public void updateUser(View view){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String name = profileBinding.etName.getText().toString();
        String email = profileBinding.etEmail.getText().toString();
        String rol = profileBinding.etRol.getText().toString();
        String sql = String.format("UPDATE users set name='%s',email='%s',rol='%s', WHERE id='%s'",name,email,rol,idUser);
        db.execSQL(sql);
        Intent intent = new Intent(this,ListUsersActivity.class);
        startActivity(intent);
    }
}