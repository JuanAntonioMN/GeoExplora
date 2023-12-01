package com.example.geoexplora;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseGeoExplora extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "GeoExploraUsuario.db";
    private static final int VERSION_BD = 2;

    public DataBaseGeoExplora(Context context){
        super(context, NOMBRE_BD, null, VERSION_BD);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tablas
        db.execSQL("CREATE TABLE " + TableUsuario.Usuarios.NOMBRE_TABLA + " (" +
                TableUsuario.Usuarios._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TableUsuario.Usuarios.COLUMNA_ALIAS + " TEXT NOT NULL, " +
                TableUsuario.Usuarios.COLUMNA_PUNTAJE + " INTEGER NOT NULL);");

        db.execSQL("CREATE TABLE " + TableContenidos.Contenidos.NOMBRE_TABLA + " (" +
                TableContenidos.Contenidos._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TableContenidos.Contenidos.COLUMNA_TEMA + " TEXT NOT NULL, " +
                TableContenidos.Contenidos.COLUMNA_IMAGEN + " INTEGER NOT NULL, " +
                TableContenidos.Contenidos.COLUMNA_INFORMACION + " TEXT NOT NULL, " +
                TableContenidos.Contenidos.COLUMNA_BANDERA + " INTEGER NOT NULL);");

        db.execSQL("CREATE TABLE " + TableImagenes.Imagenes.NOMBRE_TABLA + " (" +
                TableImagenes.Imagenes._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TableImagenes.Imagenes.COLUMNA_PRINCIPAL + " INTEGER NOT NULL, " +
                TableImagenes.Imagenes.COLUMNA_SECUNDARIA + " INTEGER NOT NULL, " +
                TableImagenes.Imagenes.COLUMNA_DATA1 + " INTEGER NOT NULL, " +
                TableImagenes.Imagenes.COLUMNA_DATA2 + " INTEGER NOT NULL, " +
                TableImagenes.Imagenes.COLUMNA_DATA3 + " INTEGER NOT NULL);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Manejar actualizaciones de la base de datos si es necesario
    }
}
