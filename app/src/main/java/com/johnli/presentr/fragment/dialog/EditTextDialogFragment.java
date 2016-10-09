package com.johnli.presentr.fragment.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.johnli.presentr.R;

public class EditTextDialogFragment extends DialogFragment {

    String mTitle;
    EditTextDialogListener listener;

    public static final String TITLE_KEY = "TITLE_KEY";
    private View view;

    public EditTextDialogFragment() {
        // Required empty public constructor
    }

    public static EditTextDialogFragment newInstance() {
        EditTextDialogFragment fragment = new EditTextDialogFragment();
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE_KEY, mTitle);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = savedInstanceState;
        if(bundle == null) {
            bundle = getArguments();
        }

        if(bundle != null) {
            mTitle = bundle.getString(TITLE_KEY);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_edit_text_dialog, null);
        builder.setTitle(mTitle);
        builder.setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        onPositiveClick();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onNegativeClick();
                    }
                });
        return builder.create();
    }

    private void onNegativeClick() {
        if(listener != null) {
            listener.onEditTextCancel();
        }
    }

    void onPositiveClick() {
        EditText editText = (EditText) view.findViewById(R.id.edit_text);
        if(listener != null) {
            listener.onEditTextOk(editText.getText().toString());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (EditTextDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement EditTextDialogListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface EditTextDialogListener {
        void onEditTextOk(String editTextString);
        void onEditTextCancel();
    }
}
