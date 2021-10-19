package com.example.db.entidades;

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

public class Mac_previo {
    public int id_mac_previo;
    public boolean implante;
    public boolean diu;
    public boolean oral;
    public boolean inyectable;
    public boolean barrera;

    private void Inicializar(boolean implante, boolean diu, boolean oral, boolean inyectable, boolean barrera,int id_mac_previo){
        this.id_mac_previo = id_mac_previo;
        this.implante = implante;
        this.diu = diu;
        this.oral = oral;
        this.inyectable = inyectable;
        this.barrera = barrera;
    }

    public Mac_previo(boolean implante, boolean diu, boolean oral, boolean inyectable, boolean barrera,int id_mac_previo) {
        Inicializar(implante, diu, oral, inyectable, barrera,id_mac_previo);
    }
    public Mac_previo(boolean implante, boolean diu, boolean oral, boolean inyectable, boolean barrera) {
        Inicializar(implante, diu, oral, inyectable, barrera,-1);
    }
}