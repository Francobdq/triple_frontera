package com.example.triple_frontera;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.service.controls.Control;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.db.DbHelper;
import com.example.db.entidades.Controles;
import com.example.db.entidades.Sereologias;

public class IngresarControl extends AppCompatActivity {

    // nuevo control
    EditText et_nc_edad_gestacional;
    RadioButton rb_nc_ecografia_si, rb_nc_ecografia_no, rb_nc_ecografia_solicitada;
    RadioButton rb_nc_hpv_si,rb_nc_hpv_no, rb_nc_pap_si,rb_nc_pap_no, rb_nc_a_gripal_si,rb_nc_a_gripal_no, rb_nc_tba_si,rb_nc_tba_no;
    RadioButton rb_nc_db_previa, rb_nc_db_colocada,rb_nc_db_no, rb_nc_vhb_previa, rb_nc_vhb_colocada,rb_nc_vhb_no;
    RadioButton rb_nc_control_clinico_normal, rb_nc_control_clinico_patologico;
    RadioButton rb_nc_sifilis_solicitado, rb_nc_sifilis_positivo, rb_nc_sifilis_negativo,rb_nc_sifilis_no, rb_nc_hiv_solicitado, rb_nc_hiv_positivo, rb_nc_hiv_negativo,rb_nc_hiv_no, rb_nc_chagas_solicitado,rb_nc_chagas_positivo, rb_nc_chagas_negativo,rb_nc_chagas_no, rb_nc_vhb_solicitado, rb_nc_vhb_positivo, rb_nc_vhb_negativo,rb_nc_vhb_sereologia_no, rb_nc_gas_solicitado, rb_nc_gas_positivo, rb_nc_gas_negativo,rb_nc_gas_no;
    RadioButton rb_nc_hb_solicitado,rb_nc_hb_no, rb_nc_hb_resultado, rb_nc_glucemia_solicitado,rb_nc_glucemia_no, rb_nc_glucemia_resultado, rb_nc_grupo_solicitado, rb_nc_grupo_no, rb_nc_grupo_resultado;
    EditText et_nc_grupo_resultado, et_nc_glucemia_resultado, et_nc_hb_resultado;

    EditText et_nc_tension_arterial;
    EditText et_nc_observaciones;

    int id_paciente;
    Context ctx;

    View control_view;

    LayoutInflater inflater;

    int id_control = -1;


    public IngresarControl(Context ctx, int id_paciente, LayoutInflater inflater){
        this.ctx = ctx;
        this.id_paciente = id_paciente;
        this.inflater = inflater;
        abrirDialogControles();
    }


    private void abrirDialogControles(){
        AlertDialog.Builder alert = new AlertDialog.Builder(ctx, android.R.style.Theme_Material_Dialog_Alert);

        control_view = inflater.inflate(R.layout.control_dialog,null);


        alert.setView(control_view);

        AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

    }

