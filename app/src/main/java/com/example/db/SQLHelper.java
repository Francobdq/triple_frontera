package com.example.db;

import android.database.sqlite.SQLiteDatabase;

public class SQLHelper {
    

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

    public static final String NOMBRE_TABLA_SEROLOGIAS = "sereologias";
    public static final String ID_SEROLOGIA = "id_sereologia";
    public static final String SIFILIS_SEROLOGIA = "sifilis_sereologia";
    public static final String HIV_SEROLOGIA = "hiv_sereologia";
    public static final String CHAGAS_SEROLOGIA = "chagas_sereologia";
    public static final String VHB_SEROLOGIA = "vhb_sereologia";
    public static final String GAS_SEROLOGIA = "gas_sereologia";
    public static final String HB_SEROLOGIA = "hb_sereologia";
    public static final String GLUCEMIA = "glucemia";
    public static final String GRUPO_FACTOR_SEROLOGIA = "grupo_factor_sereologia";

    public static final String CREAR_TABLA_SEROLOGIAS = " CREATE TABLE " +
            NOMBRE_TABLA_SEROLOGIAS+ " ("+
                ID_SEROLOGIA +" INTEGER NOT NULL, "+
                SIFILIS_SEROLOGIA +" INTEGER NOT NULL, "+
                HIV_SEROLOGIA + " INTEGER NOT NULL, "+
                CHAGAS_SEROLOGIA + " INTEGER NOT NULL, "+
                VHB_SEROLOGIA + " INTEGER NOT NULL, "+
                GAS_SEROLOGIA + " INTEGER NOT NULL, "+
                HB_SEROLOGIA + " VARCHAR NOT NULL, "+
                GLUCEMIA + " VARCHAR NOT NULL, "+
                GRUPO_FACTOR_SEROLOGIA +" VARCHAR NOT NULL, "+
                " CONSTRAINT "+ ID_SEROLOGIA +" PRIMARY KEY ("+ID_SEROLOGIA+") "+
            "); ";


    /* CREATE TABLE app (
            id_app VARCHAR NOT NULL,
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
    
        public static final String NOMBRE_TABLA_APP = "app";
        public static final String ID_APP = "id_app";
        public static final String HTA_GESTACIONAL = "hta_gestacional";
        public static final String HTA_DBT_GESTACIONAL = "hta_dbt_gestacional";
        public static final String TOXOPLASMOSIS = "toxoplasmosis";
        public static final String TBC = "tbc";
        public static final String SIFILIS = "sifilis";
        public static final String CHAGAS = "chagas";
        public static final String DBT = "dbt";
    
        public static final String CREAR_TABLA_APP = " CREATE TABLE " +
                NOMBRE_TABLA_APP+ " ("+
                    ID_APP +" VARCHAR NOT NULL, "+
                    HTA_GESTACIONAL +" BOOLEAN NOT NULL, "+
                    HTA_DBT_GESTACIONAL + " BOOLEAN NOT NULL, "+
                    TOXOPLASMOSIS + " BOOLEAN NOT NULL, "+
                    TBC + " BOOLEAN NOT NULL, "+
                    SIFILIS + " BOOLEAN NOT NULL, "+
                    CHAGAS + " BOOLEAN NOT NULL, "+
                    DBT + " BOOLEAN NOT NULL, "+
                    " CONSTRAINT "+ ID_APP +" PRIMARY KEY ("+ID_APP+") "+
                "); ";

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
    public static final String NOMBRE_TABLA_MAC_PREVIO = "mac_previo";
    public static final String ID_MAC_PREVIO = "id_mac_previo";
    public static final String IMPLANTE = "implante";
    public static final String DIU = "diu";
    public static final String ORAL = "oral";
    public static final String INYECTABLE = "inyectable";
    public static final String BARRERA = "barrera";

    public static final String CREAR_TABLA_MAC_PREVIO = " CREATE TABLE " +
            NOMBRE_TABLA_MAC_PREVIO+ " ("+
                ID_MAC_PREVIO +" INTEGER NOT NULL, "+
                IMPLANTE +" BOOLEAN NOT NULL, "+
                DIU + " BOOLEAN NOT NULL, "+
                ORAL + " BOOLEAN NOT NULL, "+
                INYECTABLE + " BOOLEAN NOT NULL, "+
                BARRERA + " BOOLEAN NOT NULL, "+
                " CONSTRAINT "+ ID_MAC_PREVIO +" PRIMARY KEY ("+ID_MAC_PREVIO+") "+
            "); ";

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
        id_app_fk INTEGER NOT NULL,
        CONSTRAINT id_antecedente PRIMARY KEY (id_antecedente)
    );

    */
    public static final String NOMBRE_TABLA_ANTECEDENTES = "antecedentes";
    public static final String ID_ANTECEDENTE = "id_antecedente";
    public static final String EDAD_PRIMER_PARTO = "edad_primer_parto";
    public static final String GESTACIONES = "gestaciones";
    public static final String PARTOS = "partos";
    public static final String CESAREAS = "cesareas";
    public static final String ABORTOS = "abortos";
    public static final String EMBARAZO_PLANIFICADO = "embarazo_planificado";
    public static final String FECHA_ULTIMA_MENSTRUACION = "fecha_ultima_menstruacion";
    public static final String FECHA_ULTIMO_PARTO = "fecha_ultimo_parto";
    public static final String PARTO_ESTIMADO = "parto_estimado";
    public static final String ID_MAC_PREVIO_FK = "id_mac_previo_fk";
    public static final String ID_APP_FK = "id_app_fk";

