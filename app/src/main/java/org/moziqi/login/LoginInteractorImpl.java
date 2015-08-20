package org.moziqi.login;

import android.os.Handler;
import android.text.TextUtils;

import org.w3c.dom.Text;

/**
 * Created by moziqi on 15-8-11.
 */
public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void login(final String username, final String password, final OnLoginFinishListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(username)) {
                    listener.onUsernameError();
                    error = true;
                }
                if (TextUtils.isEmpty(password)) {
                    listener.onPasswordError();
                    error = true;
                }
                if (!error) {
                    listener.onSuccess();
                }
            }
        }, 2000);
    }
}
