package com.johnli.presentr.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.johnli.presentr.R;
import com.johnli.presentr.fragment.RoomListFragment;
import com.johnli.presentr.fragment.dialog.EditTextDialogFragment;

public class RoomListActivity extends AppCompatActivity implements EditTextDialogFragment.EditTextDialogListener{

    private RoomListFragment roomListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        roomListFragment = RoomListFragment.newInstance();
        transaction.replace(android.R.id.content, roomListFragment, "");
        transaction.commit();
    }

    @Override
    public void onEditTextOk(String editTextString) {
        roomListFragment.createRoom(editTextString);
    }

    @Override
    public void onEditTextCancel() {

    }
}
