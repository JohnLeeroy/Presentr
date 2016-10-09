package com.johnli.presentr.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.johnli.presentr.R;
import com.johnli.presentr.account.AccountManager;
import com.johnli.presentr.account.AccountModuleInterface;
import com.johnli.presentr.account.FirebaseAccountModule;
import com.johnli.presentr.fragment.dialog.ProgressDialogFragment;
import com.johnli.presentr.presenter.LoginPresenterImpl;
import com.johnli.presentr.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView {
    String TAG = "MainActivity";
    private static final String PROGRESS_DIALOG_TAG = "MAIN_ACTIVITY_PROGRESS_DIALOG_TAG";
    AccountManager accountManager;
    LoginPresenterImpl presenter;
    private ProgressDialogFragment progressDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AccountModuleInterface accountModule = new FirebaseAccountModule();
        accountManager = new AccountManager(accountModule);
        presenter = new LoginPresenterImpl(accountManager);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attach(this);
        presenter.signInAnonymously();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detach();
    }

    @Override
    public void showActivityIndicator() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showDialogFragment();
            }
        });
    }

    @Override
    public void hideActivityIndicator() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(progressDialogFragment != null) {
                    progressDialogFragment.dismiss();
                }
            }
        });
    }

    private void showDialogFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        progressDialogFragment = (ProgressDialogFragment) fragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG);
        if (progressDialogFragment == null) {
            progressDialogFragment = new ProgressDialogFragment.Builder()
                    .setTitle("Signing in")
                    .create();
        } else {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.remove(progressDialogFragment);
            transaction.commit();
        }
        progressDialogFragment.show(fragmentManager, PROGRESS_DIALOG_TAG);
    }

    @Override
    public void goToRoomList() {
        Intent intent = new Intent(this, RoomListActivity.class);
        startActivity(intent);
    }
}
