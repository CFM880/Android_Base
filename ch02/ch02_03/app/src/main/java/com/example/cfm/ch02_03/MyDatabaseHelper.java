package com.example.cfm.ch02_03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    final String CREATE_TABLE_SQL =
            "create table dict(_id integer primary " +
                    "key autoincrement , word , detail)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("---调用onUpdate---+oldVersion" + "--->" + newVersion);
    }

    public MyDatabaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }
}
