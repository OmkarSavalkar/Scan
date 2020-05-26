package com.example.myscan.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myscan.R;
import com.example.myscan.modelscanstore.Listitem;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyAdapterViewHolder>
{
    List<Listitem> Listitemarraylist;
    Context context;
    public Myadapter(List<Listitem> Listitemarraylist, Context context)
    {
        this.Listitemarraylist=Listitemarraylist;
        this.context=context;
    }


    @NonNull
    @Override
    public Myadapter.MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        return new MyAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myadapter.MyAdapterViewHolder holder, int position) {

        Listitem listitem=Listitemarraylist.get(position);
        holder.textviewtype.setText(listitem.getType());
        holder.textviewcode.setText(listitem.getCode());
        Linkify.addLinks(holder.textviewcode,Linkify.ALL);
    }

    @Override
    public int getItemCount() {
        return Listitemarraylist.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView textviewcode,textviewtype;
        CardView cardView;
        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewcode = itemView.findViewById(R.id.Textviewcode);
            textviewtype=itemView.findViewById(R.id.textviewtype);
            cardView=itemView.findViewById(R.id.listitemcardview);
        }
    }


}
