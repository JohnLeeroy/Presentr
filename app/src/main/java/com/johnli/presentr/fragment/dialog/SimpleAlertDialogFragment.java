package com.johnli.presentr.fragment.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by johnli on 9/23/16.
 * Contains title, message, and OK button
 */
public class SimpleAlertDialogFragment extends DialogFragment {
    public static final String TITLE_KEY = "TITLE_KEY";
    public static final String MESSAGE_KEY = "MESSAGE_KEY";

    private String mTitle;
    private String mMessage;


    public static class Builder {
        private String title;
        private String message;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public SimpleAlertDialogFragment create() {
            Bundle args = new Bundle();
            args.putString(TITLE_KEY, title);
            args.putString(MESSAGE_KEY, message);
            SimpleAlertDialogFragment fragment = SimpleAlertDialogFragment.newInstance();
            fragment.setArguments((args));
            return fragment;
        }
    }

    public static SimpleAlertDialogFragment newInstance() {
        SimpleAlertDialogFragment fragment = new SimpleAlertDialogFragment();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = savedInstanceState;
        if (bundle == null) {
            bundle = getArguments();
        }

        if (bundle != null) {
            mTitle = bundle.getString(TITLE_KEY);
            mMessage = bundle.getString(MESSAGE_KEY);
        }

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(mTitle)
                .setMessage(mMessage)
                .setPositiveButton(android.R.string.ok, null)
                .create();

        return dialog;
    }
}

