package com.example.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.db.entidades.Antecedentes;
import com.example.db.entidades.App;
import com.example.db.entidades.Controles;
import com.example.db.entidades.Mac_previo;
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
    private static final int DATABASE_VERSION = 15;
    private static final String DATABASE_NAME = "triple_frontera.db";

    private static long last_id = -1;


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

    private boolean insertarLocalizacion(String nombre_tabla, String nombre_key, String nombre,String fk_key, int fk){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(id_key, last_id);
        values.put(nombre_key, nombre);
        if (fk != -1)
            values.put(fk_key, fk);

        last_id = db.insert(nombre_tabla, null, values);

        db.close();

        return (last_id != -1);
    }


    public boolean addPais(String nombre){
        boolean salida = insertarLocalizacion(SQLHelper.NOMBRE_TABLA_PAISES, SQLHelper.NOMBRE_PAIS, nombre, "", -1);
        return salida;
    }

    public boolean addArea_operativa(String nombre, int id_pais){
        boolean salida = insertarLocalizacion(SQLHelper.NOMBRE_TABLA_AREAS_OPERATIVAS, SQLHelper.NOMBRE_AREA_OPERATIVA, nombre, SQLHelper.ID_PAIS_FK, id_pais);
        return salida;
    }

    public boolean addParaje(String nombre, int id_area_operativa){
        boolean salida = insertarLocalizacion(SQLHelper.NOMBRE_TABLA_PARAJES, SQLHelper.NOMBRE_PARAJE, nombre, SQLHelper.ID_AREA_OPERATIVA_FK, id_area_operativa);
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

    private ContentValues getValuesPaciente(Pacientes paciente){
        ContentValues values = new ContentValues();

        //values.put(SQLHelper.ID_PACIENTE, paciente.id);
        values.put(SQLHelper.NOMBRE_PACIENTE, paciente.nombre);
        values.put(SQLHelper.APELLIDO_PACIENTE, paciente.apellido);
        values.put(SQLHelper.DOCUMENTO_PACIENTE, paciente.documento);
        values.put(SQLHelper.FECHA_DE_NACIMIENTO_PACIENTE, paciente.fecha_de_nacimiento);
        values.put(SQLHelper.ORIGEN_PACIENTE, paciente.origen);
        values.put(SQLHelper.NACIONALIDAD_PACIENTE, paciente.nacionalidad);
        values.put(SQLHelper.NUM_VIVIENDA_PACIENTE, paciente.num_vivienda);
        values.put(SQLHelper.ID_ANTECEDENTE_FK, paciente.id_antecedente_fk);
        values.put(SQLHelper.ID_PARAJE_FK, paciente.id_paraje_fk);

        return values;
    }

    public boolean addPaciente(Pacientes paciente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValuesPaciente(paciente);

        last_id = db.insert(SQLHelper.NOMBRE_TABLA_PACIENTES, null, values);
        return (last_id != -1);

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

    private ContentValues getValuesControl(Controles control){
        ContentValues values = new ContentValues();

        //values.put(SQLHelper.ID_CONTROL, control.id);
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
        values.put(SQLHelper.ID_PACIENTE_CONTROL_FK, control.id_paciente_control_fk);

        return values;
    }

    public boolean addControl(Controles control){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValuesControl(control);

        last_id = db.insert(SQLHelper.NOMBRE_TABLA_CONTROLES, null, values);
        return (last_id != -1);
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

    private ContentValues getValuesSereologia(Sereologias sereologia){
        ContentValues values = new ContentValues();

        //values.put(SQLHelper.ID_SEROLOGIA, sereologia.id);
        values.put(SQLHelper.SIFILIS_SEROLOGIA, sereologia.sifilis);
        values.put(SQLHelper.HIV_SEROLOGIA, sereologia.hiv);
        values.put(SQLHelper.CHAGAS_SEROLOGIA, sereologia.chagas);
        values.put(SQLHelper.VHB_SEROLOGIA, sereologia.vhb);
        values.put(SQLHelper.GAS_SEROLOGIA, sereologia.gas);
        values.put(SQLHelper.HB_SEROLOGIA, sereologia.hb);
        values.put(SQLHelper.GLUCEMIA, sereologia.glucemia);
        values.put(SQLHelper.GRUPO_FACTOR_SEROLOGIA, sereologia.grupo_factor);
        values.put(SQLHelper.ID_CONTROL_FK, sereologia.id_control_fk);

        return values;

    }

    public boolean addSereologia(Sereologias sereologia){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValuesSereologia(sereologia);

        last_id = db.insert(SQLHelper.NOMBRE_TABLA_SEROLOGIAS, null, values);
        return (last_id != -1);
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

    private ContentValues getValuesAntecedente(Antecedentes antecedente){
        ContentValues values = new ContentValues();

        //values.put(SQLHelper.ID_ANTECEDENTE, antecedente.id);
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

        return values;
    }

    public boolean addAntecedente(Antecedentes antecedente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValuesAntecedente(antecedente);

        last_id = db.insert(SQLHelper.NOMBRE_TABLA_ANTECEDENTES, null, values);
        return (last_id != -1);
    }

    /* CREATE TABLE app (
            id_app INTEGER,
            hta_gestacional BOOLEAN NOT NULL,
            hta_dbt_gestacional BOOLEAN NOT NULL,
            toxoplasmosis BOOLEAN NOT NULL,
            tbc BOOLEAN NOT NULL,
            sifilis BOOLEAN NOT NULL,
            chagas BOOLEAN NOT NULL,
            dbt BOOLEAN NOT NULL,
            CONSTRAINT id_app PRIMARY KEY (id_app)
    );
    */

    private ContentValues getValuesApp(App app){
        ContentValues values = new ContentValues();

        //values.put(SQLHelper.ID_APP, app.id);
        values.put(SQLHelper.HTA_GESTACIONAL, app.hta_gestacional);
        values.put(SQLHelper.HTA_DBT_GESTACIONAL, app.hta_dbt_gestacional);
        values.put(SQLHelper.TOXOPLASMOSIS, app.toxoplasmosis);
        values.put(SQLHelper.TBC, app.tbc);
        values.put(SQLHelper.SIFILIS, app.sifilis);
        values.put(SQLHelper.CHAGAS, app.chagas);
        values.put(SQLHelper.DBT, app.dbt);

        return values;
    }

    public boolean addApp(App app){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValuesApp(app);

        last_id = db.insert(SQLHelper.NOMBRE_TABLA_APP, null, values);
        return (last_id != -1);
    }

    /*
    CREATE TABLE mac_previo (
        id_mac_previo INTEGER NOT NULL,
        implante BOOLEAN NOT NULL,
        diu BOOLEAN NOT NULL,
        oral BOOLEAN NOT NULL,
        inyectable BOOLEAN NOT NULL,
        barrera BOOLEAN NOT NULL,
        CONSTRAINT id_mac_previo PRIMARY KEY (id_mac_previo)
    );

    */

    private ContentValues getValuesMacPrevio(Mac_previo mac_previo){
        ContentValues values = new ContentValues();

        //values.put(SQLHelper.ID_MAC_PREVIO, mac_previo.id);
        values.put(SQLHelper.IMPLANTE, mac_previo.implante);
        values.put(SQLHelper.DIU, mac_previo.diu);
        values.put(SQLHelper.ORAL, mac_previo.oral);
        values.put(SQLHelper.INYECTABLE, mac_previo.inyectable);
        values.put(SQLHelper.BARRERA, mac_previo.barrera);

        return values;
    }

    public boolean addMacPrevio(Mac_previo mac_previo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValuesMacPrevio(mac_previo);

        last_id = db.insert(SQLHelper.NOMBRE_TABLA_MAC_PREVIO, null, values);
        return (last_id != -1);
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

                Pacientes paciente = new Pacientes(nombre, apellido, documento, fecha_nacimiento, origen, nacionalidad, num_vivienda, id_antecedente_fk, id_paraje_fk, id);
                pacientes.add(paciente);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return pacientes;
    }

    public List<Controles> getControlesFromPaciente(int id_paciente){
        System.out.println("CONTROLESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
        System.out.println("ID PACIENTE: " + id_paciente);
        System.out.println("ID_PACIENTE_FK: " + SQLHelper.ID_PACIENTE_CONTROL_FK );
        List<Controles> controles = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_CONTROLES + " WHERE " + SQLHelper.ID_PACIENTE_CONTROL_FK + " = " + id_paciente;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int edad_gestacional = cursor.getInt(1);
                String ecografia = cursor.getString(2);
                int hpv = cursor.getInt(3);
                int pap = cursor.getInt(4);
                int a_gripal = cursor.getInt(5);
                int tba_inmunizacion = cursor.getInt(6);
                String db_inmunizacion = cursor.getString(7);
                String vhb_inmunizacion = cursor.getString(8);
                String control_clinico = cursor.getString(9);
                float tension_arterial = cursor.getFloat(10);
                String observaciones = cursor.getString(11);
                //int id_sereologia_fk = cursor.getInt(12);
                int id_paciente_control_fk = cursor.getInt(12);

                //Sereologias sereologia = getAllSereologiaFromControles(id).get(0);
                Controles control = new Controles(id, edad_gestacional,ecografia, hpv, pap, a_gripal,tba_inmunizacion, db_inmunizacion, vhb_inmunizacion, control_clinico, tension_arterial, observaciones, id_paciente_control_fk);
                controles.add(control);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        System.out.println(controles.size());
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
    
    public Sereologias getAllSereologiaFromControles(int id_control){
        Sereologias sereologias = null;

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_SEROLOGIAS + " WHERE " + SQLHelper.ID_CONTROL_FK + " = " + id_control;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String sifilis = cursor.getString(1);
                String hiv = cursor.getString(2);
                String chagas = cursor.getString(3);
                String vhb = cursor.getString(4);
                String gas = cursor.getString(5);
                String hb = cursor.getString(6);
                String glucemia = cursor.getString(7);
                String grupo_factor = cursor.getString(8);
                int id_control_fk = cursor.getInt(9);

                Sereologias sereologia = new Sereologias(id, sifilis, hiv, chagas, vhb, gas, hb, glucemia, grupo_factor, id_control_fk);
                sereologias = sereologia;
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

    // paciente tiene la fk de un antecedente y este es el que debo retornar


    public Antecedentes getAntecedente(int id_antecedente){
        Antecedentes antecedentes = null;

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_ANTECEDENTES + " WHERE " + SQLHelper.ID_ANTECEDENTE + " = " + id_antecedente;

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

                antecedentes = new Antecedentes(id, edad_primer_parto, gestaciones, partos, ceseareas, abortos, emabarazo_planificado, fecha_ultima_menstruacion, fecha_ultimo_parto, parto_estimado, id_mac_previo_fk, id_app_fk);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return antecedentes;
    }
    /*
    LA CLASE APP TIENE:
    public int id_app;
    public boolean hta_gestacional;
    public boolean hta_dbt_gestacional;
    public boolean toxoplasmosis;
    public boolean tbc;
    public boolean sifilis;
    public boolean chagas;
    public boolean dbt;
    */

    public App getApp(int id_app){
        App app = null;

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_APP + " WHERE " + SQLHelper.ID_APP + " = " + id_app;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                //int id_app = cursor.getInt(0);
                boolean hta_gestacional = cursor.getInt(1) == 1;
                boolean hta_dbt_gestacional = cursor.getInt(2) == 1;
                boolean toxoplasmosis = cursor.getInt(3) == 1;
                boolean tbc = cursor.getInt(4) == 1;
                boolean sifilis = cursor.getInt(5) == 1;
                boolean chagas = cursor.getInt(6) == 1;
                boolean dbt = cursor.getInt(7) == 1;

                app = new App(hta_gestacional, hta_dbt_gestacional, toxoplasmosis, tbc, sifilis, chagas, dbt, id_app);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return app;
    }
    /*
    LA CLASE MAC_PREVIO TIENE:
    public int id_mac_previo;
    public boolean implante;
    public boolean diu;
    public boolean oral;
    public boolean inyectable;
    public boolean barrera;
    */

    public Mac_previo getMacPrevio(int id_mac_previo){
        Mac_previo mac_previo = null;

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_MAC_PREVIO + " WHERE " + SQLHelper.ID_MAC_PREVIO + " = " + id_mac_previo;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                //int id_mac_previo = cursor.getInt(0);
                boolean implante = cursor.getInt(1) == 1;
                boolean diu = cursor.getInt(2) == 1;
                boolean oral = cursor.getInt(3) == 1;
                boolean inyectable = cursor.getInt(4) == 1;
                boolean barrera = cursor.getInt(5) == 1;

                mac_previo = new Mac_previo(implante, diu, oral, inyectable, barrera, id_mac_previo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return mac_previo;
    }

    /* 
    LA CLASE CONTROLES TIENE:
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

    public Controles getControl(int id_control){
        Controles control = null;

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_CONTROLES + " WHERE " + SQLHelper.ID_CONTROL + " = " + id_control;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int edad_gestacional = cursor.getInt(1);
                String ecografia = cursor.getString(2);
                boolean hpv = cursor.getInt(3) == 1;
                boolean pap = cursor.getInt(4) == 1;
                boolean a_gripal = cursor.getInt(5) == 1;
                boolean tba_inmunizacion = cursor.getInt(6) == 1;
                String db_inmunizacion = cursor.getString(7);
                String vhb_inmunizacion = cursor.getString(8);
                String control_clinico = cursor.getString(9);
                float tension_arterial = cursor.getFloat(10);
                String observaciones = cursor.getString(11);
                int id_paciente_control_fk = cursor.getInt(12);

                control = new Controles(id, edad_gestacional, ecografia, hpv, pap, a_gripal, tba_inmunizacion, db_inmunizacion, vhb_inmunizacion, control_clinico, tension_arterial, observaciones, id_paciente_control_fk);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return control;
    }


    public Pacientes getPaciente(int id_paciente){
        Pacientes paciente = null;

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_PACIENTES + " WHERE " + SQLHelper.ID_PACIENTE + " = " + id_paciente;

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

                paciente = new Pacientes(nombre, apellido, documento, fecha_nacimiento, origen, nacionalidad, num_vivienda, id_antecedente_fk, id_paraje_fk,id);
                break;
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return paciente;
    }

    public void UpdatePaciente(int id_paciente, Pacientes paciente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValuesPaciente(paciente);

        db.update(SQLHelper.NOMBRE_TABLA_PACIENTES, values, SQLHelper.ID_PACIENTE + " = ?", new String[]{String.valueOf(id_paciente)});
        db.close();
    }

    public void UpdateControl(int id_control, Controles control){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValuesControl(control);

        db.update(SQLHelper.NOMBRE_TABLA_CONTROLES, values, SQLHelper.ID_CONTROL + " = ?", new String[]{String.valueOf(id_control)});
        db.close();
    }

    public void UpdateSereologia(int id_sereologia, Sereologias sereologia){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValuesSereologia(sereologia);

        db.update(SQLHelper.NOMBRE_TABLA_SEROLOGIAS, values, SQLHelper.ID_SEROLOGIA + " = ?", new String[]{String.valueOf(id_sereologia)});
        db.close();
    }

    public void UpdateAntecedente(int id_antecedente, Antecedentes Antecedente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValuesAntecedente(Antecedente);

        db.update(SQLHelper.NOMBRE_TABLA_ANTECEDENTES, values, SQLHelper.ID_ANTECEDENTE + " = ?", new String[]{String.valueOf(id_antecedente)});
        db.close();
    }

    public void UpdateMacPrevio(int id_mac_previo, Mac_previo mac_previo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValuesMacPrevio(mac_previo);

        db.update(SQLHelper.NOMBRE_TABLA_MAC_PREVIO, values, SQLHelper.ID_MAC_PREVIO + " = ?", new String[]{String.valueOf(id_mac_previo)});
        db.close();
    }

    public void UpdateApp(int id_app, App app){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValuesApp(app);

        db.update(SQLHelper.NOMBRE_TABLA_APP, values, SQLHelper.ID_APP + " = ?", new String[]{String.valueOf(id_app)});
        db.close();
    }





    public int getLastInsertedId(){
        return (int) last_id;
    }
}
