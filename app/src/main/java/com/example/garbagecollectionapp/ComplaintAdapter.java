package com.example.garbagecollectionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.Viewholder> {


    ArrayList<Complaint> mList;
    private ComplaintAdapter.RecyclerViewClickListener listener;

    public ComplaintAdapter(ArrayList<Complaint> mList, ComplaintAdapter.RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list,parent,false);

        return new ComplaintAdapter.Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Complaint vacancy1 = mList.get(position);
        holder.txtbname.setText("User Name "+vacancy1.getName());
        holder.txtaddress.setText("Area "+vacancy1.getAddress());
        holder.txttype.setText("Description "+vacancy1.getDes());
        holder.txtarea.setText("Number "+vacancy1.getNumber());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateList(ArrayList<Complaint> filteredComplaints) {
        mList.clear();
        mList.addAll(filteredComplaints);
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
