package com.example.myproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myproyecto.databinding.ActivityProfileProductBinding;
import com.example.myproyecto.entities.ProductEntity;

public class ProfileProductActivity extends AppCompatActivity {
    private ActivityProfileProductBinding profileProductBinding;
    private DbHelper dbHelper;
    private int idProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileProductBinding=ActivityProfileProductBinding.inflate(getLayoutInflater());
        View view = profileProductBinding.getRoot();
        setContentView(view);
        dbHelper=new DbHelper(this);
        ProductEntity productData =(ProductEntity) getIntent().getSerializableExtra("productData");
        getProduct(productData);
    }
    public void getProduct(ProductEntity product){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

            idProduct=product.getId();

            profileProductBinding.etProductName.setText(product.getProductName());
            profileProductBinding.etStock.setText(String.valueOf(product.getStock()));
            profileProductBinding.etCategory.setText(product.getCategory());
            profileProductBinding.etAuthor.setText(product.getAuthor());
            profileProductBinding.etValue.setText(String.valueOf(product.getValue()));

    }
    public void updateProduct(View view){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String productName = profileProductBinding.etProductName.getText().toString();
        String category = profileProductBinding.etCategory.getText().toString();
        String author = profileProductBinding.etAuthor.getText().toString();
        String sql = String.format(
                "UPDATE products set productName='%s',category='%s',author='%s' WHERE id='%s'",
                productName,category,author,idProduct);
        db.execSQL(sql);
        Intent intent = new Intent(this,ListProductActivity.class);
        startActivity(intent);
    }
}