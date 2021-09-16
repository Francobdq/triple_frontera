package com.example.triple_frontera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ListadoPacientes extends AppCompatActivity {

    String[] pacientes;

    EditText buscar_paciente;
    ListView list_pacientes;

    int id_paciente_seleccionado = -1;


    

    // derecha
    TextView nombre_header_tv;

    TextView nombre_paciente;
    TextView apellidos_paciente;
    TextView documento_paciente;
    TextView fecha_de_nacimiento;


    Spinner sp_controles;

    TextView estado;
    TextView edad_gestacional;
    TextView eco;
    TextView fpp;
    TextView control_clinico;

    TextView sifilis;
    TextView hiv;
    TextView chagas;
    TextView vhb;
    TextView hb;
    TextView glucemia;
    TextView grupo_y_factor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_pacientes);

        buscar_paciente= (EditText)findViewById(R.id.buscar_paciente);
        
        list_pacientes=(ListView)findViewById(R.id.lista_pacientes);
 
        // derecha 
        nombre_header_tv = (TextView)findViewById(R.id.nombre_header_tv);

        nombre_paciente = (TextView)findViewById(R.id.di_nombre_tv);
        apellidos_paciente = (TextView)findViewById(R.id.di_apellidos_tv);
        documento_paciente = (TextView)findViewById(R.id.di_documento_tv);
        fecha_de_nacimiento = (TextView)findViewById(R.id.di_fecha_de_nacimiento_tv);


        sp_controles = (Spinner)findViewById(R.id.sp_controles);

        estado = (TextView)findViewById(R.id.tb_estado_tv);
        edad_gestacional = (TextView)findViewById(R.id.tb_edad_gestacional_tv);
        eco = (TextView)findViewById(R.id.tb_eco_tv);
        fpp = (TextView)findViewById(R.id.tb_fpp_tv);
        control_clinico = (TextView)findViewById(R.id.tb_control_clinico_tv);

        sifilis = (TextView)findViewById(R.id.tb_sifilis_tv);
        hiv = (TextView)findViewById(R.id.tb_hiv_tv);
        chagas = (TextView)findViewById(R.id.tb_chagas_tv);
        vhb = (TextView)findViewById(R.id.tb_vhb_tv);
        hb = (TextView)findViewById(R.id.tb_hb_tv);
        glucemia = (TextView)findViewById(R.id.tb_glucemia_tv);
        grupo_y_factor = (TextView)findViewById(R.id.tb_grupo_y_factor_tv);

        int id_paraje = 0;

        pacientes = BaseDeDatos.pacientesNombreConDNI(id_paraje);
        listaPacientes();
        seleccionItemLista();

        cargarDatosPaciente();
        cargarControlesEnSpinner();
    }


    public void irAEmbarazada(View view){
        Intent myIntent = new Intent(ListadoPacientes.this, Ingresar_embarazada.class);
        ListadoPacientes.this.startActivity(myIntent);
    }

    private void listaPacientes(){
        ArrayAdapter adapter= new ArrayAdapter(this,R.layout.list_item,R.id.text,pacientes);
        list_pacientes.setAdapter(adapter);


        buscar_paciente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
 
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }
 
            @Override
            public void afterTextChanged(Editable s) {
 
            }
        });
    }

    private void seleccionItemLista(){
        list_pacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String paciente = pacientes[position];
                Toast.makeText(getApplicationContext(),paciente, Toast.LENGTH_SHORT).show();
            }   
        });
    }

    private void cargarControlesEnSpinner(){
        String[] controles = BaseDeDatos.Controles(id_paciente_seleccionado);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.list_item,R.id.text,controles);
        sp_controles.setAdapter(adapter);
        
        sp_controles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cargarDatosDeControl(1); // el id es auxiliar se debe buscar el id del control
            }
        
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // selecciono el primer control del spinner
                sp_controles.setSelection(0);
            }
        
        });
    }

    private void cargarDatosDeControl(int id_control){
        String[] datosDeControl = BaseDeDatos.getDatosControl(id_control); 

        estado.setText(datosDeControl[0]);
        edad_gestacional.setText(datosDeControl[1]);
        eco.setText(datosDeControl[2]);
        fpp.setText(datosDeControl[3]);
        control_clinico.setText(datosDeControl[4]);

        // datos de laboratorio
        sifilis.setText(datosDeControl[5]);
        hiv.setText(datosDeControl[6]);
        chagas.setText(datosDeControl[7]);
        vhb.setText(datosDeControl[8]);
        hb.setText(datosDeControl[9]);
        glucemia.setText(datosDeControl[10]);
        grupo_y_factor.setText(datosDeControl[11]);



    }

    private void cargarDatosPaciente(){
        String[] datosPersonales = BaseDeDatos.DatosPersonales(id_paciente_seleccionado);

        nombre_header_tv.setText(datosPersonales[0] + ", " + datosPersonales[1]);

        nombre_paciente.setText(datosPersonales[0]);
        apellidos_paciente.setText(datosPersonales[1]);
        documento_paciente.setText(datosPersonales[2]);
        fecha_de_nacimiento.setText(datosPersonales[3]);
    }

}