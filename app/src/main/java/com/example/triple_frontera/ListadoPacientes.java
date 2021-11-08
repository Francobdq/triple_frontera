package com.example.triple_frontera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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

import com.example.db.DbHelper;
import com.example.db.entidades.Antecedentes;
import com.example.db.entidades.Controles;
import com.example.db.entidades.Pacientes;
import com.example.db.entidades.Sereologias;

import java.util.List;

public class ListadoPacientes extends AppCompatActivity {

    String[] pacientes;

    EditText buscar_paciente;
    ListView list_pacientes;

    int id_paciente_seleccionado = -1;

    String pais_elegido;
    String area_operativa_elegida;
    String paraje_elegido;
    int id_paraje;
    int[] id_pacientes;
    

    // derecha
    TextView nombre_header_tv;

    TextView nombre_paciente;
    TextView apellidos_paciente;
    TextView documento_paciente;
    TextView fecha_de_nacimiento;


    TextView estado;
    TextView edad_gestacional;
    TextView eco;
    TextView fpp;
    TextView control_clinico;
    TextView inmunizacion_agripal, inmunizacion_tba, inmunizacion_db, inmunizacion_vhb;

    TextView sifilis;
    TextView hiv;
    TextView chagas;
    TextView vhb;
    TextView hb;
    TextView glucemia;
    TextView grupo_y_factor;