    public static final String CREAR_TABLA_ANTECEDENTES = " CREATE TABLE " +
                NOMBRE_TABLA_ANTECEDENTES+ " ("+
                ID_ANTECEDENTE +" INTEGER NOT NULL, "+
                EDAD_PRIMER_PARTO +" INTEGER DEFAULT 0 NOT NULL, "+
                GESTACIONES + " INTEGER NOT NULL, "+
                PARTOS + " INTEGER NOT NULL, "+
                CESAREAS + " INTEGER DEFAULT 0 NOT NULL, "+
                ABORTOS + " INTEGER NOT NULL, "+
                EMBARAZO_PLANIFICADO +" BOOLEAN NOT NULL, "+
                FECHA_ULTIMA_MENSTRUACION + " DATE NOT NULL, "+
                FECHA_ULTIMO_PARTO + " DATE, "+
                PARTO_ESTIMADO + " DATE, "+
                ID_MAC_PREVIO_FK + " INTEGER NOT NULL, "+
                ID_APP_FK + " INTEGER NOT NULL, "+
                " FOREIGN KEY ("+ID_MAC_PREVIO_FK+") REFERENCES "+NOMBRE_TABLA_MAC_PREVIO+" ("+ID_MAC_PREVIO+"), "+
                " FOREIGN KEY ("+ID_APP_FK+") REFERENCES "+NOMBRE_TABLA_APP+" ("+ID_APP+"), "+
                " CONSTRAINT "+ ID_ANTECEDENTE +" PRIMARY KEY ("+ID_ANTECEDENTE+") "+
            "); ";

    /*
    CREATE TABLE paises (
        id_pais INTEGER NOT NULL,
        nombre_pais VARCHAR NOT NULL,
        CONSTRAINT id_paises PRIMARY KEY (id_pais)
    );

    */
    public static final String NOMBRE_TABLA_PAISES = "paises";
    public static final String ID_PAIS = "id_pais";
    public static final String NOMBRE_PAIS = "nombre_pais";

    public static final String CREAR_TABLA_PAISES = " CREATE TABLE " +
            NOMBRE_TABLA_PAISES+ " ("+
                ID_PAIS +" INTEGER NOT NULL, "+
                NOMBRE_PAIS +" VARCHAR NOT NULL, "+
                " CONSTRAINT "+ ID_PAIS +" PRIMARY KEY ("+ID_PAIS+") "+
            "); ";

    /*
    CREATE TABLE areas_operativas (
                id_area_operativa INTEGER NOT NULL,
                nombre_area_operativa VARCHAR NOT NULL,
                id_pais_fk INTEGER NOT NULL,
                CONSTRAINT id_area_operativa PRIMARY KEY (id_area_operativa)
    );
    */
    public static final String NOMBRE_TABLA_AREAS_OPERATIVAS = "areas_operativas";
    public static final String ID_AREA_OPERATIVA = "id_area_operativa";
    public static final String NOMBRE_AREA_OPERATIVA = "nombre_area_operativa";
    public static final String ID_PAIS_FK = "id_pais_fk";

