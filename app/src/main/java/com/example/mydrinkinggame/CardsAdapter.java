package com.example.mydrinkinggame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.viewHolder> {

    Context context;
    Activity activity;
    ArrayList<String> arrayList;
    SQLiteHelper db;

    public CardsAdapter(Context context, Activity activity, ArrayList<String> arrayList){
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
    }


    @Override
    public CardsAdapter.viewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cards_list, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardsAdapter.viewHolder holder, final int position) {
        holder.content.setText(arrayList.get(position));
        db = new SQLiteHelper(context);

        holder.delete.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                //deleting note
                db.deleteCard(arrayList.get(position));
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                //display edit dialog
                showDialog(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView content;
        ImageView delete, edit;
        public viewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
        }
    }
    public void showDialog(final int pos) {

        final EditText content;
        Button submit;
        String oldContent=arrayList.get(pos);
        final Dialog dialog = new Dialog(activity);
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

        content.setText(arrayList.get(pos));

        submit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                if (content.getText().toString().isEmpty()) {
                    content.setError("Please Enter a challenge");
                }else {
                    db.updateCard(content.getText().toString(), oldContent);
                    arrayList.set(pos, content.getText().toString());
                    dialog.cancel();

                    //notify list
                    notifyDataSetChanged();
                }
            }
        });
    }
}
