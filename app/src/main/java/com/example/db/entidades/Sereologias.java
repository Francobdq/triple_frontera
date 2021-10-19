package com.example.db.entidades;

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

public class Sereologias {
    public int id;
    public String sifilis;
    public String hiv;
    public String chagas;
    public String vhb;
    public String gas;
    public String hb;
    public String glucemia;
    public String grupo_factor;
    public int id_control_fk;

    public Sereologias(int id, String sifilis, String hiv, String chagas, String vhb, String gas, String hb, String glucemia, String grupo_factor, int id_control_fk) {
        this.id = id;
        this.sifilis = sifilis;
        this.hiv = hiv;
        this.chagas = chagas;
        this.vhb = vhb;
        this.gas = gas;
        this.hb = hb;
        this.glucemia = glucemia;
        this.grupo_factor = grupo_factor;
        this.id_control_fk = id_control_fk;
    }

    

}

