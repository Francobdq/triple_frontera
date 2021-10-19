package com.example.triple_frontera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.db.DbHelper;
import com.example.db.entidades.Zona;

import java.util.List;

public class Lugar_de_trabajo extends AppCompatActivity {


    Localizacion localizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // deshabilita la rotación de pantalla
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_de_trabajo);
        getSupportActionBar().hide();
        Spinner pais = (Spinner) findViewById(R.id.spinner_pais);
        Spinner area_operativa = (Spinner) findViewById(R.id.spinner_area_operativa);
        Spinner paraje = (Spinner) findViewById(R.id.spinner_paraje);

        localizacion = new Localizacion(Lugar_de_trabajo.this, pais, area_operativa, paraje);
    
    }


    public void Continuar(View view){
        Toast.makeText(getApplicationContext(), "Continuo a listado pacientes", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(Lugar_de_trabajo.this, ListadoPacientes.class);
        myIntent.putExtra("pais_elegido", localizacion.getNombrePais());
        myIntent.putExtra("area_operativa_elegida", localizacion.getNombreAreaOperativa());
        myIntent.putExtra("paraje_elegido", localizacion.getNombreParaje());
        
        myIntent.putExtra("id_paraje", localizacion.getId_paraje());
        Lugar_de_trabajo.this.startActivity(myIntent);
        //Intent myIntent = new Intent(MainActivity.this, Lugar_de_trabajo.class);

        //myIntent.putExtra("idEdificio", idEdificio); //Optional parameters
        //MainActivity.this.startActivity(myIntent);
    }
    
}