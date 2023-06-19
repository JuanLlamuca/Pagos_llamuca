package com.example.rolpagos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rolpagos.bdd.BDHelper;

public class MainActivity extends  AppCompatActivity {

    EditText fo_cedula,fo_funcionario, fo_cargo, fo_area, fo_hijo,fo_estado, fo_extras,fo_retraso,fo_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //UNIR ELEMENT0S DE BACK CON FRONT
        fo_cedula=findViewById(R.id.txt_Cedula);
        fo_funcionario=findViewById(R.id.txt_formulario);
        fo_cargo=findViewById(R.id.txt_cargo);
        fo_area=findViewById(R.id.txt_area);
        fo_hijo=findViewById(R.id.txt_hijos);
        fo_estado=findViewById(R.id.txt_estado);
        fo_extras=findViewById(R.id.txt_horas);
        fo_retraso=findViewById(R.id.txt_retrasos);
        fo_total = findViewById(R.id.txt_sueldo);
    }

    public double determinarSueldo(String cargo,String retraso,int hijo,int extras){
        double sueldo=0.00;
        //String cargo=et_cargo.getText().toString();
        if (cargo.equals("Administrativo")==true) {
            if (retraso.equals("si") == true) {
                if (hijo > 0) {
                    if (extras > 0) {
                        sueldo = (880.00 - 0.8) + (50 * hijo) + (12 * extras);
                    }
                }

            }else if(retraso.equals("no")==true){
                if (hijo > 0) {
                    if (extras > 0) {
                        sueldo = 880.00 + (50 * hijo) + (12 * extras);
                    }
                }
            }

        }else if(cargo.equals("Docente")==true) {
            if (retraso.equals("si") == true) {
                if (hijo > 0) {
                    if (extras > 0) {
                        sueldo = (1000.0 - 0.8) + (50 * hijo) + (12 * extras);
                    }
                }

            }else if(retraso.equals("no")==true){
                if (hijo > 0) {
                    if (extras > 0) {
                        sueldo = 1000.0 + (50 * hijo) + (12 * extras);
                    }
                }
            }
        }
        return sueldo;
    }

    public void registrar(View view){
            BDHelper admin = new BDHelper(this, "registro.db", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String cedula = fo_cedula.getText().toString();
            String funcionario = fo_funcionario.getText().toString();
            String cargo = fo_cargo.getText().toString();
            String area = fo_area.getText().toString();
            int hijo = Integer.parseInt(fo_hijo.getText().toString());
            String estado = fo_estado.getText().toString();
            String retraso = fo_retraso.getText().toString();
            int extras = Integer.parseInt(fo_extras.getText().toString());

            if (!cedula.isEmpty() &&!funcionario.isEmpty() && !cargo.isEmpty() && !area.isEmpty() && !estado.isEmpty()) {
                ContentValues registro = new ContentValues();
                registro.put("for_cedula",cedula);
                registro.put("for_funcionario", funcionario);
                registro.put("for_cargo", cargo);
                registro.put("for_area", area);
                registro.put("for_hijo", hijo);
                registro.put("for_estado", estado);

                bd.insert("tblFormulario", null, registro);
                Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                fo_cedula.setText("");
                fo_funcionario.setText("");
                fo_area.setText("");
                fo_cargo.setText("");
                fo_estado.setText("");
                fo_hijo.setText("");
                fo_retraso.setText("");
                fo_extras.setText("");
                fo_total.setText(this.determinarSueldo(cargo, retraso, extras, hijo) + "");
                bd.close();
            } else {
                Toast.makeText(this, "FAVOR INGRESAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
            }
    }

    public void MostrarDatos (View view) {
        BDHelper admin = new BDHelper(this, "registro.db", null, 2);
        SQLiteDatabase bd = admin.getReadableDatabase();
        String cedula = fo_cedula.getText().toString();

        if (!cedula.isEmpty()) {

            // Columnass que voy a recuperar de la base de datos
            String[] columnas = {"for_cedula", "for_funcionario", "for_cargo", "for_area", "for_hijo", "for_estado"};

            // Define la cláusula con el parametro de la bd: usu_cedula, WHERE para seleccionar el registro con el ID
            String whereClause = "for_cedula = ?";
            String[] whereArgs = new String[] {cedula};

            // Realiza la consulta
            Cursor cursor = bd.query("tblFormulario", columnas, whereClause, whereArgs, null, null, null);

            if (cursor.moveToFirst()) {
                // Obtén los valores de las columnas del cursor
                @SuppressLint("Range") String funcionario = cursor.getString(cursor.getColumnIndex("for_funcionario"));
                @SuppressLint("Range") String cargo = cursor.getString(cursor.getColumnIndex("for_cargo"));
                @SuppressLint("Range") String area = cursor.getString(cursor.getColumnIndex("for_area"));
                @SuppressLint("Range") String hijo = cursor.getString(cursor.getColumnIndex("for_hijo"));
                @SuppressLint("Range") String estado = cursor.getString(cursor.getColumnIndex("for_estado"));


                // Muestra los valores en los campos correspondientes
                fo_funcionario.setText(funcionario);
                fo_cargo.setText(cargo);
                fo_area.setText(area);
                fo_hijo.setText(hijo);
                fo_estado.setText(estado);

            }
            cursor.close();

        }else{
            Toast.makeText(this,"INGRESE LA CEDULA DEL FUNCIONARIO",Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void ActualizarDatos(View view) {
        BDHelper admin = new BDHelper(this, "registro.db", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Conectar backend con variables String para el SQLite
        String cedula = fo_cedula.getText().toString();
        String funcionario = fo_funcionario.getText().toString();
        String cargo = fo_cargo.getText().toString();
        String area = fo_area.getText().toString();
        String hijo = fo_hijo.getText().toString();
        String estado = fo_estado.getText().toString();


        if (!cedula.isEmpty() && !funcionario.isEmpty() && !cargo.isEmpty() && !area.isEmpty() && !hijo.isEmpty()) {

            ContentValues valores = new ContentValues();
            valores.put("for_funcionario", funcionario);
            valores.put("for_cargo", cargo);
            valores.put("for_area", area);
            valores.put("for_hijo", hijo);
            valores.put("for_estado", estado);

            // Definir la cláusula WHERE para identificar el registro a actualizar (por ejemplo, basado en un ID único)
            String whereClause = "for_cedula = ?";
            String[] whereArgs = new String[] {cedula};

            // Ejecutar la actualización
            int filasActualizadas = bd.update("tblFormulario", valores, whereClause, whereArgs);

            if (filasActualizadas > 0) {
                Toast.makeText(this, "ACTUALIZACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
                this.LimpiaConsultas();
            } else {
                Toast.makeText(this, "NO SE PUDO ACTUALIZAR EL REGISTRO", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "PRIMERO HAZ UNA CONSULTA AL USUARIO", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }


    public void EliminarDatos(View view) {
        BDHelper admin = new BDHelper(this, "registro.db", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cedula = fo_cedula.getText().toString();

        if (!cedula.isEmpty()) {
            String whereClause = "for_cedula = ?";
            String[] whereArgs = new String[] {cedula};

            // Realiza la eliminación
            int filasEliminadas = bd.delete("tblFormulario", whereClause, whereArgs);

            if (filasEliminadas > 0) {
                Toast.makeText(this, "ELIMINACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
                //Limpiar campos
                this.LimpiaConsultas();
            } else {
                Toast.makeText(this, "NO SE PUDO ELIMINAR EL REGISTRO", Toast.LENGTH_SHORT).show();
            }
            bd.close();
        }else{
            Toast.makeText(this,"INGRESE LA CEDULA DEL FUNCIONARIO",Toast.LENGTH_SHORT).show();
        }
    }


    private void LimpiaConsultas() {
        fo_funcionario.setText("");
        fo_cargo.setText("");
        fo_area.setText("");
        fo_hijo.setText("");
        fo_estado.setText("");
        fo_retraso.setText("");
        fo_extras.setText("");
    }

}