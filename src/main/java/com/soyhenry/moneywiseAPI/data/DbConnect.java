package com.soyhenry.moneywiseAPI.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    private static final String DB_DRIVER  = "org.h2.Driver";
    private static final String JDBC_URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection openConnection()  {
        Connection connection = null;
        try{
            System.out.println("Getting conenection");
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println(" SQL connection has been closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
