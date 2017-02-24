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

public class UserAdapter extends RecyclerView.Adapter<User_row> {
    LayoutInflater inflater;
    ArrayList<USers> mUsers;

    public UserAdapter(Context context, ArrayList<USers> users) {
        inflater = LayoutInflater.from(context);
        mUsers = users;
    }


    @Override
    public User_row onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.user_details_row, parent, false);
        return new User_row(v);
    }

    @Override
    public void onBindViewHolder(User_row holder, int position) {
        USers user=mUsers.get(position);
        holder.name.setText(user.getName());
        holder.roll.setText(user.getRoll());
        holder.branch.setText(user.getBranch());


    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
