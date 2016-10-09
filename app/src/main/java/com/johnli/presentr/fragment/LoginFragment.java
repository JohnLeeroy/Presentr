package com.johnli.presentr.fragment;

import android.content.Context;
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
import android.widget.TextView;

import com.johnli.presentr.R;
import com.johnli.presentr.account.AccountManager;
import com.johnli.presentr.account.AccountModuleInterface;
import com.johnli.presentr.account.FirebaseAccountModule;
import com.johnli.presentr.activity.RoomListActivity;
import com.johnli.presentr.fragment.dialog.ProgressDialogFragment;
import com.johnli.presentr.presenter.LoginPresenterImpl;
import com.johnli.presentr.view.LoginView;

public class LoginFragment extends Fragment implements View.OnClickListener, LoginView {

    private static final String PROGRESS_DIALOG_TAG = "LOGIN_PROGRESS_DIALOG_TAG";

    private LoginDelegate loginDelegate;
    private ProgressDialogFragment progressDialogFragment;

    EditText emailField;
    EditText passwordField;
    Button signInButton;
    Button anonSignInButton;
    TextView signUpLabel;

    LoginPresenterImpl presenter;
    AccountManager accountManager;

    public static final String TAG = "LoginFragment";

    public LoginFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccountModuleInterface accountModule = new FirebaseAccountModule();
        accountManager = new AccountManager(accountModule);
        presenter = new LoginPresenterImpl(accountManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        bindUi(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == signInButton.getId()) {
            presenter.signIn(emailField.getText().toString(), passwordField.getText().toString());
        } else if (id == signUpLabel.getId()) {
            loginDelegate.transitionToRegister();
        } else if (id == anonSignInButton.getId()) {
            presenter.signInAnonymously();
        }
    }

    private void bindUi(View view) {
        emailField = (EditText) view.findViewById(R.id.emailField);
        passwordField = (EditText) view.findViewById(R.id.passwordField);
        signInButton = (Button) view.findViewById(R.id.loginButton);
        anonSignInButton = (Button) view.findViewById(R.id.anonymousButton);
        signUpLabel = (TextView) view.findViewById(R.id.signInLabel);

        signInButton.setOnClickListener(this);
        signUpLabel.setOnClickListener(this);
        anonSignInButton.setOnClickListener(this);
    }


    @Override
    public void showActivityIndicator() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showDialogFragment();
            }
        });
    }

    @Override
    public void hideActivityIndicator() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(progressDialogFragment != null) {
                    progressDialogFragment.dismiss();
                }
            }
        });
    }

    @Override
    public void goToRoomList() {
        Intent intent = new Intent(getActivity(), RoomListActivity.class);
        startActivity(intent);
    }

    private void showDialogFragment() {
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginDelegate) {
            loginDelegate = (LoginDelegate) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LoginDelegate");
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

    public interface LoginDelegate {
        void transitionToRegister();
    }
}