package com.johnli.presentr.model;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by johnli on 9/29/16.
 */
public class FUser implements UserInterface {

    FirebaseUser user;

    public FUser(FirebaseUser user) {
        this.user = user;
    }

    @Override
    public String getId() {
        return user.getUid();
    }

    @Override
    public String getName() {
        return user.getDisplayName();
    }
}
