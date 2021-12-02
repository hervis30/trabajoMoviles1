package com.example.myproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myproyecto.databinding.ActivityMainBinding;
import com.example.myproyecto.databinding.ActivityRegisterProductsBinding;

public class RegisterProductsActivity extends AppCompatActivity {
    private ActivityRegisterProductsBinding registerProductsBinding;
    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerProductsBinding = ActivityRegisterProductsBinding.inflate(getLayoutInflater());
        View view = registerProductsBinding.getRoot();
        setContentView(view);
        dbHelper=new DbHelper(this);

    }
    public void registerProduct(View view){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues productData = new ContentValues();
        String productName = registerProductsBinding.etProductName.getText().toString();
        String stock = registerProductsBinding.etStock.getText().toString();
        String category = registerProductsBinding.etCategory.getText().toString();
        String author = registerProductsBinding.etAuthor.getText().toString();
        String value = registerProductsBinding.etValue.getText().toString();
        String storeName=registerProductsBinding.etStoreName.getText().toString();
        String storeEmail=registerProductsBinding.etStoreEmail.getText().toString();
        productData.put("productName", productName);
        productData.put("stock", stock);
        productData.put("category",category);
        productData.put("author",author);
        productData.put("value",value);
        productData.put("storeName",storeName);
        productData.put("emailStore",storeEmail);
        long newProduct = db.insert("products",null,productData);
        Toast.makeText(this, "" +newProduct, Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this,ProfileProductActivity.class);
        //startActivity(intent);
    }
    public void goToListProducts(View view){
        Intent intent = new Intent(this, ListProductActivity.class);
        startActivity(intent);
    }
}