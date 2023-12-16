package com.example.demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.ViewHolder> {

    private static final String Tag = "RecyclerView";

    private Context context;
    private ArrayList<Food_Model>ModelList;

    public static int pos = 0;
    public RecyclerAdapter1(Context context, ArrayList<Food_Model> modelList) {
        this.context = context;
        ModelList = modelList;
    }

    @NonNull
    @Override
    public RecyclerAdapter1.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {


        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_row,parent,false);
        return new RecyclerAdapter1.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerAdapter1.ViewHolder holder, int position) {
        holder.Food_Name.setText(ModelList.get(position).getName());
        holder.Food_Price.setText(ModelList.get(position).getPrice());

        Picasso.get().load(ModelList.get(position).getImage()).into(holder.Food_image);
    }

    @Override
    public int getItemCount() {

        return ModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView Food_Name,Food_Price;
        ImageView Food_image;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            Food_Name = itemView.findViewById(R.id.Food_Name);
            Food_Price = itemView.findViewById(R.id.Food_Price);
            Food_image = itemView.findViewById(R.id.Food_Image);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(),UpdateItem.class);
                    pos =  getAdapterPosition()  ;
                    // System.out.println(pos);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
