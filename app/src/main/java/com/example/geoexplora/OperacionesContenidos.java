package com.example.geoexplora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class OperacionesContenidos {
    private SQLiteDatabase baseDatos;
    private DataBaseGeoExplora dbHelper;
    public OperacionesContenidos(Context context) {
        dbHelper = new DataBaseGeoExplora(context);
    }

    public void abrir() throws SQLException {
        baseDatos = dbHelper.getWritableDatabase();
    }

    public void cerrar() {
        dbHelper.close();
    }
    public long insertarContenido(String tema, int imagen, String informacion, int bandera) {
        ContentValues valores = new ContentValues();
        valores.put(TableContenidos.Contenidos.COLUMNA_TEMA, tema);
        valores.put(TableContenidos.Contenidos.COLUMNA_IMAGEN, imagen);
        valores.put(TableContenidos.Contenidos.COLUMNA_INFORMACION, informacion);
        valores.put(TableContenidos.Contenidos.COLUMNA_BANDERA, bandera);

        return baseDatos.insert(TableContenidos.Contenidos.NOMBRE_TABLA, null, valores);
    }

    public List<Contenidos> obtenerTodosLosContenidos() {
        List<Contenidos> listaContenidos = new ArrayList<>();

        // Especifica las columnas que deseas recuperar
        String[] columnas = {
                TableContenidos.Contenidos._ID,
                TableContenidos.Contenidos.COLUMNA_TEMA,
                TableContenidos.Contenidos.COLUMNA_IMAGEN,
                TableContenidos.Contenidos.COLUMNA_INFORMACION,
                TableContenidos.Contenidos.COLUMNA_BANDERA
        };

        // Realiza la consulta a la base de datos
        Cursor cursor = baseDatos.query(
                TableContenidos.Contenidos.NOMBRE_TABLA,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(TableContenidos.Contenidos._ID));
                String tema = cursor.getString(cursor.getColumnIndexOrThrow(TableContenidos.Contenidos.COLUMNA_TEMA));
                int imagen = cursor.getInt(cursor.getColumnIndexOrThrow(TableContenidos.Contenidos.COLUMNA_IMAGEN));
                String informacion = cursor.getString(cursor.getColumnIndexOrThrow(TableContenidos.Contenidos.COLUMNA_INFORMACION));
                int bandera = cursor.getInt(cursor.getColumnIndexOrThrow(TableContenidos.Contenidos.COLUMNA_BANDERA));

                // Crea un objeto Contenidos y agrégalo a la lista
                Contenidos contenido = new Contenidos( tema, imagen,informacion, bandera);
                listaContenidos.add(contenido);

            } while (cursor.moveToNext());

            // Cierra el cursor después de usarlo
            cursor.close();
        }

        return listaContenidos;
    }

    public int actualizarContenido(int idContenido, String nuevoTema, int nuevaImagen, String nuevaInformacion, int nuevaBandera) {
        ContentValues valores = new ContentValues();
        valores.put(TableContenidos.Contenidos.COLUMNA_TEMA, nuevoTema);
        valores.put(TableContenidos.Contenidos.COLUMNA_IMAGEN, nuevaImagen);
        valores.put(TableContenidos.Contenidos.COLUMNA_INFORMACION, nuevaInformacion);
        valores.put(TableContenidos.Contenidos.COLUMNA_BANDERA, nuevaBandera);

        String whereClause = TableContenidos.Contenidos._ID + " = ?";
        String[] whereArgs = {String.valueOf(idContenido)};

        return baseDatos.update(TableContenidos.Contenidos.NOMBRE_TABLA, valores, whereClause, whereArgs);
    }

    public int eliminarContenido(int idContenido) {
        String whereClause = TableContenidos.Contenidos._ID + " = ?";
        String[] whereArgs = {String.valueOf(idContenido)};

        return baseDatos.delete(TableContenidos.Contenidos.NOMBRE_TABLA, whereClause, whereArgs);
    }
    public void eliminarTodosLosContenidos() {
        baseDatos.delete(TableContenidos.Contenidos.NOMBRE_TABLA, null, null);
    }

}
