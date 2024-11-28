package com.example.garbagecollectionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.Viewholder>{

    ArrayList<Mylocation> mList;
    private LocationAdapter.RecyclerViewClickListener listener;

    public LocationAdapter(ArrayList<Mylocation> mList, RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationlist,parent,false);

        return new LocationAdapter.Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Mylocation vacancy1 = mList.get(position);
        holder.txtbname.setText("Driver Name -"+vacancy1.getName());
        holder.txtaddress.setText("Location -"+vacancy1.getArea());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img1;
        TextView txtbname,txtaddress;


        public Viewholder(@NonNull View itemView) {


            super(itemView);

            img1 =itemView.findViewById(R.id.img1);
            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
}
