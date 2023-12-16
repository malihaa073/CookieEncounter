package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout regUsername , regEmail,regPhoneNo,regPassword;
    Button regToLOginBtn,regBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

      regToLOginBtn =findViewById(R.id.reg_login_btn);
      regUsername = findViewById(R.id.reg_username);
      regEmail =findViewById(R.id.reg_email);
      regPassword =findViewById(R.id.reg_password);
      regBtn =findViewById(R.id.reg_btn);
      regPhoneNo =findViewById(R.id.reg_phoneNo);



      regBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


              rootNode = FirebaseDatabase.getInstance();
              reference =rootNode.getReference("users");

              String username =regUsername.getEditText().getText().toString();
              String email =regEmail.getEditText().getText().toString();
              String phoneNo =regPhoneNo.getEditText().getText().toString();
              String password =regPassword.getEditText().getText().toString();


              UserHelperClass helperClass =new UserHelperClass(username,email,phoneNo,password);
              reference.child(username).setValue(helperClass);
              Intent intent =new Intent(SignUp.this,MainActivity.class);
              startActivity(intent);
              finish();

          }
      });


      regToLOginBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent =new Intent(SignUp.this,MainActivity.class);
              startActivity(intent);
              finish();
          }
      });

    }
}