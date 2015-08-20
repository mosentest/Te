package org.moziqi.login;

/**
 * Created by moziqi on 15-8-12.
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginInteractor = new LoginInteractorImpl();
        this.loginView = loginView;
    }

    @Override
    public void validateCredentials(String username, String password) {
        loginView.showProgress();
        loginInteractor.login(username, password, this);
    }

    @Override
    public void onUsernameError() {
        loginView.setUsernameError();
        loginView.hideProgress();
    }

    @Override
    public void onPasswordError() {
        loginView.setPasswordError();
        loginView.hideProgress();
    }

    @Override
    public void onSuccess() {
        loginView.navigateToHome();
    }
}
