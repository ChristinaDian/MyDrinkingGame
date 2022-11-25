package com.example.mydrinkinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.mydrinkinggame.LoginActivity.USERNAME;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameET;
    EditText passwordET;
    EditText rePasswordET;
    EditText nameET;
    Button registerB;
    TextView loginB;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET =findViewById(R.id.editTextTextEmailAddress2);
        passwordET=findViewById(R.id.editTextTextPassword2);
        rePasswordET=findViewById(R.id.editTextTextRePassword);
        nameET=findViewById(R.id.editTextTextName);

        registerB=findViewById(R.id.registerButton);
        loginB=findViewById(R.id.loginTextView);
        db=new SQLiteHelper(this);

        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString();
                String pass= passwordET.getText().toString();
                String rePass = rePasswordET.getText().toString();
                String name =nameET.getText().toString();

                if (username.equals("")||pass.equals("")||rePass.equals("")||name.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields",
                    Toast.LENGTH_SHORT).show();
                }else {
                    if (pass.equals(rePass)) {
                        Boolean checkUser = db.checkUsername(username);
                        if (checkUser == false) {
                            db.addUsers(username, pass, name);
                            USERNAME= username;
                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}