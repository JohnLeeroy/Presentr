package com.johnli.presentr.model.provider;

import com.johnli.presentr.model.UserInterface;

/**
 * Created by johnli on 10/8/16.
 */
public interface UserProviderInterface {

    UserInterface getUser(String id);
}
