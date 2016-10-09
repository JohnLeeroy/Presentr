package com.johnli.presentr.view;

/**
 * Created by johnli on 10/8/16.
 */
public interface RegisterView {

    void showActivityIndicator();
    void hideActivityIndicator();
    void goToRoomList();

    void showErrorMessage(String message);
}
