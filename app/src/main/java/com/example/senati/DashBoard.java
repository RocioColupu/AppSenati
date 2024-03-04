package com.example.senati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DashBoard extends AppCompatActivity {
    private EditText nom, ape, dni, usu, cla;
    private Button editar, cerrar, guardar;

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        nom = (EditText) findViewById(R.id.txtnombre);
        ape = (EditText) findViewById(R.id.txtapellido);
        dni = (EditText) findViewById(R.id.txtdni);
        usu = (EditText) findViewById(R.id.txtusuario);
        cla = (EditText) findViewById(R.id.txtclave);

        guardar = findViewById(R.id.btnActualizar);

        editar = (Button) findViewById(R.id.btnEditar);
        editar.setOnClickListener(this::habilitarEdicion);

        cerrar = (Button) findViewById(R.id.btnRegistrar);
        cerrar.setOnClickListener(this::cerrarSesion);


        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        mostrarDatosUsuario();

        // Configurar listener del botón de guardar
        guardar.setOnClickListener(view -> actualizarDatosUsuario());
    }

    private void mostrarDatosUsuario (){

        String nombre = sharedPreferences.getString("nombre: ", "");
        String apellido = sharedPreferences.getString("apellido", "");
        String documento = sharedPreferences.getString("dni: ", "");
        String usuario = sharedPreferences.getString("usuario: ", "");
        String clave = sharedPreferences.getString("clave: ", "");

        nom.setText(nombre);
        ape.setText(apellido);
        dni.setText(documento);
        usu.setText(usuario);
        cla.setText(clave);

        nom.setEnabled(false);
        ape.setEnabled(false);
        dni.setEnabled(false);
        usu.setEnabled(false);
        cla.setEnabled(false);


        guardar.setVisibility(View.GONE);
    }

    private void habilitarEdicion(View view){
        nom.setEnabled(true);
        ape.setEnabled(true);
        dni.setEnabled(true);
        usu.setEnabled(true);
        cla.setEnabled(true);

        guardar.setVisibility(View.VISIBLE);
        editar.setVisibility(View.GONE);
    }

    private void actualizarDatosUsuario() {
        // Obtener nuevos datos del usuario ingresados por el usuario
        String nuevoNombre = nom.getText().toString();
        String nuevoApellido = ape.getText().toString();
        String nuevoDocumento = dni.getText().toString();
        String nuevoUsuario = usu.getText().toString();
        String nuevaClave = cla.getText().toString();

        // Actualizar SharedPreferences con los nuevos datos del usuario
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nombre", nuevoNombre);
        editor.putString("apellido", nuevoApellido);
        editor.putString("dni", nuevoDocumento);
        editor.putString("usuario", nuevoUsuario);
        editor.putString("clave", nuevaClave);
        editor.apply();

        // Deshabilitar la edición de los campos de texto después de guardar los cambios
        nom.setEnabled(false);
        ape.setEnabled(false);
        dni.setEnabled(false);
        usu.setEnabled(false);
        cla.setEnabled(false);

        // Ocultar el botón de guardar
        guardar.setVisibility(View.GONE);
        editar.setVisibility(View.VISIBLE);
    }

    private void cerrarSesion(View view) {

        // Regresa a la pantalla de inicio de sesión
        Intent intent = new Intent(DashBoard.this, SalidaActivity.class);
        startActivity(intent);
        finish(); // Esto evita que el usuario pueda volver a la pantalla de perfil presionando el botón "Atrás"
    }
}