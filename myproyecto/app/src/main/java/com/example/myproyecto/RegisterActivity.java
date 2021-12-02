package com.example.myproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.myproyecto.databinding.ActivityMainBinding;
import com.example.myproyecto.databinding.ActivityRegisterBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private  ActivityRegisterBinding registerBinding;
    TextInputLayout til_role;
    AutoCompleteTextView act_etRol;
    ArrayList<String>arrayList_role;
    ArrayAdapter<String>arrayAdapter_role;
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = registerBinding.getRoot();
        setContentView(view);
        til_role=(TextInputLayout)findViewById(R.id.til_role);
        act_etRol=(AutoCompleteTextView)findViewById(R.id.act_etRol);
        arrayList_role=new ArrayList<>();
        arrayList_role.add("usuario");
        arrayList_role.add("vendedor");
        arrayAdapter_role=new ArrayAdapter<>(
                getApplicationContext(),R.layout.tv_entity,arrayList_role);
        act_etRol.setAdapter(arrayAdapter_role);
        act_etRol.setThreshold(1);
        dbHelper = new DbHelper(this);

    }

    public void registerUser(View view){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues userData = new ContentValues();
        String name = registerBinding.etName.getText().toString();
        String rol = registerBinding.actEtRol.getText().toString();
        String email= registerBinding.etEmail.getText().toString();
        String identification = registerBinding.etIdentification.getText().toString();
        String password = registerBinding.etPassword.getText().toString();
        if(name.equals("")||rol.equals("")||email.equals("")||identification.equals("")||password.equals("")){
            Toast.makeText(RegisterActivity.this,"Por favor llene todos los campos",Toast.LENGTH_SHORT).show();
        }
        else{  userData.put("name", name);
            userData.put("email", email);
            userData.put("identification", identification);
            userData.put("rol",rol);
            userData.put("password", password);
            long newUser = db.insert("users",null,userData);
            Toast.makeText(this, ""+newUser, Toast.LENGTH_SHORT).show();}

        //Intent intent=new Intent(this,ProfileActivity.class);
        //startActivity(intent);
    }
    public void goTolistUsers(View view){
        Intent intent=new Intent(this,ListUsersActivity.class);
        startActivity(intent);
    }
}