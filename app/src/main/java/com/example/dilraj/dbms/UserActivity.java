package com.example.dilraj.dbms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.dilraj.dbms.Interface.BookClickInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class UserActivity extends AppCompatActivity {
    Button prev, current, add;
    RecyclerView user_recylcer, book_recylcer, rent_recylcer;
    LinearLayout prev_layout, curr_layout, add_layout;
    NewUserHandler newUserHandler;
    ArrayList<Books> books;
    BookAdapter bookAdapter;
    ArrayList<Rent> rents;
    String USERID = null;
    RentAdapter rentAdapter;
    BookClickInterface bookClickInterface = new BookClickInterface() {
        @Override
        public void OnClick(final int position) {
            new AlertDialog.Builder(UserActivity.this)
                    .setTitle("Issue Book?")
                    .setMessage("Are you sure you want to issue this book?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Books book = books.get(position);
                            String bookid = book.getId();
                            String timeStamp = new SimpleDateFormat(getResources().getString(R.string.date_format), Locale.getDefault()).format(new Date());
                            Rent rent = new Rent(USERID, bookid, timeStamp);
                            newUserHandler.addRent(rent);
                            newUserHandler.updateIssuedBooks(bookid);
                            books.remove(position);
                            bookAdapter.itemRemoved(books,position);

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        USERID = getIntent().getStringExtra("USERID");
        prev = (Button) findViewById(R.id.u_button6);
        current = (Button) findViewById(R.id.u_button7);
        add = (Button) findViewById(R.id.u_button8);
        newUserHandler = new NewUserHandler(this, null, null, 1);
        prev_layout = (LinearLayout) findViewById(R.id.u_linear_layoutusers);
        curr_layout = (LinearLayout) findViewById(R.id.u_linear_layoutrent);
        add_layout = (LinearLayout) findViewById(R.id.u_linear_layoutubooks);
        user_recylcer = (RecyclerView) findViewById(R.id.u_recylclerview1);
        book_recylcer = (RecyclerView) findViewById(R.id.u_recylclerview2);
        rent_recylcer = (RecyclerView) findViewById(R.id.u_recylclerview3);
        books = newUserHandler.getBooksUnIssued();
        rents=newUserHandler.getRentsOfUser(USERID);
        LinearLayoutManager manager1 = new LinearLayoutManager(UserActivity.this);
        LinearLayoutManager manager2 = new LinearLayoutManager(UserActivity.this);
        LinearLayoutManager manager3 = new LinearLayoutManager(UserActivity.this);
        bookAdapter = new BookAdapter(UserActivity.this, books, bookClickInterface, 1);
        rentAdapter = new RentAdapter(UserActivity.this, rents);
        book_recylcer.setAdapter(bookAdapter);
        book_recylcer.setLayoutManager(manager2);
        rent_recylcer.setAdapter(rentAdapter);
        rent_recylcer.setLayoutManager(manager3);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_layout.setVisibility(View.VISIBLE);
                prev_layout.setVisibility(View.GONE);
                curr_layout.setVisibility(View.GONE);
                book_recylcer.setVisibility(View.VISIBLE);
                rent_recylcer.setVisibility(View.GONE);
                add.setSelected(true);
                prev.setSelected(false);
                current.setSelected(false);
            }
        });
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rents.clear();
                rents=newUserHandler.getRentsOfUser(USERID);
                rentAdapter.itemAdded(rents,rents.size());
                add_layout.setVisibility(View.GONE);
                prev_layout.setVisibility(View.GONE);
                curr_layout.setVisibility(View.VISIBLE);
                book_recylcer.setVisibility(View.GONE);
                rent_recylcer.setVisibility(View.VISIBLE);
                add.setSelected(false);
                prev.setSelected(false);
                current.setSelected(true);
            }
        });


    }
}
