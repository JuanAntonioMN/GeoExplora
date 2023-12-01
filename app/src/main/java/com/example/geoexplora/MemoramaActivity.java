package com.example.geoexplora;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;

public class MemoramaActivity extends AppCompatActivity {
    private TextView tv_p1;
    private ImageView[] imageViews = new ImageView[12];
    private Integer[] cardsArray = {101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206};
    private int firstCard, secondCard;
    private int clickedFirst, clickedSecond;
    private int cardNumber = 1;
    private int turn = 1;
    private int playerPoints = 0;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_memorama);
        initializeViews();
        try {
            // Intenta inicializar el MediaPlayer
            mediaPlayer = MediaPlayer.create(this, R.raw.cancion);
            mediaPlayer.setLooping(true);  // Esto establece la reproducción en bucle
            mediaPlayer.start();
        } catch (Exception e) {
            // Captura cualquier excepción y muestra el rastreo en la consola
            e.printStackTrace();
        }
        findViewById(android.R.id.content).post(() -> {
            Collections.shuffle(Arrays.asList(cardsArray)); // Baraja las cartas
            // Asigna las imágenes de memorama_back a todas las ImageView
            // Luego, asigna las imágenes barajadas a las ImageView
            for (int i = 0; i < imageViews.length; i++) {
                int card = cardsArray[i];
                int cardResource = mapCardToResource(card);
                imageViews[i].setImageResource(cardResource);
                imageViews[i].invalidate(); // Forzar la redibujación
            }
            Arrays.asList(imageViews).forEach(iv -> iv.setImageResource(R.drawable.memorama_back));
        });
        setOnClickListeners();
    }

    private void initializeViews() {
        tv_p1 = findViewById(R.id.jugador);
        int[] imageIds = {
                R.id.iv_11, R.id.iv_12, R.id.iv_13, R.id.iv_14,
                R.id.iv_21, R.id.iv_22, R.id.iv_23, R.id.iv_24,
                R.id.iv_31, R.id.iv_32, R.id.iv_33, R.id.iv_34
        };

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = findViewById(imageIds[i]);
            imageViews[i].setTag(String.valueOf(i)); // Set tag as the position in the array
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Liberar recursos del MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    private void setOnClickListeners() {
        for (final ImageView iv : imageViews) {
            iv.setOnClickListener(v -> {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv, theCard);
            });
        }
    }

    private void doStuff(ImageView iv, int card) {
        int cardResource = mapCardToResource(cardsArray[card] % 100);
        iv.setImageResource(cardResource);

        if (cardNumber == 1) {
            firstCard = cardsArray[card] % 100;
            cardNumber = 2;
            clickedFirst = card;
            iv.setEnabled(false);
        } else if (cardNumber == 2) {
            secondCard = cardsArray[card] % 100;
            cardNumber = 1;
            clickedSecond = card;

            disableAllImageViews();

            new Handler().postDelayed(() -> calculate(), 500);
        }
    }

    private int mapCardToResource(int card) {
        int[] cardResources = {
                R.drawable.memorama_cangrejo, R.drawable.memorama_elefante, R.drawable.memorama_hipopotamo,
                R.drawable.memorama_jirafa, R.drawable.memorama_koala, R.drawable.memorama_mono,
                R.drawable.memorama_cangrejo_2, R.drawable.memorama_elefante_2, R.drawable.memorama_hipopotamo_2,
                R.drawable.memorama_jirafa_2, R.drawable.memorama_koala_2, R.drawable.memorama_mono_2
        };
        return cardResources[card % 100];
    }

    private void disableAllImageViews() {
        Arrays.asList(imageViews).forEach(iv -> iv.setEnabled(false));
    }

    private void calculate() {
        if (firstCard == secondCard) {
            Arrays.asList(clickedFirst, clickedSecond).forEach(clicked ->
                    imageViews[clicked].setVisibility(View.INVISIBLE));

            if (turn == 1) {
                playerPoints++;
                tv_p1.setText("Puntos: " + playerPoints);
            }
        } else {
            Arrays.asList(imageViews).forEach(iv -> iv.setImageResource(R.drawable.memorama_back));
        }

        enableAllImageViews();
        checkEnd();
    }

    private void enableAllImageViews() {
        Arrays.asList(imageViews).forEach(iv -> iv.setEnabled(true));
    }

    private void checkEnd() {
        if (Arrays.stream(imageViews).allMatch(iv -> iv.getVisibility() == View.INVISIBLE)) {
            new AlertDialog.Builder(this)
                    .setMessage("Genial, terminaste el juego! \nPuntos: " + playerPoints)
                    .setCancelable(false)
                    .setPositiveButton("Jugar de nuevo", (dialogInterface, i) -> {
                        Intent intent = new Intent(getApplicationContext(), MemoramaActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("Salir", (dialogInterface, i) -> finish())
                    .show();
        }
    }
}
