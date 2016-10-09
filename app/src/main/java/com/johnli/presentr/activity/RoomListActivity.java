package com.johnli.presentr.activity;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.johnli.presentr.R;
import com.johnli.presentr.fragment.RoomListFragment;
import com.johnli.presentr.model.provider.RoomListProvider;

public class RoomListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RoomListFragment roomListFragment = RoomListFragment.newInstance();
        transaction.replace(android.R.id.content, roomListFragment, "");
        transaction.commit();
    }
}
