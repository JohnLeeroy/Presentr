package com.johnli.presentr.account;

import com.johnli.presentr.model.UserInterface;

/**
 * Created by johnli on 10/8/16.
 */
public interface AuthenticationListener {
    void onFinish(boolean isSuccess, UserInterface user);
}
