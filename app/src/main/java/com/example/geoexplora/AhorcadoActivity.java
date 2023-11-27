package com.example.geoexplora;

import android.os.Bundle;
import android.os.Handler;
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
    private TextView animalInfoTextView;
    private String word;
    private String hiddenWord;
    private List<String> guesses;
    private int bodyParts;
    private Handler handler = new Handler();
    private int infoIndex = 0;
    private static final int INFO_INTERVAL = 5000;
    private int currentAnimalIndex = 0;
    String[] animalsArray = {
            "Jaguar", "Quetzal", "Venado", "Lobo mexicano", "Tlacuache",
            "Nutria", "Ajolote", "Teporingo", "Tapir", "Ocelote"};
    String[][] datosAnimales = {
            {"Jaguar", "Es el tercer felino más grande del mundo.", "Tiene un pelaje amarillo dorado con manchas negras en forma de rosetas.", "Se encuentra en las selvas de América.", "Es un depredador solitario y ágil.", "A menudo se asocia con la fuerza y la valentía en la cultura indígena."},
            {"Quetzal", "Es un ave de colores brillantes que se encuentra en América Central.", "Su nombre proviene de la palabra náhuatl que significa 'precioso' o 'hermoso'.", "Tiene un papel importante en las antiguas mitologías mesoamericanas.", "Su plumaje verde y rojo es muy llamativo.", "Es una especie simbólica y emblemática de la región."},
            {"Venado", "Es un herbívoro que se encuentra en bosques y áreas arboladas.", "Los machos suelen tener cuernos que arrojan y regeneran cada año.", "Son ágiles saltadores y corredores.", "A menudo son cazados por su carne y cuernos.", "Son considerados un símbolo de la naturaleza y la espiritualidad en muchas culturas indígenas."},
            {"Lobo mexicano", "Es una subespecie del lobo gris.", "Fueron declarados extintos en la naturaleza en 1980.", "Se han realizado esfuerzos de reintroducción para salvar a la especie.", "Son conocidos por ser depredadores sociales que cazan en manadas.", "Tienen un pelaje denso y colores que varían de gris a marrón."},
            {"Tlacuache", "Es el único marsupial que se encuentra en América del Norte.", "Son conocidos por su capacidad para resistir venenos de serpientes.", "Son criaturas nocturnas que se alimentan de una variedad de alimentos.", "Tienen una cola prensil que les ayuda a trepar y agarrarse a las ramas.", "Son considerados beneficiosos para el ecosistema, ya que se alimentan de insectos y roedores."},
            {"Nutria", "Es un mamífero semiacuático que se encuentra en ríos y lagos.", "Tienen un pelaje espeso e impermeable que les ayuda a mantenerse secos en el agua.", "Son conocidas por su agilidad y habilidad para nadar.", "Se alimentan principalmente de peces y crustáceos.", "Sus madrigueras son llamadas 'madrigueras de nutria'."},
            {"Ajolote", "Es una especie de salamandra que es única en México.", "Tiene la capacidad de regenerar extremidades, corazón, pulmones e incluso partes del cerebro.", "Es conocido por su apariencia extraña y su aspecto parecido al de un monstruo acuático.", "Se encuentra en cuerpos de agua dulce, especialmente en los lagos de México.", "Es un animal popular en la investigación científica debido a sus propiedades regenerativas."},
            {"Teporingo", "También conocido como conejo zacatuche, es uno de los conejos más pequeños del mundo.", "Se encuentra en los bosques de oyamel en México.", "Está en peligro crítico de extinción debido a la pérdida de hábitat.", "Tiene orejas pequeñas y una cola corta.", "Es una especie endémica de México y se encuentra solo en ciertas regiones."},
            {"Tapir", "Son animales herbívoros que pueden llegar a pesar hasta 300 kg.", "Tienen una trompa prensil que utilizan para agarrar hojas y frutas.", "Son conocidos por ser solitarios y retraídos.", "Se encuentran en selvas y bosques tropicales de América Central y América del Sur.", "Son importantes para la dispersión de semillas en sus hábitats."},
            {"Ocelote", "Son cazadores sigilosos y se mueven con facilidad entre los árboles y la maleza.", "Su pelaje manchado los hace parecerse a pequeños leopardos.", "Se encuentran en una variedad de hábitats, desde selvas hasta zonas de matorrales.", "Son solitarios y marcan su territorio con orina y rasguños.", "Son conocidos por su visión aguda y su capacidad para cazar en la oscuridad."}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorcado);

        hiddenWordTextView = findViewById(R.id.hiddenWordTextView);
        letterEditText = findViewById(R.id.letterEditText);
        guessButton = findViewById(R.id.guessButton);
        animalInfoTextView = findViewById(R.id.animalInfoTextView);

        initializeGame();
        handler.postDelayed(infoUpdateRunnable, INFO_INTERVAL);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeGuess();
            }
        });
    }

    private void initializeGame() {
        bodyParts = 6;
        // Seleccionar una palabra aleatoria de la lista.
        Random random = new Random();
        int randomAnimalIndex = random.nextInt(animalsArray.length);
        word = animalsArray[randomAnimalIndex].toLowerCase();
        hiddenWord = "";
        for (int i = 0; i < word.length(); i++) {
            hiddenWord += "-";
        }
        guesses = new ArrayList<>();
        currentAnimalIndex = randomAnimalIndex;
        // Obtener la información del animal seleccionado
        String[] infoAnimal = datosAnimales[randomAnimalIndex];
        // Mostrar la información del animal en animalInfoTextView
        displayAnimalInfo(infoAnimal);
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
            Toast.makeText(this, "Número de partes del cuerpo restantes: " + bodyParts, Toast.LENGTH_SHORT).show();
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
    }
    private Runnable infoUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            // Obtener la información del animal seleccionado
            String[] infoAnimal = datosAnimales[currentAnimalIndex];
            // Mostrar la información del animal en animalInfoTextView
            displayAnimalInfo(infoAnimal);
            // Actualizar el índice para el siguiente intervalo
            infoIndex = (infoIndex + 1) % infoAnimal.length;
            // Programar la próxima actualización
            handler.postDelayed(this, INFO_INTERVAL);
        }
    };

    private void displayAnimalInfo(String[] infoAnimal) {
        // Mostrar la información del animal actual
        if(infoIndex != 0){
            String displayedInfo = infoAnimal[infoIndex];
            animalInfoTextView.setText(displayedInfo);
        }
    }
}
