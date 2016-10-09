package com.johnli.presentr.presenter;

import com.johnli.presentr.view.LoginView;

/**
 * Created by johnli on 10/8/16.
 */
public interface LoginPresenterInterface {

    void attach(LoginView loginView);
    void detach();

    void signInAnonymously();
    void signIn(String email, String password);
}
