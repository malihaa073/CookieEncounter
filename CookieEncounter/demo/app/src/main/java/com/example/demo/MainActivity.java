package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity  {
 Button callSignUp,callSignIn;
 com.google.android.material.textfield.TextInputEditText username,password;
    public static String usrnm="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callSignUp = findViewById(R.id.callSignUp);
        callSignIn =findViewById(R.id.callSignIn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);



        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
                finish();


            }
        });


       /* callSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
              startActivity(intent);
                finish();


            }
        });

*/


    }

  private Boolean validateUsername(){
        String val =username.getText().toString();
        if(val.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }else {
            username.setError(null);
         //   username.setErrorEnabled(false);
            return true;
        }
  }

  private Boolean validatePassword(){
        String val =password.getText().toString();
        if(val.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        } else{
            password.setError(null);
         //   password.setErrorEnabled(false);
            return true;
        }
  }

  public void loginUser(View view){
        if(!validateUsername() || !validatePassword()){
            return;
        }
        else{
            isUser();
        }
  }

    public void AdminLogIn(View view){
        Intent intent = new Intent(getApplicationContext(),AdminSignIn.class);
        startActivity(intent);
    }

  private void isUser(){

       final String userEnteredUsername = username.getText().toString().trim();
       final String userEnteredPassword = password.getText().toString().trim();
        usrnm = userEnteredUsername;

      DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
      Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

      checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull  DataSnapshot snapshot) {
            if(snapshot.exists()){
                username.setError(null);
             //   username.setErrorEnabled(false);
                String passwordFromDB =snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                if(passwordFromDB.equals(userEnteredPassword)){

                    username.setError(null);
                 //   username.setErrorEnabled(false);


                    String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                    String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                    String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                   Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);

                    FirebaseDatabase.getInstance().getReference("current").setValue(usernameFromDB);

                    intent.putExtra("username",usernameFromDB);
                    intent.putExtra("email",emailFromDB);
                    intent.putExtra("phoneNo",phoneNoFromDB);
                    intent.putExtra("password",passwordFromDB);

                    startActivity(intent);
                }
                else{
                    password.setError("Wrong Password ");
                    password.requestFocus();
                }
            }
            else {
                username.setError("No such User exist");
                username.requestFocus();
            }

          }

          @Override
          public void onCancelled(@NonNull  DatabaseError error) {

          }
      });

    }

}