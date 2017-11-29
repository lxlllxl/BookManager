package com.example.administrator.bookmanager.admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/13.
 * 数据库
 */

public class databaseHelp extends SQLiteOpenHelper {
    private static final String DB_NAME = "bookmanager.db";
    private static final String Table_Name1 = "admin";//读者表
    private static final String Table_Name2 = "bookinfo";//图书表
    private static final String Table_Name3 = "borrow";//借书表
    private static final String Table_Name4 = "pay";//还书表

    private static final String Creat_table = "create table admin(_id integer primary key autoincrement,user text,password text,email text )";

    private static final String Creat_table2 = "create table bookinfo(_id integer primary key autoincrement,bookname text,author text," +
            "page integer,price double,publish text,intime text )";
    private static final String Creat_table3 = "create table borrow(_Bid integer primary key autoincrement,Borname text,Bookid integer,bookname text,bookauthor text,nowtime text)";


    //借书表，Bid是借阅记录编号，Borname是借阅者名称，bookname 是书名，Bookid是书籍编号，bookauthor是作者名称，nowtime是当前系统时间
    private static final String Creat_table4 = "create table pay(_Rid integer primary key autoincrement,Borname text,Bookid integer,bookname text,bookauthor text,nowtime text)";
    //还书表，Rid是归还记录编号，Borname是借阅者名称，bookname 是书名，Bookid是书籍编号，bookauthor是作者名称，nowtime是当前系统时间
    public databaseHelp(Context context) {
        super(context, DB_NAME, null, 2);
    }

    SQLiteDatabase db;

    //往admin表中插入信息
    public void insert(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(Table_Name1, null, values);
        db.close();

    }

    //查询所有读者信息
    public Cursor query() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(Table_Name1, null, null, null, null, null, null);
        return cursor;

    }

    //删除读者的信息
    public void del(int id) {
        if (db == null) {
            db = getWritableDatabase();
            db.delete(Table_Name1, "_id=?", new String[]{String.valueOf(id)});
        }
        db.close();

    }

    //通过id查询读者信息
    public Cursor queryid(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(Table_Name1, null, "_id=" + id, null, null, null, null);
        return cursor;

    }

    //往bookinfo表中插入数据
    public void insertbookinfo(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(Table_Name2, null, values);
        db.close();

    }

    //往bookinfo中查询所有数据
    public Cursor querybookinfo() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(Table_Name2, null, null, null, null, null, null);
        return cursor;

    }

    //往bookinfo表中通过id查找
    public Cursor querybookinfoid(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(Table_Name2, null, "_id=" + id, null, null, null, null);
        return cursor;

    }

    //删除图书信息
    public void delbookinfo(int id) {
        if (db == null) {
            db = getWritableDatabase();
            db.delete(Table_Name2, "_id=?", new String[]{String.valueOf(id)});
        }
        db.close();

    }

    //往borrow表中添加数据
    public void insertorrowo(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(Table_Name3, null, values);
        db.close();

    }
    //查询boroow表中的所有数据
    public Cursor queryborrowinfo() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(Table_Name3, null, null, null, null, null, null);
        return cursor;

    }
    //查询borrow表中的所有数据
    public List<Map<String, Object>> queryborrow(){
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query(Table_Name3, null,null, null, null, null, null);
        while(cursor.moveToNext()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("_Bid",cursor.getString(cursor.getColumnIndex("_Bid")));
            map.put("Borname",cursor.getString(cursor.getColumnIndex("Borname")));
            map.put("Bookid",cursor.getString(cursor.getColumnIndex("Bookid")));
            map.put("bookname",cursor.getString(cursor.getColumnIndex("bookname")));
            map.put("bookauthor",cursor.getString(cursor.getColumnIndex("bookauthor")));
            map.put("nowtime",cursor.getString(cursor.getColumnIndex("nowtime")));
            data.add(map);
        }
        return data;
    }
    //在borrow表中根据用户查询

    public List<Map<String, Object>> queryuser(String str){
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query(Table_Name3, null,"Borname=?", new String[]{str}, null, null, null);
        while(cursor.moveToNext()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("_Bid",cursor.getString(cursor.getColumnIndex("_Bid")));
            map.put("Borname",cursor.getString(cursor.getColumnIndex("Borname")));
            map.put("Bookid",cursor.getString(cursor.getColumnIndex("Bookid")));
            map.put("bookname",cursor.getString(cursor.getColumnIndex("bookname")));
            map.put("bookauthor",cursor.getString(cursor.getColumnIndex("bookauthor")));
            map.put("nowtime",cursor.getString(cursor.getColumnIndex("nowtime")));
            data.add(map);
        }
        return data;
    }
    //通过删除borrow表的信息
    public void delborrow(int id) {
        if (db == null) {
            db = getWritableDatabase();
            db.delete(Table_Name3, "_Bid=?", new String[]{String.valueOf(id)});
        }
        db.close();

    }

    //通过id查询borrow表里的信息
    public Cursor queryborrowinfoid(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(Table_Name3, null, "_Bid=" + id, null, null, null, null);
        return cursor;

    }

    //往pay表中添加数据
    public void insertpay(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(Table_Name4, null, values);
        db.close();

    }
    //查询pay表中的所有数据
    public List<Map<String, Object>> querypay(){
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query(Table_Name4, null,null, null, null, null, null);
        while(cursor.moveToNext()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("_Rid",cursor.getString(cursor.getColumnIndex("_Rid")));
            map.put("Borname",cursor.getString(cursor.getColumnIndex("Borname")));
            map.put("Bookid",cursor.getString(cursor.getColumnIndex("Bookid")));
            map.put("bookname",cursor.getString(cursor.getColumnIndex("bookname")));
            map.put("bookauthor",cursor.getString(cursor.getColumnIndex("bookauthor")));
            map.put("nowtime",cursor.getString(cursor.getColumnIndex("nowtime")));
            data.add(map);
        }
        return data;
    }
    //在pay表中根据用户查询

    public List<Map<String, Object>> querypayuser(String str){
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query(Table_Name4, null,"Borname=?", new String[]{str}, null, null, null);
        while(cursor.moveToNext()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("_Rid",cursor.getString(cursor.getColumnIndex("_Rid")));
            map.put("Borname",cursor.getString(cursor.getColumnIndex("Borname")));
            map.put("Bookid",cursor.getString(cursor.getColumnIndex("Bookid")));
            map.put("bookname",cursor.getString(cursor.getColumnIndex("bookname")));
            map.put("bookauthor",cursor.getString(cursor.getColumnIndex("bookauthor")));
            map.put("nowtime",cursor.getString(cursor.getColumnIndex("nowtime")));
            data.add(map);
        }
        return data;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;
        db.execSQL(Creat_table);
        db.execSQL(Creat_table2);
        db.execSQL(Creat_table3);
        db.execSQL(Creat_table4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(Creat_table);
                break;
            case 2:
                db.execSQL(Creat_table2);
                break;
            case 3:
                db.execSQL(Creat_table3);
                break;
            case 4:
                db.execSQL(Creat_table4);
                break;
            default:

        }

    }

    //打开外键
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