    private void actualizarComponentesNuevoControl(){
        // et : edad gestacional

        et_nc_edad_gestacional = (EditText)control_view.findViewById(R.id.et_nc_edad_gestacional);
        // rb [si, no, solicitada]: ecografia

        rb_nc_ecografia_si = (RadioButton)control_view.findViewById(R.id.rb_nc_ecografia_si);
        rb_nc_ecografia_no = (RadioButton)control_view.findViewById(R.id.rb_nc_ecografia_no);
        rb_nc_ecografia_solicitada = (RadioButton)control_view.findViewById(R.id.rb_nc_ecografia_solicitada);
        // rb [si, no] : hpv, pap, a_gripal, tba

        rb_nc_hpv_si = (RadioButton)control_view.findViewById(R.id.rb_nc_hpv_si);
        rb_nc_hpv_no = (RadioButton)control_view.findViewById(R.id.rb_nc_hpv_no);

        rb_nc_pap_si = (RadioButton)control_view.findViewById(R.id.rb_nc_pap_si);
        rb_nc_pap_no = (RadioButton)control_view.findViewById(R.id.rb_nc_pap_no);
        rb_nc_a_gripal_si = (RadioButton)control_view.findViewById(R.id.rb_nc_a_gripal_si);
        rb_nc_a_gripal_no = (RadioButton)control_view.findViewById(R.id.rb_nc_a_gripal_no);
        rb_nc_tba_si = (RadioButton)control_view.findViewById(R.id.rb_nc_tba_si);
        rb_nc_tba_no = (RadioButton)control_view.findViewById(R.id.rb_nc_tba_no);
        // rb [previa, colocada, no] : db, vhb

        rb_nc_db_previa = (RadioButton)control_view.findViewById(R.id.rb_nc_db_previa);
        rb_nc_db_colocada = (RadioButton)control_view.findViewById(R.id.rb_nc_db_colocada);
        rb_nc_db_no = (RadioButton)control_view.findViewById(R.id.rb_nc_db_no);
        rb_nc_vhb_previa = (RadioButton)control_view.findViewById(R.id.rb_nc_vhb_previa);
        rb_nc_vhb_colocada = (RadioButton)control_view.findViewById(R.id.rb_nc_vhb_colocada);
        rb_nc_vhb_no = (RadioButton)control_view.findViewById(R.id.rb_nc_vhb_no);
        // rb [normal, patologico] : control_clinico

        rb_nc_control_clinico_normal = (RadioButton)control_view.findViewById(R.id.rb_nc_control_clinico_normal);
        rb_nc_control_clinico_patologico = (RadioButton)control_view.findViewById(R.id.rb_nc_control_clinico_patologico);
        // rb [solicitado, positivo, negativo, no] : sifilis, hiv, chagas, vhb, gas
        rb_nc_sifilis_solicitado = (RadioButton)control_view.findViewById(R.id.rb_nc_sifilis_solicitado);
        rb_nc_sifilis_positivo = (RadioButton)control_view.findViewById(R.id.rb_nc_sifilis_positivo);
        rb_nc_sifilis_negativo = (RadioButton)control_view.findViewById(R.id.rb_nc_sifilis_negativo);
        rb_nc_sifilis_no = (RadioButton)control_view.findViewById(R.id.rb_nc_sifilis_no);
        rb_nc_hiv_solicitado = (RadioButton)control_view.findViewById(R.id.rb_nc_hiv_solicitado);
        rb_nc_hiv_positivo = (RadioButton)control_view.findViewById(R.id.rb_nc_hiv_positivo);
        rb_nc_hiv_negativo = (RadioButton)control_view.findViewById(R.id.rb_nc_hiv_negativo);
        rb_nc_hiv_no = (RadioButton)control_view.findViewById(R.id.rb_nc_hiv_no);
        rb_nc_chagas_solicitado = (RadioButton)control_view.findViewById(R.id.rb_nc_chagas_solicitado);
        rb_nc_chagas_positivo = (RadioButton)control_view.findViewById(R.id.rb_nc_chagas_positivo);
        rb_nc_chagas_negativo = (RadioButton)control_view.findViewById(R.id.rb_nc_chagas_negativo);
        rb_nc_chagas_no = (RadioButton)control_view.findViewById(R.id.rb_nc_chagas_no);
        rb_nc_vhb_solicitado = (RadioButton)control_view.findViewById(R.id.rb_nc_vhb_solicitado);
        rb_nc_vhb_positivo = (RadioButton)control_view.findViewById(R.id.rb_nc_vhb_positivo);
        rb_nc_vhb_negativo = (RadioButton)control_view.findViewById(R.id.rb_nc_vhb_negativo);
        rb_nc_vhb_no = (RadioButton)control_view.findViewById(R.id.rb_nc_vhb_no);
        rb_nc_gas_solicitado = (RadioButton)control_view.findViewById(R.id.rb_nc_gas_solicitado);
        rb_nc_gas_positivo = (RadioButton)control_view.findViewById(R.id.rb_nc_gas_positivo);
        rb_nc_gas_negativo = (RadioButton)control_view.findViewById(R.id.rb_nc_gas_negativo);
        rb_nc_gas_no = (RadioButton)control_view.findViewById(R.id.rb_nc_gas_no);
        // rb [solicitado, no, resultado] : hb, glucemia, grupo

        rb_nc_hb_solicitado = (RadioButton)control_view.findViewById(R.id.rb_nc_hb_solicitado);
        rb_nc_hb_no = (RadioButton)control_view.findViewById(R.id.rb_nc_hb_no);
        rb_nc_hb_resultado = (RadioButton)control_view.findViewById(R.id.rb_nc_hb_resultado);
        rb_nc_glucemia_solicitado = (RadioButton)control_view.findViewById(R.id.rb_nc_glucemia_solicitado);
        rb_nc_glucemia_no = (RadioButton)control_view.findViewById(R.id.rb_nc_glucemia_no);
        rb_nc_glucemia_resultado = (RadioButton)control_view.findViewById(R.id.rb_nc_glucemia_resultado);
        rb_nc_grupo_solicitado = (RadioButton)control_view.findViewById(R.id.rb_nc_grupo_solicitado);
        rb_nc_grupo_no = (RadioButton)control_view.findViewById(R.id.rb_nc_grupo_no);
        rb_nc_grupo_resultado = (RadioButton)control_view.findViewById(R.id.rb_nc_grupo_resultado);
        // et : grupo_resultado, glucemia_resultado, hb_resultado

        et_nc_grupo_resultado = (EditText)control_view.findViewById(R.id.et_nc_grupo_resultado);
        et_nc_tension_arterial = (EditText)control_view.findViewById(R.id.et_nc_tension_arterial);
        et_nc_observaciones = (EditText)control_view.findViewById(R.id.et_nc_observaciones);
        et_nc_glucemia_resultado = (EditText)control_view.findViewById(R.id.et_nc_glucemia_resultado);
        et_nc_hb_resultado = (EditText)control_view.findViewById(R.id.et_nc_hb_resultado);
    }

