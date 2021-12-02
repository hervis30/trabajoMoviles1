package com.example.myproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myproyecto.adapters.UserAdapter;
import com.example.myproyecto.databinding.ActivityListUsersBinding;
import com.example.myproyecto.entities.UserEntity;

import java.util.ArrayList;

public class ListUsersActivity extends AppCompatActivity {
private ActivityListUsersBinding listUsersBinding;
    private  DbHelper dbHelper;
    private int idUser;
    private ArrayList<UserEntity> usersArrayList;
    private UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listUsersBinding=ActivityListUsersBinding.inflate(getLayoutInflater());
        View view = listUsersBinding.getRoot();
        setContentView(view);
        usersArrayList=new ArrayList<>();
        dbHelper=new DbHelper(this);
        userAdapter=new UserAdapter(this,usersArrayList);
        listUsersBinding.rvListUsers.setHasFixedSize(true);
        listUsersBinding.rvListUsers.setLayoutManager(new LinearLayoutManager(this));
        listUsersBinding.rvListUsers.setAdapter(userAdapter);
        listUsers();

    }
    public  void listUsers(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery(
                "SELECT*FROM users",
                null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                idUser = Integer.parseInt(cursor.getString(0));
                int indexEmailRow =cursor.getColumnIndex("email");
                int indexNameRow =cursor.getColumnIndex("name");
                int indexIdentificationRow =cursor.getColumnIndex("identification");
                int indexPasswordRow =cursor.getColumnIndex("password");
                int indexRolRow =cursor.getColumnIndex("rol");

                String name =  cursor.getString(indexNameRow).toString();
                String email =  cursor.getString(indexEmailRow).toString();
                long identification = Long.parseLong(cursor.getString(indexIdentificationRow));
                String password =  cursor.getString(indexPasswordRow).toString();
                String rol = cursor.getString(indexRolRow).toString();
                //Log.d("Nombre", name);
                UserEntity userEntity=new UserEntity();
                userEntity.setId(idUser);
                userEntity.setName(name);
                userEntity.setEmail(email);
                userEntity.setIdentification(identification);
                userEntity.setPassword(password);
                userEntity.setRol(rol);
                usersArrayList.add(userEntity);
            }
            userAdapter.notifyDataSetChanged();
        }
    }
}