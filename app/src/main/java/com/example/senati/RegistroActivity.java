package com.example.senati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    private EditText nom, ape, dnii, usu, pass;
    private Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nom = (EditText) findViewById(R.id.txtNombre);
        ape = (EditText) findViewById(R.id.txtApellido);
        dnii = (EditText) findViewById(R.id.txtDni);
        usu = (EditText) findViewById(R.id.txtUsuario);
        pass = (EditText) findViewById(R.id.txtPassword);

        reg = (Button) findViewById(R.id.btnReg);
        reg.setOnClickListener(this::Registrar);
    }

    public void Registrar (View view){
        Administrador admin=new Administrador( this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos= admin.getWritableDatabase();
        String nombre =nom.getText().toString();
        String apellido= ape.getText().toString();
        String dni= dnii.getText().toString();
        String usuario= usu.getText().toString();
        String clave= pass.getText().toString();

        if(!nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty() && !usuario.isEmpty() && !clave.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("Nombre",nombre);
            registro.put("Apellido",apellido);
            registro.put("DNI",dni);
            registro.put("Usuario",usuario);
            registro.put("Clave",clave);

            BaseDeDatos.insert("personas", null, registro);
            BaseDeDatos.close();
            nom.setText("");
            ape.setText("");
            dnii.setText("");
            usu.setText("");
            pass.setText("");

            Toast.makeText( this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegistroActivity.this, PrincipalActivity.class); // Para que nos regrese al Login
            startActivity(intent); //Inicializa la vista
        } else{
            Toast.makeText(this,  "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}