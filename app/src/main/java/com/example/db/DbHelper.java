package com.example.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.db.entidades.Antecedentes;
import com.example.db.entidades.Controles;
import com.example.db.entidades.Pacientes;
import com.example.db.entidades.Sereologias;
import com.example.db.entidades.Zona;

import java.util.ArrayList;
import java.util.List;

/* 
    abrir archivos .db para revisar que esté todo bien
    crear area operativa y paraje para ver relaciones
    probar buscar.
    una vez que todo eso funcione, creo la base de datos completa.

    >

    empezar a crear las distintas busquedas (pacientes, controles, etc)
    testear creando nuevos pacientes y nuevos controles
    hablar con seba para ver de sacar tablas de la bdd y sumarlas entre si para ahorrar tiempos de busqueda.

*/




public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "triple_frontera.db";

    //private static final String SQL_CREATE_ENTRIES = SQLHelper.CREAR_TABLAS;
    //private static final String SQL_DELETE_ENTRIES = "DROP TABLE "+SQLHelper.NOMBRE_TABLA_PAISES+";";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SQLHelper.crearTablas(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // se ejecuta cuando cambia la versión de la base de datos
        SQLHelper.eliminarTablas(db);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private boolean insertarLocalizacion(String nombre_tabla, String id_key, int id,String nombre_key, String nombre,String fk_key, int fk){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id_key, id);
        values.put(nombre_key, nombre);
        if (fk != -1)
            values.put(fk_key, fk);

        long insert = db.insert(nombre_tabla, null, values);
        return (insert != -1);
    }


    public boolean addPais(int id, String nombre){
        boolean salida = insertarLocalizacion(SQLHelper.NOMBRE_TABLA_PAISES, SQLHelper.ID_PAIS, id, SQLHelper.NOMBRE_PAIS, nombre, "", -1);
        return salida;
    }

    public boolean addArea_operativa(int id, String nombre, int id_pais){
        boolean salida = insertarLocalizacion(SQLHelper.NOMBRE_TABLA_AREAS_OPERATIVAS, SQLHelper.ID_AREA_OPERATIVA, id, SQLHelper.NOMBRE_AREA_OPERATIVA, nombre, SQLHelper.ID_PAIS_FK, id_pais);
        return salida;
    }

    public boolean addParaje(int id, String nombre, int id_area_operativa){
        boolean salida = insertarLocalizacion(SQLHelper.NOMBRE_TABLA_PARAJES, SQLHelper.ID_PARAJE, id, SQLHelper.NOMBRE_PARAJE, nombre, SQLHelper.ID_AREA_OPERATIVA_FK, id_area_operativa);
        return salida;
    }

    /*
    CREATE TABLE pacientes (
        id_paciente INTEGER NOT NULL,
        nombre_paciente VARCHAR NOT NULL,
        apellido_paciente VARCHAR NOT NULL,
        documento_paciente INTEGER NOT NULL,
        fecha_de_nacimiento_paciente DATE NOT NULL,
        origen_paciente VARCHAR NOT NULL,
        nacionalidad_paciente VARCHAR NOT NULL,
        num_vivienda_paciente INTEGER NOT NULL,
        id_antecedente_fk INTEGER NOT NULL,
        id_paraje_fk INTEGER NOT NULL,
        CONSTRAINT id_paciente PRIMARY KEY (id_paciente)
    );
    */

    public boolean addPaciente(Pacientes paciente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLHelper.ID_PACIENTE, paciente.id);
        values.put(SQLHelper.NOMBRE_PACIENTE, paciente.nombre);
        values.put(SQLHelper.APELLIDO_PACIENTE, paciente.apellido);
        values.put(SQLHelper.DOCUMENTO_PACIENTE, paciente.documento);
        values.put(SQLHelper.FECHA_DE_NACIMIENTO_PACIENTE, paciente.fecha_de_nacimiento);
        values.put(SQLHelper.ORIGEN_PACIENTE, paciente.origen);
        values.put(SQLHelper.NACIONALIDAD_PACIENTE, paciente.nacionalidad);
        values.put(SQLHelper.NUM_VIVIENDA_PACIENTE, paciente.num_vivienda);
        values.put(SQLHelper.ID_ANTECEDENTE_FK, paciente.id_antecedente_fk);
        values.put(SQLHelper.ID_PARAJE_FK, paciente.id_paraje_fk);

        long insert = db.insert(SQLHelper.NOMBRE_TABLA_PACIENTES, null, values);
        return (insert != -1);

    }

 
    /*
    CREATE TABLE controles (
        id_control INTEGER NOT NULL,
        edad_gestacional INTEGER NOT NULL,
        ecografia INTEGER NOT NULL,
        hpv BOOLEAN NOT NULL,
        pap BOOLEAN NOT NULL,
        a_gripal BOOLEAN NOT NULL,
        tba_inmunizacion BOOLEAN NOT NULL,
        db_inmunizacion INTEGER NOT NULL,
        vhb_inmunizacion INTEGER NOT NULL,
        control_clinico VARCHAR NOT NULL,
        tension_arterial FLOAT NOT NULL,
        observaciones LONGVARCHAR,
        id_sereologia_fk INTEGER NOT NULL,
        id_paciente_control_fk INTEGER NOT NULL,
        CONSTRAINT id_control PRIMARY KEY (id_control)
    );
    */
    public boolean addControl(Controles control){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SQLHelper.ID_CONTROL, control.id);
        values.put(SQLHelper.EDAD_GESTACIONAL, control.edad_gestacional);
        values.put(SQLHelper.ECOGRAFIA, control.ecografia);
        values.put(SQLHelper.HPV, control.hpv);
        values.put(SQLHelper.PAP, control.pap);
        values.put(SQLHelper.A_GRIPAL, control.a_gripal);
        values.put(SQLHelper.TBA_INMUNIZACION, control.tba_inmunizacion);
        values.put(SQLHelper.DB_INMUNIZACION, control.db_inmunizacion);
        values.put(SQLHelper.VHB_INMUNIZACION, control.vhb_inmunizacion);
        values.put(SQLHelper.CONTROL_CLINICO, control.control_clinico);
        values.put(SQLHelper.TENSION_ARTERIAL, control.tension_arterial);
        values.put(SQLHelper.OBSERVACIONES, control.observaciones);
        values.put(SQLHelper.ID_SEROLOGIA_FK, control.id_sereologia_fk);
        values.put(SQLHelper.ID_PACIENTE_CONTROL_FK, control.id_paciente_control_fk);

        long insert = db.insert(SQLHelper.NOMBRE_TABLA_CONTROLES, null, values);
        return (insert != -1);
    }

    /*
    
    CREATE TABLE sereologias (
        id_sereologia INTEGER NOT NULL,
        sifilis_sereologia INTEGER NOT NULL,
        hiv_sereologia INTEGER NOT NULL,
        chagas_sereologia INTEGER NOT NULL,
        vhb_sereologia INTEGER NOT NULL,
        gas_sereologia INTEGER NOT NULL,
        hb_sereologia VARCHAR NOT NULL,
        glucemia VARCHAR NOT NULL,
        grupo_factor_sereologia VARCHAR NOT NULL,
        CONSTRAINT pk_sereologia PRIMARY KEY (id_sereologia)
    );
    */ 
    public boolean addSereologia(Sereologias sereologia){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SQLHelper.ID_SEROLOGIA, sereologia.id);
        values.put(SQLHelper.SIFILIS_SEROLOGIA, sereologia.sifilis);
        values.put(SQLHelper.HIV_SEROLOGIA, sereologia.hiv);
        values.put(SQLHelper.CHAGAS_SEROLOGIA, sereologia.chagas);
        values.put(SQLHelper.VHB_SEROLOGIA, sereologia.vhb);
        values.put(SQLHelper.GAS_SEROLOGIA, sereologia.gas);
        values.put(SQLHelper.HB_SEROLOGIA, sereologia.hb);
        values.put(SQLHelper.GLUCEMIA, sereologia.glucemia);
        values.put(SQLHelper.GRUPO_FACTOR_SEROLOGIA, sereologia.grupo_factor);

        long insert = db.insert(SQLHelper.NOMBRE_TABLA_SEROLOGIAS, null, values);
        return (insert != -1);
    }

    /* 
    CREATE TABLE antecedentes (
        id_antecedente INTEGER NOT NULL,
        edad_primer_parto INTEGER DEFAULT 0 NOT NULL,
        gestaciones INTEGER NOT NULL,
        partos INTEGER NOT NULL,
        cesareas INTEGER DEFAULT 0 NOT NULL,
        abortos INTEGER NOT NULL,
        emabarazo_planificado BOOLEAN NOT NULL,
        fecha_ultima_menstruacion DATE NOT NULL,
        fecha_ultimo_parto DATE,
        parto_estimado DATE,
        id_mac_previo_fk INTEGER NOT NULL,
        id_app_fk VARCHAR NOT NULL,
        CONSTRAINT id_antecedente PRIMARY KEY (id_antecedente)
    );

    */

    public boolean addAntecedente(Antecedentes antecedente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SQLHelper.ID_ANTECEDENTE, antecedente.id);
        values.put(SQLHelper.EDAD_PRIMER_PARTO, antecedente.edad_primer_parto);
        values.put(SQLHelper.GESTACIONES, antecedente.gestaciones);
        values.put(SQLHelper.PARTOS, antecedente.partos);
        values.put(SQLHelper.CESAREAS, antecedente.ceseareas);
        values.put(SQLHelper.ABORTOS, antecedente.abortos);
        values.put(SQLHelper.EMBARAZO_PLANIFICADO, antecedente.embarazo_planificado);
        values.put(SQLHelper.FECHA_ULTIMA_MENSTRUACION, antecedente.fecha_ultima_menstruacion);
        values.put(SQLHelper.FECHA_ULTIMO_PARTO, antecedente.fecha_ultimo_parto);
        values.put(SQLHelper.PARTO_ESTIMADO, antecedente.parto_estimado);
        values.put(SQLHelper.ID_MAC_PREVIO_FK, antecedente.id_mac_previo_fk);
        values.put(SQLHelper.ID_APP_FK, antecedente.id_app_fk);

        long insert = db.insert(SQLHelper.NOMBRE_TABLA_ANTECEDENTES, null, values);
        return (insert != -1);
    }



    private List<Zona> getAllZonaFromOtraZona(int id_otra_zona, String nombre_tabla, String nombre_id_otra_tabla_fk){
        List<Zona> zonas = new ArrayList<>();


        String selectQuery = "SELECT * FROM " + nombre_tabla;
        if(id_otra_zona != -1)
            selectQuery += " WHERE " + nombre_id_otra_tabla_fk + " = " + id_otra_zona;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int fk = -1;
                if(id_otra_zona != -1)
                    fk = cursor.getInt(2);
                Zona zona = new Zona(id, nombre, fk);
                zonas.add(zona);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return zonas;
    }
    private List<Zona> getAllZona(String nombre_tabla){
        return getAllZonaFromOtraZona(-1, nombre_tabla, "");
    }
    
    public List<Zona> getAllPaises(){
        return getAllZona(SQLHelper.NOMBRE_TABLA_PAISES);
    }

    public List<Zona> getAllAreasOperativasFromPais(int id_pais){
        return getAllZonaFromOtraZona(id_pais, SQLHelper.NOMBRE_TABLA_AREAS_OPERATIVAS,SQLHelper.ID_PAIS_FK);
    }

    public List<Zona> getAllParajesFromAreaOperativa(int id_area_operativa){
        return getAllZonaFromOtraZona(id_area_operativa, SQLHelper.NOMBRE_TABLA_PARAJES, SQLHelper.ID_AREA_OPERATIVA_FK);
    }


    public List<Pacientes> getPacientesFromParaje(int id_paraje){
        List<Pacientes> pacientes = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_PACIENTES + " WHERE " + SQLHelper.ID_PARAJE_FK + " = " + id_paraje;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String apellido = cursor.getString(2);
                int documento = cursor.getInt(3);
                String fecha_nacimiento = cursor.getString(4);
                String origen = cursor.getString(5);
                String nacionalidad = cursor.getString(6);
                int num_vivienda = cursor.getInt(7);
                int id_antecedente_fk = cursor.getInt(8);
                int id_paraje_fk = cursor.getInt(9);

                Pacientes paciente = new Pacientes(id, nombre, apellido, documento, fecha_nacimiento, origen, nacionalidad, num_vivienda, id_antecedente_fk, id_paraje_fk);
                pacientes.add(paciente);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return pacientes;
    }

    public List<Controles> getControlesFromPaciente(int id_paciente){
        List<Controles> controles = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_CONTROLES + " WHERE " + SQLHelper.ID_PACIENTE_CONTROL_FK + " = " + id_paciente;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int edad_gestacional = cursor.getInt(1);
                int ecografia = cursor.getInt(2);
                int hpv = cursor.getInt(3);
                int pap = cursor.getInt(4);
                int a_gripal = cursor.getInt(5);
                int tba_inmunizacion = cursor.getInt(6);
                int db_inmunizacion = cursor.getInt(7);
                int vhb_inmunizacion = cursor.getInt(8);
                String control_clinico = cursor.getString(9);
                float tension_arterial = cursor.getFloat(10);
                String observaciones = cursor.getString(11);
                int id_sereologia_fk = cursor.getInt(12);
                int id_paciente_control_fk = cursor.getInt(13);

                Controles control = new Controles(id, edad_gestacional, ecografia, hpv, pap, a_gripal, tba_inmunizacion, db_inmunizacion, vhb_inmunizacion, control_clinico, tension_arterial, observaciones, id_sereologia_fk, id_paciente_control_fk);
                controles.add(control);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return controles;
    }

    /*
    LA CLASE SEREOLOGIAS TIENE:
    public int id;
    public int sifilis;
    public int hiv;
    public int chagas;
    public int vhb;
    public int gas;
    public String hb;
    public String glucemia;
    public String grupo_factor;

    */
    
    public List<Sereologias> getAllSereologiaFromControles(int id_control){
        List<Sereologias> sereologias = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_SEROLOGIAS + " WHERE " + SQLHelper.ID_SEROLOGIA_FK + " = " + id_control;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int sifilis = cursor.getInt(1);
                int hiv = cursor.getInt(2);
                int chagas = cursor.getInt(3);
                int vhb = cursor.getInt(4);
                int gas = cursor.getInt(5);
                String hb = cursor.getString(6);
                String glucemia = cursor.getString(7);
                String grupo_factor = cursor.getString(8);

                Sereologias sereologia = new Sereologias(id, sifilis, hiv, chagas, vhb, gas, hb, glucemia, grupo_factor);
                sereologias.add(sereologia);
            } while (cursor.moveToNext());
        }
    
        cursor.close();
        db.close();
        return sereologias;
    }

    /*
    LA CLASE ANTECEDENTES TIENE:
    public int id;  
    public int edad_primer_parto;
    public int gestaciones;
    public int partos;
    public int ceseareas;
    public int abortos;
    public boolean emabarazo_planificado;
    public String fecha_ultima_menstruacion;
    public String fecha_ultimo_parto;
    public String parto_estimado;
    public int id_mac_previo_fk;
    public String id_app_fk;
    */

    public List<Antecedentes> getAllAntecedentesFromPaciente(int id_paciente){
        List<Antecedentes> antecedentes = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_ANTECEDENTES + " WHERE " + SQLHelper.ID_ANTECEDENTE_FK + " = " + id_paciente;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int edad_primer_parto = cursor.getInt(1);
                int gestaciones = cursor.getInt(2);
                int partos = cursor.getInt(3);
                int ceseareas = cursor.getInt(4);
                int abortos = cursor.getInt(5);
                boolean emabarazo_planificado = cursor.getInt(6) == 1;
                String fecha_ultima_menstruacion = cursor.getString(7);
                String fecha_ultimo_parto = cursor.getString(8);
                String parto_estimado = cursor.getString(9);
                int id_mac_previo_fk = cursor.getInt(10);
                int id_app_fk = cursor.getInt(11);

                Antecedentes antecedente = new Antecedentes(id, edad_primer_parto, gestaciones, partos, ceseareas, abortos, emabarazo_planificado, fecha_ultima_menstruacion, fecha_ultimo_parto, parto_estimado, id_mac_previo_fk, id_app_fk);
                antecedentes.add(antecedente);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return antecedentes;
    }
    
}
