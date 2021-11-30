package com.example.myproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.myproyecto.adapters.UserProductAdapter;
import com.example.myproyecto.databinding.ActivityListUserProductBinding;
import com.example.myproyecto.entities.UserProductEntity;

import java.util.ArrayList;

public class ListUserProductActivity extends AppCompatActivity {
    ActivityListUserProductBinding listUserProductBinding;
    private DbHelper dbHelper;
    private int idUserProduct;
    private ArrayList<UserProductEntity> userProductArrayList;
    private UserProductAdapter userProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listUserProductBinding=ActivityListUserProductBinding.inflate(getLayoutInflater());
        View view = listUserProductBinding.getRoot();
        setContentView(view);
        userProductArrayList=new ArrayList<>();
        dbHelper=new DbHelper(this);
        userProductAdapter= new UserProductAdapter(this,userProductArrayList);
        listUserProductBinding.rvListUserProdut.setHasFixedSize(true);
        listUserProductBinding.rvListUserProdut.setLayoutManager(new LinearLayoutManager(this));
        listUserProductBinding.rvListUserProdut.setAdapter(userProductAdapter);
        listUserProduct();
    }
    public void listUserProduct(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery(
                "SELECT * FROM products",null);
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                idUserProduct = Integer.parseInt(cursor.getString(0));
                int indexProductNameRow = cursor.getColumnIndex("productName");
                int indexStockRow = cursor.getColumnIndex("stock");
                int indexCategoryRow = cursor.getColumnIndex("category");
                int indexAuthorRow = cursor.getColumnIndex("author");
                int indexValueRow = cursor.getColumnIndex("value");
                String productName = cursor.getString(indexProductNameRow).toString();
                int stock = Integer.parseInt(cursor.getString(indexStockRow));
                String category = cursor.getString(indexCategoryRow).toString();
                String author = cursor.getString(indexAuthorRow).toString();
                int value = Integer.parseInt(cursor.getString(indexValueRow));

                UserProductEntity userProductEntity=new UserProductEntity();
                userProductEntity.setId(idUserProduct);
                userProductEntity.setProductName(productName);
                userProductEntity.setStock(stock);
                userProductEntity.setCategory(category);
                userProductEntity.setAuthor(author);
                userProductEntity.setValue(value);
                userProductArrayList.add(userProductEntity);

            }
            userProductAdapter.notifyDataSetChanged();
        }
    }
}