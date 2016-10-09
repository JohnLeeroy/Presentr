package com.johnli.presentr.fragment.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ProgressDialogFragment extends DialogFragment {

    public static class Builder {
        private String title;
        private String message;
        private boolean isCancelable = false;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        public ProgressDialogFragment create() {
            Bundle args = new Bundle();
            args.putString(TITLE_KEY, title);
            args.putString(MESSAGE_KEY, message);
            args.putBoolean(CANCELLABLE_KEY, isCancelable);
            ProgressDialogFragment fragment = ProgressDialogFragment.newInstance();
            fragment.setArguments((args));
            return fragment;
        }
    }

    public static final String TAG = "com.unikey.kevo.ProgressDialogFragment";
    public static final String TITLE_KEY = "TITLE_KEY";
    public static final String MESSAGE_KEY = "MESSAGE_KEY";
    public static final String CANCELLABLE_KEY = "CANCELABLE_KEY";

    public ProgressDialogFragment() {
    }

    private String mTitle;
    private String mMessage;
    private boolean mIsCancellable;

    ProgressDialogListener mListener;

    public static ProgressDialogFragment newInstance() {
        return new ProgressDialogFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE_KEY, mTitle);
        outState.putString(MESSAGE_KEY, mMessage);
        outState.putBoolean(CANCELLABLE_KEY, mIsCancellable);
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
            mIsCancellable = bundle.getBoolean(CANCELLABLE_KEY, false);
        }

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle(mTitle);
        progressDialog.setMessage(mMessage);
        progressDialog.setCancelable(mIsCancellable);
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mListener != null) {
                    mListener.onDialogDismiss();
                }
            }
        });
        return progressDialog;
    }

    public void setProgressDialogListener(ProgressDialogListener listener) {
        mListener = listener;
    }

    public interface ProgressDialogListener {
        void onDialogDismiss();
    }
}
