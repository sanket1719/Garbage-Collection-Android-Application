package com.example.garbagecollectionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.Viewholder> {


    ArrayList<Status> mList;
    private StatusAdapter.RecyclerViewClickListener listener;

    public StatusAdapter(ArrayList<Status> mList, RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list,parent,false);

        return new StatusAdapter.Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Status vacancy1 = mList.get(position);
        holder.txtbname.setText("User Name "+vacancy1.getName());
        holder.txtaddress.setText("Date  "+vacancy1.getDate());
        holder.txttype.setText("Status "+vacancy1.getStatus());
        holder.txtarea.setText("Complaint "+vacancy1.getDescription());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void updateList(ArrayList<Status> filteredStatuses) {
        mList.clear();
        mList.addAll(filteredStatuses);
        notifyDataSetChanged();
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img1;
        TextView txtbname,txtaddress,txttype,txtarea;


        public Viewholder(@NonNull View itemView) {


            super(itemView);

            img1 =itemView.findViewById(R.id.img1);
            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);
            txttype = itemView.findViewById(R.id.emailtext);
            txtarea = itemView.findViewById(R.id.txtarea);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
}
