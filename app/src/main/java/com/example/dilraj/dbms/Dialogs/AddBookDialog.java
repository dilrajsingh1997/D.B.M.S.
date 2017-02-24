package com.example.dilraj.dbms.Dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dilraj.dbms.R;

/**
 * Created by JatinThareja on 24-Feb-17.
 */

public class AddBookDialog extends DialogFragment {
    EditText id,name,author;
    Button addbook_button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_addbook,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        id= (EditText) view.findViewById(R.id.addbook_edittext_id);
        name= (EditText) view.findViewById(R.id.addbook_edittext_name);
        author= (EditText) view.findViewById(R.id.addbook_edittext_author);
        addbook_button= (Button) view.findViewById(R.id.addbook_button);
        addbook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Add Code to insert book", Toast.LENGTH_SHORT).show();
                //TODO add book to the sql database
            }
        });

    }
}
