package com.example.faizrehman.online_parking_system.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.faizrehman.online_parking_system.Adapters.TabAdapter;
import com.example.faizrehman.online_parking_system.MainActivity;
import com.example.faizrehman.online_parking_system.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentArrayListl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        fragmentArrayListl = new ArrayList<>();
        tabLayout.addTab(tabLayout.newTab().setText("View Parking"));
        tabLayout.addTab(tabLayout.newTab().setText("Book Parking"));
        tabLayout.addTab(tabLayout.newTab().setText("View Booking"));
        tabLayout.addTab(tabLayout.newTab().setText("Feedback"));

        Feedback_Fragment feedback_fragment = new Feedback_Fragment();
        View_Booking view_booking = new View_Booking();
        View_Parking_Fragment view_parking_fragment = new View_Parking_Fragment();
        Book_Parking book_parking = new Book_Parking();





        fragmentArrayListl.add(view_parking_fragment);
        fragmentArrayListl.add(book_parking);
        fragmentArrayListl.add(view_booking);
        fragmentArrayListl.add(feedback_fragment);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), fragmentArrayListl);
        //is line se tablayout k neche jo shade araaha hai woh change hoga pageviewer k mutabik
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPager.setOffscreenPageLimit(0);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder.setTitle("Exit !!");
                builder.setMessage("you want Logout..??");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(UserActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setPositiveButton("Back",null);
                builder.create().show();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);


        return true;
    }
}
