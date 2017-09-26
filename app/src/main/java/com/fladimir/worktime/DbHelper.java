package com.fladimir.worktime;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ScDz on 2017/9/26.
 * ClassNote:
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "tomato.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_CONFIG = "config";
    private static final String TABLE_TOMATO = "tomato";
    private static final String CREAT_TABLE_CONFIG = "create TABLE IF NOT EXISTS " + TABLE_CONFIG +
            "(_id integer primary key autoincrement, notify boolean default true," +
            " shock boolean default false, time int default 20  )";
    private static final String CREAT_TABLE_TOMATO = "create TABLE IF NOT EXISTS " + TABLE_TOMATO +
            "(_id integer primary key autoincrement, date Date, time text)";
    private SQLiteDatabase db;

    public DbHelper(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db = sqLiteDatabase;
        db.execSQL(CREAT_TABLE_CONFIG);
        db.execSQL(CREAT_TABLE_TOMATO);
    }

    /**
     * 构造DBHelper的传递的Version与之前的Version调用此函数
     *
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * query
     *
     * @param sql
     * @param args
     * @return
     */
    public Cursor rawQuery(String sql, String[] args) {
        db = getWritableDatabase();
        Cursor c = db.rawQuery(sql, args);
        return c;
    }

    /**
     * 关闭数据库
     */
    public void close() {
        if (db != null) {
            db.close();
        }
    }
}
