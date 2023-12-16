package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NewItem extends AppCompatActivity {
    public int x = 0;
    public String tst = "";
    com.google.android.material.textfield.TextInputEditText Image, Name, Price, phn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        Image = findViewById(R.id.link);
        Name = findViewById(R.id.food_name);
        Price = findViewById(R.id.food_price);


        FirebaseDatabase.getInstance().getReference("item2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                tst = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }

    public boolean AddItem(View view){
        int flag = 0;
        String fimage = Image.getText().toString();
        String fname = Name.getText().toString();
        String fprice = Price.getText().toString();


        if(fimage.isEmpty()){
            Image.setError("Field cannot be empty");
            return false;
        }
        if(fname.isEmpty()){
            Name.setError("Field cannot be empty");
            return false;
        }
        if(fprice.isEmpty()){
            Price.setError("Field cannot be empty");
            return false;
        }


        final int[] cnt = {0};
        x = Integer.parseInt(tst);
        x++;
        String text = String.valueOf(x);
        System.out.println(x);
        System.out.println(text);
        FirebaseDatabase.getInstance().getReference("item2").setValue(text);


        Food_Model f = new Food_Model(fimage, fname, fprice);
        FirebaseDatabase.getInstance().getReference().child("FoodName").child(text).setValue(f);
        Toast.makeText(NewItem.this,"successfull",Toast.LENGTH_SHORT).show();
        finish();
        return true;
    }
}