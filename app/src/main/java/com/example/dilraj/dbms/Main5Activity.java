package com.example.dilraj.dbms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.dilraj.dbms.Dialogs.AddBookDialog;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {

    Button user_list, book_list, rent_list;
    RecyclerView user_recylcer, book_recylcer, rent_recylcer;
    LinearLayout user_layout,book_layout,rent_layout;
    NewUserHandler newUserHandler;
    ArrayList<Books> books;
    ArrayList<USers> users;
    ArrayList<Rent> rents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        newUserHandler = new NewUserHandler(this, null, null, 1);
        rent_list = (Button) findViewById(R.id.button8);
        book_list = (Button) findViewById(R.id.button7);
        user_list = (Button) findViewById(R.id.button6);
        user_layout= (LinearLayout) findViewById(R.id.linear_layoutusers);
        book_layout= (LinearLayout) findViewById(R.id.linear_layoutubooks);
        rent_layout= (LinearLayout) findViewById(R.id.linear_layoutrent);
        user_recylcer = (RecyclerView) findViewById(R.id.recylclerview1);
        book_recylcer = (RecyclerView) findViewById(R.id.recylclerview2);
        rent_recylcer = (RecyclerView) findViewById(R.id.recylclerview3);

        users = newUserHandler.getUsers();
        books = newUserHandler.getBooks();
        rents = newUserHandler.getRent();
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        LinearLayoutManager manager3 = new LinearLayoutManager(this);
        BookAdapter bookAdapter = new BookAdapter(this, books);
        UserAdapter userAdapter = new UserAdapter(this, users);
        RentAdapter rentAdapter = new RentAdapter(this, rents);
        user_recylcer.setAdapter(userAdapter);
        user_recylcer.setLayoutManager(manager1);
        book_recylcer.setAdapter(bookAdapter);
        book_recylcer.setLayoutManager(manager2);
        rent_recylcer.setAdapter(rentAdapter);
        rent_recylcer.setLayoutManager(manager3);
        user_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_layout.setVisibility(View.VISIBLE);
                book_layout.setVisibility(View.GONE);
                rent_layout.setVisibility(View.GONE);
                user_recylcer.setVisibility(View.VISIBLE);
                book_recylcer.setVisibility(View.GONE);
                rent_recylcer.setVisibility(View.GONE);
                user_list.setSelected(true);
                book_list.setSelected(false);
                rent_list.setSelected(false);
            }
        });
        book_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_layout.setVisibility(View.GONE);
                book_layout.setVisibility(View.VISIBLE);
                rent_layout.setVisibility(View.GONE);
                user_recylcer.setVisibility(View.GONE);
                book_recylcer.setVisibility(View.VISIBLE);
                rent_recylcer.setVisibility(View.GONE);
                user_list.setSelected(false);
                book_list.setSelected(true);
                rent_list.setSelected(false);

            }
        });
        rent_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_layout.setVisibility(View.GONE);
                book_layout.setVisibility(View.GONE);
                rent_layout.setVisibility(View.VISIBLE);
                user_recylcer.setVisibility(View.GONE);
                book_recylcer.setVisibility(View.GONE);
                rent_recylcer.setVisibility(View.VISIBLE);
                user_list.setSelected(false);
                book_list.setSelected(false);
                rent_list.setSelected(true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.admin_menu_addbook:
                AddBookDialog dialog=new AddBookDialog();
                dialog.show(getSupportFragmentManager(),"ADD");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
