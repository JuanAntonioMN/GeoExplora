package com.example.geoexplora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class OperacionesImagenes {
    private SQLiteDatabase baseDatos;
    private DataBaseGeoExplora dbHelper;

    public OperacionesImagenes(Context context) {
        dbHelper = new DataBaseGeoExplora(context);
    }

    public void abrir() throws SQLException {
        baseDatos = dbHelper.getWritableDatabase();
    }

    public void cerrar() {
        dbHelper.close();
    }
    public long insertarImagenes(int imagen1,int imagen2,int imagen3,int imagen4,int imagen5) {
        ContentValues valores = new ContentValues();
        valores.put(TableImagenes.Imagenes.COLUMNA_PRINCIPAL, imagen1);
        valores.put(TableImagenes.Imagenes.COLUMNA_SECUNDARIA, imagen2);
        valores.put(TableImagenes.Imagenes.COLUMNA_DATA1,imagen3);
        valores.put(TableImagenes.Imagenes.COLUMNA_DATA2,imagen4);
        valores.put(TableImagenes.Imagenes.COLUMNA_DATA3,imagen5);


        return baseDatos.insert(TableImagenes.Imagenes.NOMBRE_TABLA, null, valores);
    }
    public List<Imagenes> obtenerTodasLasImagenes() {
        List<Imagenes> listaImagenes = new ArrayList<>();

        // Especifica las columnas que deseas recuperar
        String[] columnas = {
                TableImagenes.Imagenes.COLUMNA_PRINCIPAL,
                TableImagenes.Imagenes.COLUMNA_SECUNDARIA,
                TableImagenes.Imagenes.COLUMNA_DATA1,
                TableImagenes.Imagenes.COLUMNA_DATA2,
                TableImagenes.Imagenes.COLUMNA_DATA3
        };

        // Realiza la consulta a la base de datos
        Cursor cursor = baseDatos.query(
                TableImagenes.Imagenes.NOMBRE_TABLA,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int imagenPrincipal = cursor.getInt(cursor.getColumnIndexOrThrow(TableImagenes.Imagenes.COLUMNA_PRINCIPAL));
                int imagenSecundaria = cursor.getInt(cursor.getColumnIndexOrThrow(TableImagenes.Imagenes.COLUMNA_SECUNDARIA));
                int imagenData1 = cursor.getInt(cursor.getColumnIndexOrThrow(TableImagenes.Imagenes.COLUMNA_DATA1));
                int imagenData2 = cursor.getInt(cursor.getColumnIndexOrThrow(TableImagenes.Imagenes.COLUMNA_DATA2));
                int imagenData3 = cursor.getInt(cursor.getColumnIndexOrThrow(TableImagenes.Imagenes.COLUMNA_DATA3));

                // Crea un objeto Imagenes y agrégalo a la lista
                Imagenes imagenes = new Imagenes(imagenPrincipal, imagenSecundaria, imagenData1, imagenData2, imagenData3);
                listaImagenes.add(imagenes);

            } while (cursor.moveToNext());

            // Cierra el cursor después de usarlo
            cursor.close();
        }

        return listaImagenes;
    }

    public void eliminarImagenes() {
        baseDatos.delete(TableImagenes.Imagenes.NOMBRE_TABLA, null, null);
    }



}
