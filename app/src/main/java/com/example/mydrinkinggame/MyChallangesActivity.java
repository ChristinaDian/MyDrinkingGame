package com.example.mydrinkinggame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyChallangesActivity extends AppCompatActivity {

    ArrayList<String> arrayList;
    RecyclerView recyclerView;
    FloatingActionButton actionButton;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_challanges);

        recyclerView=findViewById(R.id.recycler_view);
        actionButton=findViewById(R.id.addButton);
        db=new SQLiteHelper(this);
        displayCards();
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void displayCards() {
        arrayList =new ArrayList<>(db.getCards());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CardsAdapter adapter = new CardsAdapter(getApplicationContext(), this, arrayList);
        recyclerView.setAdapter(adapter);

    }
    public void showDialog() {
        final EditText content;
        Button submit;
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.dialog);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        content = dialog.findViewById(R.id.content);
        submit = dialog.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                if (content.getText().toString().isEmpty()) {
                    content.setError("Please Enter your challenge");
                }else {
                    db.insertCard(content.getText().toString());
                    dialog.cancel();
                    displayCards();
                }
            }
        });
    }
}