package com.johnli.presentr.account;

import com.johnli.presentr.model.provider.UserProviderInterface;

/**
 * Created by johnli on 10/8/16.
 */
public interface AccountModuleInterface {

    void signInAnonymously(AuthenticationListener listener);
    void signIn(String email, String password, AuthenticationListener listener);
    void signOut();
    void createAccount(String email, String password, AuthenticationListener listener);
    UserProviderInterface getUserProvider();
}
