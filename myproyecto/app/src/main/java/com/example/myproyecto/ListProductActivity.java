package com.example.myproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myproyecto.adapters.ProductAdapter;
import com.example.myproyecto.databinding.ActivityListProductBinding;
import com.example.myproyecto.databinding.ActivityListUsersBinding;
import com.example.myproyecto.entities.ProductEntity;

import java.util.ArrayList;

public class ListProductActivity extends AppCompatActivity {
    private ActivityListProductBinding listProductBinding;
    private DbHelper dbHelper;
    private int idProduct;

    private ArrayList<ProductEntity> productsArrayList;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listProductBinding = ActivityListProductBinding.inflate(getLayoutInflater());
        View view = listProductBinding.getRoot();
        setContentView(view);
        productsArrayList = new ArrayList<>();
        dbHelper = new DbHelper(this);
        productAdapter = new ProductAdapter(this, productsArrayList);
        listProductBinding.rvListProducts.setHasFixedSize(true);
        listProductBinding.rvListProducts.setLayoutManager(new LinearLayoutManager(this));
        listProductBinding.rvListProducts.setAdapter(productAdapter);
        listProducts();
    }

    public void listProducts() {

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM products", null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                idProduct = Integer.parseInt(cursor.getString(0));
                int indexProductNameRow = cursor.getColumnIndex("productName");
                int indexStockRow = cursor.getColumnIndex("stock");
                int indexCategoryRow = cursor.getColumnIndex("category");
                int indexAuthorRow = cursor.getColumnIndex("author");
                int indexValueRow = cursor.getColumnIndex("value");
                int indexStoreNameRow = cursor.getColumnIndex("storeName");
                int indexEmailStoreRow = cursor.getColumnIndex("emailStore");

                String productName = cursor.getString(indexProductNameRow).toString();
                int stock = Integer.parseInt(cursor.getString(indexStockRow));
                String category = cursor.getString(indexCategoryRow).toString();
                String author = cursor.getString(indexAuthorRow).toString();
                int value = Integer.parseInt(cursor.getString(indexValueRow));
                String storeName = cursor.getString(indexStoreNameRow).toString();
                String emailStore = cursor.getString(indexEmailStoreRow).toString();

                //Boolean checkEmail = dbHelper.checkEmail(emailStore);
                //(checkEmail==true){}
                //ProductEntity productEntity=new ProductEntity();

                        ProductEntity productEntity = new ProductEntity();

                        productEntity.setId(idProduct);
                        productEntity.setProductName(productName);
                        productEntity.setStock(stock);
                        productEntity.setCategory(category);
                        productEntity.setAuthor(author);
                        productEntity.setValue(value);
                        productEntity.setStoreName(storeName);
                        productEntity.setEmailStore(emailStore);
                        productsArrayList.add(productEntity);


                    }
                }

                //ProductEntity productEntity=new ProductEntity();

                //productEntity.setId(idProduct);
                //productEntity.setProductName(productName);
                //productEntity.setStock(stock);
                //productEntity.setCategory(category);
                //productEntity.setAuthor(author);
                //productEntity.setValue(value);
                //productEntity.setStoreName(storeName);
                //productEntity.setEmailStore(emailStore);
                //productsArrayList.add(productEntity);


                //Log.d("Author",author);


                productAdapter.notifyDataSetChanged();
            }
        }

