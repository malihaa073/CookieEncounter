package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
   Button foodlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        foodlist = findViewById(R.id.foodList);
        ImageSlider imageSlider =findViewById(R.id.slider);

        List<SlideModel> slideModels =new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.slide2));
        slideModels.add(new SlideModel(R.drawable.slide1));
        slideModels.add(new SlideModel(R.drawable.slide3));
        slideModels.add(new SlideModel(R.drawable.slide5));
        imageSlider.setImageList(slideModels,true);


        Intent i = getIntent();
        String usrnm = i.getStringExtra("username");

        foodlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,FoodList.class);
                intent.putExtra("usrnm", usrnm);
                startActivity(intent);

            }
        });



        //Initialize and assign variable
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottomNav);
        //Set Profile Selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        // Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext()
                                , PreviousOrder.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext()
                                , SearchActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext()
                                , FinalCart.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.dashboard:
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext()
                                , LogoutActivity.class));
                        overridePendingTransition(0, 0);

                        return true;

                }
                return false;
            }
        });



    }
/*
    public void MainCart(View v) {
        Intent intent = new Intent(DashboardActivity.this,FinalCart.class);
        startActivity(intent);
    }

    public void Orders(View v) {
        Intent intent = new Intent(DashboardActivity.this,PreviousOrder.class);
        startActivity(intent);
    }
*/



}