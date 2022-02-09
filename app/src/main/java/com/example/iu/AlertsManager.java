package com.example.iu;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AlertsManager {


    public static void ConfirmarSalida(Context ctx, AppCompatActivity act)
    {

        new AlertDialog.Builder(ctx)
                .setTitle("Volver al listado.")
                .setMessage("¿Está seguro que desea salir? Asegurese de haber guardado los cambios.")
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        act.finish();
                    }
                })
                .setNegativeButton("cancelar", null)
                .show();
    }

    public static void ConfirmarSalida(Context ctx, DialogInterface dialog)
    {

        new AlertDialog.Builder(ctx)
                .setTitle("Volver al ingreso")
                .setMessage("¿Está seguro que desea salir? Asegurese de haber guardado los cambios.")
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface a, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("cancelar", null)
                .show();
    }
}
