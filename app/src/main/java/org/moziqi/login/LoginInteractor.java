package org.moziqi.login;

/**
 * Created by moziqi on 15-8-11.
 */
public interface LoginInteractor {
    public void login(String username, String password,
      OnLoginFinishListener onLoginFinishListener);
}
