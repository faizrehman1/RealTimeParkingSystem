package com.example.faizrehman.online_parking_system.User;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faizrehman.online_parking_system.Adapters.Slot_Adapter;
import com.example.faizrehman.online_parking_system.Models.Slot_ReservationModel;
import com.example.faizrehman.online_parking_system.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by faizrehman on 1/27/17.
 */

public class Book_Parking extends android.support.v4.app.Fragment {

    private TextView edittext;
    private Calendar myCalendar;
    private CheckBox park1, park2, park3;
    private ListView listView;
    private RelativeLayout relativeLayout;
    private Slot_Adapter slot_adapter;
    private ArrayList<Slot_ReservationModel> slot_viewModelArrayList;
    private Button btn;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private Spinner spinnertime, spinnerhours;
    private ArrayList<String> stringArrayList;
    private String plazaName = "";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.book_parking, null);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        edittext = (TextView) view.findViewById(R.id.datee);
        park1 = (CheckBox) view.findViewById(R.id.park_1);
        park2 = (CheckBox) view.findViewById(R.id.park_2);
        park3 = (CheckBox) view.findViewById(R.id.park_3);
        spinnerhours = (Spinner) view.findViewById(R.id.select_hour);
        spinnertime = (Spinner) view.findViewById(R.id.slect_time);
        btn = (Button)view.findViewById(R.id.s_slot);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.slot_container);
        listView = (ListView) view.findViewById(R.id.listt);
        slot_viewModelArrayList = new ArrayList<>();
        slot_adapter = new Slot_Adapter(slot_viewModelArrayList, getActivity());
        listView.setAdapter(slot_adapter);


        int spinner_pos = spinnertime.getSelectedItemPosition();
        String[] size_values = getResources().getStringArray(R.array.times);
        final int size = Integer.valueOf(size_values[spinner_pos]);

        int spinner_poss = spinnerhours.getSelectedItemPosition();
        String[] size_valuess = getResources().getStringArray(R.array.stimes);
        final int sizee = Integer.valueOf(size_valuess[spinner_poss]);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(!edittext.getText().toString().matches("")){
                if (park1.isChecked()) {
                    plazaName = "Gulshan";
                    relativeLayout.setVisibility(View.VISIBLE);
                    myRef.child("user-booked-slots").child("Gulshan").child(edittext.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            slot_viewModelArrayList.clear();
                            if (dataSnapshot.getValue() != null) {
                                // for (DataSnapshot data : dataSnapshot.getChildren()) {
                                //   Slot_ReservationModel slot_reservationModel = data.getValue(Slot_ReservationModel.class);
                                //    HashMap<String,Integer> hashMap = (HashMap)data.getValue();
                                //  String className = data.getValue().getClass().getSimpleName();
                                for (int i = 0; i <= 4; i++) {
                                    if (dataSnapshot.hasChild(String.valueOf(i))) {
                                        slot_viewModelArrayList.add(new Slot_ReservationModel(i, true));
                                    } else {
                                        slot_viewModelArrayList.add(new Slot_ReservationModel(i, false));
                                    }
                                    //  slot_adapter.notifyDataSetChanged();
                                    //   }
                                    slot_adapter.notifyDataSetChanged();
                                }
                            } else {

                                slot_viewModelArrayList.add(new Slot_ReservationModel(0, false));
                                slot_viewModelArrayList.add(new Slot_ReservationModel(1, false));
                                slot_viewModelArrayList.add(new Slot_ReservationModel(2, false));
                                slot_viewModelArrayList.add(new Slot_ReservationModel(3, false));
                                slot_viewModelArrayList.add(new Slot_ReservationModel(4, false));
                                slot_adapter.notifyDataSetChanged();


                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else if (park2.isChecked()) {
                    plazaName = "Saddar";
                    relativeLayout.setVisibility(View.VISIBLE);
                    myRef.child("user-booked-slots").child("Saddar").child(edittext.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            slot_viewModelArrayList.clear();
                            if (dataSnapshot.getValue() != null) {
                                for (int i = 0; i <= 4; i++) {
                                    if (dataSnapshot.hasChild(String.valueOf(i))) {
                                        slot_viewModelArrayList.add(new Slot_ReservationModel(i, true));
                                    } else {
                                        slot_viewModelArrayList.add(new Slot_ReservationModel(i, false));
                                    }
                                    //  slot_adapter.notifyDataSetChanged();
                                    //   }
                                    slot_adapter.notifyDataSetChanged();
                                }

                            } else {

                                slot_viewModelArrayList.add(new Slot_ReservationModel(0, false));
                                slot_viewModelArrayList.add(new Slot_ReservationModel(1, false));
                                slot_viewModelArrayList.add(new Slot_ReservationModel(2, false));
                                slot_viewModelArrayList.add(new Slot_ReservationModel(3, false));
                                slot_viewModelArrayList.add(new Slot_ReservationModel(4, false));
                                slot_adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                } else if (park3.isChecked()) {
                    plazaName = "Nazimabad";
                    relativeLayout.setVisibility(View.VISIBLE);
                    myRef.child("user-booked-slots").child("Nazimabad").child(edittext.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            slot_viewModelArrayList.clear();
                            if (dataSnapshot.getValue() != null) {
                                for (int i = 0; i <= 4; i++) {
                                    if (dataSnapshot.hasChild(String.valueOf(i))) {
                                        slot_viewModelArrayList.add(new Slot_ReservationModel(i, true));
                                    } else {
                                        slot_viewModelArrayList.add(new Slot_ReservationModel(i, false));
                                    }
                                    //  slot_adapter.notifyDataSetChanged();
                                    //   }
                                    slot_adapter.notifyDataSetChanged();
                                }
                                //  slot_adapter.notifyDataSetChanged();
                            } else {

                                slot_viewModelArrayList.add(new Slot_ReservationModel(0, false));
                                slot_viewModelArrayList.add(new Slot_ReservationModel(1, false));
                                slot_viewModelArrayList.add(new Slot_ReservationModel(2, false));
                                slot_viewModelArrayList.add(new Slot_ReservationModel(3, false));
                                slot_viewModelArrayList.add(new Slot_ReservationModel(4, false));
                                slot_adapter.notifyDataSetChanged();


                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else {
                    Toast.makeText(getActivity(), "Select Plaza", Toast.LENGTH_SHORT).show();
                }
            }else{
                  Toast.makeText(getActivity(), "Select date for show Slots.", Toast.LENGTH_SHORT).show();

              }
            }
        });

        park1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    park2.setChecked(false);
                    park3.setChecked(false);
                }
                relativeLayout.setVisibility(View.GONE);
            }
        });
        park2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    park1.setChecked(false);
                    park3.setChecked(false);

                }
                relativeLayout.setVisibility(View.GONE);
            }
        });
        park3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    park1.setChecked(false);
                    park2.setChecked(false);

                }
                relativeLayout.setVisibility(View.GONE);
            }
        });

        myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                view.setMinDate(System.currentTimeMillis() - 1000);
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                relativeLayout.setVisibility(View.GONE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                String myFormat = "MM/dd/yy"; //In which you need put here
                final SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Booking Area");
                builder.setMessage("You want to Book that slot?");
                builder.setNegativeButton("Booked", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (edittext.getText().toString() ==null) {
                            Toast.makeText(getActivity(), "Please select Date", Toast.LENGTH_SHORT).show();
                        } else if (edittext.getText().toString().matches("")) {
                            Toast.makeText(getActivity(), "Please select Date", Toast.LENGTH_SHORT).show();
                        } else {
                            myRef.child("user-booked-slots").child(plazaName).child(edittext.getText().toString()).child(String.valueOf(position)).setValue(new Slot_ReservationModel(sdf.format(myCalendar.getTime()).toString(), size, sizee, String.valueOf(System.currentTimeMillis()), plazaName, slot_viewModelArrayList.get(position).getSlot_no(), true, mAuth.getCurrentUser().getEmail().toString(),mAuth.getCurrentUser().getUid()));
                            slot_viewModelArrayList.remove(position);
                            slot_viewModelArrayList.add(new Slot_ReservationModel(position, true));
                            slot_adapter.notifyDataSetChanged();
                        }
                    }
                });
                builder.setPositiveButton("Back", null);
                builder.create().show();

            }
        });


        return view;
    }

    private void updateLabel() {

     //   String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        edittext.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

}