    SpinnerControl sp_controles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // deshabilita la rotación de pantalla
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_pacientes);
        getSupportActionBar().hide();

        buscar_paciente= (EditText)findViewById(R.id.buscar_paciente);
        
        list_pacientes=(ListView)findViewById(R.id.lista_pacientes);
 
        // derecha 
        nombre_header_tv = (TextView)findViewById(R.id.nombre_header_tv);

        nombre_paciente = (TextView)findViewById(R.id.di_nombre_tv);
        apellidos_paciente = (TextView)findViewById(R.id.di_apellidos_tv);
        documento_paciente = (TextView)findViewById(R.id.di_documento_tv);
        fecha_de_nacimiento = (TextView)findViewById(R.id.di_fecha_de_nacimiento_tv);




        estado = (TextView)findViewById(R.id.tb_estado_tv);
        edad_gestacional = (TextView)findViewById(R.id.tb_edad_gestacional_tv);
        eco = (TextView)findViewById(R.id.tb_eco_tv);
        fpp = (TextView)findViewById(R.id.tb_fpp_tv);
        control_clinico = (TextView)findViewById(R.id.tb_control_clinico_tv);
        inmunizacion_agripal = (TextView)findViewById(R.id.tb_inmunizacion_agripal_tv);
        inmunizacion_tba = (TextView)findViewById(R.id.tb_inmunizacion_tba_tv);
        inmunizacion_db = (TextView)findViewById(R.id.tb_inmunizacion_db_tv);
        inmunizacion_vhb = (TextView)findViewById(R.id.tb_inmunizacion_vhb_tv);


        sifilis = (TextView)findViewById(R.id.tb_sifilis_tv);
        hiv = (TextView)findViewById(R.id.tb_hiv_tv);
        chagas = (TextView)findViewById(R.id.tb_chagas_tv);
        vhb = (TextView)findViewById(R.id.tb_vhb_tv);
        hb = (TextView)findViewById(R.id.tb_hb_tv);
        glucemia = (TextView)findViewById(R.id.tb_glucemia_tv);
        grupo_y_factor = (TextView)findViewById(R.id.tb_grupo_y_factor_tv);

        Intent intent = getIntent();
        pais_elegido = intent.getStringExtra("pais_elegido");
        area_operativa_elegida = intent.getStringExtra("area_operativa_elegida");
        paraje_elegido = intent.getStringExtra("paraje_elegido");
        id_paraje = intent.getIntExtra("id_paraje", -1);

        Spinner controles = (Spinner)findViewById(R.id.sp_controles);
        sp_controles = new SpinnerControl(controles,ListadoPacientes.this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        DbHelper db = new DbHelper(ListadoPacientes.this);


        List<Pacientes> pacientes_todo = db.getPacientesFromParaje(id_paraje);
        pacientes = new String[pacientes_todo.size()];
        id_pacientes = new int[pacientes_todo.size()];

        System.out.println("------------------------------");
        System.out.println("Pacientes de paraje de id " + id_paraje);
        for(int i = 0; i < pacientes_todo.size(); i++){
            pacientes[i] = pacientes_todo.get(i).documento + " - " + pacientes_todo.get(i).nombre + " " + pacientes_todo.get(i).apellido;
            id_pacientes[i] = pacientes_todo.get(i).id;
            System.out.println(pacientes[i]);
        }

        listaPacientes();
        seleccionItemLista();


        cargarControlesEnSpinner(db);

        db.close();
    }

    public void irAEmbarazada(boolean editable){
        Intent myIntent = new Intent(ListadoPacientes.this, Ingresar_embarazada.class);
        myIntent.putExtra("pais_elegido", pais_elegido);
        myIntent.putExtra("area_operativa_elegida", area_operativa_elegida);
        myIntent.putExtra("paraje_elegido", paraje_elegido);
        myIntent.putExtra("id_paraje", id_paraje);
        myIntent.putExtra("id_paciente", id_paciente_seleccionado);
        myIntent.putExtra("editable", editable);
        ListadoPacientes.this.startActivity(myIntent);
    }

    public void editarEmbarazada(View view){
        irAEmbarazada(true);
    }

    public void verEmbarazada(View view){
        irAEmbarazada(false);
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
                id_paciente_seleccionado = id_pacientes[position];
                Toast.makeText(getApplicationContext(), id_paciente_seleccionado  +"-"+ paciente, Toast.LENGTH_SHORT).show();

                // cargar datos del paciente en el lado deerecho
                DbHelper db = new DbHelper(ListadoPacientes.this);
                Pacientes paciente_seleccionado = db.getPaciente(id_paciente_seleccionado );
                System.out.println("---------------------------------------------------------------------");
                System.out.println(id_paciente_seleccionado );
                System.out.println(paciente_seleccionado);
                if(paciente_seleccionado != null ){
                    System.out.println(paciente_seleccionado.nombre);
                    cargarDatosPaciente(paciente_seleccionado);
                    // cargar controles del paciente
                    cargarControlesEnSpinner(db);
                }



                
                // cierro la base de datos
                db.close();
            }   
        });
    }

    private void cargarControlesEnSpinner(DbHelper db){

        List<Controles> controles_ob = db.getControlesFromPaciente(id_paciente_seleccionado);
        sp_controles.cargarDatosEnSpinner(controles_ob);

        Pacientes paciente = db.getPaciente(id_paciente_seleccionado);
        reiniciarDatosControl();
        sp_controles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cargarDatosDeControl(controles_ob.get(position),db.getAntecedente(paciente.id_antecedente_fk), db.getAllSereologiaFromControles(sp_controles.getId(position))); // el id es auxiliar se debe buscar el id del control
            }
        
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // selecciono el primer control del spinner
            }
        
        });
    }

    private void reiniciarDatosControl(){
        estado.setText("-");
        edad_gestacional.setText("-");
        eco.setText("-");
        fpp.setText("-");
        control_clinico.setText("-");
        // en el caso de las inmunizaciones simplemente escondo los elementos de la vista
        inmunizacion_agripal.setVisibility(View.GONE);
        inmunizacion_tba.setVisibility(View.GONE);
        inmunizacion_db.setVisibility(View.GONE);
        inmunizacion_vhb.setVisibility(View.GONE);


        sifilis.setText("-");
        hiv.setText("-");
        chagas.setText("-");
        vhb.setText("-");
        hb.setText("-");
        glucemia.setText("-");
        grupo_y_factor.setText("-");

    }

    private void cargarDatosDeControl(Controles control, Antecedentes antecedente, Sereologias sereologia){
        if(control == null){
            Toast.makeText(getApplicationContext(), "No existe el control", Toast.LENGTH_SHORT).show();
            return;
        }

        estado.setText(antecedente.es_puerpera(25,10,2021));
        edad_gestacional.setText(""+control.edad_gestacional);
        eco.setText(""+control.ecografia);
        fpp.setText(""+antecedente.parto_estimado);
        control_clinico.setText(""+control.control_clinico);

        inmunizacion_agripal.setVisibility((control.a_gripal)? View.VISIBLE: View.GONE);
        inmunizacion_tba.setVisibility((control.tba_inmunizacion)?View.VISIBLE: View.GONE);
        inmunizacion_db.setVisibility((control.inmunizacion_db()) ? View.VISIBLE : View.GONE);
        inmunizacion_vhb.setVisibility((control.inmunizacion_vhb()) ? View.VISIBLE: View.GONE);

        if (sereologia == null){
            Toast.makeText(getApplicationContext(), "No existe la sereologia", Toast.LENGTH_SHORT).show();
            return;
        }


        // datos de laboratorio
        sifilis.setText(""+sereologia.sifilis);
        hiv.setText(""+sereologia.hiv);
        chagas.setText(""+sereologia.chagas);
        vhb.setText(""+sereologia.vhb);
        hb.setText(""+sereologia.hb);
        glucemia.setText(""+sereologia.glucemia);
        grupo_y_factor.setText(""+sereologia.grupo_factor);



    }

    private void cargarDatosPaciente(Pacientes paciente_seleccionado){

        nombre_header_tv.setText(paciente_seleccionado.nombre + ", " + paciente_seleccionado.apellido);

        nombre_paciente.setText(paciente_seleccionado.nombre);
        apellidos_paciente.setText(paciente_seleccionado.apellido);
        documento_paciente.setText((""+paciente_seleccionado.documento)); // paso el documento a string
        fecha_de_nacimiento.setText(paciente_seleccionado.fecha_de_nacimiento);

    }

}