package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UpdateItem extends AppCompatActivity {
    public int x = 0;
    public String tst = "";
    int prev_position = RecyclerAdapter1.pos + 1;
    com.google.android.material.textfield.TextInputEditText Image, Name, Price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        Image = findViewById(R.id.link);
        Name = findViewById(R.id.food_name);
        Price = findViewById(R.id.food_price);

        String text = String.valueOf(prev_position);
        FirebaseDatabase.getInstance().getReference("FoodName").child(text).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Image.setText(snapshot.child("image").getValue().toString());
                Name.setText(snapshot.child("name").getValue().toString());
                Price.setText(snapshot.child("price").getValue().toString());
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public boolean UpdateItemBTN(View view){
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
        String text = String.valueOf(prev_position);
        Food_Model f = new Food_Model(fimage, fname, fprice);
        FirebaseDatabase.getInstance().getReference().child("FoodName").child(text).setValue(f);
        Toast.makeText(UpdateItem.this,"successfull",Toast.LENGTH_SHORT).show();
        finish();
        return true;
    }
}