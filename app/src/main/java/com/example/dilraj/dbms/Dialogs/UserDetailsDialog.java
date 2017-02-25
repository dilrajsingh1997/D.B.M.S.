package com.example.dilraj.dbms.Dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dilraj.dbms.R;

/**
 * Created by JatinThareja on 25-Feb-17.
 */

public class UserDetailsDialog extends DialogFragment {
    TextView phone,address;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogTheme);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_details_dialog,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phone= (TextView) view.findViewById(R.id.userdialog_phone);
        address= (TextView) view.findViewById(R.id.userdialog_address);
        Bundle bundle=getArguments();
        if (bundle!=null) {
            String ph = bundle.getString("PHONE");
            String ad = bundle.getString("ADD");
            phone.setText(ph);
            address.setText(ad);
        }
    }
}
