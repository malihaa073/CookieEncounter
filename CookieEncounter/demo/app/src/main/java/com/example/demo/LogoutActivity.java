package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogoutActivity extends AppCompatActivity {
   // private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);



                Intent intent=new Intent(LogoutActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
    }
}