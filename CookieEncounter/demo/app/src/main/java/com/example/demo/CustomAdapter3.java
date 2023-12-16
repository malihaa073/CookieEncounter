package com.example.demo;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

public class CustomAdapter3 extends ArrayAdapter<FinalCartItem>{
    private List<FinalCartItem> fnlist;
    private List<FinalCartItem> orig;
    private Activity context;
    public int flag, pr = 0;

    public CustomAdapter3(Activity context, List<FinalCartItem> cnslist) {
        super(context, R.layout.sample_layout3, cnslist);
        this.context = context;
        this.fnlist = cnslist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout3, null, true);

        flag = position;
        FinalCartItem fn = fnlist.get(position);
        TextView t1 = view.findViewById(R.id.fnn);
        TextView t2 = view.findViewById(R.id.fnp);
        TextView t3 = view.findViewById(R.id.fnbn);
        TextView t4 = view.findViewById(R.id.fnphn);
        TextView t5 = view.findViewById(R.id.fnad);
        TextView t6 = view.findViewById(R.id.fndi);

        t1.setText("Items: " + fn.name);
        t2.setText("Total Price: "+ fn.price);
        t3.setText("Customer: " + fn.bname);
        t4.setText("Phone: "+ fn.phn);
        t5.setText("Address: " + fn.location);
        t6.setText("Instruction: "+ fn.instruction);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Previously Ordered", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return fnlist.size();
    }

    @Override
    public FinalCartItem getItem(int position) {
        return fnlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
