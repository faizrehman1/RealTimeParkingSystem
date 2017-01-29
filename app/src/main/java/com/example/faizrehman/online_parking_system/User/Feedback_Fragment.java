package com.example.faizrehman.online_parking_system.User;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.faizrehman.online_parking_system.Models.Feedback_Model;
import com.example.faizrehman.online_parking_system.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by faizrehman on 1/27/17.
 */

public class Feedback_Fragment extends android.support.v4.app.Fragment {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    EditText editText;
    Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.feedback_fragment,null);
        super.onCreateView(inflater, container, savedInstanceState);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();


        editText = (EditText)view.findViewById(R.id.feedback_field);
        button = (Button)view.findViewById(R.id.sbmit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child("feedback").push().setValue(new Feedback_Model(mAuth.getCurrentUser().getEmail().toString(),editText.getText().toString()));

                editText.setText("");
            }
        });

        return view;

    }
}