    public static final String CREAR_TABLA_AREAS_OPERATIVAS = " CREATE TABLE " +
            NOMBRE_TABLA_AREAS_OPERATIVAS+ " ("+
                ID_AREA_OPERATIVA +" INTEGER NOT NULL, "+
                NOMBRE_AREA_OPERATIVA +" VARCHAR NOT NULL, "+
                ID_PAIS_FK + " INTEGER NOT NULL, "+
                " FOREIGN KEY ("+ID_PAIS_FK+") REFERENCES "+NOMBRE_TABLA_PAISES+" ("+ID_PAIS+"), "+
                " CONSTRAINT "+ ID_AREA_OPERATIVA +" PRIMARY KEY ("+ID_AREA_OPERATIVA+") "+
            "); ";

    /* 
    CREATE TABLE parajes (
        id_paraje INTEGER NOT NULL,
        nombre_paraje VARCHAR NOT NULL,
        id_area_operativa_fk INTEGER NOT NULL,
        CONSTRAINT id_paraje PRIMARY KEY (id_paraje)
    );
    */
    public static final String NOMBRE_TABLA_PARAJES = "parajes";
    public static final String ID_PARAJE = "id_paraje";
    public static final String NOMBRE_PARAJE = "nombre_paraje";
    public static final String ID_AREA_OPERATIVA_FK = "id_area_operativa_fk";

    public static final String CREAR_TABLA_PARAJES = " CREATE TABLE " +
            NOMBRE_TABLA_PARAJES+ " ("+
                ID_PARAJE +" INTEGER NOT NULL, "+
                NOMBRE_PARAJE +" VARCHAR NOT NULL, "+
                ID_AREA_OPERATIVA_FK + " INTEGER NOT NULL, "+
                " FOREIGN KEY ("+ID_AREA_OPERATIVA_FK+") REFERENCES "+NOMBRE_TABLA_AREAS_OPERATIVAS+" ("+ID_AREA_OPERATIVA+"), "+
                " CONSTRAINT "+ ID_PARAJE +" PRIMARY KEY ("+ID_PARAJE+") "+
            "); ";

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

    public static final String NOMBRE_TABLA_PACIENTES = "pacientes";
    public static final String ID_PACIENTE = "id_paciente";
    public static final String NOMBRE_PACIENTE = "nombre_paciente";
    public static final String APELLIDO_PACIENTE = "apellido_paciente";
    public static final String DOCUMENTO_PACIENTE = "edad_paciente";
    public static final String FECHA_DE_NACIMIENTO_PACIENTE = "fecha_de_nacimiento_paciente";
    public static final String ORIGEN_PACIENTE = "origen_paciente";
    public static final String NACIONALIDAD_PACIENTE = "nacionalidad_paciente";
    public static final String NUM_VIVIENDA_PACIENTE = "num_vivienda_paciente";
    public static final String ID_ANTECEDENTE_FK = "id_antecedente_fk";
    public static final String ID_PARAJE_FK = "id_paraje_fk";



    public static final String CREAR_TABLA_PACIENTES = " CREATE TABLE " +
            NOMBRE_TABLA_PACIENTES+ " ("+
                ID_PACIENTE +" INTEGER NOT NULL, "+
                NOMBRE_PACIENTE +" VARCHAR NOT NULL, "+
                APELLIDO_PACIENTE + " VARCHAR NOT NULL, "+
                DOCUMENTO_PACIENTE + " INTEGER NOT NULL, "+
                FECHA_DE_NACIMIENTO_PACIENTE+ " DATE NOT NULL, "+
                ORIGEN_PACIENTE +" VARCHAR NOT NULL, "+
                NACIONALIDAD_PACIENTE +" VARCHAR NOT NULL, "+
                NUM_VIVIENDA_PACIENTE +" INTEGER NOT NULL, "+
                ID_ANTECEDENTE_FK +" INTEGER NOT NULL, "+
                ID_PARAJE_FK+ " INTEGER NOT NULL, "+
                " CONSTRAINT "+ ID_PACIENTE +" PRIMARY KEY ("+ID_PACIENTE+"), "+
                " FOREIGN KEY ("+ID_ANTECEDENTE_FK+") REFERENCES "+NOMBRE_TABLA_ANTECEDENTES+" ("+ID_ANTECEDENTE+"), "+
                " FOREIGN KEY ("+ID_PARAJE_FK+") REFERENCES "+NOMBRE_TABLA_PARAJES+" ("+ID_PARAJE+")"+
            "); ";


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

