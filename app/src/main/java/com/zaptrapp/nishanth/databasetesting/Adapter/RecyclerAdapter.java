package com.zaptrapp.nishanth.databasetesting.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zaptrapp.nishanth.databasetesting.Model.Data;
import com.zaptrapp.nishanth.databasetesting.R;

import java.util.ArrayList;

/**
 * Created by Nishanth on 05-Sep-17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Viewholder> {

    private ArrayList<Data> data;
    private Data model;
    private Context context;

    public RecyclerAdapter(ArrayList<Data> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_recycler, parent, false);

        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

        model = data.get(position);
        holder.name_rv.setText(model.getName());
        holder.amount_rv.setText(String.valueOf(model.getAmount()));
        holder.description_rv.setText(model.getDescription());

    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder {

        // 1. Declare your Views here

        public TextView name_rv;
        public TextView amount_rv;
        public TextView description_rv;


        public Viewholder(View itemView) {
            super(itemView);

            // 2. Define your Views here
            name_rv = (TextView)itemView.findViewById(R.id.name_rv);
            amount_rv = (TextView)itemView.findViewById(R.id.amount_rv);
            description_rv = (TextView)itemView.findViewById(R.id.description_rv);

        }
    }
}
