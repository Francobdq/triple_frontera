package com.example.db.entidades;

// esta clase puede ser tanto un país, como un area operativa o un paraje
public class Zona {
    public int id;
    public String nombre;
    public int fk = -1;

    public Zona(int id, String nombre, int fk){
        this.id = id;
        this.nombre = nombre;
        this.fk = fk;
    }
    
    public Zona(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

}
