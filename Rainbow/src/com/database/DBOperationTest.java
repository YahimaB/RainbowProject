package com.database;

import java.sql.SQLException;

/**
 * Created by Yahima on 13.11.2017.
 */
public class DBOperationTest {

    public static void main(String[] args) {

        DBSelect sel = new DBSelect();
        boolean flag = true;
        String userID = null;
        try {
            userID = sel.selectItem("prototype#1","Users","UserID","NationalID","000103550174");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(userID);
    }
}
