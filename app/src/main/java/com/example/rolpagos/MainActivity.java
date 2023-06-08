package com.example.rolpagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rolpagos.bdd.BDHelper;

public class MainActivity extends  AppCompatActivity {

    EditText fo_funcionario, fo_cargo, fo_area, fo_hijo,fo_estado, fo_extras,fo_retraso,fo_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //UNIR ELEMENT0S DE BACK CON FRONT
        fo_funcionario=findViewById(R.id.txt_formulario);
        fo_cargo=findViewById(R.id.txt_cargo);
        fo_area=findViewById(R.id.txt_area);
        fo_hijo=findViewById(R.id.txt_hijos);
        fo_estado=findViewById(R.id.txt_estado);
    }

    public void registrar(View view){
        BDHelper admin=new BDHelper(this,"registro.db",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String funcionario=fo_funcionario.getText().toString();
        String cargo=fo_cargo.getText().toString();
        String area=fo_area.getText().toString();
        String hijo=fo_hijo.getText().toString();
        String estado=fo_estado.getText().toString();

        if(!funcionario.isEmpty() && !cargo.isEmpty() && !area.isEmpty() && !hijo.isEmpty() && !estado.isEmpty()){
            ContentValues registro=new ContentValues();
            registro.put("for_funcionario",funcionario);
            registro.put("for_cargo",cargo);
            registro.put("for_area",area);
            registro.put("for_hijo",hijo);
            registro.put("for_estado",estado);
            bd.insert("tblFormulario",null,registro);
            Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
            fo_funcionario.setText("");
            fo_area.setText("");
            fo_cargo.setText("");
            fo_hijo.setText("");
            fo_estado.setText("");
            bd.close();
        }else{
            Toast.makeText(this,"FAVOR INGRESAR TODOS LOS CAMPOS",Toast.LENGTH_SHORT).show();
        }





    }



}