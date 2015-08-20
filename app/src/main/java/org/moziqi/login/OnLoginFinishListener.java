package org.moziqi.login;

/**
 * Created by moziqi on 15-8-11.
 */
public interface OnLoginFinishListener {
    public void onUsernameError();
    public void onPasswordError();
    public void onSuccess();
}
