package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void AddNewItem(View view){
            Intent intent = new Intent(getApplicationContext(),NewItem.class);
            startActivity(intent);
    }
    public void UpdateItem(View view){
        Intent intent = new Intent(getApplicationContext(), FoodList1.class);
        startActivity(intent);
    }
    public void allOrder(View view){
        Intent intent = new Intent(getApplicationContext(), AdminOrders.class);
        startActivity(intent);
    }
}