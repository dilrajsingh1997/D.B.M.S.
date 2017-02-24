package com.example.dilraj.dbms;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLIntegrityConstraintViolationException;

public class Main3Activity extends AppCompatActivity {

    EditText e1, e2, e3, e4, e5, e6;
    String name, branch, roll, phone, address, psswd;
    NewUserHandler newUserHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        newUserHandler = new NewUserHandler(this, null, null, 1);
        e1 = (EditText) findViewById(R.id.editText3);
        e2 = (EditText) findViewById(R.id.editText4);
        e3 = (EditText) findViewById(R.id.editText8);
        e4 = (EditText) findViewById(R.id.editText5);
        e5 = (EditText) findViewById(R.id.editText6);
        e6 = (EditText) findViewById(R.id.editText7);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater in = getMenuInflater();
        in.inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.menu.menu_test:

        }
        return true;
    }

    public void addUser(View view){
        name = e1.getText().toString();
        branch = e2.getText().toString();
        roll = e3.getText().toString();
        phone = e4.getText().toString();
        address = e5.getText().toString();
        psswd = e6.getText().toString();
        USers u = new USers(name, branch, roll, phone, address, psswd);
        try{
            if(newUserHandler.checkUser(roll)){
                Toast.makeText(this, "Exists", Toast.LENGTH_SHORT).show();
                return;
            }
            newUserHandler.addUserDB(u);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        }catch (SQLIntegrityConstraintViolationException e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

}
