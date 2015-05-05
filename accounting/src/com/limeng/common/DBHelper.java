package com.limeng.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    
    private Context context;
    
    private static final String DB_NAME = "accounting.db";
    
    private static final int DB_VERSION = 2;
    
    /**
     * 创建消费记录表
     */
    public static final String CREATE_TABLE_RECORD = "create table record(id integer primary key autoincrement, type varchar(32), cost REAL(8,2), record_time long)";
    
    public static final String DROP_TABLE_RECORD = "drop table record";
    
    /**
     * @param context
     */
    public static final String CREATE_TABLE_BUDGET = "create table budget(id integer primary key autoincrement, month integer, total REAL(8,2), year integer)";
    
    public static final String DROP_TABLE_BUDGET = "drop table budget";
    
    public DBHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }
    
    public DBHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RECORD);
        
        db.execSQL(CREATE_TABLE_BUDGET);
        
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_RECORD);
        db.execSQL(CREATE_TABLE_RECORD);
        
        db.execSQL(DROP_TABLE_BUDGET);
        db.execSQL(CREATE_TABLE_BUDGET);
    }
    
    public SQLiteDatabase getSQLiteDatabase() {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
        } catch (SQLiteException e) {
            db = this.getReadableDatabase();
        }
        return db;
        
    }
    
}
