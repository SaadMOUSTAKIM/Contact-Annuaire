package com.example.moustakim_annauire_pro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    private List<UserContact> dataList;
    private Activity context;
    private RoomDB database;


    public MainAdapter(Activity context, List<UserContact> dataList)
    {
       this.context=context;
       this.dataList=dataList;
       notifyDataSetChanged();
    }
    {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        UserContact data=dataList.get(position);


        database=RoomDB.getInstance(context);


        holder.fname.setText(data.getFirst_name());
        holder.lname.setText(data.getLast_name());
        holder.job.setText(data.getJob());
        holder.email.setText(data.getEmail());
        holder.tel.setText(data.getPhone());



        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        UserContact d=dataList.get(holder.getAdapterPosition());
                        database.userContactDao().delete(d);
                        dataList.clear();
                        dataList.addAll(database.userContactDao().getAll());
                        notifyDataSetChanged();
                        Toast toast=new Toast(context);
                        toast.makeText(context,"the contact was successfully deleted",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("confirmation");
                alertDialog.setMessage("are you sure you want to delete this");
                alertDialog.show();

                return true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searching(String s) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView fname;
        TextView lname;
        TextView job;
        TextView tel;
        TextView email;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fname=itemView.findViewById(R.id.fname);
            lname=itemView.findViewById(R.id.lname);
            job=itemView.findViewById(R.id.job);
            tel=itemView.findViewById(R.id.Tel);
            email=itemView.findViewById(R.id.email);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}
