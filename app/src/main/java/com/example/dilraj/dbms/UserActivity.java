package com.example.dilraj.dbms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dilraj.dbms.Interface.BookClickInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class UserActivity extends AppCompatActivity {
    Button prev, current, add;
    RecyclerView history_recylcer, book_recylcer, rent_recylcer;
    LinearLayout prev_layout, curr_layout, add_layout;
    NewUserHandler newUserHandler;
    ArrayList<Books> books;
    BookAdapter bookAdapter;
    ArrayList<Rent> rents,history_rents;
    String USERID = null;
    RentAdapter rentAdapter,historyAdapter;
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
                            rents.clear();
                            try{
                                rents=newUserHandler.getRentsOfUser(USERID);
                            } catch (Exception e){
                                Toast.makeText(UserActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                            rentAdapter.itemAdded(rents,rents.size());

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
        history_recylcer = (RecyclerView) findViewById(R.id.u_recylclerview1);
        book_recylcer = (RecyclerView) findViewById(R.id.u_recylclerview2);
        rent_recylcer = (RecyclerView) findViewById(R.id.u_recylclerview3);
        books = newUserHandler.getBooksUnIssued();
        history_rents=newUserHandler.getHistoryrent(USERID);
        try{
            rents=newUserHandler.getRentsOfUser(USERID);
        } catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        LinearLayoutManager manager1 = new LinearLayoutManager(UserActivity.this);
        LinearLayoutManager manager2 = new LinearLayoutManager(UserActivity.this);
        LinearLayoutManager manager3 = new LinearLayoutManager(UserActivity.this);
        bookAdapter = new BookAdapter(UserActivity.this, books, bookClickInterface, 1);
        rentAdapter = new RentAdapter(UserActivity.this, rents);
        historyAdapter = new RentAdapter(UserActivity.this,history_rents);
        history_recylcer.setAdapter(historyAdapter);
        history_recylcer.setLayoutManager(manager1);
        book_recylcer.setAdapter(bookAdapter);
        book_recylcer.setLayoutManager(manager2);
        rent_recylcer.setAdapter(rentAdapter);
        rent_recylcer.setLayoutManager(manager3);
        ItemTouchHelper.Callback callback=new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0,ItemTouchHelper.START| ItemTouchHelper.END);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                new AlertDialog.Builder(UserActivity.this)
                        .setTitle("Return Book?")
                        .setMessage("Are you sure you want to return this book?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String timeStamp = new SimpleDateFormat(getResources().getString(R.string.date_format), Locale.getDefault()).format(new Date());
                                Rent rent=rents.get(viewHolder.getAdapterPosition());
                                String bookid=rent.getBookid();
                                String due = rent.getDate().split(" ")[0];
                                String today = timeStamp.split(" ")[0];
                                int y1 = Integer.parseInt(due.split("-")[0]);
                                int m1 = Integer.parseInt(due.split("-")[1]);
                                int d1 = Integer.parseInt(due.split("-")[2]);
                                int y2 = Integer.parseInt(today.split("-")[0]);
                                int m2 = Integer.parseInt(today.split("-")[1]);
                                int d2 = Integer.parseInt(today.split("-")[2]);

                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.DAY_OF_MONTH, d1);
                                c.set(Calendar.MONTH, m1);
                                c.set(Calendar.YEAR, y1);
                                Date fdate = c.getTime();

                                c.set(Calendar.DAY_OF_MONTH, d2);
                                c.set(Calendar.MONTH, m2);
                                c.set(Calendar.YEAR, y2);
                                Date ldate = c.getTime();
                                long days = (ldate.getTime() - fdate.getTime())/1000/60/60/24;
                                if(days>10){
                                    Toast.makeText(UserActivity.this, "You have exceeded the 10-day limit. Your fine is " + String.valueOf((days-10)*5), Toast.LENGTH_SHORT).show();
                                    rentAdapter.notifyDataSetChanged();
                                }else{
                                    newUserHandler.updateBooks(bookid);
                                    newUserHandler.delete_rent(bookid);
                                    newUserHandler.setHistoryReturnDate(rent.getDate(),timeStamp);
                                    history_rents.clear();
                                    history_rents=newUserHandler.getHistoryrent(USERID);
                                    historyAdapter.itemAdded(history_rents,history_rents.size());
                                    rents.remove(viewHolder.getAdapterPosition());
                                    rentAdapter.itemRemoved(rents,viewHolder.getAdapterPosition());
                                    books.clear();
                                    books = newUserHandler.getBooksUnIssued();
                                    bookAdapter.bookInserted(books);
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        };
        ItemTouchHelper helper=new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rent_recylcer);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_layout.setVisibility(View.VISIBLE);
                prev_layout.setVisibility(View.GONE);
                curr_layout.setVisibility(View.GONE);
                book_recylcer.setVisibility(View.VISIBLE);
                rent_recylcer.setVisibility(View.GONE);
                history_recylcer.setVisibility(View.GONE);
                add.setSelected(true);
                prev.setSelected(false);
                current.setSelected(false);
            }
        });
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_layout.setVisibility(View.GONE);
                prev_layout.setVisibility(View.GONE);
                curr_layout.setVisibility(View.VISIBLE);
                book_recylcer.setVisibility(View.GONE);
                rent_recylcer.setVisibility(View.VISIBLE);
                history_recylcer.setVisibility(View.GONE);
                add.setSelected(false);
                prev.setSelected(false);
                current.setSelected(true);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_layout.setVisibility(View.GONE);
                prev_layout.setVisibility(View.VISIBLE);
                curr_layout.setVisibility(View.GONE);
                book_recylcer.setVisibility(View.GONE);
                rent_recylcer.setVisibility(View.GONE);
                history_recylcer.setVisibility(View.VISIBLE);
                add.setSelected(false);
                prev.setSelected(true);
                current.setSelected(false);

            }
        });


    }
}
