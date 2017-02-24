package com.example.dilraj.dbms;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by JatinThareja on 24-Feb-17.
 */

public class Rent_row extends RecyclerView.ViewHolder {
    TextView userid,bookid,date;
    public Rent_row(View itemView) {
        super(itemView);
        userid= (TextView) itemView.findViewById(R.id.rent_row_userid);
        bookid = (TextView) itemView.findViewById(R.id.rent_row_bookid);
        date= (TextView) itemView.findViewById(R.id.rent_row_date);
    }
}
