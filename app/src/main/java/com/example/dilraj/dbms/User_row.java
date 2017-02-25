package com.example.dilraj.dbms;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dilraj.dbms.Interface.UserClickInterface;

/**
 * Created by JatinThareja on 24-Feb-17.
 */

public class User_row extends RecyclerView.ViewHolder {

    TextView roll,name,branch;
    public User_row(final View itemView,  final UserClickInterface userClickInterface) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userClickInterface.OnClick(getAdapterPosition());
            }
        });
        roll= (TextView) itemView.findViewById(R.id.user_row_roll);
        name= (TextView) itemView.findViewById(R.id.user_row_name);
        branch= (TextView) itemView.findViewById(R.id.user_row_branch);
    }
}
