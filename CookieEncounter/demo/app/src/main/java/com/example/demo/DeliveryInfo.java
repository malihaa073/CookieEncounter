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
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class DeliveryInfo extends AppCompatActivity {

    public String user = "";
    DatabaseReference ref, ef;
    com.google.android.material.textfield.TextInputEditText Image, Name, Price, phn;
    String total_rs, ans;
    String[] arr2 = new String[102];
    final int[] ix = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_info);

        Image = findViewById(R.id.link1);
        Name = findViewById(R.id.food_name1);
        Price = findViewById(R.id.food_price1);
        phn = findViewById(R.id.link2);
        ref = FirebaseDatabase.getInstance().getReference("AddToCart");
        ef = FirebaseDatabase.getInstance().getReference("AddToCart");

        FirebaseDatabase.getInstance().getReference("current").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                user = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        final int[] flag2 = {0};
        final int[] flag = {0};
        int x = 100;
        int y = x+2;
        int ara[] = new int[y];
        Arrays.fill(ara, 0);
        int arra[] = new int[y];
        Arrays.fill(arra, 0);
        String[] arr = new String[y];



        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dns:snapshot.getChildren()){
                    AddCart1 req = dns.getValue(AddCart1.class);

                    if(req.usrnm.equals(user)){
                        int indx1 = Integer.parseInt(req.fid);
                        int indx2 = Integer.parseInt(req.fqnt);
                        int indx3 = Integer.parseInt(req.price);

                        ara[indx1] += indx2;
                        arr[indx1] = req.name;
                        arra[indx1] += indx3;
                        arr2[ix[0]] = dns.getRef().getKey().toString();
                        ix[0]++;
                        //dns.getRef().removeValue();
                        System.out.println("here: " + req.fid);
                        System.out.println("here: " + req.name);
                    }
                }
                ans ="";
                int cnt = 0;
                for(int i=1; i<y; i++){
                    if(ara[i]>0){
                        String pr="", id="", qnt="";
                        cnt += arra[i];
                        qnt = Integer.toString((int) ara[i]);

                        ans += arr[i];
                        ans += "(";
                        ans += qnt;
                        ans += ") ";
                        System.out.println("here: " + ans);
                        System.out.println("here: " + qnt);
                    }
                }
                total_rs = Integer.toString(cnt);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public boolean ConfirmOrder(View view){
        String fimage = Image.getText().toString();
        String fname = Name.getText().toString();
        String fprice = Price.getText().toString();
        String phone = phn.getText().toString();

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
        if(phone.isEmpty()){
            phn.setError("Field cannot be empty");
            return false;
        }
        FinalCartItem fci = new FinalCartItem(ans, total_rs, user, fname, fprice, fimage, phone);
        FirebaseDatabase.getInstance().getReference().child("Order").push().setValue(fci);
        Toast.makeText(DeliveryInfo.this,"Order Placed",Toast.LENGTH_SHORT).show();
        for(int j=0; j<ix[0]; j++){
            FirebaseDatabase.getInstance().getReference("AddToCart").child(arr2[j]).removeValue();
        }
        finish();


        return true;
    }
}