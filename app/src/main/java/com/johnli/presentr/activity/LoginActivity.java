package com.johnli.presentr.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.johnli.presentr.R;
import com.johnli.presentr.account.AccountManager;
import com.johnli.presentr.account.AccountModuleInterface;
import com.johnli.presentr.account.FirebaseAccountModule;
import com.johnli.presentr.fragment.LoginFragment;
import com.johnli.presentr.fragment.RegisterFragment;

public class LoginActivity extends AppCompatActivity implements LoginFragment.LoginDelegate{
    String TAG = "MainActivity";
    AccountManager accountManager;

    LoginFragment loginFragment;
    RegisterFragment registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AccountModuleInterface accountModule = new FirebaseAccountModule();
        accountManager = new AccountManager(accountModule);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if(savedInstanceState != null) {
            loginFragment =  (LoginFragment) fragmentManager.findFragmentByTag(LoginFragment.TAG);
        } else {
            loginFragment = LoginFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(android.R.id.content, loginFragment, LoginFragment.TAG);
            transaction.commit();
        }
    }

    @Override
    public void transitionToRegister() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        registerFragment = (RegisterFragment) fragmentManager.findFragmentByTag(RegisterFragment.TAG);
        if(registerFragment == null) {
            registerFragment = RegisterFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(android.R.id.content, registerFragment, RegisterFragment.TAG);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
