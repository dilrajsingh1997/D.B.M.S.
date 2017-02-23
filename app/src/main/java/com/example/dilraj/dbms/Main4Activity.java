package com.example.dilraj.dbms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    String name, desig, phone, uid, psswd;
    EditText e1, e2, e3, e4, e5;
    NewUserHandler newUserHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        newUserHandler = new NewUserHandler(this, null, null, 1);
        e1 = (EditText) findViewById(R.id.editText9);
        e2 = (EditText) findViewById(R.id.editText10);
        e3 = (EditText) findViewById(R.id.editText11);
        e4 = (EditText) findViewById(R.id.editText12);
        e5 = (EditText) findViewById(R.id.editText13);
    }

    public void addAdmin(View view){
        name = e1.getText().toString();
        desig = e2.getText().toString();
        phone = e3.getText().toString();
        uid = e4.getText().toString();
        psswd = e5.getText().toString();
        Admins a = new Admins(name, desig, phone, uid, psswd);
        try{
            if(newUserHandler.checkAdmin(uid)){
                Toast.makeText(this, "Exists", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!newUserHandler.checkIdIn(uid)){
                Toast.makeText(this, "Not registered", Toast.LENGTH_SHORT).show();
                return;
            }
            newUserHandler.addAdminDB(a);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Main5Activity.class);
            startActivity(i);
        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
