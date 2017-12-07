package com.aasys.sts.server.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PostgreSQLJDBC {

    private static String DB_CONFIG_BUNDLE = "com.aasys.sts.dbconfig";
    private static String DB_USER = "dbUser";
    private static String DB_PASSWORD = "dbPass";
    private static String DB_SID = "dbSID";
    private static String DB_HOST = "dbHost";
    private static String DB_PORT = "dbPort";

    private static Connection _connection = null;

    public static Connection getConnection()  throws SQLException,
            ClassNotFoundException{

        ResourceBundle dbconfig = ResourceBundle.getBundle(DB_CONFIG_BUNDLE);

        if (_connection == null)
            _connection = openDBConnection(dbconfig.getString(DB_USER),
                                           dbconfig.getString(DB_PASSWORD),
                                           dbconfig.getString(DB_SID),
                                           dbconfig.getString(DB_HOST),
                                           Integer.parseInt(dbconfig.getString(DB_PORT)));
        return _connection;
    }

    private static Connection openDBConnection(String user, String pass,
                                              String dbSID, String host, int port) throws SQLException,
            ClassNotFoundException {

        String driver = "org.postgresql.Driver";
        Class.forName(driver);

        String url = "jdbc:postgresql://" + host + "/" + dbSID;
        String username = user;
        String password = pass;

        return DriverManager.getConnection(url, username, password);
    }
}
