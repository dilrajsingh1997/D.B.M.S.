package com.example.dilraj.dbms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void newUser(View view){
        Intent i = new Intent(this, Main3Activity.class);
        startActivity(i);
    }

    public void newAdmin(View view){
        Intent i = new Intent(this, Main4Activity.class);
        startActivity(i);
    }

    public void signIn(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
