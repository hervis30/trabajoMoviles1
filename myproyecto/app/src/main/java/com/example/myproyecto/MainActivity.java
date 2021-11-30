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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        mainBinding.btnLogin.setOnClickListener(this);
        mainBinding.btnSingUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
         String email = mainBinding.etEmail.getText().toString();
         String password = mainBinding.etPassword.getText().toString();

                Toast.makeText(this,"Login",Toast.LENGTH_SHORT).show();


                break;

            case R.id.btnSingUp:

                Intent intent = new Intent(this, RegisterProductsActivity.class);
                startActivity(intent);
                break;

        }
    }
}