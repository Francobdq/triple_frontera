package com.example.db.entidades;

/* 
    CREATE TABLE antecedentes (
        id_antecedente INTEGER NOT NULL,
        edad_primer_parto INTEGER DEFAULT 0 NOT NULL,
        gestaciones INTEGER NOT NULL,
        partos INTEGER NOT NULL,
        cesareas INTEGER DEFAULT 0 NOT NULL,
        abortos INTEGER NOT NULL,
        embarazo_planificado BOOLEAN NOT NULL,
        fecha_ultima_menstruacion DATE NOT NULL,
        fecha_ultimo_parto DATE,
        parto_estimado DATE,
        id_mac_previo_fk INTEGER NOT NULL,
        id_app_fk INTENGER NOT NULL,
        CONSTRAINT id_antecedente PRIMARY KEY (id_antecedente)
    );

    */

public class Antecedentes {
    public int id;  
    public int edad_primer_parto;
    public int gestaciones;
    public int partos;
    public int ceseareas;
    public int abortos;
    public boolean embarazo_planificado;
    public String fecha_ultima_menstruacion;
    public String fecha_ultimo_parto;
    public String parto_estimado;
    public int id_mac_previo_fk;
    public int id_app_fk;

    public Antecedentes(int id, int edad_primer_parto, int gestaciones, int partos, int ceseareas, int abortos, boolean embarazo_planificado, String fecha_ultima_menstruacion, String fecha_ultimo_parto, String parto_estimado, int id_mac_previo_fk, int id_app_fk) {
        this.id = id;
        this.edad_primer_parto = edad_primer_parto;
        this.gestaciones = gestaciones;
        this.partos = partos;
        this.ceseareas = ceseareas;
        this.abortos = abortos;
        this.embarazo_planificado = embarazo_planificado;
        this.fecha_ultima_menstruacion = fecha_ultima_menstruacion;
        this.fecha_ultimo_parto = fecha_ultimo_parto;
        this.parto_estimado = parto_estimado;
        this.id_mac_previo_fk = id_mac_previo_fk;
        this.id_app_fk = id_app_fk;
    }
}
