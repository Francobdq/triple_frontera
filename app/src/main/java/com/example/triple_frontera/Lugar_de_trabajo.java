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

    private static final int TIPO_PAIS = 0;
    private static final int TIPO_AREA_OPERATIVA = 1;
    private static final int TIPO_PARAJE = 2;


    Spinner pais;
    Spinner area_operativa;
    Spinner paraje;

    int[] id_paises;
    int[] id_areas_operativas;
    int[] id_parajes;

    int id_paraje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // deshabilita la rotación de pantalla
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_de_trabajo);
        getSupportActionBar().hide();
        pais = (Spinner) findViewById(R.id.spinner_pais);
        area_operativa = (Spinner) findViewById(R.id.spinner_area_operativa);
        paraje = (Spinner) findViewById(R.id.spinner_paraje);

        ActualizarSpinner(TIPO_PAIS);

        // BORRAR ESTO ES PARA DEBUG
        /*Toast.makeText(getApplicationContext(), "continuar", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(Lugar_de_trabajo.this, ListadoPacientes.class);
        Lugar_de_trabajo.this.startActivity(myIntent);*/
        //return;
        //
    }


    private String[] nombreAndID(List<Zona> lista, int tipo) {
        String[] lista_string = new String[lista.size()];

        int[] id_ref = new int[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            lista_string[i] = lista.get(i).nombre;
            id_ref[i] = lista.get(i).id;
        }

        if (tipo == TIPO_PAIS)
            id_paises = id_ref;
        else if (tipo == TIPO_AREA_OPERATIVA)
            id_areas_operativas = id_ref;
        else
            id_parajes = id_ref;

        return lista_string;
    }


    private void ActualizarSpinner(int tipo, int id){
        String[] datos;
        Spinner sp;
        DbHelper db = new DbHelper(Lugar_de_trabajo.this);
        if (tipo == TIPO_PAIS){
            System.out.println("--------------------------------------------------");
            datos = nombreAndID(db.getAllPaises(), TIPO_PAIS);
            System.out.println("paises: " + id_paises);
            sp = pais;
        }
        else if (tipo == TIPO_AREA_OPERATIVA){
            datos = nombreAndID(db.getAllAreasOperativasFromPais(id), TIPO_AREA_OPERATIVA);
            sp = area_operativa;
        }
        else{
            datos = nombreAndID(db.getAllParajesFromAreaOperativa(id), TIPO_PARAJE);
            sp = paraje;
        }

        ArrayAdapter<String> adapter_datos = new ArrayAdapter<String>(Lugar_de_trabajo.this, android.R.layout.simple_spinner_dropdown_item, datos);
        sp.setAdapter(adapter_datos);
        sp.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                
                public void onItemSelected(AdapterView<?> spn, android.view.View v, int posicion, long id) {
                    Toast.makeText(spn.getContext(), "Has seleccionado " + spn.getItemAtPosition(posicion).toString(),Toast.LENGTH_LONG).show();
                    if(id_paises != null && tipo == TIPO_PAIS){
                        int id_pais = id_paises[posicion];
                        System.out.println("------------------------------------------------");
                        System.out.println(id_pais + "-" + spn.getItemAtPosition(posicion).toString());
                        // borro los datos del spinner tipo_paraje
                        paraje.setAdapter(null);
                        // actualizo los datos del spinner tipo_area_operativa
                        ActualizarSpinner(TIPO_AREA_OPERATIVA, id_pais);
                    }
                    else if(id_areas_operativas != null && tipo == TIPO_AREA_OPERATIVA){
                        int id_area_operativa = id_areas_operativas[posicion];
                        System.out.println("------------------------------------------------");
                        System.out.println(id_area_operativa + "-" + spn.getItemAtPosition(posicion).toString());
                        ActualizarSpinner(TIPO_PARAJE, id_area_operativa);
                    }
                    else{
                        id_paraje = id_parajes[posicion];
                        System.out.println("------------------------------------------------");
                        System.out.println(id_paraje + "-" + spn.getItemAtPosition(posicion).toString());
                    }

                }
                public void onNothingSelected(AdapterView<?> spn) {
                }
        });

        db.close();
    }

    private void ActualizarSpinner(int tipo){
        ActualizarSpinner(tipo, 0);
    }


    public void Continuar(View view){
        Toast.makeText(getApplicationContext(), "Continuo a listado pacientes", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(Lugar_de_trabajo.this, ListadoPacientes.class);
        myIntent.putExtra("id_paraje", id_paraje);
        Lugar_de_trabajo.this.startActivity(myIntent);
        //Intent myIntent = new Intent(MainActivity.this, Lugar_de_trabajo.class);

        //myIntent.putExtra("idEdificio", idEdificio); //Optional parameters
        //MainActivity.this.startActivity(myIntent);
    }
    
}