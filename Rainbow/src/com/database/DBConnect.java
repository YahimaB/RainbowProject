package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Yahima on 04.10.2017.
 */
public class DBConnect {

    public static Connection connect(String DBName){
        Connection conn = null;
        try {
        String url = "jdbc:sqlite:src/com/resources/databases/data.db";
        conn = DriverManager.getConnection(url);

    } catch (SQLException e) {
        System.out.println("no connection" + e.getMessage());
    }

    return conn;
    }


}
