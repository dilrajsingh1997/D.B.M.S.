package com.example.dilraj.dbms;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by JatinThareja on 24-Feb-17.
 */

public class Book_row extends RecyclerView.ViewHolder {
    TextView id,name,author;
    public Book_row(View itemView) {
        super(itemView);
        id= (TextView) itemView.findViewById(R.id.book_row_ID);
        name= (TextView) itemView.findViewById(R.id.book_row_name);
        author= (TextView) itemView.findViewById(R.id.book_row_author);
    }
}
