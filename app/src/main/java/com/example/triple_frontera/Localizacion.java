package com.example.triple_frontera;

/* 
    Clase que se encarga del manejo de los spinners de localizacion (pais, area_operativa, paraje) 
*/


import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.db.DbHelper;
import com.example.db.entidades.Zona;

import java.util.List;

public class Localizacion {

    // para controlar de que tipo de localizacion se trata
    private static final int TIPO_PAIS = 0;
    private static final int TIPO_AREA_OPERATIVA = 1;
    private static final int TIPO_PARAJE = 2;

    private Spinner pais;
    private Spinner area_operativa;
    private Spinner paraje;

    private int[] id_paises;
    private int[] id_areas_operativas;
    private int[] id_parajes;

    // son los id en bdd de los elementos seleccionados actualmente
    private int id_pais = -1;
    private int id_area_operativa = -1;
    private int id_paraje = -1;

    private Context ctx;

    public Localizacion(Context ctx, Spinner pais, Spinner area_operativa, Spinner paraje) {
        this.ctx = ctx;
        this.pais = pais;
        this.area_operativa = area_operativa;
        this.paraje = paraje;

        ActualizarSpinner(TIPO_PAIS);
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

    private void SeleccionoPais(int posicion){
        id_pais = id_paises[posicion];
        System.out.println("------------------------------------------------");
        System.out.println(id_pais + "-" + pais.getItemAtPosition(posicion).toString());
        // borro los datos del spinner tipo_paraje
        paraje.setAdapter(null);
        // actualizo los datos del spinner tipo_area_operativa
        ActualizarSpinner(TIPO_AREA_OPERATIVA, id_pais);
    }

    private void SeleccionoAreaOperativa(int posicion){
        id_area_operativa = id_areas_operativas[posicion];
        System.out.println("------------------------------------------------");
        System.out.println(id_area_operativa + "-" + area_operativa.getItemAtPosition(posicion).toString());
        ActualizarSpinner(TIPO_PARAJE, id_area_operativa);
    }

    private void SeleccionoParaje(int posicion){
        id_paraje = id_parajes[posicion];
        System.out.println("------------------------------------------------");
        System.out.println(id_paraje + "-" + paraje.getItemAtPosition(posicion).toString());
    }

    private void ActualizarSpinner(int tipo, int id){
        String[] datos;
        Spinner sp;
        DbHelper db = new DbHelper(ctx);
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

        ArrayAdapter<String> adapter_datos = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, datos);
        sp.setAdapter(adapter_datos);
        sp.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                
                public void onItemSelected(AdapterView<?> spn, android.view.View v, int posicion, long id) {
                    Toast.makeText(spn.getContext(), "Has seleccionado " + spn.getItemAtPosition(posicion).toString(),Toast.LENGTH_LONG).show();
                    if(id_paises != null && tipo == TIPO_PAIS){
                        SeleccionoPais(posicion);
                    }
                    else if(id_areas_operativas != null && tipo == TIPO_AREA_OPERATIVA){
                        SeleccionoAreaOperativa(posicion);
                    }
                    else{
                        SeleccionoParaje(posicion);
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


    public static int getIndex(Spinner spinner, String texto) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(texto)) {
                return i;
            }
        }
        return -1;
    }

    public void SeleccionManual(String nombre_pais, String nombre_area_operativa, String nombre_paraje){
        System.out.println("SELECCIONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
        System.out.println("pais: " + nombre_pais);
        System.out.println("area operativa: " + nombre_area_operativa);
        System.out.println("paraje: " + nombre_paraje);

        if (nombre_pais != null){
            int index = getIndex(pais, nombre_pais);

            if (index != -1){
                SeleccionoPais(index);
                System.out.println("index pais: " + index);
                System.out.println(pais.getItemAtPosition(index).toString());
                pais.setSelection(index);
            }
        }


        if (nombre_area_operativa != null){
            int index = getIndex(area_operativa, nombre_area_operativa);
            if (index != -1){
                SeleccionoAreaOperativa(index);
                System.out.println("index area operativa: " + index);
                System.out.println(area_operativa.getItemAtPosition(index));
                area_operativa.setSelection(index);
                System.out.println(area_operativa.getSelectedItem().toString());
            }
        }

        if (nombre_paraje != null){
            int index = getIndex(paraje, nombre_paraje);
            if (index != -1){
                SeleccionoParaje(index);
                System.out.println("index paraje: " + index);
                System.out.println(paraje.getItemAtPosition(index).toString());
                paraje.setSelection(index);
            }
        }
    }

    public void makeEditable(boolean editable){
        pais.setEnabled(editable);
        area_operativa.setEnabled(editable);
        paraje.setEnabled(editable);
    }
    

    /* GETTERS */

    public int getId_pais(){
        return id_pais;
    }

    public int getId_areaOperativa(){
        return id_area_operativa;
    }

    public int getId_paraje(){
        return id_paraje;
    }

    public String getNombrePais(){
        return pais.getSelectedItem().toString();
    }

    public String getNombreAreaOperativa(){
        return area_operativa.getSelectedItem().toString();
    }

    public String getNombreParaje(){
        return paraje.getSelectedItem().toString();
    }

}
