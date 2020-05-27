package com.examen.android.StateSQLiteHelper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TableUsersInformation {

    public String id = "id";
    private String nombre = "nombre";
    private String fecha = "fecha";
    private String puesto = "puesto";

    String table_users_info = "table_users_info";
    String sql = "CREATE TABLE IF NOT EXISTS " + table_users_info + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + nombre + " TEXT," + fecha + " TEXT," + puesto + " TEXT)";

    public String getSql() {
        return sql;
    }

    public void AddInformationTableUser(SQLiteDatabase db, int id, String nombre, String fecha, String puesto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.nombre, nombre);
        contentValues.put(this.fecha, fecha);
        contentValues.put(this.puesto, puesto);
        db.insert(table_users_info, null, contentValues);
        Log.d("DataBase procesado", "una fila insertada");
    }

    public String upDateInformation(SQLiteDatabase db, String activated, String typeUser) {
        return "UPDATE table_session SET " + this.nombre + " = " + activated + "," + this.fecha + "=" + typeUser;
    }

}
