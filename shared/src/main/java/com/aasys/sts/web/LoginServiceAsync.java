package com.aasys.sts.web;

import com.aasys.sts.shared.LoginUser;
import com.aasys.sts.shared.core.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by aasys on 10/27/15.
 */
public interface LoginServiceAsync {

    void loginServer(LoginUser loginUser, AsyncCallback<User> callback);

}
