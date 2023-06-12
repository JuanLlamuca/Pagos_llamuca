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
        fo_extras=findViewById(R.id.txt_horas);
        fo_retraso=findViewById(R.id.txt_retrasos);
    }

    public double determinarSueldo(String cargo){
        double sueldo=0.00;
        //String cargo=et_cargo.getText().toString();
        if (cargo.equals("Administrativo")==true) {
            sueldo=880.00;
        }else if(cargo.equals("Docente")==true){

            sueldo= 1000.00;
        }
        return sueldo;
    }


    public double subsidio(int numero){
        double sub=0.00;

        if(numero>0){
            sub=numero*50;
        }else{
            sub=0;
        }
        return sub;
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
            fo_estado.setText("");
            fo_total.setText(this.determinarSueldo(cargo)+"");
            int numHijos=Integer.parseInt(hijo);


            bd.close();
        }else{
            Toast.makeText(this,"FAVOR INGRESAR TODOS LOS CAMPOS",Toast.LENGTH_SHORT).show();
        }
    }





}