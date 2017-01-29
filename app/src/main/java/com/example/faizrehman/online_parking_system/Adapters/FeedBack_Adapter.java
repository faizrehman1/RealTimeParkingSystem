package com.example.faizrehman.online_parking_system.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.faizrehman.online_parking_system.Models.Feedback_Model;
import com.example.faizrehman.online_parking_system.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by faizrehman on 1/27/17.
 */

public class FeedBack_Adapter extends BaseAdapter {

    private ArrayList<Feedback_Model> slot_reservationModelArrayList ;
    private Context context;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    public FeedBack_Adapter(ArrayList<Feedback_Model> slot_reservationModelArrayList, Context context) {
        this.slot_reservationModelArrayList = slot_reservationModelArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return slot_reservationModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return slot_reservationModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.feedback_reiview,null);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        TextView textViewuserName = (TextView) view.findViewById(R.id.username);
        TextView textViewSlotNum = (TextView) view.findViewById(R.id.messagee);

        textViewSlotNum.setText("Customer Email :"+slot_reservationModelArrayList.get(position).getMessage());
        textViewuserName.setText("Customer Message :"+slot_reservationModelArrayList.get(position).getUid());

        return view;
    }
}
