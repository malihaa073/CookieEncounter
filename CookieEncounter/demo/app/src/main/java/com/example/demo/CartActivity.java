package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CartActivity extends AppCompatActivity {
    ImageView plus,minus;
    TextView food_txt,Name,Price,total_price;
    int count =0;

    String price1,name1;

    int prev_position = RecyclerAdapter.pos + 1;
    int position_cnt = 0;

    public String Name_cart = "";
    public String Price_cart = "";
    public String test = "";
    DatabaseReference databaseReference,addcart;
    FirebaseDatabase firebaseDatabase;


    String final_pr;

    int mytotalp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);

        food_txt = findViewById(R.id.food_txt);

        Name = findViewById(R.id.name);
        Price = findViewById(R.id.price);

        total_price=findViewById(R.id.totalPrice);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        addcart = FirebaseDatabase.getInstance().getReference().child("AddToCart");
        gotofunction();

    }


    public void minus(View view)
    {
        count--;



        int p = Integer.parseInt(price1);

        int myprice =mytotalp;

        myprice = myprice -p;

        int res = myprice;
        String total_rs = Integer.toString(res);

        final_pr = total_rs;
        String ctr = Integer.toString(count);

        total_price.setText(total_rs);

        food_txt.setText(ctr);
    }
    public void plus(View view)
    {
        count++;

        int p = Integer.parseInt(price1);
        int res = p*count;
        mytotalp = res;

        String total_rs = Integer.toString(res);
        String ctr = Integer.toString(count);

        final_pr=total_rs;
        total_price.setText(total_rs);

        food_txt.setText(ctr);
    }

    public void AddtoCart(View view)
    {
        System.out.println("here: " + MainActivity.usrnm);
        FirebaseDatabase.getInstance().getReference("current").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                test = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        String text = String.valueOf(prev_position);
        AddCart1 cart  =new AddCart1(Name_cart,final_pr, test, text, food_txt.getText().toString());
        addcart.push().setValue(cart);

        Toast.makeText(CartActivity.this,"successfull",Toast.LENGTH_SHORT).show();
        finish();

    }

    public void ShowCart(View view)
    {

        //finish();
        Intent intent = new Intent(CartActivity.this, FinalCart.class);
        finish();
        startActivity(intent);
    }


    public void gotofunction()
    {
       // String pre_value = Integer.toString(prev_position);

        String text = String.valueOf(prev_position);

        Query query = databaseReference.child("FoodName").child(text);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Food_Model model = new Food_Model();

                test = MainActivity.usrnm;
               Name_cart = snapshot.child("name").getValue().toString();
               Price_cart = snapshot.child("price").getValue().toString();



                Name.setText(Name_cart);
                Price.setText(Price_cart);

                price1=Price_cart;
                name1=Name_cart;
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }

