package com.aasys.sts.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by aasys on 10/27/15.
 */
public class LoginUser implements IsSerializable {
    private String _email;
    private String _password;

    public LoginUser(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public LoginUser() {
    }

    public String getEmail() {
        return _email;
    }

    public String getPassword() {
        return _password;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public void setPassword(String password) {
        _password = password;
    }
}

