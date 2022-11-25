package com.example.mydrinkinggame;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KingsCupActivity extends AppCompatActivity {


    private boolean visibility=false;
    ImageView rulesIV;
    ImageView cardsIV;

    TextView startTV;
    FloatingActionButton rulesB;
    private int i=0;
    List<Integer> cardsList = Arrays.asList(
            R.drawable.clubs2, R.drawable.clubs3, R.drawable.clubs4, R.drawable.clubs5, R.drawable.clubs6,
            R.drawable.clubs7,R.drawable.clubs8,R.drawable.clubs9,R.drawable.clubs10,
            R.drawable.clubs_ace, R.drawable.clubs_jack,R.drawable.clubs_queen, R.drawable.clubs_king,

            R.drawable.spades2, R.drawable.spades3, R.drawable.spades4,R.drawable.spades5, R.drawable.spades6,
            R.drawable.spades7, R.drawable.spades8, R.drawable.spades9, R.drawable.spades10,
            R.drawable.spades_ace, R.drawable.spades_jack, R.drawable.spades_queen, R.drawable.spades_king,

            R.drawable.hearts2,R.drawable.hearts3, R.drawable.hearts4, R.drawable.hearts5, R.drawable.hearts6,
            R.drawable.hearts7, R.drawable.hearts8, R.drawable.hearts9, R.drawable.hearts10,
            R.drawable.hearts_ace,R.drawable.hearts_jack,R.drawable.hearts_queen, R.drawable.hearts_king,

            R.drawable.diamonds2, R.drawable.diamonds3, R.drawable.diamonds4, R.drawable.diamonds5,R.drawable.diamonds6,
            R.drawable.diamonds7,R.drawable.diamonds8,R.drawable.diamonds9, R.drawable.diamonds10,
            R.drawable.diamonds_ace,R.drawable.diamonds_jack,R.drawable.diamonds_queen, R.drawable.diamonds_king);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kings_cup);

        Toast.makeText(getApplicationContext(),"Click to start",Toast.LENGTH_LONG).show();

        rulesIV=findViewById(R.id.infoImageView);
        rulesIV.setVisibility(View.INVISIBLE);

        rulesB=findViewById(R.id.rulesFloatingActionButton);
        rulesB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!visibility){
                    rulesIV.setVisibility(View.VISIBLE);
                    visibility=true;
                }else{
                    rulesIV.setVisibility(View.INVISIBLE);
                    visibility=false;
                }

            }
        });


        Collections.shuffle(cardsList, new Random());

        startTV=findViewById(R.id.startTextView);

        cardsIV=findViewById(R.id.cardImageView);
        cardsIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTV.setVisibility(View.INVISIBLE);
                cardsIV.animate()
                        .alpha(0f)
                        .setDuration(500)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) { }
                            @Override
                            public void onAnimationEnd(Animator animator) {
                                cardsIV.setImageResource(cardsList.get(i));
                                cardsIV.animate().alpha(1).setDuration(800);
                            }
                            @Override
                            public void onAnimationCancel(Animator animator) { }
                            @Override
                            public void onAnimationRepeat(Animator animator) { }
                        });
               // cardsIV.setImageResource(cardsList.get(i));
                i++;
                if(i>=cardsList.size())
                {
                    Toast.makeText(getApplicationContext(),"You finished the deck; Deck restarted",Toast.LENGTH_LONG).show();
                    i=0;
                    Collections.shuffle(cardsList, new Random());
                    cardsIV.setImageResource(R.drawable.card_back);
                    startTV.setVisibility(View.VISIBLE);
                }

            }
        });



    }
}