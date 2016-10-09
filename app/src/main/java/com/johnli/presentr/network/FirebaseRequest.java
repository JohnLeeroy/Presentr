package com.johnli.presentr.network;

/**
 * Created by johnli on 10/9/16.
 */
public interface FirebaseRequest<T> {

    String getPath();
    T getData();
}
