package com.example.dilraj.dbms;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by JatinThareja on 24-Feb-17.
 */

public class RentAdapter extends RecyclerView.Adapter<Rent_row> {
    LayoutInflater inflater;
    ArrayList<Rent> rents;

    public RentAdapter(Context mContext, ArrayList<Rent> rents) {
        inflater = LayoutInflater.from(mContext);
        this.rents = rents;
    }

    @Override
    public Rent_row onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.rent_details_rows, parent, false);
        return new Rent_row(v);
    }

    @Override
    public void onBindViewHolder(Rent_row holder, int position) {
        Rent rent = rents.get(position);
        holder.userid.setText(rent.getUserid());
        holder.bookid.setText(rent.getBookid());
        holder.date.setText(rent.getDate());

    }

    @Override
    public int getItemCount() {
        return rents.size();
    }

    public void itemAdded(ArrayList<Rent> rents, int size) {
        this.rents=rents;
        notifyItemInserted(size-1);
    }
}
