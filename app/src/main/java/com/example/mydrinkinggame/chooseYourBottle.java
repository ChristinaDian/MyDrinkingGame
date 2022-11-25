package com.example.mydrinkinggame;

import static com.example.mydrinkinggame.LoginActivity.USERNAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class chooseYourBottle extends AppCompatActivity {
    ImageView absolutIV;
    ImageView smirnoffIV;
    ImageView bombayIV;
    ImageView yagerIV;
    ImageView jackIV;
    ImageView beerIV;
    ImageView waterIV;
    ImageView colaIV;
    ImageView redBullIV;
    SQLiteHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_your_bottle);

        db=new SQLiteHelper(this);

        absolutIV=findViewById(R.id.absolutImageView);
        absolutIV.setOnClickListener(onClickListener);

        smirnoffIV=findViewById(R.id.smirnoffImageView);
        smirnoffIV.setOnClickListener(onClickListener);

        bombayIV=findViewById(R.id.bombayImageView);
        bombayIV.setOnClickListener(onClickListener);

        yagerIV=findViewById(R.id.yagerImageView);
        yagerIV.setOnClickListener(onClickListener);

        jackIV=findViewById(R.id.jackImageView);
        jackIV.setOnClickListener(onClickListener);

        beerIV=findViewById(R.id.beerImageView);
        beerIV.setOnClickListener(onClickListener);

        waterIV=findViewById(R.id.waterImageView);
        waterIV.setOnClickListener(onClickListener);

        colaIV=findViewById(R.id.colaImageView);
        colaIV.setOnClickListener(onClickListener);

        redBullIV=findViewById(R.id.redBullImageView);
        redBullIV.setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(chooseYourBottle.this, SpinTheBottle.class);
            switch (v.getId()) {
                case R.id.absolutImageView:
                    db.setBottle(USERNAME,"absolut");
                    break;
                case R.id.smirnoffImageView:
                    db.setBottle(USERNAME,"smirnoff");
                    break;
                case R.id.bombayImageView:
                    db.setBottle(USERNAME,"bombay");
                    break;
                case R.id.yagerImageView:
                    db.setBottle(USERNAME,"yager");
                    break;
                case R.id.jackImageView:
                    db.setBottle(USERNAME,"jack");
                    break;
                case R.id.beerImageView:
                    db.setBottle(USERNAME,"beer");
                    break;
                case R.id.waterImageView:
                    db.setBottle(USERNAME,"water");
                    break;
                case R.id.colaImageView:
                    db.setBottle(USERNAME,"cola");
                    break;
                case R.id.redBullImageView:
                    db.setBottle(USERNAME,"redBull");
                    break;
            }
            startActivity(intent);

        }
    };
}