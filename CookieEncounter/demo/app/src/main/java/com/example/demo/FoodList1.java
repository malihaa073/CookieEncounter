package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodList1 extends AppCompatActivity {

    public int x = 0;

    RecyclerView recyclerView1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    private Context context;

    private ArrayList<Food_Model>ModelList;

    private RecyclerAdapter1 recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list1);



        recyclerView1 = findViewById(R.id.recyclerview1);
        recyclerView1.setHasFixedSize(true);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        ModelList = new ArrayList<>();



        updateMydata();


    }

    private void updateMydata() {

        Query query = databaseReference.child("FoodName");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    Food_Model model = new Food_Model();

                    model.setName(snapshot1.child("name").getValue().toString());
                    model.setPrice(snapshot1.child("price").getValue().toString());
                    model.setImage(snapshot1.child("image").getValue().toString());
                    System.out.println(snapshot1.child("image").getValue().toString());
                    System.out.println(snapshot1.child("name").getValue().toString());
                    x++;
                    ModelList.add(model);
                }
                recyclerAdapter = new RecyclerAdapter1(getApplicationContext(),ModelList);
                recyclerView1.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}