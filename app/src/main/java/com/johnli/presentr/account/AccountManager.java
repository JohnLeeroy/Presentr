package com.johnli.presentr.account;

import android.support.annotation.NonNull;

import com.johnli.presentr.model.UserInterface;

/**
 * Created by johnli on 10/8/16.
 */
public class AccountManager {

    AccountModuleInterface accountModule;
    UserInterface currentUser;

    public AccountManager(AccountModuleInterface accountModule) {
        this.accountModule = accountModule;
    }

    public void signInAnonymously(@NonNull final AuthenticationListener listener) {
        AuthenticationListener listenerWrapper = new AuthenticationListener() {
            @Override
            public void onFinish(boolean isSuccess, UserInterface user) {
                if(isSuccess && user != null) {
                    currentUser = user;
                }
                if(listener != null) {
                    listener.onFinish(isSuccess, user);
                }
            }
        };
        accountModule.signInAnonymously(listenerWrapper);
    }

    public void signIn(String email, String password, @NonNull final AuthenticationListener listener) {

        AuthenticationListener listenerWrapper = new AuthenticationListener() {
            @Override
            public void onFinish(boolean isSuccess, UserInterface user) {
                if(isSuccess && user != null) {
                    currentUser = user;
                }
                if(listener != null) {
                    listener.onFinish(isSuccess, user);
                }
            }
        };
        accountModule.signIn(email, password, listenerWrapper);
    }

    public void createAccount(String email, String password, AuthenticationListener listener) {
        accountModule.createAccount(email, password, listener);
    }

    public UserInterface getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        accountModule.signOut();
    }
}
