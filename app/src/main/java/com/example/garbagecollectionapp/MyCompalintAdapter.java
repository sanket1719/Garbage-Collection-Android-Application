package com.example.garbagecollectionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCompalintAdapter extends RecyclerView.Adapter<MyCompalintAdapter.ViewHolder>{

    ArrayList<Complaint> mList;
    Context context;

    public MyCompalintAdapter(ArrayList<Complaint> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list,parent,false);

        return new MyCompalintAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Complaint vacancy1 = mList.get(position);
        holder.txtbname.setText("User Name "+vacancy1.getName());
        holder.txtaddress.setText("Date  "+vacancy1.getAddress());
        holder.txttype.setText("Number "+vacancy1.getNumber());
        holder.txtarea.setText("Description "+vacancy1.getDes());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img1;
        TextView txtbname,txtaddress,txttype,txtarea;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img1 =itemView.findViewById(R.id.img1);
            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);
            txttype = itemView.findViewById(R.id.emailtext);
            txtarea = itemView.findViewById(R.id.txtarea);

        }
    }
}
