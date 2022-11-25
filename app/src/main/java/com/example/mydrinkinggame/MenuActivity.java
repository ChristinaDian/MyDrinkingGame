package com.example.mydrinkinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button cardGameB;
    Button spinBottleB;
    Button spinWheelB;
    Button kingsCupB;
    User user;
    public static SQLiteHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        db=new SQLiteHelper(this);

        cardGameB = findViewById(R.id.cardGamebutton);
        cardGameB.setOnClickListener(onClickListener);

        kingsCupB=findViewById(R.id.kingsCupButton);
        kingsCupB.setOnClickListener(onClickListener);

        spinBottleB=findViewById(R.id.spinTheBottleButton);
        spinBottleB.setOnClickListener(onClickListener);

/*        spinWheelB=findViewById(R.id.spinTheWheelButton);
        spinWheelB.setOnClickListener(onClickListener);*/


    }
        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=null;
                switch (v.getId()) {
                    case R.id.cardGamebutton:
                        intent = new Intent(MenuActivity.this, CardGameActivity.class);
                    break;
                    case R.id.kingsCupButton:
                        intent = new Intent(MenuActivity.this, KingsCupActivity.class);
                        break;
                    case R.id.spinTheBottleButton:
                        intent = new Intent(MenuActivity.this, SpinTheBottle.class);
                        break;
/*                    case R.id.spinTheWheelButton:
                        intent = new Intent(MenuActivity.this, SpinTheWheeel.class);
                        break;*/
                }
                startActivity(intent);
            }
        };
    }
