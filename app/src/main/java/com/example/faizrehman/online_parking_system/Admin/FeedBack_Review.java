package com.example.faizrehman.online_parking_system.Admin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.faizrehman.online_parking_system.Adapters.FeedBack_Adapter;
import com.example.faizrehman.online_parking_system.Models.Feedback_Model;
import com.example.faizrehman.online_parking_system.Models.Slot_ReservationModel;
import com.example.faizrehman.online_parking_system.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by faiz on 1/28/2017.
 */

public class FeedBack_Review extends android.support.v4.app.Fragment {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private ListView listView;
    private ArrayList<Feedback_Model> arrayList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.feedback_reply,null);
        listView = (ListView)view.findViewById(R.id.feedback_list);
        arrayList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        final FeedBack_Adapter feedBack_adapter = new FeedBack_Adapter(arrayList,getActivity());
        listView.setAdapter(feedBack_adapter);

        myRef.child("feedback").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Feedback_Model feedback_model = data.getValue(Feedback_Model.class);
                    arrayList.add(feedback_model);
                    feedBack_adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = (LayoutInflater.from(getActivity()));
                View vieww = layoutInflater.inflate(R.layout.reply_layout,null);
                builder.setTitle("FeedBack Reply");
                builder.setMessage("Please Share your Reply");
                builder.setNegativeButton("Reply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                builder.setPositiveButton("Back",null);
                builder.setView(vieww);
                builder.create().show();
            }
        });

        return view;

    }
}
