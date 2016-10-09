package com.johnli.presentr.network;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by johnli on 10/9/16.
 */
public class FirebaseRequestManager {
    private DatabaseReference mDatabase;

    private final static FirebaseRequestManager requestManager = new FirebaseRequestManager();

    public static FirebaseRequestManager getInstance() { return requestManager; }

    public void sendRequest(FirebaseRequest request) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(request.getPath()).setValue(request.getData()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {

                }
            }
        });
    }
}
