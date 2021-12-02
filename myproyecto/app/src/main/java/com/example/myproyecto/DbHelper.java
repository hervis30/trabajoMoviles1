package com.example.myproyecto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 14;
    public static final String DATABASE_NAME = "cesdeshop";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name VARCHAR(50),email VARCHAR(50),"+
                "identification int,rol VARCHAR(16) ,password VARCHAR(16))");

        sqLiteDatabase.execSQL("CREATE TABLE products (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "productName VARCHAR(50),stock int,"+
                "category VARCHAR(50), author VARCHAR(50),"+
                "value int,storeName VARCHAR(50),emailStore VARCHAR(50))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS products");
        onCreate(sqLiteDatabase);
    }
    public boolean checkPassEmailRol(String email,String rol,String password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT*FROM users WHERE email=? and rol=? and password=?",
                new String[]{email,rol,password});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
    public boolean checkEmailPass(String email,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(
                "SELECT*FROM users WHERE email=? and password=?",
                new String[]{email,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}
