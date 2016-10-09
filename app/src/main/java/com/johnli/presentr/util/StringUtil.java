package com.johnli.presentr.util;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by johnli on 10/9/16.
 */
public class StringUtil {

    public static boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

}
