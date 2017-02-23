package com.example.dilraj.dbms;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.sql.Array;
import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {

    Button button;
    NewUserHandler newUserHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        newUserHandler = new NewUserHandler(this, null, null, 1);
        button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder al = new AlertDialog.Builder(Main5Activity.this);
                ArrayList<String> ar = newUserHandler.getUsers();
                ArrayAdapter<String> ad = new ArrayAdapter<String>(Main5Activity.this, android.R.layout.simple_list_item_1, ar);
                al.setAdapter(ad, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                al.show();
            }
        });
    }
}
