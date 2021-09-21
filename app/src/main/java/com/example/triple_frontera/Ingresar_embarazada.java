package com.example.triple_frontera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Ingresar_embarazada extends AppCompatActivity {

    // izquierda
    EditText nombre, apellido, documento, fecha_de_nacimiento;
    Spinner origen, nacionalidad, pais, area_operativa, paraje;
    EditText numero_de_vivienda;


    // derecha
    TextView header_nombre_y_apellido;
    EditText edad_primer_parto, gestaciones, partos,cesareas,abortos;
    RadioButton rb_embarazo_planificado_si, rb_embarazo_planificado_no;
    EditText ultimo_parto, ultima_menstruacion, parto_estimado;
    CheckBox cb_implante, cb_diu, cb_oral, cb_inyectable, cb_barrera;
    CheckBox cb_hta_gestacional, cb_hta_dbt_gestacional, cb_toxoplasmosis, cb_sifilis, cb_chagas, cb_dbt, cb_tbc;
    Spinner controles,hijos;
    ImageButton edit_control, edit_hijo;
    ImageButton add_control, add_hijo;

    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_embarazada);
        
        inicializarElementos();
        inicializarSpinners();
        actualizarHeader();
    }

    private void inicializarSpinners(){
        // busco los datos de cada spinner en la base de datos y los cargo en el spinner
    }

    private void actualizarHeader(){
        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                header_nombre_y_apellido.setText(nombre.getText().toString() + ", " + apellido.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        apellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                header_nombre_y_apellido.setText(nombre.getText().toString() + ", " + apellido.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void inicializarElementos(){
        // OBTENGO LOS ELEMENTOS 
        // izquierda
        nombre = (EditText) findViewById(R.id.et_nombre);
        apellido = (EditText) findViewById(R.id.et_apellido);
        documento = (EditText) findViewById(R.id.et_documento);
        fecha_de_nacimiento = (EditText) findViewById(R.id.et_fecha_de_nacimiento);
        origen = (Spinner) findViewById(R.id.sp_origen);
        nacionalidad = (Spinner) findViewById(R.id.sp_nacionalidad);
        pais = (Spinner) findViewById(R.id.sp_pais);
        area_operativa = (Spinner) findViewById(R.id.sp_area_operativa);
        paraje = (Spinner) findViewById(R.id.sp_paraje);
        numero_de_vivienda = (EditText) findViewById(R.id.et_numero_de_vivienda);

        // derecha
        header_nombre_y_apellido = (TextView) findViewById(R.id.et_header_nombre_y_apellido);
        edad_primer_parto = (EditText) findViewById(R.id.et_edad_primer_parto);
        gestaciones = (EditText) findViewById(R.id.et_gestaciones);
        partos = (EditText) findViewById(R.id.et_partos);
        cesareas = (EditText) findViewById(R.id.et_cesareas);
        abortos = (EditText) findViewById(R.id.et_abortos);
        rb_embarazo_planificado_si = (RadioButton) findViewById(R.id.rb_embarazo_planificado_si);
        rb_embarazo_planificado_no = (RadioButton) findViewById(R.id.rb_embarazo_planificado_no);

        ultimo_parto = (EditText) findViewById(R.id.et_ultimo_parto);
        ultima_menstruacion = (EditText) findViewById(R.id.et_ultima_menstruacion);
        parto_estimado = (EditText) findViewById(R.id.et_parto_estimado);

        cb_implante = (CheckBox) findViewById(R.id.cb_implante);
        cb_diu = (CheckBox) findViewById(R.id.cb_diu);
        cb_oral = (CheckBox) findViewById(R.id.cb_oral);
        cb_inyectable = (CheckBox) findViewById(R.id.cb_inyectable);
        cb_barrera = (CheckBox) findViewById(R.id.cb_barrera);

        cb_hta_gestacional = (CheckBox) findViewById(R.id.cb_hta_gestacional);
        cb_hta_dbt_gestacional = (CheckBox) findViewById(R.id.cb_hta_dbt_gestacional);
        cb_toxoplasmosis = (CheckBox) findViewById(R.id.cb_toxoplasmosis);
        cb_sifilis = (CheckBox) findViewById(R.id.cb_sifilis);
        cb_chagas = (CheckBox) findViewById(R.id.cb_chagas);
        cb_dbt = (CheckBox) findViewById(R.id.cb_dbt);
        cb_tbc = (CheckBox) findViewById(R.id.cb_tbc);

        controles = (Spinner) findViewById(R.id.sp_edit_controles);
        hijos = (Spinner) findViewById(R.id.sp_edit_hijos);
        edit_control = (ImageButton) findViewById(R.id.ib_edit_control);
        edit_hijo = (ImageButton) findViewById(R.id.ib_edit_hijo);
        add_control = (ImageButton) findViewById(R.id.ib_add_control);
        add_hijo = (ImageButton) findViewById(R.id.ib_add_hijo);

        guardar = (Button) findViewById(R.id.btn_guardar_embarazada);
    }

    private String[] obtenerDatos(){
        // obtengo los datos de cada elemento para guardarlos en la base de datos

        // izquierda
        String str_nombre = nombre.getText().toString();
        String str_apellido = apellido.getText().toString();
        String str_documento = documento.getText().toString();
        String str_fecha_de_nacimiento = fecha_de_nacimiento.getText().toString();

        String str_numero_de_vivienda = numero_de_vivienda.getText().toString();

        // derecha
        String str_edad_primer_parto = edad_primer_parto.getText().toString();
        String str_gestaciones = gestaciones.getText().toString();
        String str_partos = partos.getText().toString();
        String str_cesareas = cesareas.getText().toString();
        String str_abortos = abortos.getText().toString();

        String str_embarazo_planificado = String.valueOf(rb_embarazo_planificado_si.isChecked());

        String str_ultimo_parto = ultimo_parto.getText().toString();
        String str_ultima_menstruacion = ultima_menstruacion.getText().toString();
        String str_parto_estimado = parto_estimado.getText().toString();

        String str_implante = String.valueOf(cb_implante.isChecked());
        String str_diu =  String.valueOf(cb_diu.isChecked());
        String str_oral =  String.valueOf(cb_oral.isChecked());
        String str_inyectable =  String.valueOf(cb_inyectable.isChecked());
        String str_barrera =  String.valueOf(cb_barrera.isChecked());

        String str_hta_gestacional =  String.valueOf(cb_hta_gestacional.isChecked());
        String str_hta_dbt_gestacional =  String.valueOf(cb_hta_dbt_gestacional.isChecked());
        String str_toxoplasmosis =  String.valueOf(cb_toxoplasmosis.isChecked());
        String str_sifilis =  String.valueOf(cb_sifilis.isChecked());
        String str_chagas =  String.valueOf(cb_chagas.isChecked());
        String str_dbt =  String.valueOf(cb_dbt.isChecked());
        String str_tbc =  String.valueOf(cb_tbc.isChecked());

        String[] salida = new String[]{str_nombre, str_apellido, str_documento, str_fecha_de_nacimiento, str_numero_de_vivienda,
                str_edad_primer_parto, str_gestaciones, str_partos, str_cesareas, str_abortos, str_embarazo_planificado,
                str_ultimo_parto, str_ultima_menstruacion, str_parto_estimado, str_implante, str_diu, str_oral,
                str_inyectable, str_barrera, str_hta_gestacional, str_hta_dbt_gestacional, str_toxoplasmosis, str_sifilis,
                str_chagas, str_dbt, str_tbc};

        for (int i = 0; i < salida.length; i++) {
            if(salida[i].equals("")){
                Toast.makeText(this, "Faltan datos por completar.", Toast.LENGTH_SHORT).show();
            }
        }


        return salida;
    }

    public void addControl(View view){

    }

    public void addHijo(View view){}

    public void guardar(View view){
        // obtengo los datos
        String[] datos = obtenerDatos();
        // los guardo en la base de datos
    }
}