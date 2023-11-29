package com.example.geoexplora;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class OperacionesUsuarios {
    private SQLiteDatabase baseDatos;
    private DataBaseGeoExplora dbHelper;

    public OperacionesUsuarios(Context context) {
        dbHelper = new DataBaseGeoExplora(context);
    }

    public void abrir() throws SQLException {
        baseDatos = dbHelper.getWritableDatabase();
    }

    public void cerrar() {
        dbHelper.close();
    }
    public long insertarUsuario(String alias, int puntaje) {
        ContentValues valores = new ContentValues();
        valores.put(TableUsuario.Usuarios.COLUMNA_ALIAS, alias);
        valores.put(TableUsuario.Usuarios.COLUMNA_PUNTAJE, puntaje);

        return baseDatos.insert(TableUsuario.Usuarios.NOMBRE_TABLA, null, valores);
    }

    public int actualizarPuntajeUsuario(int idUsuario, int nuevoPuntaje) {
        ContentValues valores = new ContentValues();
        valores.put(TableUsuario.Usuarios.COLUMNA_PUNTAJE, nuevoPuntaje);

        String whereClause = TableUsuario.Usuarios._ID + " = ?";
        String[] whereArgs = {String.valueOf(idUsuario)};

        return baseDatos.update(TableUsuario.Usuarios.NOMBRE_TABLA, valores, whereClause, whereArgs);
    }
    public int eliminarUsuario(int idUsuario) {
        String whereClause = TableUsuario.Usuarios._ID + " = ?";
        String[] whereArgs = {String.valueOf(idUsuario)};

        return baseDatos.delete(TableUsuario.Usuarios.NOMBRE_TABLA, whereClause, whereArgs);
    }
}