    public static final String NOMBRE_TABLA_CONTROLES = "controles";
    public static final String ID_CONTROL = "id_control";
    public static final String EDAD_GESTACIONAL = "edad_gestacional";
    public static final String ECOGRAFIA = "ecografia";
    public static final String HPV = "hpv";
    public static final String PAP = "pap";
    public static final String A_GRIPAL = "a_gripal";
    public static final String TBA_INMUNIZACION = "tba_inmunizacion";
    public static final String DB_INMUNIZACION = "db_inmunizacion";
    public static final String VHB_INMUNIZACION = "vhb_inmunizacion";
    public static final String CONTROL_CLINICO = "control_clinico";
    public static final String TENSION_ARTERIAL = "tension_arterial";
    public static final String OBSERVACIONES = "observaciones";
    public static final String ID_SEROLOGIA_FK = "id_sereologia_fk";
    public static final String ID_PACIENTE_CONTROL_FK = "id_paciente_control_fk";

    public static final String CREAR_TABLA_CONTROLES = " CREATE TABLE " +
            NOMBRE_TABLA_CONTROLES+ " ("+
                ID_CONTROL +" INTEGER NOT NULL, "+
                EDAD_GESTACIONAL +" INTEGER NOT NULL, "+
                ECOGRAFIA + " INTEGER NOT NULL, "+
                HPV + " BOOLEAN NOT NULL, "+
                PAP + " BOOLEAN NOT NULL, "+
                A_GRIPAL + " BOOLEAN NOT NULL, "+
                TBA_INMUNIZACION + " BOOLEAN NOT NULL, "+
                DB_INMUNIZACION + " INTEGER NOT NULL, "+
                VHB_INMUNIZACION + " INTEGER NOT NULL, "+
                CONTROL_CLINICO +" VARCHAR NOT NULL, "+
                TENSION_ARTERIAL + " FLOAT NOT NULL, "+
                OBSERVACIONES +" LONGVARCHAR, "+
                ID_SEROLOGIA_FK +" INTEGER NOT NULL, "+
                ID_PACIENTE_CONTROL_FK +" INTEGER NOT NULL, "+
                " CONSTRAINT "+ ID_CONTROL +" PRIMARY KEY ("+ID_CONTROL+"), "+
                " FOREIGN KEY ("+ID_SEROLOGIA_FK+") REFERENCES "+NOMBRE_TABLA_SEROLOGIAS+" ("+ID_SEROLOGIA+"), "+
                " FOREIGN KEY ("+ID_PACIENTE_CONTROL_FK+") REFERENCES "+NOMBRE_TABLA_PACIENTES+" ("+ID_PACIENTE+")"+
            "); ";


    public static final void crearTablas(SQLiteDatabase db){
        db.execSQL(SQLHelper.CREAR_TABLA_SEROLOGIAS);
        db.execSQL(SQLHelper.CREAR_TABLA_APP);
        db.execSQL(SQLHelper.CREAR_TABLA_MAC_PREVIO);
        db.execSQL(SQLHelper.CREAR_TABLA_ANTECEDENTES);
        db.execSQL(SQLHelper.CREAR_TABLA_PAISES);
        db.execSQL(SQLHelper.CREAR_TABLA_AREAS_OPERATIVAS);
        db.execSQL(SQLHelper.CREAR_TABLA_PARAJES);
        db.execSQL(SQLHelper.CREAR_TABLA_PACIENTES);
        db.execSQL(SQLHelper.CREAR_TABLA_CONTROLES);
    }

    public static final void eliminarTablas(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS "+NOMBRE_TABLA_SEROLOGIAS);
        db.execSQL("DROP TABLE IF EXISTS "+NOMBRE_TABLA_APP);
        db.execSQL("DROP TABLE IF EXISTS "+NOMBRE_TABLA_MAC_PREVIO);
        db.execSQL("DROP TABLE IF EXISTS "+NOMBRE_TABLA_ANTECEDENTES);
        db.execSQL("DROP TABLE IF EXISTS "+NOMBRE_TABLA_PAISES);
        db.execSQL("DROP TABLE IF EXISTS "+NOMBRE_TABLA_AREAS_OPERATIVAS);
        db.execSQL("DROP TABLE IF EXISTS "+NOMBRE_TABLA_PARAJES);
        db.execSQL("DROP TABLE IF EXISTS "+NOMBRE_TABLA_PACIENTES);
        db.execSQL("DROP TABLE IF EXISTS "+NOMBRE_TABLA_CONTROLES);
    }

    public static final void eliminarTablas(SQLiteDatabase db, String tabla){
        db.execSQL("DROP TABLE IF EXISTS "+tabla);
    }
        

    /* FALTAN HIJOS, PUERPERAS Y EMBARAZADAS PERO AUN NO ESTÁ MODELADO.*/

}