    private String solicitado_positivo_negativo_no(boolean solicitado, boolean positivo, boolean negativo){
        if(solicitado)
            return "SOLICITADO";
        if(positivo)
            return "POSITIVO";
        if(negativo)
            return "NEGATIVO";
        return "NO";
    }


    /*
    SEREOLOGIA:
        public int id;
        public int sifilis;
        public int hiv;
        public int chagas;
        public int vhb;
        public int gas;
        public String hb;
        public String glucemia;
        public String grupo_factor;
        public int id_control_fk;

    CONTROL:
        public int id;
        public int edad_gestacional;
        public int ecografia;
        public boolean hpv;
        public boolean pap;
        public boolean a_gripal;
        public boolean tba_inmunizacion;
        public int db_inmunizacion;
        public int vhb_inmunizacion;
        public String control_clinico;
        public float tension_arterial;
        public String observaciones;
        public int id_paciente_control_fk;

        public Sereologias sereologia;

    */

    private Sereologias obtenerSereologia(){
        boolean out_rb_nc_sifilis_solicitado = rb_nc_sifilis_solicitado.isChecked();
        boolean out_rb_nc_sifilis_positivo = rb_nc_sifilis_positivo.isChecked();
        boolean out_rb_nc_sifilis_negativo = rb_nc_sifilis_negativo.isChecked();
        boolean out_rb_nc_hiv_solicitado = rb_nc_hiv_solicitado.isChecked();
        boolean out_rb_nc_hiv_positivo = rb_nc_hiv_positivo.isChecked();
        boolean out_rb_nc_hiv_negativo = rb_nc_hiv_negativo.isChecked();
        boolean out_rb_nc_chagas_solicitado = rb_nc_chagas_positivo.isChecked();
        boolean out_rb_nc_chagas_positivo = rb_nc_chagas_positivo.isChecked();
        boolean out_rb_nc_chagas_negativo = rb_nc_chagas_negativo.isChecked();
        boolean out_rb_nc_vhb_solicitado = rb_nc_vhb_solicitado.isChecked();
        boolean out_rb_nc_vhb_positivo = rb_nc_vhb_positivo.isChecked();
        boolean out_rb_nc_vhb_negativo = rb_nc_vhb_negativo.isChecked();
        boolean out_rb_nc_gas_solicitado = rb_nc_gas_solicitado.isChecked();
        boolean out_rb_nc_gas_positivo = rb_nc_gas_positivo.isChecked();
        boolean out_rb_nc_gas_negativo = rb_nc_gas_negativo.isChecked();
        boolean out_rb_nc_hb_solicitado = rb_nc_hb_solicitado.isChecked();
        boolean out_rb_nc_hb_resultado = rb_nc_hb_resultado.isChecked();
        boolean out_rb_nc_glucemia_solicitado = rb_nc_glucemia_solicitado.isChecked();
        boolean out_rb_nc_glucemia_resultado = rb_nc_glucemia_resultado.isChecked();
        boolean out_rb_nc_grupo_solicitado = rb_nc_grupo_solicitado.isChecked();
        boolean out_rb_nc_grupo_resultado = rb_nc_grupo_resultado.isChecked();
        String str_nc_grupo_resultado = et_nc_grupo_resultado.getText().toString();

        String str_nc_glucemia_resultado = et_nc_glucemia_resultado.getText().toString();
        String str_nc_hb_resultado = et_nc_hb_resultado.getText().toString();

        return new Sereologias(
                0,
                solicitado_positivo_negativo_no(out_rb_nc_sifilis_solicitado, out_rb_nc_sifilis_positivo, out_rb_nc_sifilis_negativo),
                solicitado_positivo_negativo_no(out_rb_nc_hiv_solicitado, out_rb_nc_hiv_positivo, out_rb_nc_hiv_negativo),
                solicitado_positivo_negativo_no(out_rb_nc_chagas_solicitado, out_rb_nc_chagas_positivo, out_rb_nc_chagas_negativo),
                solicitado_positivo_negativo_no(out_rb_nc_vhb_solicitado, out_rb_nc_vhb_positivo, out_rb_nc_vhb_negativo),
                solicitado_positivo_negativo_no(out_rb_nc_gas_solicitado, out_rb_nc_gas_positivo, out_rb_nc_gas_negativo),
                (out_rb_nc_hb_resultado ? str_nc_hb_resultado : (out_rb_nc_hb_solicitado ? "SOLICITADO" : "NO")),
                (out_rb_nc_glucemia_resultado ? str_nc_glucemia_resultado : (out_rb_nc_glucemia_solicitado ? "SOLICITADO" : "NO")),
                (out_rb_nc_grupo_resultado ? str_nc_grupo_resultado : (out_rb_nc_grupo_solicitado ? "SOLICITADO" : "NO")),
                -1
        );
    }

