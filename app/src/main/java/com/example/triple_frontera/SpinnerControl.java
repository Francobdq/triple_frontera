package com.example.triple_frontera;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.db.DbHelper;
import com.example.db.entidades.Controles;

import java.util.List;

public class SpinnerControl {

    Spinner sp_controles;
    Context ctx;

    int[] id_controles;

    public SpinnerControl(Spinner controles, Context ctx){
        this.sp_controles = controles;
        this.ctx = ctx;
    }


    public void cargarDatosEnSpinner(List<Controles> controles){
        id_controles = new int[controles.size()];

        // ahora cargo los controles en el spinner
        if (controles.size() > 0){
            // cargo el spinner
            String[] nombres_controles = new String[controles.size()];

            for (int i = 0; i < controles.size(); i++){
                nombres_controles[i] = controles.get(i).fecha_control;
                id_controles[i] = controles.get(i).id;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, nombres_controles);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_controles.setAdapter(adapter);
            sp_controles.setSelection(controles.size()-1);
        }
    }


    public int getLength(){
        return id_controles.length;
    }

    public int getId(int index){
        return id_controles[index];
    }

    public int indexSelectedItem(){
        return sp_controles.getSelectedItemPosition();
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener, int seleccionDefecto){
        sp_controles.setSelection(seleccionDefecto);
        sp_controles.setOnItemSelectedListener(listener);
    }
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener){
        setOnItemSelectedListener(listener, 0);
    }

    public void actualizarDatosControlDB(int id_paciente){
        DbHelper db = new DbHelper(ctx);
        List<Controles> controles = db.getControlesFromPaciente(id_paciente);
        cargarDatosEnSpinner(controles);
        db.close();
    }
}
