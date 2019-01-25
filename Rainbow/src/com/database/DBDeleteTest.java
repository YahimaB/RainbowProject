package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yahima on 05.10.2017.
 */
public class DBDeleteTest {

    //Delete a row specified by the parameter

    public void delete(String DBName, String TBName, String FDParam, int param) throws SQLException {
        String paramOut = Integer.toString(param);
        delete(DBName, TBName, FDParam, paramOut);
    }

    public void delete(String DBName, String TBName, String FDParam, String param) throws SQLException {

        //get ID of User

        DBSelect sel = new DBSelect();
        String UserID = sel.selectItem(DBName,TBName,"UserID",FDParam,param);
        System.out.println(UserID);
        String sql = "DELETE FROM " + TBName + " WHERE " + FDParam + " = ?";

        String sql1 ="DELETE FROM Info WHERE UserID = ?";

        try (Connection conn = DBConnect.connect(DBName);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, param);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try (Connection conn = DBConnect.connect(DBName);
             PreparedStatement pstmt = conn.prepareStatement(sql1)) {

            pstmt.setString(1, UserID);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
