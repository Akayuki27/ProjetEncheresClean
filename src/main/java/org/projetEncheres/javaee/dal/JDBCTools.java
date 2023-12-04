package org.projetEncheres.javaee.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTools {

    private static String bddurl;
    private static String dbUser;
    private static String dbPassword;

    //bloc d'initialisation statique

    static {     

    	bddurl = Settings.getProperty("bddurl");
    	dbUser = Settings.getProperty("dbUser");
    	dbPassword = Settings.getProperty("dbPassword");
    }

    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(bddurl, dbUser, dbPassword);

        return connection;
    }

}