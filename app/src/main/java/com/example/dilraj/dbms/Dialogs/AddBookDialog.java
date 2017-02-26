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

import com.example.dilraj.dbms.Books;
import com.example.dilraj.dbms.Interface.BookClickInterface;
import com.example.dilraj.dbms.NewUserHandler;
import com.example.dilraj.dbms.R;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created by JatinThareja on 24-Feb-17.
 */

public class AddBookDialog extends DialogFragment {

    EditText id,name,author;
    Button addbook_button;
    NewUserHandler newUserHandler;
    BookClickInterface clickInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogTheme);
        newUserHandler = new NewUserHandler(getContext(), null, null, 1);
    }

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
                Books b = new Books(id.getText().toString(), name.getText().toString(), author.getText().toString(),0);
                try {
                    newUserHandler.addBook(b);
                    Toast.makeText(getContext(),"Book Added", Toast.LENGTH_SHORT).show();
                    dismiss();
                }catch (SQLIntegrityConstraintViolationException e){
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();
        clickInterface.OnClick(0);

    }

    public void setListener(BookClickInterface bookClickInterface) {
        clickInterface=bookClickInterface;
    }
}
