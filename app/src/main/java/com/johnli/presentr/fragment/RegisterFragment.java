package com.johnli.presentr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.johnli.presentr.R;
import com.johnli.presentr.account.AccountManager;
import com.johnli.presentr.account.AccountModuleInterface;
import com.johnli.presentr.account.FirebaseAccountModule;
import com.johnli.presentr.activity.RoomListActivity;
import com.johnli.presentr.presenter.RegisterPresenter;
import com.johnli.presentr.view.RegisterView;

public class RegisterFragment extends Fragment implements View.OnClickListener, RegisterView {
    private static final String PROGRESS_DIALOG_TAG = "REGISTER_PROGRESS_DIALOG_TAG";
    public static final String TAG = "RegisterFragment";

    private RegisterPresenter presenter;
    private ProgressDialogFragment progressDialogFragment;

    EditText emailField;
    EditText passwordField;
    EditText confirmPasswordField;
    Button registerButton;

    AccountManager accountManager;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccountModuleInterface accountModule = new FirebaseAccountModule();
        accountManager = new AccountManager(accountModule);
        presenter = new RegisterPresenter(accountManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        bindUi(view);
        return view;
    }

    void bindUi(View view) {
        emailField = (EditText) view.findViewById(R.id.emailField);
        passwordField = (EditText) view.findViewById(R.id.passwordField);
        confirmPasswordField = (EditText) view.findViewById(R.id.confirmPasswordField);
        registerButton = (Button) view.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
    }

    private void showProgressDialogFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        progressDialogFragment = (ProgressDialogFragment) fragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG);
        if (progressDialogFragment == null) {
            progressDialogFragment = new ProgressDialogFragment.Builder()
                    .setMessage("Signing in")
                    .create();
        } else {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.remove(progressDialogFragment);
            transaction.commit();
        }
        progressDialogFragment.show(fragmentManager, PROGRESS_DIALOG_TAG);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == registerButton.getId()) {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            String confirmPassword = confirmPasswordField.getText().toString();
            presenter.registerUser(email, password, confirmPassword);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attach(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detach();
    }

    @Override
    public void showActivityIndicator() {
        showProgressDialogFragment();
    }

    @Override
    public void hideActivityIndicator() {
        if(progressDialogFragment != null) {
            progressDialogFragment.dismiss();
        }
    }

    @Override
    public void goToRoomList() {
        Intent intent = new Intent(getActivity(), RoomListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
