package com.johnli.presentr.model.provider;

import com.google.firebase.auth.FirebaseUser;
import com.johnli.presentr.model.FUser;
import com.johnli.presentr.model.UserInterface;

/**
 * Created by johnli on 10/8/16.
 */
public class FUserProvider implements UserProviderInterface {
    @Override
    public UserInterface getUser(String id) {
        //TODO
        return null;
    }

    public FUser createFirebaseUser(FirebaseUser user) {
        return new FUser(user);
    }

    private boolean doesUserExist(FirebaseUser user) {
        //TODO
        return false;
    }
}
