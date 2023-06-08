package com.example.rolpagos.bdd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.rolpagos.R;

public class BDHelper extends AppCompatActivity {

    public BDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(funcionario, cargo, area, hijos, estado_civil);
    }

    public void onCreate(SQLiteDatabase db) {
        //CREACIÓN DE LAS TABLAS
        db.execSQL("CREATE TABLE tblFormulario"+"(" +
                "for_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "for_funcionario text NOT NULL,"+
                "for_cargo text NOT NULL," +
                "for_area integer NOT NULL,"+
                "for_hijos text NOT NULL," +
                "for_civil text NOT NULL)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //CAMBIE LA VERSIÓN DE LA TABLA DE LA BDD
        db.execSQL("DROP TABLE tblFormulario");
        onCreate(db);
    }

}