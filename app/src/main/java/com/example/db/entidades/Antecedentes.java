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


    // es puerpera si la fecha actual es mayor a la fecha de parto estimado
    public String es_puerpera(int dia, int mes, int anio){
        int[] fecha_ultimo_parto = format_fecha(parto_estimado);
        int dia_parto_estimado = fecha_ultimo_parto[0];
        int mes_parto_estimado = fecha_ultimo_parto[1];
        int anio_parto_estimado = fecha_ultimo_parto[2];

        if(anio > anio_parto_estimado)
            return "PUERPERA";

        if(anio == anio_parto_estimado){
            if(mes > mes_parto_estimado)
                return "PUERPERA";
            if(mes == mes_parto_estimado){
                if(dia > dia_parto_estimado)
                    return "PUERPERA";
            }
        }

        return "EMBARAZADA.";
    }

    public static int[] format_fecha(String fecha){
        // borro los espacios
        fecha = fecha.replace(" ", "");
        // le hago un format al texto para separar, dia mes y anio (dd/MM/yyyy)
        String[] fecha_separada = fecha.split("/");
        // borro los espacios

        String str_parto_estimado = "--/--/----";

        int[] out = {-1,-1,-1};


        // verifico que sea correcto
        if(fecha_separada.length == 3){
            if(fecha_separada[0].length() == 1)
                fecha_separada[0] = "0"+fecha_separada[0];
            if(fecha_separada[1].length() == 1)
                fecha_separada[1] = "0"+fecha_separada[1];


            // verifico que el dia sea correcto
            if(fecha_separada[0].length()  == 2){
                // verifico que el mes sea correcto
                if(fecha_separada[1].length() == 2){
                    // verifico que el anio sea correcto
                    if(fecha_separada[2].length() == 4){
                        // hago un parseint de todo y me aseguro que si hay un string que no sea un numero, le asigno cero como su valor
                        int dia = Integer.parseInt(fecha_separada[0]);
                        int mes = Integer.parseInt(fecha_separada[1]);
                        int anio = Integer.parseInt(fecha_separada[2]);

                        if(mes <= 12 && mes >= 1){
                            out[0] = dia;
                            out[1] = mes;
                            out[2] = anio;
                            return out;
                        }
                    }
                }
            }
        }
        return out;
    }

    public static String calcularPartoEstimado(int dia, int mes, int anio){
        int[] dias_mes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // para calcular el parto estimado a la fecha actual se le suman 280 dias
        
        int mes_actual = mes;
        int dia_actual = dia;
        int anio_actual = anio;

        

        int dias_transcurridos = 0;
        int dias_mes_actual = dias_mes[mes_actual - 1];

        while(dias_transcurridos < 280){
            System.out.println("mes actual: " + mes_actual + " dia actual: " + dia_actual + " anio actual: " + anio_actual);
            if(dia_actual == dias_mes_actual){

                mes_actual++;
                dia_actual = 1;

                if(mes_actual > 12){
                    mes_actual = 1;
                    anio_actual = anio_actual + 1;
                }

                dias_mes_actual = dias_mes[mes_actual - 1];
            }else{
                dia_actual++;
            }
            dias_transcurridos++;
        }

        
        String dia_parto_estimado = ""+dia_actual;
        String mes_parto_estimado = ""+mes_actual;
        String anio_parto_estimado = ""+anio_actual;

        // si el día o el mes tienen un solo digito agrego un cero al inicio
        if(dia_actual < 10){
            dia_parto_estimado = "0" + dia_parto_estimado;
        }
        if(mes_actual < 10){
            mes_parto_estimado = "0" + mes_parto_estimado;
        }

        return dia_parto_estimado + "/" + mes_parto_estimado + "/" + anio_parto_estimado;


    }

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
