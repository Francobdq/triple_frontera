package com.example.triple_frontera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // para el login
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText)findViewById(R.id.usuario);
        password = (EditText)findViewById(R.id.password);
    }

    public void Continuar(View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        if(user.equals("") || pass.equals("")){
            Toast.makeText(getApplicationContext(), "Faltan datos", Toast.LENGTH_SHORT).show();
            return;
        }

        if(BaseDeDatos.Login(user, pass)){
            Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(MainActivity.this, Lugar_de_trabajo.class);

            //myIntent.putExtra("idEdificio", idEdificio); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
        }
    }


}