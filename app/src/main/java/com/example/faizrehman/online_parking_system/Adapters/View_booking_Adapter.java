package com.example.faizrehman.online_parking_system.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.faizrehman.online_parking_system.Models.Slot_ReservationModel;
import com.example.faizrehman.online_parking_system.R;

import java.util.ArrayList;

/**
 * Created by faizrehman on 1/27/17.
 */

public class View_booking_Adapter extends BaseAdapter {

    ArrayList<Slot_ReservationModel> slot_reservationModelArrayList;
    Context context;

    public View_booking_Adapter(ArrayList<Slot_ReservationModel> slot_reservationModelArrayList, Context context) {
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
        View view = layoutInflater.inflate(R.layout.view_booking_adapter,null);

        TextView textplazaname = (TextView)view.findViewById(R.id.plaza_name);
        TextView slotNumber = (TextView)view.findViewById(R.id.slot_noo);
        TextView date = (TextView)view.findViewById(R.id.adatee);
        TextView startdate = (TextView)view.findViewById(R.id.start_time);
        TextView endtime = (TextView)view.findViewById(R.id.end_time);
        TextView totalhour = (TextView)view.findViewById(R.id.total_hour);


        textplazaname.setText(slot_reservationModelArrayList.get(position).getPlazaName());

        slotNumber.setText(String.valueOf(slot_reservationModelArrayList.get(position).getSlot_no()));
        date.setText(slot_reservationModelArrayList.get(position).getCurrentTime());
        startdate.setText(String.valueOf(slot_reservationModelArrayList.get(position).getStarttime()));
        endtime.setText(String.valueOf(slot_reservationModelArrayList.get(position).getStarttime()+slot_reservationModelArrayList.get(position).getSelectHour()));
        totalhour.setText(String.valueOf(slot_reservationModelArrayList.get(position).getSelectHour()));




        return view;
    }
}
