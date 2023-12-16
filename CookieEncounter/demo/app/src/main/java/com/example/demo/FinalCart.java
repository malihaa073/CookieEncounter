package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalCart extends AppCompatActivity{

    String user = "";
    public String tst = "";
    private ListView listview;
    DatabaseReference ref;
    private List<AddCart1> reqlist;
    private CustomAdapter1 customAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_cart);

        ref = FirebaseDatabase.getInstance().getReference("AddToCart");
        reqlist = new ArrayList<>();
        customAdapter1 = new CustomAdapter1(FinalCart.this, reqlist);


        listview = findViewById(R.id.llistviewId);

        FirebaseDatabase.getInstance().getReference("item").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                tst = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        FirebaseDatabase.getInstance().getReference("current").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                user = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStart() {

        FirebaseDatabase.getInstance().getReference("item").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                tst = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        //int x = Integer.parseInt(tst);
        int x = 100;
        int y = x+2;
        int ara[] = new int[y];
        Arrays.fill(ara, 0);
        int arra[] = new int[y];
        Arrays.fill(arra, 0);
        String[] arr = new String[y];
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                reqlist.clear();
                for(DataSnapshot dns:snapshot.getChildren()){
                    AddCart1 req = dns.getValue(AddCart1.class);

                    if(req.usrnm.equals(user)){
                        int indx1 = Integer.parseInt(req.fid);
                        int indx2 = Integer.parseInt(req.fqnt);
                        int indx3 = Integer.parseInt(req.price);

                        ara[indx1] += indx2;
                        arr[indx1] = req.name;
                        arra[indx1] += indx3;

                        //dns.getRef().removeValue();
                    }
                }
                for(int i=1; i<=x; i++){
                    if(ara[i]>0){
                        String pr="", id="", qnt="";
                        pr = Integer.toString((int) arra[i]);
                        id = Integer.toString(i);
                        qnt = Integer.toString((int) ara[i]);
                        AddCart1 rq = new AddCart1(arr[i], pr, user, id, qnt);
                        reqlist.add(rq);
                        //FirebaseDatabase.getInstance().getReference().child("AddToCart").push().setValue(rq);
                    }
                }
                listview.setAdapter(customAdapter1);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        super.onStart();
    }

    public void Placed(View v){
        /*final int[] flag = {0};
        int x = 100;
        int y = x+2;
        int ara[] = new int[y];
        Arrays.fill(ara, 0);
        int arra[] = new int[y];
        Arrays.fill(arra, 0);
        String[] arr = new String[y];
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for(DataSnapshot dns:snapshot.getChildren()){
                    AddCart1 req = dns.getValue(AddCart1.class);

                    if(req.usrnm.equals(user)){
                        int indx1 = Integer.parseInt(req.fid);
                        int indx2 = Integer.parseInt(req.fqnt);
                        int indx3 = Integer.parseInt(req.price);

                        ara[indx1] += indx2;
                        arr[indx1] = req.name;
                        arra[indx1] += indx3;

                        dns.getRef().removeValue();
                    }
                }
                String ans ="";
                int cnt = 0;
                for(int i=1; i<=6; i++){
                    if(ara[i]>0){
                        String pr="", id="", qnt="";
                        cnt += arra[i];
                        qnt = Integer.toString((int) ara[i]);

                        ans += arr[i];
                        ans += "(";
                        ans += qnt;
                        ans += ") ";
                    }
                }
                String total_rs = Integer.toString(cnt);
                System.out.println("here: " + ans);
                FinalCartItem fci = new FinalCartItem(ans, total_rs, user);
                if(flag[0] ==0){
                    FirebaseDatabase.getInstance().getReference().child("Order").push().setValue(fci);
                    flag[0] = 1;
                    Toast.makeText(FinalCart.this,"Order Placed",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });*/

        Intent intent = new Intent(FinalCart.this, DeliveryInfo.class);
        finish();
        startActivity(intent);
    }
}