package com.example.geoexplora;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MemoramaActivity extends AppCompatActivity {
    private TextView tv_p1;
    private ImageView[] imageViews = new ImageView[12];
    private Integer[] cardsArray = {101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206};
    private int m_cangrejo, m_elefante, m_hipopotamo, m_jirafa, m_koala, m_mono,
            m_cangrejo_2, m_elefante_2, m_hipopotamo_2, m_jirafa_2, m_koala_2, m_mono_2;
    private int firstCard, secondCard;
    private int clickedFirst, clickedSecond;
    private int cardNumber = 1;
    private int turn = 1;
    private int playerPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_memorama);
        initializeViews();
        frontOfCardsResources();
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

    private void frontOfCardsResources() {
        m_cangrejo = R.drawable.memorama_cangrejo;
        m_elefante = R.drawable.memorama_elefante;
        m_hipopotamo = R.drawable.memorama_hipopotamo;
        m_jirafa = R.drawable.memorama_jirafa;
        m_koala = R.drawable.memorama_koala;
        m_mono = R.drawable.memorama_mono;
        m_cangrejo_2 = R.drawable.memorama_cangrejo_2;
        m_elefante_2 = R.drawable.memorama_elefante_2;
        m_hipopotamo_2 = R.drawable.memorama_hipopotamo_2;
        m_jirafa_2 = R.drawable.memorama_jirafa_2;
        m_koala_2 = R.drawable.memorama_koala_2;
        m_mono_2 = R.drawable.memorama_mono_2;
    }

    private void setOnClickListeners() {
        for (final ImageView iv : imageViews) {
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int theCard = Integer.parseInt((String) v.getTag());
                    doStuff(iv, theCard);
                }
            });
        }
    }

    private void doStuff(ImageView iv, int card) {
        int cardResource = mapCardToResource(cardsArray[card]);
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

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            }, 1000);
        }
    }

    private int mapCardToResource(int card) {
        int[] cardResources = {
                m_cangrejo, m_elefante, m_hipopotamo, m_jirafa, m_koala, m_mono,
                m_cangrejo_2, m_elefante_2, m_hipopotamo_2, m_jirafa_2, m_koala_2, m_mono_2
        };
        return cardResources[card % 100];
    }

    private void disableAllImageViews() {
        for (ImageView iv : imageViews) {
            iv.setEnabled(false);
        }
    }

    private void calculate() {
        if (firstCard == secondCard) {
            for (int clicked : Arrays.asList(clickedFirst, clickedSecond)) {
                imageViews[clicked].setVisibility(View.INVISIBLE);
            }

            if (turn == 1) {
                playerPoints++;
                tv_p1.setText("Puntos: " + playerPoints);
            }
        } else {
            for (ImageView iv : imageViews) {
                iv.setImageResource(R.drawable.memorama_back);
            }
        }

        enableAllImageViews();
        checkEnd();
    }

    private void enableAllImageViews() {
        for (ImageView iv : imageViews) {
            iv.setEnabled(true);
        }
    }

    private void checkEnd() {
        if (Arrays.stream(imageViews).allMatch(iv -> iv.getVisibility() == View.INVISIBLE)) {
            new AlertDialog.Builder(this)
                    .setMessage("Game Over \nPuntos: " + playerPoints)
                    .setCancelable(false)
                    .setPositiveButton("New", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), MemoramaActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .show();
        }
    }
}
