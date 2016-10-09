package com.johnli.presentr.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.johnli.presentr.R;
import com.johnli.presentr.fragment.RoomFragment;
import com.johnli.presentr.fragment.RoomListFragment;

public class RoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle bundle = savedInstanceState;
        if(savedInstanceState == null) {
            bundle = getIntent().getExtras();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RoomFragment roomFragment = RoomFragment.newInstance();
        if(bundle != null) {
            roomFragment.setArguments(bundle);
        }
        transaction.replace(R.id.room_fragment, roomFragment, "");
        transaction.commit();
    }

}
