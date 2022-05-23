package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {

    List<fetchuserdata> data;

    public myadapter(List<fetchuserdata> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);

        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.rfname.setText(data.get(position).getName());
        holder.rmobile.setText(data.get(position).getMobileno());
        holder.remail.setText(data.get(position).getEmail());
        holder.radddress.setText(data.get(position).getAddress());
        holder.rpost.setText(data.get(position).getPost());
        holder.rdoj.setText(data.get(position).getDoj());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
      TextView rfname , remail,rmobile,radddress,rpost,rdoj;

      public myviewholder(@NonNull View itemView) {
          super(itemView);
          rfname = itemView.findViewById(R.id.fname);
          remail = itemView.findViewById(R.id.email);
          rmobile = itemView.findViewById(R.id.mobileno);
          radddress = itemView.findViewById(R.id.address);
          rpost = itemView.findViewById(R.id.post);
          rdoj= itemView.findViewById(R.id.doj);

      }
  }
}
