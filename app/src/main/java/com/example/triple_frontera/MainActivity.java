package com.example.triple_frontera;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.db.DbHelper;
import com.example.db.SQLHelper;
import com.example.db.entidades.Antecedentes;
import com.example.db.entidades.Controles;
import com.example.db.entidades.Pacientes;
import com.example.db.entidades.Sereologias;
import com.example.db.entidades.Zona;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // para el login
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // deshabilita la rotación de pantalla
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        username = (EditText)findViewById(R.id.usuario);
        password = (EditText)findViewById(R.id.password);

        /*DbHelper db = new DbHelper(MainActivity.this);
        db.addPais("Argentina");
        db.addArea_operativa("area 1", db.getLastInsertedId());
        db.addPais("Bolivia");
        db.addPais("Paraguay");
        int id_paraguay = db.getLastInsertedId();
        db.addArea_operativa("area 2", id_paraguay);
        int id_area_2 = db.getLastInsertedId();
        db.addParaje("paraje 1", id_area_2);
        int id_paraje_1 = db.getLastInsertedId();
        db.addParaje("paraje 2", id_area_2);
        db.addArea_operativa("area 3", id_paraguay);
        int id_area_3 = db.getLastInsertedId();
        db.addParaje("paraje 3", id_area_3);
        int id_paraje_3 = db.getLastInsertedId();
        db.addParaje("paraje 4", id_area_3);
        
       

        Pacientes paciente_paraje_3_1 = new Pacientes("Cristela","Perez",22334566,"22/10/1999","Criolla","Argentina",10,0,id_paraje_3);
        Pacientes paciente_paraje_1_1 = new Pacientes("Abigail", "Lopez", 23450244, "10/03/1980", "Originaria", "Paraguay", 11, 0, id_paraje_1);
        Pacientes paciente_paraje_3_3 = new Pacientes("Juana", "Perez", 23456789, "15/05/1984", "Originaria", "Paraguay", 12, 0, id_paraje_3);
        Pacientes paciente_paraje_3_4 = new Pacientes("Maria", "Franco", 23456729, "01/02/1977", "Criolla", "Bolivia", 13, 0, id_paraje_3);
        Pacientes paciente_paraje_3_5 = new Pacientes("Josefa", "Dumbsky", 23454099, "30/05/1989", "Originaria", "Paraguay", 14, 0, id_paraje_3);


        db.addPaciente(paciente_paraje_3_1);
        db.addPaciente(paciente_paraje_1_1);
        db.addPaciente(paciente_paraje_3_3);
        db.addPaciente(paciente_paraje_3_4);
        db.addPaciente(paciente_paraje_3_5);


        //Toast.makeText(MainActivity.this, "Success1 = "+success1 + " success = " + success, Toast.LENGTH_LONG).show();
        //Toast.makeText(MainActivity.this, "Success2 = "+success2, Toast.LENGTH_SHORT).show();
        List<Zona> paises = db.getAllPaises();
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("PAISES: ");
        for(int i = 0; i < paises.size(); i++){
            String datos = "Pais = " +paises.get(i).id +"-"+  paises.get(i).nombre;
            Toast.makeText(MainActivity.this, datos, Toast.LENGTH_SHORT).show();
            System.out.println(datos);
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("AREAS OPERATIVAS DE ARGENTINA: ");
        List<Zona> areas = db.getAllAreasOperativasFromPais(0);
        for(int i = 0; i < areas.size(); i++){
            String datos = "Area = " +areas.get(i).id +"-"+  areas.get(i).nombre;
            Toast.makeText(MainActivity.this, datos, Toast.LENGTH_SHORT).show();
            System.out.println(datos);
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("AREAS OPERATIVAS DE BOLIVIA: ");
        List<Zona> areas2 = db.getAllAreasOperativasFromPais(1);
        for(int i = 0; i < areas2.size(); i++){
            String datos = "Area = " +areas2.get(i).id +"-"+  areas2.get(i).nombre;
            Toast.makeText(MainActivity.this, datos, Toast.LENGTH_SHORT).show();
            System.out.println(datos);
        }
        System.out.println("");
        System.out.println("AREAS OPERATIVAS DE PARAGUAY: ");
        List<Zona> areas3 = db.getAllAreasOperativasFromPais(2);
        for(int i = 0; i < areas3.size(); i++){
            String datos = "Area = " +areas3.get(i).id +"-"+  areas3.get(i).nombre;
            Toast.makeText(MainActivity.this, datos, Toast.LENGTH_SHORT).show();
            System.out.println(datos);
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("PARAJES:");
        List<Zona> paraje = db.getAllParajesFromAreaOperativa(1);
        for(int i = 0; i < paraje.size(); i++){
            String datos = "Paraje = " +paraje.get(i).id +"-"+  paraje.get(i).nombre;
            Toast.makeText(MainActivity.this, datos, Toast.LENGTH_SHORT).show();
            System.out.println(datos);
        }

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Pacientes paraje 1:");
        List<Pacientes> pacientes1 = db.getPacientesFromParaje(1);
        for(int i = 0; i < pacientes1.size(); i++){
            String datos = "Paciente = " +pacientes1.get(i).id +"-"+  pacientes1.get(i).nombre + " " + pacientes1.get(i).apellido + " " + pacientes1.get(i).documento + " " + pacientes1.get(i).fecha_de_nacimiento + " " + pacientes1.get(i).nacionalidad + " " + pacientes1.get(i).id_paraje_fk;
            Toast.makeText(MainActivity.this, datos, Toast.LENGTH_SHORT).show();
            System.out.println(datos);
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Pacientes paraje 2:");
        List<Pacientes> pacientes2 = db.getPacientesFromParaje(2);
        for(int i = 0; i < pacientes2.size(); i++){
            String datos = "Paciente = " +pacientes2.get(i).id +"-"+  pacientes2.get(i).nombre + " " + pacientes2.get(i).apellido + " " + pacientes2.get(i).documento + " " + pacientes2.get(i).fecha_de_nacimiento + " " + pacientes2.get(i).nacionalidad + " " + pacientes2.get(i).id_paraje_fk;
            Toast.makeText(MainActivity.this, datos, Toast.LENGTH_SHORT).show();
            System.out.println(datos);
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Pacientes paraje 3:");
        List<Pacientes> pacientes = db.getPacientesFromParaje(3);
        for(int i = 0; i < pacientes.size(); i++){
            String datos = "Paciente = " +pacientes.get(i).id +"-"+  pacientes.get(i).nombre + " " + pacientes.get(i).apellido + " " + pacientes.get(i).documento + " " + pacientes.get(i).fecha_de_nacimiento + " " + pacientes.get(i).nacionalidad + " " + pacientes.get(i).id_paraje_fk;
            Toast.makeText(MainActivity.this, datos, Toast.LENGTH_SHORT).show();
            System.out.println(datos);
        }


        Controles control = new Controles( 0,0, "SI", 0, 0, 0,  0, "SI", "SI","NORMAL",0f,0);
        db.addControl(control);

        Sereologias sereologia1 = new Sereologias(0,"","","","","","10","glucemia","+0", db.getLastInsertedId());
        db.addSereologia(sereologia1);*/


        // BORRAR ESTO ES PARA DEBUG
        Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(MainActivity.this, Lugar_de_trabajo.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void IniciarSesion(View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        if(user.equals("") || pass.equals("")){
            Toast.makeText(getApplicationContext(), "Faltan datos", Toast.LENGTH_SHORT).show();
            return;
        }

        if(true){
            Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(MainActivity.this, Lugar_de_trabajo.class);
            MainActivity.this.startActivity(myIntent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
        }
    }


}