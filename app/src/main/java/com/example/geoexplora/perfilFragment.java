package com.example.geoexplora;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class perfilFragment extends Fragment{
    private ImageView fondoImageView;
    private int[] fondos = {R.drawable.fondo1, R.drawable.fondo2, R.drawable.fondo3, R.drawable.fondo4, R.drawable.fondo5};
    private int fondoIndex = 0;
    private int NUM_MAX = 4;
    private static final String PREFS_KEY = "FondoPreferido";
    private String nombreUsuarioActual;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfil, container, false);

        fondoImageView = view.findViewById(R.id.fondoPantalla);

        //Guarda la imagen que  se alla elgido con el boton de cambiar fondo
        SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        fondoIndex = preferences.getInt("background_index", 0);
        fondoImageView.setImageResource(fondos[fondoIndex]);

        EditText editTextNombreUsuario = view.findViewById(R.id.editTextNombreUsuario);
        Button btnGuardarNombre = view.findViewById(R.id.btnGuardarNombre);

        // Manejar clic del botón para guardar el nombre
        btnGuardarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUsuario = editTextNombreUsuario.getText().toString().trim();

                if (!nombreUsuario.isEmpty()) {
                    // Guardar el nombre en la base de datos
                    guardarNombreEnBD(requireContext(), nombreUsuario);
                    TextView nombreUsuarioTextView = view.findViewById(R.id.nombreUsuario);
                    nombreUsuarioTextView.setText(nombreUsuario);
                } else {
                    // Manejar el caso en el que el campo esté vacío
                    Toast.makeText(requireContext(), "Ingrese un nombre de usuario válido", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button btnCambioFondo = view.findViewById(R.id.btnFondo);
        btnCambioFondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fondoIndex = (fondoIndex + 1) % fondos.length;
                if(fondoIndex > NUM_MAX)
                    fondoIndex = 0;
                // Guardar el índice del fondo seleccionado
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("background_index", fondoIndex);
                editor.apply();
                // Cambia el fondo del ImageView al siguiente en el array
                fondoImageView.setImageResource(fondos[fondoIndex]);
            }
        });

        return view;
    }
    private boolean nombreUsuarioExistente(SQLiteDatabase db, String nombreUsuario) {
        String query = "SELECT * FROM " + TableUsuario.Usuarios.NOMBRE_TABLA +
                " WHERE " + TableUsuario.Usuarios.COLUMNA_ALIAS + " = ?";
        String[] selectionArgs = {nombreUsuario};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        boolean existe = cursor.moveToFirst();
        cursor.close();

        return existe;
    }
    private void guardarNombreEnBD(Context context, String nombreUsuario) {
        // Obtener una instancia de la base de datos para escribir
        SQLiteDatabase db = new DataBaseGeoExplora(context).getWritableDatabase();

        // Verificar si el nombre ya existe
        if (nombreUsuarioExistente(db, nombreUsuario)) {
            // El nombre ya existe, puedes realizar una consulta para obtener el puntaje
            int puntaje = obtenerPuntaje(db, nombreUsuario);

            // Actualizar el TextView con el nuevo puntaje
            TextView textViewWinStreak = requireActivity().findViewById(R.id.textViewWinStreak);
            textViewWinStreak.setText("Total de puntos: " + puntaje);

            Toast.makeText(context, "Bienvenido de nuevo, " + nombreUsuario + "! Puntuación: " + puntaje, Toast.LENGTH_SHORT).show();
        } else {
            // El nombre no existe, entonces podemos guardarlo
            ContentValues values = new ContentValues();
            values.put(TableUsuario.Usuarios.COLUMNA_ALIAS, nombreUsuario);
            values.put(TableUsuario.Usuarios.COLUMNA_PUNTAJE, 0); // Otra columna, si es necesario

            // Insertar el nuevo nombre en la base de datos
            db.insert(TableUsuario.Usuarios.NOMBRE_TABLA, null, values);

            // Actualizar el TextView con el puntaje inicial (0)
            TextView textViewWinStreak = requireActivity().findViewById(R.id.textViewWinStreak);
            textViewWinStreak.setText("Total de puntos: 0");

            Toast.makeText(context, "Nombre de usuario guardado", Toast.LENGTH_SHORT).show();
        }

        // Cerrar la base de datos después de usarla
        db.close();
    }
    private int obtenerPuntaje(SQLiteDatabase db, String nombreUsuario) {
        // Define la proyección que contiene solo la columna de puntaje
        String[] proyeccion = {TableUsuario.Usuarios.COLUMNA_PUNTAJE};

        // Especifica la cláusula WHERE para buscar el nombre de usuario
        String seleccion = TableUsuario.Usuarios.COLUMNA_ALIAS + " = ?";
        String[] seleccionArgs = {nombreUsuario};

        // Realiza la consulta
        Cursor cursor = db.query(
                TableUsuario.Usuarios.NOMBRE_TABLA,  // Nombre de la tabla
                proyeccion,                         // Columnas que deseas obtener
                seleccion,                          // Cláusula WHERE
                seleccionArgs,                      // Valores de la cláusula WHERE
                null,                               // No agrupar las filas
                null,                               // No filtrar por grupos de filas
                null                                // No ordenar el resultado
        );

        int puntaje = 0;

        // Verifica si hay algún resultado y obtén el puntaje
        if (cursor != null && cursor.moveToFirst()) {
            int indicePuntaje = cursor.getColumnIndex(TableUsuario.Usuarios.COLUMNA_PUNTAJE);
            puntaje = cursor.getInt(indicePuntaje);
            cursor.close();
        }

        return puntaje;
    }

}





