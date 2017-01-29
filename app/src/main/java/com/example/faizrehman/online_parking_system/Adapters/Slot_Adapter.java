package com.example.faizrehman.online_parking_system.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.faizrehman.online_parking_system.Models.Slot_ReservationModel;
import com.example.faizrehman.online_parking_system.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by faizrehman on 1/27/17.
 */

public class Slot_Adapter extends BaseAdapter {

    private ArrayList<Slot_ReservationModel> slot_reservationModelArrayList ;
    private Context context;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    public Slot_Adapter(ArrayList<Slot_ReservationModel> slot_reservationModelArrayList, Context context) {
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
        View view = layoutInflater.inflate(R.layout.slot_view,null);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        TextView textViewSlotNum = (TextView) view.findViewById(R.id.slotnum);
        Button button = (Button)view.findViewById(R.id.isreservation_btn);


        textViewSlotNum.setText(String.valueOf(slot_reservationModelArrayList.get(position).getSlot_no()));



        if(slot_reservationModelArrayList.get(position).isBooked()){
            button.setText("Booked");
            button.setBackgroundColor(Color.parseColor("#EEFF41"));
            button.setEnabled(false);
            button.setFocusable(true);
        }else {
            button.setText("Available");
        }



        return view;
    }
}
