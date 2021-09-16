package com.example.triple_frontera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Lugar_de_trabajo extends AppCompatActivity {

    Spinner pais;
    Spinner area_operativa;
    Spinner paraje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_de_trabajo);
        pais = (Spinner) findViewById(R.id.spinner_pais);
        area_operativa = (Spinner) findViewById(R.id.spinner_area_operativa);
        paraje = (Spinner) findViewById(R.id.spinner_paraje);

        ActualizarSpinner(BaseDeDatos.TIPO_PAISES);

        // BORRAR ESTO ES PARA DEBUG
        Toast.makeText(getApplicationContext(), "continuar", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(Lugar_de_trabajo.this, ListadoPacientes.class);
        Lugar_de_trabajo.this.startActivity(myIntent);
        return;
        //
    }


    private void ActualizarSpinner(int tipo, int id){
        String[] datos;
        Spinner sp;
        if (tipo == BaseDeDatos.TIPO_PAISES){
            datos = BaseDeDatos.Paises();
            sp = pais;
        }
        else if (tipo == BaseDeDatos.TIPO_AREAS_OPERATIVAS){
            datos = BaseDeDatos.AreasOperativas(id);
            sp = area_operativa;
        }
        else{
            datos = BaseDeDatos.Parajes(id);
            sp = paraje;
        }

        ArrayAdapter<String> adapter_datos = new ArrayAdapter<String>(Lugar_de_trabajo.this, android.R.layout.simple_spinner_dropdown_item, datos);
        sp.setAdapter(adapter_datos);
        sp.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                
                public void onItemSelected(AdapterView<?> spn, android.view.View v, int posicion, long id) {
                    Toast.makeText(spn.getContext(), "Has seleccionado " + spn.getItemAtPosition(posicion).toString(),Toast.LENGTH_LONG).show();
                    if(tipo == BaseDeDatos.TIPO_PAISES){
                        int id_pais = posicion;
                        ActualizarSpinner(BaseDeDatos.TIPO_AREAS_OPERATIVAS, id_pais);
                    }
                    else if(tipo == BaseDeDatos.TIPO_AREAS_OPERATIVAS){
                        int id_areas_operativas = BaseDeDatos.getID_area_operativa(spn.getItemAtPosition(posicion).toString());
                        ActualizarSpinner(BaseDeDatos.TIPO_PARAJES, id_areas_operativas);
                    }

                }
                public void onNothingSelected(AdapterView<?> spn) {
                }
        });
    }

    private void ActualizarSpinner(int tipo){
        ActualizarSpinner(tipo, 0);
    }


    public void Continuar(View view){
        Toast.makeText(getApplicationContext(), "Continuo a listado pacientes", Toast.LENGTH_SHORT).show();
        //Intent myIntent = new Intent(MainActivity.this, Lugar_de_trabajo.class);

        //myIntent.putExtra("idEdificio", idEdificio); //Optional parameters
        //MainActivity.this.startActivity(myIntent);
    }
    
}