package com.example.mydrinkinggame;

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
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

public class MyChallangesActivity extends REST_API {

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content.getText().toString().isEmpty()) {
                    content.setError("Please Enter your challenge");
                }else {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder sb = new StringBuilder();
                            sb.append("{");
                           // sb.append(" \"id\":\" " + "\"52\"" + ",");
                            sb.append("\"content\": \"" + content.getText().toString() + "\"");

                            sb.append("}");
                            final StringBuilder result = new StringBuilder();
                            try {
                                result.append(postData(
                                        content.getText().toString()
                                ));
                                JSONObject jo = (JSONObject) new JSONTokener(
                                        result.toString()
                                ).nextValue();

                                final String message = jo.getString("message");
                                if(message == null){
                                    throw new Exception("SEVER ERROR: " + result.toString());
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                    }
                                });
                            } catch (final Exception e) {
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "ERROR: " + e.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });

                    t.start();
                    db.insertCard(content.getText().toString());
                    dialog.cancel();
                    displayCards();
                }
            }
        });
    }

}