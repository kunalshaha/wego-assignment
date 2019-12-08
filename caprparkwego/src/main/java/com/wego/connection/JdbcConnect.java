package com.wego.connection;

import com.wego.util.PropertyLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author kunalshaha
 */

public class JdbcConnect {

    /**
     * Returns a singleton instance of connection object
     */

    static Connection conn = null;

    private static JdbcConnect instance = null;

    private JdbcConnect() {
        Properties loadedProp = PropertyLoader.loadProperties();
        try {
            this.conn = DriverManager.getConnection(loadedProp.getProperty("db.url"),
                    loadedProp.getProperty("db.user"), loadedProp.getProperty("db.password"));
        } catch (Exception e) {
            System.out.println("Unable to get connection"+e);
            e.printStackTrace();
        }
    }


    /**
     * @return Singleton instance object
     */
    public static JdbcConnect getInstance() {
        if (instance == null) {
            synchronized (JdbcConnect.class) {
                if (instance == null) {
                    instance = new JdbcConnect();
                    return instance;
                }
            }
        }
        return instance;
    }


    /**
     * @return getConnection Object
     */
    public Connection getConnectionObj() {
        return conn;
    }

}
