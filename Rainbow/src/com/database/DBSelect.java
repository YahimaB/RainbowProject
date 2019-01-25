package com.database;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Yahima on 04.10.2017.
 */
public class DBSelect {

    //Select items that correspond to certain parameters;

    public String selectItem(String DBName, String TBName, String FDName, String FDParam ,Integer param) throws SQLException {
       String paramOut = Integer.toString(param);
       return selectItem(DBName, TBName, FDName, FDParam, paramOut);
    }

    public String selectItem(String DBName, String TBName, String FDName, String FDParam, String param) throws SQLException {
        String ans = "";
        String sql = "SELECT " + FDName + " FROM " + TBName + " WHERE " + FDParam + " =?";

        Connection conn = DBConnect.connect(DBName);
             PreparedStatement pstmt  = conn.prepareStatement(sql);
            pstmt.setString(1,param);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if (ans.equals("")) {
                    ans = ans + rs.getString(FDName);
                } else {
                    ans = ans + "\n" + rs.getString(FDName);
                }
            }

        return ans;
    }

    //Select by two variant

    public int selectId(String DBName, String TBName, String FDName, String FDParam, String param, String FDParam2, String param2) throws SQLException {
        String ans = "";
        String sql = "SELECT " + FDName + " FROM " + TBName + " WHERE " + FDParam + " =?" + " AND " + FDParam2 + " =?";

        Connection conn = DBConnect.connect(DBName);
        PreparedStatement pstmt  = conn.prepareStatement(sql);
        pstmt.setString(1,param);
            pstmt.setString(2,param2);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if (ans.equals("")) {
                    ans = ans + rs.getString(FDName);
                } else {
                    ans = ans + "\n" + rs.getString(FDName);
                }
            }

        return Integer.parseInt(ans);
    }

    //select whole column from a Table

    public void selectColumn(String DBName, ArrayList<ArrayList> list, String name) throws SQLException {
        String ans = "";
        String sql = "SELECT * FROM Patients WHERE FullName LIKE ?";

        Connection conn = DBConnect.connect(DBName);


        PreparedStatement pstmt  = conn.prepareStatement(sql);
        pstmt.setString(1,"%"+name+"%");
        ResultSet rs    = pstmt.executeQuery();

            while (rs.next()) {
                ArrayList<String> patient = new ArrayList<>();
                String answ;
                for (int i =1; i<=18;i++) {
                    answ = rs.getString(i);
                    patient.add(answ);
                }
                list.add(patient);
            }
    }

    //get a row of BasicInfo via ID
    public void GetBasicData(String DBName, ArrayList<String> list, int PatientID) throws SQLException {
        String ans = "";
        String sql = "SELECT * FROM Info WHERE PatientId LIKE ?";

        Connection conn = DBConnect.connect(DBName);


        PreparedStatement pstmt  = conn.prepareStatement(sql);
        pstmt.setString(1, Integer.toString(PatientID));
        ResultSet rs    = pstmt.executeQuery();

        while (rs.next()) {
            String answ;
            for (int i =2; i<=15;i++) {
                answ = rs.getString(i);
                list.add(answ);
            }
        }
    }

    //TODO: list of patients(list of data);
}
