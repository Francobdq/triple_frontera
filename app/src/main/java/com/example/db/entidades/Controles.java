package com.example.db.entidades;

public class Controles {
    public int id;
    public int edad_gestacional;
    public String ecografia;
    public boolean hpv;
    public boolean pap;
    public boolean a_gripal;
    public boolean tba_inmunizacion;
    public String db_inmunizacion;
    public String vhb_inmunizacion;
    public String control_clinico;
    public float tension_arterial;
    public String observaciones;
    public int id_paciente_control_fk;

    private void Inicializar(int id, int edad_gestacional, String ecografia, int hpv, int pap, int a_gripal, int tba_inmunizacion, String db_inmunizacion, String vhb_inmunizacion, String control_clinico, float tension_arterial, String observaciones, int id_paciente_control_fk) {
        this.id = id;
        this.edad_gestacional = edad_gestacional;
        this.ecografia = ecografia;
        this.hpv = (hpv == 1);
        this.pap = (pap == 1);
        this.a_gripal = (a_gripal == 1);
        this.tba_inmunizacion = (tba_inmunizacion == 1);
        this.db_inmunizacion = db_inmunizacion;
        this.vhb_inmunizacion = vhb_inmunizacion;
        this.control_clinico = control_clinico;
        this.tension_arterial = tension_arterial;
        this.observaciones = observaciones;
        this.id_paciente_control_fk = id_paciente_control_fk;
    }

    private void Inicializar(int id, int edad_gestacional, String ecografia, boolean hpv, boolean pap, boolean a_gripal, boolean tba_inmunizacion, String db_inmunizacion, String vhb_inmunizacion, String control_clinico, float tension_arterial, String observaciones, int id_paciente_control_fk) {
        this.id = id;
        this.edad_gestacional = edad_gestacional;
        this.ecografia = ecografia;
        this.hpv = hpv;
        this.pap = pap;
        this.a_gripal = a_gripal;
        this.tba_inmunizacion = tba_inmunizacion;
        this.db_inmunizacion = db_inmunizacion;
        this.vhb_inmunizacion = vhb_inmunizacion;
        this.control_clinico = control_clinico;
        this.tension_arterial = tension_arterial;
        this.observaciones = observaciones;
        this.id_paciente_control_fk = id_paciente_control_fk;
    }

    public Controles(int id, int edad_gestacional, String ecografia, boolean hpv, boolean pap, boolean a_gripal, boolean tba_inmunizacion, String db_inmunizacion, String vhb_inmunizacion, String control_clinico, float tension_arterial, String observaciones, int id_paciente_control_fk) {
        Inicializar(id, edad_gestacional, ecografia, hpv, pap, a_gripal, tba_inmunizacion, db_inmunizacion, vhb_inmunizacion, control_clinico, tension_arterial, observaciones, id_paciente_control_fk);
    }


    public Controles(int id, int edad_gestacional, String ecografia, int hpv, int pap, int a_gripal, int tba_inmunizacion, String db_inmunizacion, String vhb_inmunizacion, String control_clinico, float tension_arterial, String observaciones, int id_paciente_control_fk) {
        Inicializar(id, edad_gestacional, ecografia, hpv, pap, a_gripal, tba_inmunizacion, db_inmunizacion, vhb_inmunizacion, control_clinico, tension_arterial, observaciones, id_paciente_control_fk);
    }


    // porque la observacion puede ser nula
    public Controles(int id, int edad_gestacional, String ecografia, int hpv, int pap, int a_gripal, int tba_inmunizacion, String db_inmunizacion, String vhb_inmunizacion, String control_clinico, float tension_arterial, int id_paciente_control_fk) {
        Inicializar(id, edad_gestacional, ecografia, hpv, pap, a_gripal, tba_inmunizacion, db_inmunizacion, vhb_inmunizacion, control_clinico, tension_arterial, "", id_paciente_control_fk);
    }

}
