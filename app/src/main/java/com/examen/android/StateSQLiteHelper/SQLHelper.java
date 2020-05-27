package com.examen.android.StateSQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLHelper extends SQLiteOpenHelper {

    public static int version = 1;
    public static String DB_name = "data_base_info.db";
    public SQLiteDatabase db;

    public TableUsersInformation tableUsersInformation = new TableUsersInformation();

    public TableUsersInformation getTableUsersInformation() {
        return tableUsersInformation;
    }

    public SQLHelper(Context context) {
        super(context, DB_name, null, version);
        // TODO Auto-generated constructor stub
        db = getWritableDatabase();
        db.execSQL(tableUsersInformation.sql);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableUsersInformation.sql);
        Log.d("base de datos operando", "tabla creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableUsersInformation.table_users_info);
        db.execSQL(tableUsersInformation.getSql());
    }
}
