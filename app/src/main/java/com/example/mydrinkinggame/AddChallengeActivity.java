package com.example.mydrinkinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddChallengeActivity extends AppCompatActivity {

    EditText challengeET;
    Button addB;
    Button backB;
    SQLiteHelper db;
    String card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenge);

        challengeET=findViewById(R.id.challengeEditText);
        addB=findViewById(R.id.addChalButton);
        backB=findViewById(R.id.backButton);
        db= new SQLiteHelper(this);

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card=challengeET.getText().toString();
                if(card.equals("")){
                    Toast.makeText(AddChallengeActivity.this, "Please enter your challenge", Toast.LENGTH_SHORT).show();
                }else{
                    db.insertCard(card);
                    challengeET.setText("");
                    Toast.makeText(AddChallengeActivity.this, "Challenge added successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardGameActivity.class);
                startActivity(intent);
            }
        });

    }
}