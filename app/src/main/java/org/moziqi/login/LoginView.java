package org.moziqi.login;

/**
 * Created by moziqi on 15-8-11.
 */
public interface LoginView {

    public void showProgress();

    public void hideProgress();

    public void setUsernameError();

    public void setPasswordError();

    public void navigateToHome();
}
