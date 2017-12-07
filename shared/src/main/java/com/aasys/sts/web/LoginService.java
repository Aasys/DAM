package com.aasys.sts.web;

import com.aasys.sts.shared.LoginUser;
import com.aasys.sts.shared.core.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Created by aasys on 10/27/15.
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
    User loginServer(LoginUser loginUser) throws Exception;
}
