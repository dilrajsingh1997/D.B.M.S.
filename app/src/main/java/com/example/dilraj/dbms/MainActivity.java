package com.example.dilraj.dbms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText e1, e2;
    String name, pass, clas = "not_specified";
    RadioGroup rg;
    Button button;
    NewUserHandler newUserHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newUserHandler = new NewUserHandler(this, null, null, 1);
        e1 = (EditText) findViewById(R.id.editText2);
        e2 = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button3);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.r1:
                        clas = "user";
                        break;
                    case R.id.r2:
                        clas = "admin";
                        break;
                    default :
                        clas = "not_specified";
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result;
                name = e1.getText().toString();
                pass = e2.getText().toString();
                switch (clas){
                    case "user":
                        result = newUserHandler.authUser(name, pass);
                        if(result){
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this,UserActivity.class);
                            i.putExtra("USERID",name);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "admin":
                        result = newUserHandler.authAdmin(name, pass);
                        if(result){
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, Main5Activity.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "not_specified":
                        Toast.makeText(MainActivity.this, "Please specify a class", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
