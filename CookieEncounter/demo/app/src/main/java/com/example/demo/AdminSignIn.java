package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminSignIn extends AppCompatActivity {

    com.google.android.material.textfield.TextInputEditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_in);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    public void loginUser(View view){
        String name = username.getText().toString();
        String pass = password.getText().toString();

        if(name.equals("5173@aust.edu") && pass.equals("admin")){
            Intent intent = new Intent(getApplicationContext(),Admin.class);
            startActivity(intent);
        }
        else{
            username.setError("Invalid!!!");
        }
    }
}