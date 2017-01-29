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

import com.example.faizrehman.online_parking_system.Adapters.View_booking_Adapter;
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
 * Created by faizrehman on 1/27/17.
 */

public class View_Booking_Admin extends  android.support.v4.app.Fragment{

    ListView listView ;
    View_booking_Adapter view_booking_adapter;
    ArrayList<Slot_ReservationModel> slot_reservationModels;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.view_bookings,null);
        super.onCreateView(inflater, container, savedInstanceState);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        slot_reservationModels = new ArrayList<>();
        listView = (ListView)view.findViewById(R.id.view_bookinglist);
        view_booking_adapter = new View_booking_Adapter(slot_reservationModels,getActivity());
        listView.setAdapter(view_booking_adapter);



        if(mAuth.getCurrentUser().getUid().toString()!=null) {
            myRef.child("user-booked-slots").child("Gulshan").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Slot_ReservationModel slot_reservationModel = data.getValue(Slot_ReservationModel.class);
                            slot_reservationModels.add(slot_reservationModel);
                            view_booking_adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            myRef.child("user-booked-slots").child("Saddar").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Slot_ReservationModel slot_reservationModel = data.getValue(Slot_ReservationModel.class);
                            slot_reservationModels.add(slot_reservationModel);
                            view_booking_adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            myRef.child("user-booked-slots").child("Nazimabad").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Slot_ReservationModel slot_reservationModel = data.getValue(Slot_ReservationModel.class);
                            slot_reservationModels.add(new Slot_ReservationModel(slot_reservationModel.getSelectdate(), slot_reservationModel.getStarttime(), slot_reservationModel.getSelectHour(), slot_reservationModel.getCurrentTime(), slot_reservationModel.getPlazaName(), slot_reservationModel.getSlot_no(), slot_reservationModel.isBooked(), slot_reservationModel.getCustName()));
                            view_booking_adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Remove");
                builder.setMessage("You want tO Cancle Reservation..?");
                builder.setPositiveButton("Unbooked", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myRef.child("user-booked-slots").child(slot_reservationModels.get(position).getPlazaName()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int i = 0;
                              for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    if (i == position) {
                                        DatabaseReference ref = dataSnapshot1.getRef();
                                        ref.removeValue();
                                        Toast.makeText(getActivity(), "Reservation Cancled", Toast.LENGTH_SHORT).show();
                                        slot_reservationModels.remove(position);
                                        view_booking_adapter.notifyDataSetChanged();
                                    }
                                  i++;
                              }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }

                        });
                    }
                });
                builder.setNegativeButton("Back", null);
                builder.show();

            }



        });

        return view;
    }
}
