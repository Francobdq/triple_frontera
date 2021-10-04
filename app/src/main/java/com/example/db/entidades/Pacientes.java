package com.example.db.entidades;

public class Pacientes {
    private int id;
    private String nombre;
    private String apellido;
    private int documento;
    private String fecha_de_nacimiento;
    private String origen;
    private String nacionalidad;
    private int num_vivienda;

    public Pacientes(int id,String nombre, String apellido, int documento, String fecha_de_nacimiento, String origen, String nacionalidad, int num_vivienda){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.origen = origen;
        this.nacionalidad = nacionalidad;
        this.num_vivienda = num_vivienda;
    }


    // getters
    public int getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDocumento() {
        return documento;
    }

    public String getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public String getOrigen() {
        return origen;
    }

    public String nacionalidad() {
        return nacionalidad;
    }

    public int getNum_vivienda() {
        return num_vivienda;
    }

    // setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setNum_vivienda(int num_vivienda) {
        this.num_vivienda = num_vivienda;
    }
}
