package com.bolton.gadgetmart_main.api.db;

import java.sql.Connection;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBConnection {
	
	private static DBConnection dbConnection;
    private final BasicDataSource ds;

    private DBConnection() {
        ds = new BasicDataSource();
        ds.setDriverClassName(DatabaseConstants.DRIVER);
        ds.setUsername(DatabaseConstants.USERNAME);
        ds.setPassword(DatabaseConstants.PASSWORD);
        ds.setUrl(DatabaseConstants.DB_URL);

    }

    public static synchronized DBConnection getDBConnection() throws Exception {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
            return dbConnection;
        } else {
            return dbConnection;
        }
    }

    public Connection getConnection() throws Exception {
        return this.ds.getConnection();
    }

}
