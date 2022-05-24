package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position) {
       String fn = data.get(position).getFname();
       String ln = data.get(position).getLname();
       String yourname = fn+" "+ln;
        holder.rfname.setText(yourname);
        holder.rmobile.setText(data.get(position).getMobileno());
        holder.remail.setText(data.get(position).getEmail());
        holder.radddress.setText(data.get(position).getAddress());
        holder.rpost.setText(data.get(position).getForpost());
        holder.rdoj.setText(data.get(position).getDoj());
        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userid = data.get(position).getUserid();
                String fname = data.get(position).getFname();
                String lname = data.get(position).getLname();
                String  mobileno = data.get(position).getMobileno();
                String email = data.get(position).getEmail();
                String address = data.get(position).getAddress();
                String post = data.get(position).getForpost();
                String doj = data.get(position).getDoj();

                Intent i = new Intent(view.getContext(),edituser.class);

                i.putExtra("userid",userid);
                i.putExtra("fname",fname);
                i.putExtra("lname",lname);
                i.putExtra("mobileno",mobileno);
                i.putExtra("email",email);
                i.putExtra("address",address);
                i.putExtra("post",post);
                i.putExtra("doj",doj);
                view.getContext().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
      TextView rfname , remail,rmobile,radddress,rpost,rdoj,btnedit;

      public myviewholder(@NonNull View itemView) {
          super(itemView);
          btnedit = itemView.findViewById(R.id.editbtn);
          rfname = itemView.findViewById(R.id.yourname);
          remail = itemView.findViewById(R.id.email);
          rmobile = itemView.findViewById(R.id.mobileno);
          radddress = itemView.findViewById(R.id.address);
          rpost = itemView.findViewById(R.id.post);
          rdoj= itemView.findViewById(R.id.doj);

      }
  }

}
