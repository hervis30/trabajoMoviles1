package com.example.myproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproyecto.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mainBinding;
    EditText etEmail;
    EditText etPassword;
    EditText btnLogin;
    private DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        dbHelper=new DbHelper(this);

        mainBinding.btnLogin.setOnClickListener(this);
        mainBinding.btnSingUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
         String email = mainBinding.etEmail.getText().toString();
         String password = mainBinding.etPassword.getText().toString();
         String rol="usuario";

         if(email.equals("")||password.equals("")){
             Toast.makeText(this,"Por favor llene todos los campos",Toast.LENGTH_SHORT).show();
                break;
         }else{
             if(email.equals("admin@cesde.net")&&password.equals("admin")){
                 Toast.makeText(MainActivity.this,"ADMINISTRATOR",Toast.LENGTH_SHORT).show();
                 Intent intent=new Intent(getApplicationContext(),AdminActivity.class);
                 startActivity(intent);
                 break;
             }else{
                 Boolean checkPassEmailRol=dbHelper.checkPassEmailRol(email,rol,password);
                 if(checkPassEmailRol==true){
                     Toast.makeText(MainActivity.this,"Exito en el ingreso",Toast.LENGTH_SHORT).show();
                     Intent intent=new Intent(getApplicationContext(),ListUserProductActivity.class);
                     startActivity(intent);
                     break;
                 }else{
                     Boolean checkEmailPass=dbHelper.checkEmailPass(email,password);
                     if(checkEmailPass==true){
                         Toast.makeText(MainActivity.this,"Exito en el ingreso",Toast.LENGTH_SHORT).show();
                         Intent intent=new Intent(getApplicationContext(),RegisterProductsActivity.class);
                         startActivity(intent);
                         break;
                     }else{
                         Toast.makeText(MainActivity.this,"Credenciales invalidas",Toast.LENGTH_SHORT).show();
                         break;
                     }
                 }
            }
         }
            case R.id.btnSingUp:

                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;

        }
    }
}