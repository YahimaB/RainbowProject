package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yahima on 05.10.2017.
 */
public class DBUpdateTest {

    //Update data of a Field in Table specified by the id

    public void update(String DBName, String TBName, String FDName, String newValue, String FDParam ,Integer param){
        String paramOut = Integer.toString(param);
        update(DBName, TBName, FDName, newValue, FDParam, paramOut);
    }

    public void update(String DBName, String TBName, String FDName, String newValue, String FDParam, String param) {
        String sql = "UPDATE " + TBName + " SET "+ FDName + " = ? " + "WHERE " + FDParam + " = ?";

        try (Connection conn = DBConnect.connect(DBName);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newValue);
            pstmt.setString(2, param);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
