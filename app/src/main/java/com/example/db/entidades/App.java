package com.example.db.entidades;

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

public class App {
    public int id_app;
    public boolean hta_gestacional;
    public boolean hta_dbt_gestacional;
    public boolean toxoplasmosis;
    public boolean tbc;
    public boolean sifilis;
    public boolean chagas;
    public boolean dbt;

 
    private void Inicializar(boolean hta_gestacional, boolean hta_dbt_gestacional, boolean toxoplasmosis, boolean tbc, boolean sifilis, boolean chagas, boolean dbt,int id_app){
        this.id_app = id_app;
        this.hta_gestacional = hta_gestacional;
        this.hta_dbt_gestacional = hta_dbt_gestacional;
        this.toxoplasmosis = toxoplasmosis;
        this.tbc = tbc;
        this.sifilis = sifilis;
        this.chagas = chagas;
        this.dbt = dbt;
    }

    public App(boolean hta_gestacional, boolean hta_dbt_gestacional, boolean toxoplasmosis, boolean tbc, boolean sifilis, boolean chagas, boolean dbt,int id_app) {
        Inicializar(hta_gestacional, hta_dbt_gestacional, toxoplasmosis, tbc, sifilis, chagas, dbt,id_app);
    }
    public App(boolean hta_gestacional, boolean hta_dbt_gestacional, boolean toxoplasmosis, boolean tbc, boolean sifilis, boolean chagas, boolean dbt) {
        Inicializar(hta_gestacional, hta_dbt_gestacional, toxoplasmosis, tbc, sifilis, chagas, dbt,-1);
    }
}





