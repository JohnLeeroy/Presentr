package com.johnli.presentr.presenter;

import com.johnli.presentr.model.UserInterface;
import com.johnli.presentr.account.AccountManager;
import com.johnli.presentr.account.AuthenticationListener;
import com.johnli.presentr.view.LoginView;

/**
 * Created by johnli on 10/8/16.
 */
public class LoginPresenterImpl implements LoginPresenterInterface, AuthenticationListener {

    AccountManager accountManager;
    LoginView view;

    public LoginPresenterImpl(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public void attach(LoginView loginView) {
        view = loginView;
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void signInAnonymously() {
        accountManager.signInAnonymously(this);
        if(view != null) {
            view.showActivityIndicator();
        }
    }

    @Override
    public void signIn(String email, String password) {
        accountManager.signIn(email, password, this);
        if(view != null) {
            view.showActivityIndicator();
            view.goToRoomList();
        }
    }

    @Override
    public void onFinish(boolean isSuccess, UserInterface user) {
        if(view != null) {
            view.hideActivityIndicator();
            if(isSuccess) {
                view.goToRoomList();
            }
        }
    }
}
