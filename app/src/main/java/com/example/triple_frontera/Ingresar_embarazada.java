package com.example.triple_frontera;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
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

    // nuevo control
    EditText et_nc_edad_gestacional;
    RadioButton rb_nc_ecografia_si, rb_nc_ecografia_no;
    RadioButton rb_nc_hpv_si, rb_nc_pap_si,rb_nc_a_gripal_si,rb_nc_tba_si;
    RadioButton rb_nc_db_previa, rb_nc_db_colocada, rb_nc_vhb_previa, rb_nc_vhb_colocada;
    RadioButton rb_nc_control_clinico_normal;
    RadioButton rb_nc_sifilis_solicitado, rb_nc_sifilis_positivo, rb_nc_sifilis_negativo, rb_nc_hiv_solicitado, rb_nc_hiv_positivo, rb_nc_hiv_negativo,rb_nc_chagas_solicitado,rb_nc_chagas_positivo, rb_nc_chagas_negativo, rb_nc_vhb_solicitado, rb_nc_vhb_positivo, rb_nc_vhb_negativo, rb_nc_gas_solicitado, rb_nc_gas_positivo, rb_nc_gas_negativo;
    RadioButton rb_nc_hb_solicitado, rb_nc_hb_resultado, rb_nc_glucemia_solicitado, rb_nc_glucemia_resultado, rb_nc_grupo_solicitado, rb_nc_grupo_resultado;
    EditText et_nc_grupo_resultado, et_nc_glucemia_resultado, et_nc_hb_resultado;

    /* 
     * terminar la parte del guardado de los datos en nuevo control
     * en ingresar embarazada hacer que sea arrastrable así se ve todo
     * base de datos
     * 
    */


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


    private void nuevoControl(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.control_dialog,null);

        alert.setView(view);

        AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

    }

    private void nuevoHijo(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.hijo_dialog,null);

        alert.setView(view);

        AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

    }
    private void actualizarComponentesNuevoControl(){
        // et : edad gestacional
        
        et_nc_edad_gestacional = (EditText)findViewById(R.id.et_nc_edad_gestacional);
        // rb [si, no]: ecografia
        
        rb_nc_ecografia_si = (RadioButton)findViewById(R.id.rb_nc_ecografia_si);
        rb_nc_ecografia_no = (RadioButton)findViewById(R.id.rb_nc_ecografia_no);
        // rb [si] : hpv, pap, a_gripal, tba
        
        rb_nc_hpv_si = (RadioButton)findViewById(R.id.rb_nc_hpv_si);
        rb_nc_pap_si = (RadioButton)findViewById(R.id.rb_nc_pap_si);
        rb_nc_a_gripal_si = (RadioButton)findViewById(R.id.rb_nc_a_gripal_si);
        rb_nc_tba_si = (RadioButton)findViewById(R.id.rb_nc_tba_si);
        // rb [previa, colocada] : db, vhb
        
        rb_nc_db_previa = (RadioButton)findViewById(R.id.rb_nc_db_previa);
        rb_nc_db_colocada = (RadioButton)findViewById(R.id.rb_nc_db_colocada);
        rb_nc_vhb_previa = (RadioButton)findViewById(R.id.rb_nc_vhb_previa);
        rb_nc_vhb_colocada = (RadioButton)findViewById(R.id.rb_nc_vhb_colocada);
        // rb [normal] : control_clinico
        
        rb_nc_control_clinico_normal = (RadioButton)findViewById(R.id.rb_nc_control_clinico_normal);
        // rb [solicitado, positivo, negativo] : sifilis, hiv, chagas, vhb, gas
        rb_nc_sifilis_solicitado = (RadioButton)findViewById(R.id.rb_nc_sifilis_solicitado);
        rb_nc_sifilis_positivo = (RadioButton)findViewById(R.id.rb_nc_sifilis_positivo);
        rb_nc_sifilis_negativo = (RadioButton)findViewById(R.id.rb_nc_sifilis_negativo);
        rb_nc_hiv_solicitado = (RadioButton)findViewById(R.id.rb_nc_hiv_solicitado);
        rb_nc_hiv_positivo = (RadioButton)findViewById(R.id.rb_nc_hiv_positivo);
        rb_nc_hiv_negativo = (RadioButton)findViewById(R.id.rb_nc_hiv_negativo);
        rb_nc_chagas_solicitado = (RadioButton)findViewById(R.id.rb_nc_chagas_solicitado);
        rb_nc_chagas_positivo = (RadioButton)findViewById(R.id.rb_nc_chagas_positivo);
        rb_nc_chagas_negativo = (RadioButton)findViewById(R.id.rb_nc_chagas_negativo);
        rb_nc_vhb_solicitado = (RadioButton)findViewById(R.id.rb_nc_vhb_solicitado);
        rb_nc_vhb_positivo = (RadioButton)findViewById(R.id.rb_nc_vhb_positivo);
        rb_nc_vhb_negativo = (RadioButton)findViewById(R.id.rb_nc_vhb_negativo);
        rb_nc_gas_solicitado = (RadioButton)findViewById(R.id.rb_nc_gas_solicitado);
        rb_nc_gas_positivo = (RadioButton)findViewById(R.id.rb_nc_gas_positivo);
        rb_nc_gas_negativo = (RadioButton)findViewById(R.id.rb_nc_gas_negativo);
        // rb [solicitado, resultado] : hb, glucemia, grupo
        
        rb_nc_hb_solicitado = (RadioButton)findViewById(R.id.rb_nc_hb_solicitado);
        rb_nc_hb_resultado = (RadioButton)findViewById(R.id.rb_nc_hb_resultado);
        rb_nc_glucemia_solicitado = (RadioButton)findViewById(R.id.rb_nc_glucemia_solicitado);
        rb_nc_glucemia_resultado = (RadioButton)findViewById(R.id.rb_nc_glucemia_resultado);
        rb_nc_grupo_solicitado = (RadioButton)findViewById(R.id.rb_nc_grupo_solicitado);
        rb_nc_grupo_resultado = (RadioButton)findViewById(R.id.rb_nc_grupo_resultado);
        // et : grupo_resultado, glucemia_resultado, hb_resultado
        
        et_nc_grupo_resultado = (EditText)findViewById(R.id.et_nc_grupo_resultado);
        et_nc_glucemia_resultado = (EditText)findViewById(R.id.et_nc_glucemia_resultado);
        et_nc_hb_resultado = (EditText)findViewById(R.id.et_nc_hb_resultado);
    }


    public void guardarNuevoControl(View view){
        if(et_nc_edad_gestacional == null)
            actualizarComponentesNuevoControl();
        
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
        nuevoControl();
    }


    public void addHijo(View view){}

    public void guardar(View view){
        // obtengo los datos
        String[] datos = obtenerDatos();
        // los guardo en la base de datos
    }
}