package com.example.geoexplora;

import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class AhorcadoActivity extends AppCompatActivity {
    private TextView hiddenWordTextView;
    private EditText letterEditText;
    private Button guessButton;

    private String word;
    private String hiddenWord;
    private List<String> guesses;

    private int bodyParts = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorcado);

        hiddenWordTextView = findViewById(R.id.hiddenWordTextView);
        letterEditText = findViewById(R.id.letterEditText);
        guessButton = findViewById(R.id.guessButton);

        initializeGame();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeGuess();
            }
        });
    }

    private void initializeGame() {
        // Lista de animales
        String[] animalsArray = {
                "Jaguar", "Quetzal", "Venado", "Lobo mexicano", "Tlacuache",
                "Nutria", "Ajolote", "Teporingo", "Tapir", "Ocelote",
                "Zopilote", "Manatí", "Tortuga laúd", "Tigrillo", "Coatí",
                "Águila real", "Tecolote", "Salamandra", "Armadillo", "Vaquita marina",
                "Cacomixtle", "Cocodrilo de pantano", "Pato criollo", "Guacamaya roja", "Camarón de río",
                "Cuitlacoche", "Pato selvático", "Pez topo", "Tejón", "Chachalaca",
                "Gavilán de Cooper", "Pez diablo", "Caimán de Morelet", "Chuparrosa", "Chara",
                "Cochipecuá", "Loro yucateco", "Tortuga carey", "Tarántula roja", "Paloma huilota",
                "Vaquita de San Antonio", "Tecolote listado", "Sinsonte", "Puma concolor", "Cuervo de cuello blanco",
                "Mapache norteño", "Ratón orejas blancas", "Murciélago de nariz larga", "Tepezcuintle", "Mariposa monarca"
        };

        // Seleccionar una palabra aleatoria de la lista.
        Random random = new Random();
        word = animalsArray[random.nextInt(animalsArray.length)].toLowerCase();

        hiddenWord = "";
        for (int i = 0; i < word.length(); i++) {
            hiddenWord += "-";
        }

        guesses = new ArrayList<>();

        bodyParts = 6;

        updateUI();
    }

    private void makeGuess() {
        String letter = letterEditText.getText().toString().toLowerCase();

        if (letter.length() != 1 || !Character.isLetter(letter.charAt(0))) {
            Toast.makeText(this, "Ingrese una sola letra válida", Toast.LENGTH_SHORT).show();
            return;
        }

        if (guesses.contains(letter)) {
            Toast.makeText(this, "Ya has ingresado esa letra", Toast.LENGTH_SHORT).show();
            return;
        }

        guesses.add(letter);

        boolean correctGuess = false;

        for (int x = 0; x < word.length(); x++) {
            char currentChar = word.charAt(x);

            if (Character.isWhitespace(currentChar)) {
                // Si el carácter actual es un espacio en blanco, simplemente copiamos el espacio en la palabra adivinada.
                hiddenWord = hiddenWord.substring(0, x) + " " + hiddenWord.substring(x + 1);
                correctGuess = true;
            } else if (letter.charAt(0) == currentChar) {
                // Si el carácter actual es igual a la letra adivinada, lo actualizamos en la palabra adivinada.
                hiddenWord = hiddenWord.substring(0, x) + currentChar + hiddenWord.substring(x + 1);
                correctGuess = true;
            }
        }

        if (!correctGuess) {
            bodyParts--;
        }

        updateUI();

        if (hiddenWord.equalsIgnoreCase(word)) {
            Toast.makeText(this, "¡Bien hecho! Has adivinado la palabra: " + word, Toast.LENGTH_LONG).show();
            initializeGame();
        }

        if (bodyParts == 0) {
            Toast.makeText(this, "¡Oh no! Has perdido. La palabra era: " + word, Toast.LENGTH_LONG).show();
            initializeGame();
        }
    }

    private void updateUI() {
        hiddenWordTextView.setText(hiddenWord);
        letterEditText.getText().clear();
        Toast.makeText(this, "Número de partes del cuerpo restantes: " + bodyParts, Toast.LENGTH_SHORT).show();
    }
}
