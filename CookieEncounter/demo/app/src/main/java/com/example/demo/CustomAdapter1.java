package com.example.demo;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

public class CustomAdapter1 extends ArrayAdapter<AddCart1> {
    private List<AddCart1> requestlist;
    private Activity context;
    public int flag, pr = 0;

    public CustomAdapter1(Activity context, List<AddCart1> requestlist) {
        super(context, R.layout.sample_layout, requestlist);
        this.context = context;
        this.requestlist = requestlist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout, null, true);

        flag = position;
        AddCart1 request = requestlist.get(position);
        TextView t1 = view.findViewById(R.id.acfood);
        TextView t2 = view.findViewById(R.id.acprice);
        TextView t3 = view.findViewById(R.id.food_txt);

        ImageView bt1 = view.findViewById(R.id.minus);
        ImageView bt2 = view.findViewById(R.id.plus);
        ImageView bt3 = view.findViewById(R.id.cross);

        t1.setText("Item: " + request.name);
        t2.setText("Price: "+request.price);
        t3.setText(request.fqnt);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cnt = 0, cnt2 = 0;
                String one = requestlist.get(position).price;
                String two = requestlist.get(position).fqnt;
                int o = 0, t = 0;
                o = Integer.parseInt(one);
                t = Integer.parseInt(two);
                cnt2 = o/t;
                o -= cnt2;
                t--;
                if(t<=0){
                    FirebaseDatabase.getInstance().getReference("AddToCart").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()){
                                String a = userSnapshot.child("usrnm").getValue().toString();
                                String b = userSnapshot.child("fid").getValue().toString();
                                String aa = requestlist.get(position).usrnm;
                                String bb = requestlist.get(position).fid;
                                if(a.equals(aa) && b.equals(bb)){
                                    userSnapshot.getRef().removeValue();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    final int[] flag = {0};
                    int cnt1 = 0, cnt21 = 0;
                    String one1 = requestlist.get(position).price;
                    String two1 = requestlist.get(position).fqnt;
                    final int[] o1 = {0};
                    final int[] t1 = { 0 };
                    o1[0] = Integer.parseInt(one);
                    t1[0] = Integer.parseInt(two);
                    cnt21 = o1[0] / t1[0];
                    o1[0] += cnt21;
                    t1[0]++;

                    String total_rs = Integer.toString(o1[0]);
                    String total_qn = Integer.toString(t1[0]);
                    int finalCnt = cnt21;
                    //final AddCart1[] rq = new AddCart1[1];
                    //final String[] orp = new String[1];
                    FirebaseDatabase.getInstance().getReference("AddToCart").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()){
                                String a = userSnapshot.child("usrnm").getValue().toString();
                                String b = userSnapshot.child("fid").getValue().toString();
                                String c = userSnapshot.child("name").getValue().toString();
                                String d = userSnapshot.child("price").getValue().toString();
                                String e = userSnapshot.child("fqnt").getValue().toString();
                                String aa = requestlist.get(position).usrnm;
                                String bb = requestlist.get(position).fid;
                                if(a.equals(aa) && b.equals(bb) && flag[0] == 0){
                                    String orp = userSnapshot.getRef().getKey().toString();
                                    int dd = Integer.parseInt(d);
                                    int ee = Integer.parseInt(e);
                                    dd -= finalCnt;
                                    ee--;
                                    flag[0] = 1;
                                    String total_d = Integer.toString(dd);
                                    String total_e = Integer.toString(ee);
                                    AddCart1 rq = new AddCart1(c, total_d, a, b, total_e);
                                    //userSnapshot.getRef().setValue(rq[0]);
                                    FirebaseDatabase.getInstance().getReference("AddToCart").child(orp).setValue(rq);
                                    Intent i = new Intent(context, FinalCart.class);
                                    context.finish();
                                    context.startActivity(i);
                                    //break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] flag = {0};
                int cnt = 0, cnt2 = 0;
                String one = requestlist.get(position).price;
                String two = requestlist.get(position).fqnt;
                final int[] o = {0};
                final int[] t = { 0 };
                o[0] = Integer.parseInt(one);
                t[0] = Integer.parseInt(two);
                cnt2 = o[0] / t[0];
                o[0] += cnt2;
                t[0]++;

                String total_rs = Integer.toString(o[0]);
                String total_qn = Integer.toString(t[0]);
                int finalCnt = cnt2;
                //final AddCart1[] rq = new AddCart1[1];
                //final String[] orp = new String[1];
                FirebaseDatabase.getInstance().getReference("AddToCart").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot userSnapshot : snapshot.getChildren()){
                            String a = userSnapshot.child("usrnm").getValue().toString();
                            String b = userSnapshot.child("fid").getValue().toString();
                            String c = userSnapshot.child("name").getValue().toString();
                            String d = userSnapshot.child("price").getValue().toString();
                            String e = userSnapshot.child("fqnt").getValue().toString();
                            String aa = requestlist.get(position).usrnm;
                            String bb = requestlist.get(position).fid;
                            if(a.equals(aa) && b.equals(bb) && flag[0] == 0){
                                String orp = userSnapshot.getRef().getKey().toString();
                                int dd = Integer.parseInt(d);
                                int ee = Integer.parseInt(e);
                                dd += finalCnt;
                                ee++;
                                flag[0] = 1;
                                String total_d = Integer.toString(dd);
                                String total_e = Integer.toString(ee);
                                AddCart1 rq = new AddCart1(c, total_d, a, b, total_e);
                                //userSnapshot.getRef().setValue(rq[0]);
                                FirebaseDatabase.getInstance().getReference("AddToCart").child(orp).setValue(rq);
                                Intent i = new Intent(context, FinalCart.class);
                                context.finish();
                                context.startActivity(i);
                                //break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //FirebaseDatabase.getInstance().getReference("AddToCart").child(orp[0]).setValue(rq[0]);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("AddToCart").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot userSnapshot : snapshot.getChildren()){
                            String a = userSnapshot.child("usrnm").getValue().toString();
                            String b = userSnapshot.child("fid").getValue().toString();
                            String aa = requestlist.get(position).usrnm;
                            String bb = requestlist.get(position).fid;
                            if(a.equals(aa) && b.equals(bb)){
                                userSnapshot.getRef().removeValue();
                                String orp = userSnapshot.getRef().getKey().toString();
                                System.out.println("here: " + orp);
                                Intent i = new Intent(context, FinalCart.class);
                                context.finish();
                                context.startActivity(i);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        return view;
    }


    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return requestlist.size();
    }

    @Override
    public AddCart1 getItem(int position) {
        return requestlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

