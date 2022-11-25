package com.example.mydrinkinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardGameActivity extends AppCompatActivity {

    TextView resultTV;
    Button nextB;
    FloatingActionButton addB;
    SQLiteHelper db;


    int i=0;

    //To Do : change to DB
   ArrayList<String> cardsContent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game);

        db=new SQLiteHelper(this);
        addB=findViewById(R.id.addFloatButton);
        nextB =findViewById(R.id.nextButton);
        resultTV=findViewById(R.id.cardResultTextView);

        cardsContent= new ArrayList<>(db.getCards());
        //cardsContent.addAll(db.getCards());

       Collections.shuffle(cardsContent, new Random());
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    resultTV.setText(cardsContent.get(i));
              i++;
              if(i>= cardsContent.size())
             {
                   i=0;
                   Collections.shuffle(cardsContent, new Random());
               }
            }
        });
        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MyChallangesActivity.class);
                startActivity(intent);
            }
        });
    }
}