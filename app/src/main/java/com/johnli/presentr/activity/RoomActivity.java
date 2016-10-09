package com.johnli.presentr.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.johnli.presentr.R;
import com.johnli.presentr.fragment.RoomFragment;

public class RoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = savedInstanceState;
        if(savedInstanceState == null) {
            bundle = getIntent().getExtras();
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final RoomFragment roomFragment = RoomFragment.newInstance();
        if(bundle != null) {
            roomFragment.setArguments(bundle);
        }
        transaction.replace(R.id.room_fragment, roomFragment, "");
        transaction.commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomFragment.createQuestionPressed();
            }
        });
    }
}
