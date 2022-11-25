package com.example.mydrinkinggame;

import static com.example.mydrinkinggame.LoginActivity.USERNAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class SpinTheBottle extends AppCompatActivity {

    private ImageView bottleIV;
    private Random random = new Random();
    private int lastDir;
    private boolean spinning;
    FloatingActionButton chooseBottleB;
    SQLiteHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_the_bottle);

        db= new SQLiteHelper(this);
        bottleIV=findViewById(R.id.bottle);
        switch (db.getBottle(USERNAME)){
            case "absolut":
                bottleIV.setImageResource(R.drawable.absolut);
                break;
            case "smirnoff":
                bottleIV.setImageResource(R.drawable.smirnoff);
                break;
            case "bombay":
                bottleIV.setImageResource(R.drawable.bombay);
                break;
            case "jack":
                bottleIV.setImageResource(R.drawable.jack);
                break;
            case "yager":
                bottleIV.setImageResource(R.drawable.yager);
                break;
            case "beer":
                bottleIV.setImageResource(R.drawable.beer);
                break;
            case "water":
                bottleIV.setImageResource(R.drawable.water);
                break;
            case "cola":
                bottleIV.setImageResource(R.drawable.coca_cola);
                break;
            case "redBull":
                bottleIV.setImageResource(R.drawable.red_bull);
                break;
        }

        ///bottleIV.setImageResource(R.drawable.);
        chooseBottleB=findViewById(R.id.chooseBottleButton);
        chooseBottleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpinTheBottle.this, chooseYourBottle.class);
                startActivity(intent);
            }
        });
    }

    public void spinBottle(View v){
        if (!spinning) {
            int newDIr = random.nextInt(2800);
            float pivotX = bottleIV.getWidth() / 2;
            float pivotY = bottleIV.getHeight() / 2;

            Animation rotate = new RotateAnimation(lastDir, newDIr, pivotX, pivotY);
            rotate.setDuration(4500);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning=true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning=false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            lastDir = newDIr;
            bottleIV.startAnimation(rotate);
        }
    }
}