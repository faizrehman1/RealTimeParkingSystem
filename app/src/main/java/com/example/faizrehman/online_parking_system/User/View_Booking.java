package com.example.faizrehman.online_parking_system.User;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by faizrehman on 1/27/17.
 */

public class View_Booking  extends  android.support.v4.app.Fragment{

    ListView listView ;
    private Calendar myCalendar;
    View_booking_Adapter view_booking_adapter;
    ArrayList<Slot_ReservationModel> slot_reservationModels;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private TextView textView;
    private Button search_btn;


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
        textView = (TextView)view.findViewById(R.id.date_select);
        search_btn = (Button)view.findViewById(R.id.search_btn);
        view_booking_adapter = new View_booking_Adapter(slot_reservationModels,getActivity());
        listView.setAdapter(view_booking_adapter);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mAuth.getCurrentUser().getUid().toString()!=null && !textView.getText().toString().matches("")) {
                    myRef.child("user-booked-slots").child("Gulshan").child(textView.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null) {
                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                    if(data.child("custName").getValue().toString().matches(mAuth.getCurrentUser().getEmail().toString())) {
                                        Slot_ReservationModel slot_reservationModel = data.getValue(Slot_ReservationModel.class);
                                        slot_reservationModels.add(slot_reservationModel);
                                        view_booking_adapter.notifyDataSetChanged();
                                    }
                                }
                            }else{
                                Toast.makeText(getActivity(),"You Don't Have Booking",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                    myRef.child("user-booked-slots").child("Saddar").child(textView.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null) {
                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                    if(data.child("custName").getValue().toString().matches(mAuth.getCurrentUser().getEmail().toString())) {
                                        Slot_ReservationModel slot_reservationModel = data.getValue(Slot_ReservationModel.class);
                                        slot_reservationModels.add(slot_reservationModel);
                                        view_booking_adapter.notifyDataSetChanged();
                                    }
                                }
                            }else{
                                Toast.makeText(getActivity(),"You Don't Have Booking",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    myRef.child("user-booked-slots").child("Nazimabad").child(textView.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null) {
                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                    if(data.child("custName").getValue().toString().matches(mAuth.getCurrentUser().getEmail().toString())) {
                                        Slot_ReservationModel slot_reservationModel = data.getValue(Slot_ReservationModel.class);
                                        slot_reservationModels.add(slot_reservationModel);
                                        view_booking_adapter.notifyDataSetChanged();
                                    }
                                }
                            }else{
                                Toast.makeText(getActivity(),"You Don't Have Booking",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{
                  Toast.makeText(getActivity(),"Select date Please !!",Toast.LENGTH_SHORT).show();
                }
            }
        });


        myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
              //  view.setMinDate(System.currentTimeMillis() - 1000);
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub



                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

               // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

//
//

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if(!textView.getText().toString().matches("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Remove");
                builder.setMessage("You want tO Cancle Reservation..?");
                builder.setPositiveButton("Unbooked", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myRef.child("user-booked-slots").child(slot_reservationModels.get(position).getPlazaName()).child(textView.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                    builder.setNeutralButton("FeedBack", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(getActivity().getSupportFragmentManager().findFragmentById(R.id.container) != null) {
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction().
                                        remove(getActivity().getSupportFragmentManager().findFragmentById(R.id.container)).commit();
                            }
                        }
                    });
                    builder.show();

            }else{
                    Toast.makeText(getActivity(),"Please Select Date first !!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
    private void updateLabel() {

        //   String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        textView.setText(sdf.format(myCalendar.getTime()));
    }
}