    private Controles obtenerDatosControl(int id_paciente){
        String str_nc_edad_gestacional = et_nc_edad_gestacional.getText().toString();
        boolean out_rb_nc_ecografia_si = rb_nc_ecografia_si.isChecked();
        boolean out_rb_nc_ecografia_no = rb_nc_ecografia_no.isChecked();
        boolean out_rb_nc_hpv_si = rb_nc_hpv_si.isChecked();
        boolean out_rb_nc_pap_si = rb_nc_pap_si.isChecked();
        boolean out_rb_nc_a_gripal_si = rb_nc_a_gripal_si.isChecked();
        boolean out_rb_nc_tba_si = rb_nc_tba_si.isChecked();
        boolean out_rb_nc_db_previa = rb_nc_db_previa.isChecked();
        boolean out_rb_nc_db_colocada = rb_nc_db_colocada.isChecked();
        boolean out_rb_nc_vhb_previa = rb_nc_vhb_previa.isChecked();
        boolean out_rb_nc_vhb_colocada = rb_nc_vhb_colocada.isChecked();
        boolean out_rb_nc_control_clinico_normal = rb_nc_control_clinico_normal.isChecked();
        String str_et_nc_tension_arterial = et_nc_tension_arterial.getText().toString();
        String str_et_nc_observaciones = et_nc_observaciones.getText().toString();

        return new Controles(
                0,
                Integer.parseInt(str_nc_edad_gestacional),
                (out_rb_nc_ecografia_si ? "SI" : (out_rb_nc_ecografia_no ? "NO" : "SOLICITADA")),
                (out_rb_nc_hpv_si),
                (out_rb_nc_pap_si),
                (out_rb_nc_a_gripal_si),
                (out_rb_nc_tba_si),
                (out_rb_nc_db_previa ? "PREVIA" : (out_rb_nc_db_colocada ? "COLOCADA" : "NO")),
                (out_rb_nc_vhb_previa ? "PREVIA": (out_rb_nc_vhb_colocada ? "COLOCADA" : "NO")),
                (out_rb_nc_control_clinico_normal ? "NORMAL": "PATOLOGICO"),
                Float.parseFloat(str_et_nc_tension_arterial),
                str_et_nc_observaciones,
                id_paciente
        );
    }

