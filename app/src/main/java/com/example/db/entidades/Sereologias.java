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
    public int sifilis;
    public int hiv;
    public int chagas;
    public int vhb;
    public int gas;
    public String hb;
    public String glucemia;
    public String grupo_factor;

    public Sereologias(int id, int sifilis, int hiv, int chagas, int vhb, int gas, String hb, String glucemia, String grupo_factor) {
        this.id = id;
        this.sifilis = sifilis;
        this.hiv = hiv;
        this.chagas = chagas;
        this.vhb = vhb;
        this.gas = gas;
        this.hb = hb;
        this.glucemia = glucemia;
        this.grupo_factor = grupo_factor;
    }

    

}

