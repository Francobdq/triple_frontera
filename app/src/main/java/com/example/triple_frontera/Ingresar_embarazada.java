package com.example.triple_frontera;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.db.DbHelper;
import com.example.db.entidades.*;

import org.w3c.dom.Text;

import java.util.List;

import javax.crypto.Mac;

public class Ingresar_embarazada extends AppCompatActivity {

    // para ahorrar tiempos de carga


    // izquierda
    EditText nombre, apellido, documento, fecha_de_nacimiento;
    Spinner origen, nacionalidad;
    Localizacion localizacion;
    int[] id_parajes;
    EditText numero_de_vivienda;


    // derecha
    TextView header_nombre_y_apellido;
    EditText edad_primer_parto, gestaciones, partos,cesareas,abortos;
    RadioButton rb_embarazo_planificado_si, rb_embarazo_planificado_no;
    EditText ultimo_parto, ultima_menstruacion, parto_estimado;
    CheckBox cb_implante, cb_diu, cb_oral, cb_inyectable, cb_barrera;
    CheckBox cb_hta_gestacional, cb_hta_dbt_gestacional, cb_toxoplasmosis, cb_sifilis, cb_chagas, cb_dbt, cb_tbc;
    int[] id_controles;
    Spinner controles,hijos;
    ImageButton edit_control, edit_hijo;
    ImageButton add_control, add_hijo;

    Button guardar;

    /* 
     * terminar la parte del guardado de los datos en nuevo control
     * base de datos
     * 
    */
    String pais_elegido;
    String area_operativa_elegida;
    String paraje_elegido;
    int id_paraje;
    int id_paciente = -1;
    Pacientes paciente;