    private void dos_opciones(RadioButton rb1, RadioButton rb2, boolean cond1){
        if(cond1)
            rb1.setChecked(true);
        else
            rb2.setChecked(true);

        
            
    }

    private boolean tres_opciones(RadioButton rb1, RadioButton rb2, RadioButton rb3, boolean cond1, boolean cond2){
        if(cond1)
            rb1.setChecked(true);
        else if (cond2)
            rb2.setChecked(true);
        else{
            rb3.setChecked(true);    
            return true;
        }

        return false;
    }

    private void tres_opcines_resultado(RadioButton rb1, RadioButton rb2, RadioButton rb3, EditText et, boolean cond1, boolean cond2, String texto){
        if(tres_opciones(rb1, rb2, rb3, cond1, cond2)){
            et.setText(texto);
        }
    }

    private void cuatro_opciones(RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4, boolean cond1, boolean cond2, boolean cond3){
        if(cond1)
            rb1.setChecked(true);
        else if (cond2)
            rb2.setChecked(true);
        else if (cond3)
            rb3.setChecked(true);
        else
            rb4.setChecked(true);
    }

    
    /* clase controles
    public class Controles {
    public int id;
    public int edad_gestacional;
    public String ecografia;
    public boolean hpv;
    public boolean pap;
    public boolean a_gripal;
    public boolean tba_inmunizacion;
    public String db_inmunizacion;
    public String vhb_inmunizacion;
    public String control_clinico;
    public float tension_arterial;
    public String observaciones;
    public int id_paciente_control_fk;
    */

    /* clase sereologia
    public int id;
    public String sifilis;
    public String hiv;
    public String chagas;
    public String vhb;
    public String gas;
    public String hb;
    public String glucemia;
    public String grupo_factor;
    */

