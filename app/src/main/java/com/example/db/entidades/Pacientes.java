package com.example.db.entidades;

public class Pacientes {
    public int id;
    public String nombre;
    public String apellido;
    public int documento;
    public String fecha_de_nacimiento;
    public String origen;
    public String nacionalidad;
    public int num_vivienda;
    public int id_antecedente_fk;
    public int id_paraje_fk;


    private void Inicializar(String nombre, String apellido, int documento, String fecha_de_nacimiento, String origen, String nacionalidad, int num_vivienda, int id_antecedente_fk, int id_paraje_fk, int id){
        this.id = id; // el id -1 significa que no se cual es su id
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.origen = origen;
        this.nacionalidad = nacionalidad;
        this.num_vivienda = num_vivienda;
        this.id_antecedente_fk = id_antecedente_fk;
        this.id_paraje_fk = id_paraje_fk;
    }


    public Pacientes(String nombre, String apellido, int documento, String fecha_de_nacimiento, String origen, String nacionalidad, int num_vivienda, int id_antecedente_fk, int id_paraje_fk, int id){
        Inicializar(nombre, apellido, documento, fecha_de_nacimiento, origen, nacionalidad, num_vivienda, id_antecedente_fk, id_paraje_fk, id);
    }

    public Pacientes(String nombre, String apellido, int documento, String fecha_de_nacimiento, String origen, String nacionalidad, int num_vivienda, int id_antecedente_fk, int id_paraje_fk){
        Inicializar(nombre, apellido, documento, fecha_de_nacimiento, origen, nacionalidad, num_vivienda, id_antecedente_fk, id_paraje_fk, -1);
    }

}