    IngresarControl ingresarControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // deshabilita la rotación de pantalla
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_embarazada);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        pais_elegido = intent.getStringExtra("pais_elegido");
        area_operativa_elegida = intent.getStringExtra("area_operativa_elegida");
        paraje_elegido = intent.getStringExtra("paraje_elegido");
        id_paraje = intent.getIntExtra("pais_elegido", -1); //if it's a string you stored.
        id_paciente = intent.getIntExtra("id_paciente", -1);

        checkInterfaz();
        inicializarElementos();
        inicializarSpinners();
        actualizarHeader();

        // si el id paciente es distinto de -1 significa que quiero cargar los datos del paciente en todos los elementos.
        if (id_paciente != -1){
            cargarDatosPrevios();
        }
        localizacion.SeleccionManual(pais_elegido, area_operativa_elegida, paraje_elegido);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void OnResume() {
        localizacion.SeleccionManual(pais_elegido, area_operativa_elegida, paraje_elegido);
    }

    private void checkInterfaz(){
        int height = getResources().getDisplayMetrics().heightPixels;
        
        if(height > 1900)
            return;
        
        ScrollView sv_derecha = findViewById(R.id.sv_derecha);
        ScrollView sv_izquierda = findViewById(R.id.sv_izquierda);

        int dp_izquierda = (int) (height * 0.39);
        int dp_derecha = (int) (height * 0.37);

        sv_izquierda.getLayoutParams().height = (int) (dp_izquierda * getResources().getDisplayMetrics().density);
        sv_derecha.getLayoutParams().height = (int) (dp_derecha * getResources().getDisplayMetrics().density);
        Toast.makeText(this, "a: " + height, Toast.LENGTH_SHORT).show();

    }

    private void inicializarSpinners(){
        //Spinner origen, nacionalidad, pais, area_operativa, paraje;
        String[] str_origenes = new String[]{"Criolla", "Originaria"};
        String[] str_nacionalidad = new String[]{"Argentina", "Paraguaya", "Boliviana"};
        
        ArrayAdapter<String> adapter_origen = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str_origenes);
        ArrayAdapter<String> adapter_nacionalidad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str_nacionalidad);

        origen.setAdapter(adapter_origen);
        nacionalidad.setAdapter(adapter_nacionalidad);

        Spinner pais = (Spinner) findViewById(R.id.sp_pais);
        Spinner area_operativa = (Spinner) findViewById(R.id.sp_area_operativa);
        Spinner paraje = (Spinner) findViewById(R.id.sp_paraje);

        // busco los datos de cada spinner en la base de datos y los cargo en el spinner
        localizacion = new Localizacion(Ingresar_embarazada.this, pais, area_operativa, paraje);



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


    private void nuevoHijo(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.hijo_dialog,null);

        alert.setView(view);

        AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

    }






    private Mac_previo obtenerDatosMAC(){
        boolean b_implante = cb_implante.isChecked();
        boolean b_diu =  cb_diu.isChecked();
        boolean b_oral =  cb_oral.isChecked();
        boolean b_inyectable =  cb_inyectable.isChecked();
        boolean b_barrera =  cb_barrera.isChecked();

        return new Mac_previo(b_implante,b_diu,b_oral,b_inyectable,b_barrera);
    }

    private App obtenerDatosAPP(){
        boolean b_hta_gestacional =  cb_hta_gestacional.isChecked();
        boolean b_hta_dbt_gestacional =  cb_hta_dbt_gestacional.isChecked();
        boolean b_toxoplasmosis =  cb_toxoplasmosis.isChecked();
        boolean b_sifilis =  cb_sifilis.isChecked();
        boolean b_chagas =  cb_chagas.isChecked();
        boolean b_dbt =  cb_dbt.isChecked();
        boolean b_tbc =  cb_tbc.isChecked();

        return new App(b_hta_gestacional, b_hta_dbt_gestacional, b_toxoplasmosis, b_tbc, b_sifilis, b_chagas, b_dbt);
    }

    private Antecedentes obtenerDatosANTECEDENTES(int id_mac, int id_app){
        String str_edad_primer_parto = edad_primer_parto.getText().toString();
        String str_gestaciones = gestaciones.getText().toString();
        String str_partos = partos.getText().toString();
        String str_cesareas = cesareas.getText().toString();
        String str_abortos = abortos.getText().toString();


        String str_ultimo_parto = ultimo_parto.getText().toString();
        String str_ultima_menstruacion = ultima_menstruacion.getText().toString();
        String str_parto_estimado = parto_estimado.getText().toString();

        String[] control = {str_edad_primer_parto, str_gestaciones, str_partos, str_cesareas, str_abortos,
        str_ultimo_parto, str_ultima_menstruacion, str_parto_estimado};

        boolean embarazo_planificado_si = rb_embarazo_planificado_si.isChecked();

        for (int i = 0; i < control.length; i++){
            if(control[i].equals("")){
                Toast.makeText(this, "Faltan datos por completar, revise los antecedentes.", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        return new Antecedentes(0, Integer.parseInt(str_edad_primer_parto),Integer.parseInt(str_gestaciones),
                Integer.parseInt(str_edad_primer_parto),Integer.parseInt(str_cesareas), Integer.parseInt(str_abortos),embarazo_planificado_si,
                str_ultima_menstruacion,str_ultimo_parto, "",id_mac,id_app);

    }
    private Pacientes obtenerDatos(int id_antecedente, int id_paraje){
        // obtengo los datos de cada elemento para guardarlos en la base de datos


        // izquierda
        String str_nombre = nombre.getText().toString();
        String str_apellido = apellido.getText().toString();
        String str_documento = documento.getText().toString();
        String str_fecha_de_nacimiento = fecha_de_nacimiento.getText().toString();

        String str_numero_de_vivienda = numero_de_vivienda.getText().toString();

        String str_origen = origen.getSelectedItem().toString();
        String str_nacionalidad = nacionalidad.getSelectedItem().toString();

        /*

        USAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR


        String str_pais = pais.getSelectedItem().toString();
        String str_area_operativa = area_operativa.getSelectedItem().toString();
        String str_paraje = paraje.getSelectedItem().toString();
q       */

        String[] control = new String[]{str_nombre, str_apellido, str_documento, str_fecha_de_nacimiento, str_numero_de_vivienda};

        for (int i = 0; i < control.length; i++) {
            if(control[i].equals("")){
                Toast.makeText(this, "Faltan datos por completar.", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        Pacientes nuevoPaciente = new Pacientes( str_nombre, str_apellido, Integer.parseInt(str_documento), str_fecha_de_nacimiento,
                str_origen, str_nacionalidad, Integer.parseInt(str_numero_de_vivienda), id_antecedente,id_paraje);

        return nuevoPaciente;

    }



    public void addControl(View view){
        if (id_paciente == -1){
            Toast.makeText(this, "Debe guardar el paciente antes de poder agregarle un nuevo control.", Toast.LENGTH_SHORT).show();
            return;
        }
        LayoutInflater inflater = getLayoutInflater();
        ingresarControl = new IngresarControl(Ingresar_embarazada.this, id_paciente, inflater);
    }


    public void guardarNuevoControl(View view){
        if(ingresarControl == null){
            Toast.makeText(this, "a", Toast.LENGTH_SHORT).show();
            return;
        }

        ingresarControl.guardarNuevoControl(view);
    }


    // se cargan los datos del paciente a los distintos elementos
    public void cargarDatosPrevios(){
        DbHelper db = new DbHelper(Ingresar_embarazada.this);

        // para eso primero busco al paciente
        paciente = db.getPaciente(id_paciente);

        // luego busco los antecedentes de ese paciente
        Antecedentes antecedente = db.getAntecedente(paciente.id_antecedente_fk);

        // ahora con eso busco el mac y el app
        Mac_previo mac_previo = db.getMacPrevio(antecedente.id_mac_previo_fk);
        App app = db.getApp(antecedente.id_app_fk);

        // ahora busco la lista de controles (para cargar en el spinner)
        List<Controles> controles = db.getControlesFromPaciente(id_paciente);
        

        // ahora cargo los datos en los elementos
        nombre.setText(paciente.nombre);
        apellido.setText(paciente.apellido);
        documento.setText(String.valueOf(paciente.documento));
        fecha_de_nacimiento.setText(paciente.fecha_de_nacimiento);
        numero_de_vivienda.setText(String.valueOf(paciente.num_vivienda));
        origen.setSelection(Localizacion.getIndex(origen, paciente.origen));
        nacionalidad.setSelection(Localizacion.getIndex(nacionalidad, paciente.nacionalidad));

        // ahora cargo los datos de los antecedentes
        edad_primer_parto.setText(String.valueOf(antecedente.edad_primer_parto));
        gestaciones.setText(String.valueOf(antecedente.gestaciones));
        partos.setText(String.valueOf(antecedente.partos));
        cesareas.setText(String.valueOf(antecedente.ceseareas));
        abortos.setText(String.valueOf(antecedente.abortos));
        ultimo_parto.setText(antecedente.fecha_ultimo_parto);
        ultima_menstruacion.setText(antecedente.fecha_ultima_menstruacion);
        parto_estimado.setText(antecedente.parto_estimado);

        if (antecedente.embarazo_planificado){
            rb_embarazo_planificado_si.setChecked(true);
        }else{
            rb_embarazo_planificado_no.setChecked(true);
        }

        // ahora cargo los datos de la mac previo
        //  CheckBox cb_implante, cb_diu, cb_oral, cb_inyectable, cb_barrera;
        cb_implante.setChecked(mac_previo.implante);
        cb_diu.setChecked(mac_previo.diu);
        cb_oral.setChecked(mac_previo.oral);
        cb_inyectable.setChecked(mac_previo.inyectable);
        cb_barrera.setChecked(mac_previo.barrera);

        // ahora cargo los datos de la app
        // CheckBox cb_hta_gestacional, cb_hta_dbt_gestacional, cb_toxoplasmosis, cb_tbc, cb_sifilis, cb_chagas, cb_dbt;
        cb_hta_gestacional.setChecked(app.hta_gestacional);
        cb_hta_dbt_gestacional.setChecked(app.hta_dbt_gestacional);
        cb_toxoplasmosis.setChecked(app.toxoplasmosis);
        cb_tbc.setChecked(app.tbc);
        cb_sifilis.setChecked(app.sifilis);
        cb_chagas.setChecked(app.chagas);
        cb_dbt.setChecked(app.dbt);


        // ahora cargo los controles en el spinner
        if (controles.size() > 0){
            // cargo el spinner
            String[] nombres_controles = new String[controles.size()];
            id_controles = new int[controles.size()];
            for (int i = 0; i < controles.size(); i++){
                nombres_controles[i] = String.valueOf(controles.get(i).id);
                id_controles[i] = controles.get(i).id;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombres_controles);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.controles.setAdapter(adapter);
            this.controles.setSelection(controles.size()-1);
        }




    }


    public void editControl(View view){
        if(ingresarControl != null){

            // obtengo el index de la seleccion actual del spinner controles
            int index = controles.getSelectedItemPosition();

            ingresarControl.editControl(view, id_controles[index]);
        }
    }

    public void addHijo(View view){}


    private void Actualizar(App app_update, Mac_previo mac_previo_update, Antecedentes antecedente_update, Pacientes paciente_update){
        DbHelper db = new DbHelper(Ingresar_embarazada.this);
        
        if (paciente == null)
            paciente = db.getPaciente(id_paciente);

        Antecedentes antecedente_viejo = db.getAntecedente(paciente.id_antecedente_fk);
        Mac_previo mac_viejo = db.getMacPrevio(antecedente_viejo.id_mac_previo_fk);
        App app_vieja = db.getApp(antecedente_viejo.id_app_fk);

        paciente_update.id_paraje_fk = paciente.id_paraje_fk;
        paciente_update.id_antecedente_fk = antecedente_viejo.id;
        antecedente_update.id_mac_previo_fk = mac_viejo.id_mac_previo;
        antecedente_update.id_app_fk = app_vieja.id_app;

        // actualizo los datos
        db.UpdatePaciente(paciente.id, paciente_update);
        db.UpdateAntecedente(antecedente_viejo.id, antecedente_update);
        db.UpdateMacPrevio(mac_viejo.id_mac_previo, mac_previo_update);
        db.UpdateApp(app_vieja.id_app, app_update);

    }

    public void guardar(View view){
        // obtengo los datos
        id_paraje = localizacion.getId_paraje();
        App app_paciente = obtenerDatosAPP();
        Mac_previo mac_previo = obtenerDatosMAC();
        Antecedentes antecedentes = obtenerDatosANTECEDENTES(-1,-1);
        Pacientes nuevoPaciente = obtenerDatos(-1, id_paraje);

        if(app_paciente == null || mac_previo == null || antecedentes == null || nuevoPaciente == null){
            return;
        }


        // si el paciente ya fue cargado entonces tengo que actualizar los datos editados
        if (id_paciente != -1){
            Actualizar(app_paciente, mac_previo, antecedentes, nuevoPaciente);
            return;
        }
        else{
            // en caso contrario tengo que guardar los datos en la base de datos
            DbHelper db = new DbHelper(Ingresar_embarazada.this);
            db.addApp(app_paciente);
            int id_app = db.getLastInsertedId();
            db.addMacPrevio(mac_previo);
            int id_mac = db.getLastInsertedId();

            antecedentes.id_mac_previo_fk = id_mac;
            antecedentes.id_app_fk = id_app;

            db.addAntecedente(antecedentes);
            nuevoPaciente.id_antecedente_fk = db.getLastInsertedId();
            db.addPaciente(nuevoPaciente);
            id_paciente = db.getLastInsertedId();
        }
        Toast.makeText(this, "Datos guardados correctamente.", Toast.LENGTH_SHORT).show();
    }
}