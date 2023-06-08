package com.example.rolpagos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText fo_funcionario, fo_cargo, fo_area, fo_hijo,fo_estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //UNIR ELEMENT0S DE BACK CON FRONT
        fo_funcionario=findViewById(R.id.txt_formulario);
        fo_cargo=findViewById(R.id.txt_cargo);
        fo_area=findViewById(R.id.txt_area);
        fo_hijo=findViewById(R.id.txt_nroHijos);
        fo_estado=findViewById(R.id.txt_estadoCivil);
    }

}