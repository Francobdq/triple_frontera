package com.example.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.db.entidades.Zona;

import java.util.ArrayList;
import java.util.List;

/* 
    abrir archivos .db para revisar que esté todo bien
    crear area operativa y paraje para ver relaciones
    probar buscar.
    una vez que todo eso funcione, creo la base de datos completa.

    >

    empezar a crear las distintas busquedas (pacientes, controles, etc)

*/




public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "triple_frontera.db";

    private static final String SQL_CREATE_ENTRIES = SQLHelper.CREAR_TABLAS;
    //private static final String SQL_DELETE_ENTRIES = "DROP TABLE "+SQLHelper.NOMBRE_TABLA_PAISES+";";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SQLHelper.crearTablas(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // se ejecuta cuando cambia la versión de la base de datos
        SQLHelper.eliminarTablas(db);
        //db.execSQL("DROP TABLE " + SQLHelper.NOMBRE_TABLA_PAISES + ";");
        //db.execSQL("DROP TABLE " + SQLHelper.NOMBRE_TABLA_AREAS_OPERATIVAS + ";");
        //db.execSQL("DROP TABLE " + SQLHelper.NOMBRE_TABLA_PARAJES + ";");
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private boolean insertarLocalizacion(String nombre_tabla, String id_key, int id,String nombre_key, String nombre,String fk_key, int fk){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id_key, id);
        values.put(nombre_key, nombre);
        if (fk != -1)
            values.put(fk_key, fk);

        long insert = db.insert(nombre_tabla, null, values);
        return (insert != -1);
    }


    public boolean addPais(int id, String nombre){
        boolean salida = insertarLocalizacion(SQLHelper.NOMBRE_TABLA_PAISES, SQLHelper.ID_PAIS, id, SQLHelper.NOMBRE_PAIS, nombre, "", -1);
        return salida;
    }

    public boolean addArea_operativa(int id, String nombre, int id_pais){
        boolean salida = insertarLocalizacion(SQLHelper.NOMBRE_TABLA_AREAS_OPERATIVAS, SQLHelper.ID_AREA_OPERATIVA, id, SQLHelper.NOMBRE_AREA_OPERATIVA, nombre, SQLHelper.ID_PAIS_FK, id_pais);
        return salida;
    }

    public boolean addParaje(int id, String nombre, int id_area_operativa){
        boolean salida = insertarLocalizacion(SQLHelper.NOMBRE_TABLA_PARAJES, SQLHelper.ID_PARAJE, id, SQLHelper.NOMBRE_PARAJE, nombre, SQLHelper.ID_AREA_OPERATIVA_FK, id_area_operativa);
        return salida;
    }


    public List<Zona> getAllPaises(){
        List<Zona> paises = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_PAISES;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                Zona zona = new Zona(id, nombre);
                paises.add(zona);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return paises;
    }

    public List<Zona> getAllAreasOperativasFromPais(int id_pais){
        List<Zona> areas_operativas = new ArrayList<>();
        
        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_AREAS_OPERATIVAS + " WHERE " + SQLHelper.ID_PAIS_FK + " = " + id_pais;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int fk = cursor.getInt(2);
                Zona zona = new Zona(id, nombre, fk);
                areas_operativas.add(zona);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return areas_operativas;
    }

    public List<Zona> getAllParajesFromAreaOperativa(int id_area_operativa){
        List<Zona> parajes = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_PARAJES + " WHERE " + SQLHelper.ID_AREA_OPERATIVA_FK + " = " + id_area_operativa;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int fk = cursor.getInt(2);
                Zona zona = new Zona(id, nombre, fk);
                parajes.add(zona);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return parajes;
    }


    public List<String> getNombresParajesFromAreaOperativa(int id_area_operativa){
        List<String> nombres = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SQLHelper.NOMBRE_TABLA_PARAJES + " WHERE " + SQLHelper.ID_AREA_OPERATIVA_FK + " = " + id_area_operativa;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                nombres.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return nombres;
    }
        
}
