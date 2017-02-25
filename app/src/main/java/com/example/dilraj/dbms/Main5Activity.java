package com.example.dilraj.dbms;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dilraj.dbms.Dialogs.AddBookDialog;
import com.example.dilraj.dbms.Dialogs.UserDetailsDialog;
import com.example.dilraj.dbms.Interface.UserClickInterface;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {

    Button user_list, book_list, rent_list;
    RecyclerView user_recylcer, book_recylcer, rent_recylcer;
    LinearLayout user_layout, book_layout, rent_layout;
    NewUserHandler newUserHandler;
    ArrayList<Books> books;
    ArrayList<USers> users;
    ArrayList<Rent> rents;
    UserClickInterface clickinterface=new UserClickInterface() {
        @Override
        public void OnClick(int position) {
            USers user=users.get(position);
            UserDetailsDialog dialog=new UserDetailsDialog();
            Bundle bundle=new Bundle();
            bundle.putString("PHONE",user.getPhone());
            bundle.putString("ADD",user.getAddress());
            dialog.setArguments(bundle);
            dialog.show(getSupportFragmentManager(),"USERS");
        }
    };
    Handler handler = new Handler();
    Runnable checkAct = new Runnable() {
        @Override
        public void run() {
            users = newUserHandler.getUsers();
            books = newUserHandler.getBooks();
            rents = newUserHandler.getRent();
            LinearLayoutManager manager1 = new LinearLayoutManager(Main5Activity.this);
            LinearLayoutManager manager2 = new LinearLayoutManager(Main5Activity.this);
            LinearLayoutManager manager3 = new LinearLayoutManager(Main5Activity.this);
            BookAdapter bookAdapter = new BookAdapter(Main5Activity.this, books);
            UserAdapter userAdapter = new UserAdapter(Main5Activity.this, users,clickinterface);
            RentAdapter rentAdapter = new RentAdapter(Main5Activity.this, rents);
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
            handler.postDelayed(checkAct, 2000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        newUserHandler = new NewUserHandler(this, null, null, 1);
        rent_list = (Button) findViewById(R.id.button8);
        book_list = (Button) findViewById(R.id.button7);
        user_list = (Button) findViewById(R.id.button6);
        user_layout = (LinearLayout) findViewById(R.id.linear_layoutusers);
        book_layout = (LinearLayout) findViewById(R.id.linear_layoutubooks);
        rent_layout = (LinearLayout) findViewById(R.id.linear_layoutrent);
        user_recylcer = (RecyclerView) findViewById(R.id.recylclerview1);
        book_recylcer = (RecyclerView) findViewById(R.id.recylclerview2);
        rent_recylcer = (RecyclerView) findViewById(R.id.recylclerview3);
        users = newUserHandler.getUsers();
        books = newUserHandler.getBooks();
        rents = newUserHandler.getRent();
        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

                return makeMovementFlags(0, ItemTouchHelper.END | ItemTouchHelper.START);
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
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                try{
                    new AlertDialog.Builder(Main5Activity.this)
                            .setTitle("Delete entry")
                            .setMessage("Are you sure you want to delete this entry?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Book_row book_row= (Book_row) viewHolder;
                                    newUserHandler.delete_book(Integer.parseInt(book_row.id.getText().toString()));
                                    handler.postDelayed(checkAct, 0);
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
                catch (SQLiteException e){
                    Toast.makeText(Main5Activity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(book_recylcer);
        handler.postDelayed(checkAct, 0);

        book_recylcer.setOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScroll(RecyclerView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                handler.removeCallbacks(checkAct);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                handler.removeCallbacks(checkAct);
            }
        });

        rent_recylcer.setOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScroll(RecyclerView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                handler.removeCallbacks(checkAct);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                handler.removeCallbacks(checkAct);
            }
        });

        user_recylcer.setOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScroll(RecyclerView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                handler.removeCallbacks(checkAct);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                handler.removeCallbacks(checkAct);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.admin_menu_addbook:
                AddBookDialog dialog = new AddBookDialog();
                dialog.show(getSupportFragmentManager(), "ADD");
                handler.postDelayed(checkAct, 0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
