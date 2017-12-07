package com.aasys.sts.server;

import com.aasys.sts.server.postgres.PostgreSQLJDBC;
import com.aasys.sts.shared.LoginUser;
import com.aasys.sts.shared.core.User;
import com.aasys.sts.web.LoginService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by aasys on 10/27/15.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    //TODO; robust this and storage of username password
    @Override
    public User loginServer(LoginUser loginUser) throws Exception {
        Connection connection = PostgreSQLJDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE email='" +
                loginUser.getEmail() +
                "' AND passwd='" +
                loginUser.getPassword() + "'; ");

        if (rs.next()) {
            User user = new User();

            user.setUserId(rs.getInt("userid"));
            user.setName(rs.getString("name"));
            user.setAddress(rs.getString("address"));
            user.setPhonenum(rs.getString("phonenum"));
            user.setEmail(rs.getString("email"));
            //user.setPassword(rs.getNString("passwd"));
            rs.close();
            return user;

        }
        rs.close();
        throw new IllegalArgumentException("Invalid email/password!");
    }

}