    private void setDatos(Controles control, Sereologias sereologia){
        et_nc_edad_gestacional.setText(String.valueOf(control.edad_gestacional));
        tres_opciones(rb_nc_ecografia_si, rb_nc_ecografia_no, rb_nc_ecografia_solicitada, control.ecografia.equals("SI"), control.ecografia.equals("NO"));
        dos_opciones(rb_nc_hpv_si, rb_nc_hpv_no, control.hpv);
        dos_opciones(rb_nc_pap_si, rb_nc_pap_no, control.pap);
        dos_opciones(rb_nc_a_gripal_si, rb_nc_a_gripal_no, control.a_gripal);
        dos_opciones(rb_nc_tba_si, rb_nc_tba_no, control.tba_inmunizacion);
        tres_opciones(rb_nc_db_previa, rb_nc_db_colocada, rb_nc_db_no, control.db_inmunizacion.equals("PREVIA"), control.db_inmunizacion.equals("COLOCADA"));
        tres_opciones(rb_nc_vhb_previa, rb_nc_vhb_colocada, rb_nc_vhb_no, control.vhb_inmunizacion.equals("PREVIA"), control.vhb_inmunizacion.equals("COLOCADA"));
        dos_opciones(rb_nc_control_clinico_normal, rb_nc_control_clinico_patologico, control.control_clinico.equals("NORMAL"));
        et_nc_tension_arterial.setText(String.valueOf(control.tension_arterial));
        et_nc_observaciones.setText(control.observaciones);
        
        cuatro_opciones(rb_nc_sifilis_solicitado, rb_nc_sifilis_positivo, rb_nc_sifilis_negativo, rb_nc_sifilis_no, sereologia.sifilis.equals("SOLICITADO"), sereologia.sifilis.equals("POSITIVO"), sereologia.sifilis.equals("NEGATIVO"));
        cuatro_opciones(rb_nc_hiv_solicitado, rb_nc_hiv_positivo, rb_nc_hiv_negativo, rb_nc_hiv_no, sereologia.hiv.equals("SOLICITADO"), sereologia.hiv.equals("POSITIVO"), sereologia.hiv.equals("NEGATIVO"));
        cuatro_opciones(rb_nc_chagas_solicitado, rb_nc_chagas_positivo, rb_nc_chagas_negativo, rb_nc_chagas_no, sereologia.chagas.equals("SOLICITADO"), sereologia.chagas.equals("POSITIVO"), sereologia.chagas.equals("NEGATIVO"));
        cuatro_opciones(rb_nc_vhb_solicitado, rb_nc_vhb_positivo, rb_nc_vhb_negativo, rb_nc_vhb_sereologia_no, sereologia.vhb.equals("SOLICITADO"), sereologia.vhb.equals("POSITIVO"), sereologia.vhb.equals("NEGATIVO"));
        cuatro_opciones(rb_nc_gas_solicitado, rb_nc_gas_positivo, rb_nc_gas_negativo, rb_nc_gas_no, sereologia.gas.equals("SOLICITADO"), sereologia.gas.equals("NO"), sereologia.gas.equals("NEGATIVO"));
        tres_opcines_resultado(rb_nc_hb_solicitado, rb_nc_hb_no, rb_nc_hb_resultado,et_nc_hb_resultado, sereologia.hb.equals("SOLICITADO"), sereologia.hb.equals("NO"), sereologia.hb);
        tres_opcines_resultado(rb_nc_glucemia_solicitado, rb_nc_glucemia_no, rb_nc_glucemia_resultado,et_nc_glucemia_resultado, sereologia.glucemia.equals("SOLICITADO"), sereologia.glucemia.equals("NO"), sereologia.glucemia);
        tres_opcines_resultado(rb_nc_grupo_solicitado, rb_nc_grupo_no, rb_nc_grupo_resultado,et_nc_grupo_resultado, sereologia.grupo_factor.equals("SOLICITADO"), sereologia.grupo_factor.equals("NO"), sereologia.grupo_factor);

    }

    public void editControl(View view, int id_control){
        this.id_control = id_control;
        // obtengo los datos de ese control y de su respectiva sereologia de la bdd
        DbHelper db = new DbHelper(ctx);
        Controles control = db.getControl(id_control);
        Sereologias sereologia = db.getAllSereologiaFromControles(control.id);
        // actualizo los componentes
        actualizarComponentesNuevoControl();
        // cargo los datos guardados en la bdd a los componentes
        setDatos(control, sereologia);
    }

    private void guardarControlEditado(DbHelper db, Sereologias sereologias, Controles control){
        db.UpdateControl(id_control, control);
        sereologias.id_control_fk = id_control;
        db.UpdateSereologia(db.getAllSereologiaFromControles(id_control).id, sereologias);

    }

    private void guardarControlNuevo(DbHelper db, Sereologias sereologias, Controles control){
        db.addControl(control);
        int id_control = db.getLastInsertedId();
        sereologias.id_control_fk = id_control;
        db.addSereologia(sereologias);
    }

    public void guardarNuevoControl(View view){
        if (id_paciente == -1){
            Toast.makeText(ctx, "No se ha seleccionado un paciente", Toast.LENGTH_SHORT).show();
            return;
        }


        actualizarComponentesNuevoControl();

        Sereologias sereologias = obtenerSereologia();
        Controles control = obtenerDatosControl(id_paciente);

        if (sereologias == null || control == null){
            Toast.makeText(ctx, "Error al obtener datos", Toast.LENGTH_SHORT).show();
            return;
        }

        // guardo los datos en la base de datos
        DbHelper db = new DbHelper(ctx);


        if(id_control != -1)
            guardarControlEditado(db, sereologias, control);
        else
            guardarControlNuevo(db, sereologias, control);


        db.close();
        Toast.makeText(ctx, "Datos guardados correctamente.", Toast.LENGTH_SHORT).show();

    }



}
