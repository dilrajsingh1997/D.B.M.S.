package com.example.dilraj.dbms;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dilraj.dbms.Interface.BookClickInterface;

import java.util.ArrayList;

/**
 * Created by JatinThareja on 24-Feb-17.
 */

public class BookAdapter extends RecyclerView.Adapter<Book_row> {
    LayoutInflater inflater;
    ArrayList<Books> books;
    int f;
    BookClickInterface clickInterface;
    public BookAdapter(Context mContext, ArrayList<Books> books, BookClickInterface bookClickInterface,int FLAG) {
        clickInterface=bookClickInterface;
        f=FLAG;
        inflater = LayoutInflater.from(mContext);
        this.books = books;
    }

    @Override
    public Book_row onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.book_details_row,parent,false);
        return new Book_row(v,clickInterface,f);
    }

    @Override
    public void onBindViewHolder(Book_row holder, int position) {
         Books book=books.get(position);
        holder.id.setText(book.getId());
        holder.name.setText(book.getName());
        holder.author.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void itemRemoved(ArrayList<Books> books, int position) {
        this.books=books;
        notifyItemRemoved(position);
    }

    public void itemAdded(ArrayList<Books> books, int size) {
        this.books=books;
        notifyItemInserted(size-1);
    }

    public void bookRemoved(ArrayList<Books> books, int adapterPosition) {
        this.books=books;
        notifyItemRemoved(adapterPosition);
    }
}
