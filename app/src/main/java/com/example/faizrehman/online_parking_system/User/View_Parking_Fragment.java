package com.example.faizrehman.online_parking_system.User;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faizrehman.online_parking_system.R;

/**
 * Created by faizrehman on 1/27/17.
 */

public class View_Parking_Fragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.view_parking_fragment,null);

         super.onCreateView(inflater, container, savedInstanceState);
        return view;

    }
}
