package org.moziqi.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.moziqi.login.LoginPresenter;
import org.moziqi.login.LoginPresenterImpl;
import org.moziqi.login.LoginView;
import org.moziqi.util.ProgressBarAsyncTask;

import moziqi.te.R;

public class LoginActivity extends ActionBarActivity implements LoginView, View.OnClickListener {

    private ProgressBar mProgressBar;
    private EditText mUsername;
    private EditText mPassword;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(this);
        mPresenter = new LoginPresenterImpl(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        mUsername.setError("username is null");
    }

    @Override
    public void setPasswordError() {
        mPassword.setError("password is null");
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        mPresenter.validateCredentials(mUsername.getText().toString(), mPassword.getText().toString());
    }
}
