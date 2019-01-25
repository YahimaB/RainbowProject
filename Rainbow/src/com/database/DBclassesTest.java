package com.database;

import java.sql.Connection;


/**
 * Created by Yahima on 04.10.2017.
 */
public class DBclassesTest {

    public static void main(String[] args) {

        Connection conn = DBConnect.connect("prototype#1");
        System.out.println(conn);

        DBSelect sel = new DBSelect();



    }

}
