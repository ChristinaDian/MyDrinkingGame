package com.example.mydrinkinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usernameET;
    EditText passwordET;
    SQLiteHelper db;
    Button loginB;
    TextView registerB;
    public static String USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET =findViewById(R.id.editTextTextEmailAddress);
        passwordET=findViewById(R.id.editTextTextPassword);
        loginB=findViewById(R.id.loginButton);
        registerB=findViewById(R.id.registerTextView);
        db=new SQLiteHelper(this);

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USERNAME= usernameET.getText().toString();
                String pass=passwordET.getText().toString();

                if (USERNAME.equals("")||pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter all the fields",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkUserPass = db.checkUsernamePassword(USERNAME, pass);
                    if (checkUserPass) {
                        Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}