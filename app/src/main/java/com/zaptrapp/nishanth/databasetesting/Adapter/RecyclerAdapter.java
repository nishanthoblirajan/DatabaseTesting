package com.zaptrapp.nishanth.databasetesting.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zaptrapp.nishanth.databasetesting.Database.DbHelperProvider;
import com.zaptrapp.nishanth.databasetesting.Database.DbHelperProviderClient;
import com.zaptrapp.nishanth.databasetesting.Model.Data;
import com.zaptrapp.nishanth.databasetesting.R;

import java.util.ArrayList;

/**
 * Created by Nishanth on 05-Sep-17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Viewholder> {

    private Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }


    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_recycler, parent, false);

        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {

        final Viewholder h = holder;
        String name = "";
        double amount = 0;
        String desc = "";
        int id=-1;
        final Cursor cursor = DbHelperProviderClient.getAllDetails(context);
        if (cursor != null) {
            if (cursor.moveToPosition(position)) {
                name = cursor.getString(cursor.getColumnIndex(DbHelperProvider.DETAILS_NAME_COLUMN));
                amount = cursor.getDouble(cursor.getColumnIndex(DbHelperProvider.DETAILS_AMOUNT_COLUMN));
                desc = cursor.getString(cursor.getColumnIndex(DbHelperProvider.DETAILS_SHORTDESC_COLUMN));
                id = cursor.getInt(cursor.getColumnIndex(DbHelperProvider.ROW_ID));
                holder.name_rv.setText(name);
                holder.amount_rv.setText(amount+"");
                holder.description_rv.setText(desc);
//                holder.delete_bt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        DbHelperProviderClient.removeDetails(cursor.get(DbHelperProvider.ROW_ID),context);
//                        notifyItemRemoved(h.getAdapterPosition());
//                    }
//                });
            }
            cursor.close();
        } else {

        }
        final int finalId = id;
        holder.delete_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelperProviderClient.removeDetails(finalId,context);
                notifyItemRemoved(h.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return DbHelperProviderClient.getAllDetails(context).getCount();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        // 1. Declare your Views here

        public TextView name_rv;
        public TextView amount_rv;
        public TextView description_rv;
        public Button edit_bt;
        public Button delete_bt;



        public Viewholder(View itemView) {
            super(itemView);

            // 2. Define your Views here
            name_rv = (TextView)itemView.findViewById(R.id.name_rv);
            amount_rv = (TextView)itemView.findViewById(R.id.amount_rv);
            description_rv = (TextView)itemView.findViewById(R.id.description_rv);
            edit_bt = itemView.findViewById(R.id.edit_bt);
            delete_bt = itemView.findViewById(R.id.delete_bt);

        }
    }
}
