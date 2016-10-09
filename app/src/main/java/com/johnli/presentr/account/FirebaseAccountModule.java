package com.johnli.presentr.account;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.johnli.presentr.model.FBUser;
import com.johnli.presentr.model.provider.FUserProvider;
import com.johnli.presentr.model.provider.UserProviderInterface;

/**
 * Created by johnli on 10/8/16.
 */
public class FirebaseAccountModule implements AccountModuleInterface {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final String TAG = "FirebaseAccountModule";
    FUserProvider firebaseUserProvider;

    public FirebaseAccountModule() {
        initFirebaseAuth();
        firebaseUserProvider = new FUserProvider();
    }

    void initFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void signInAnonymously(final AuthenticationListener listener) {
        mAuth.signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInAnonymously:onComplete:" + task.isSuccessful());
                        if(listener != null) {
                            FBUser user = firebaseUserProvider.createFirebaseUser(mAuth.getCurrentUser());
                            listener.onFinish(task.isSuccessful(), user);
                        }
                    }
                });
    }

    @Override
    public void signIn(String email, String password, final AuthenticationListener listener) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FBUser user = firebaseUserProvider.createFirebaseUser(mAuth.getCurrentUser());
                listener.onFinish(task.isSuccessful(), user);
            }
        });
    }

    @Override
    public void signOut() {
        mAuth.signOut();
    }

    @Override
    public void createAccount(String email, String password, final AuthenticationListener listener) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FBUser user = firebaseUserProvider.createFirebaseUser(mAuth.getCurrentUser());
                listener.onFinish(task.isSuccessful(), user);
            }
        });
    }

    @Override
    public UserProviderInterface getUserProvider() {
        return firebaseUserProvider;
    }
}
