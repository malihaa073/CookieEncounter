package com.example.demo;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

public class PreviousOrder extends AppCompatActivity {

    String user = "";
    private ListView listview;
    DatabaseReference ref;
    private List<FinalCartItem> fnlist;
    private CustomAdapter2 customAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_order);

        ref = FirebaseDatabase.getInstance().getReference("Order");
        fnlist = new ArrayList<>();
        customAdapter2 = new CustomAdapter2(PreviousOrder.this, fnlist);

        listview = findViewById(R.id.listviewId1);

        FirebaseDatabase.getInstance().getReference("current").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                user = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }


    @Override
    protected void onStart() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                fnlist.clear();
                for(DataSnapshot dns:snapshot.getChildren()){
                    FinalCartItem fn = dns.getValue(FinalCartItem.class);

                    if(fn.usrnm.equals(user)) fnlist.add(fn);
                }
                listview.setAdapter(customAdapter2);

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        super.onStart();
    }

}