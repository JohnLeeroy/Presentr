package com.johnli.presentr.presenter;

import android.text.TextUtils;

import com.johnli.presentr.account.AccountManager;
import com.johnli.presentr.account.AuthenticationListener;
import com.johnli.presentr.model.UserInterface;
import com.johnli.presentr.util.StringUtil;
import com.johnli.presentr.view.RegisterView;

public class RegisterPresenter {

    RegisterView view;
    AccountManager accountManager;

    public RegisterPresenter(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void attach(RegisterView view)
    {
        this.view = view;
    }

    public void detach()
    {
        view = null;
    }

    public void registerUser(String email, String password, String confirmPassword) {
        if(!validateFields(email, password, confirmPassword)) {
            return;
        }

        accountManager.createAccount(email, password, new AuthenticationListener() {
            @Override
            public void onFinish(boolean isSuccess, UserInterface user) {
                if(view != null) {
                    view.hideActivityIndicator();
                    view.goToRoomList();
                }
            }
        });
        if(view != null) {
            view.showActivityIndicator();
        }
    }

    private boolean validateFields(String email, String password, String confirmPassword) {
        if(view == null) {
            return false;
        }

        if(TextUtils.isEmpty(email)  || !StringUtil.validEmail(email)) {
            view.showErrorMessage("Email is invalid");
            return false;
        }

        if(TextUtils.isEmpty(password)) {
            view.showErrorMessage("Password is invalid");
            return false;
        }

        if(TextUtils.isEmpty(confirmPassword)) {
            view.showErrorMessage("Confirm Password is invalid");
            return false;
        }

        if(!password.equals(confirmPassword)) {
            view.showErrorMessage("Password do not match");
            return false;
        }
        return true;
    }
}
