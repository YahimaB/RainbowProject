package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yahima on 04.10.2017.
 */
public class DBInsert {

    //Add new user

    public void insertNewStartOne(String DBName, String FullName, String DoB, String Age, String PoB, String Gender,
                                  String Nationality, String FatherName, String FatherDoB, int FatherAge,
                                  String MotherName, String MotherDoB, int MotherAge, String Family, String AstanaFrom, String AstanaArrive, String AstanaAge, String AgeGroup) throws SQLException {
        String sql = "INSERT INTO Patients(FullName, DoB, Age, PoB, Gender, Nationality, FatherName, " +
                "FatherDoB, FatherAge, MotherName, MotherDoB, MotherAge, Family, AstanaFrom, AstanaArrive, AstanaAge, AgeGroup)" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection conn = DBConnect.connect(DBName);
        PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, FullName);
            pstmt.setString(2, DoB);
            pstmt.setString(3, Age);
            pstmt.setString(4, PoB);
            pstmt.setString(5, Gender);
            pstmt.setString(6, Nationality);
            pstmt.setString(7, FatherName);
            pstmt.setString(8, FatherDoB);
            pstmt.setInt(9, FatherAge);
            pstmt.setString(10, MotherName);
            pstmt.setString(11, MotherDoB);
            pstmt.setInt(12, MotherAge);
            pstmt.setString(13, Family);
            pstmt.setString(14, AstanaFrom);
            pstmt.setString(15, AstanaArrive);
            pstmt.setString(16, AstanaAge);
            pstmt.setString(17, AgeGroup);

            pstmt.executeUpdate();
    }

    public void insertNewStartTwo(String DBName, int PatientId, String Weight, String Height, String Length, String SysAD,
                                  String DiasAD, String Breath, String Heart, String Spyrometry,
                                  String RightHand, String LeftHand, String CHSS, String NewSysAD, String NewDiasAD, String Recovery) throws SQLException {
        String sql = "INSERT INTO Info(PatientId, Weight, Height, Length, SysAD, DiasAD, Breath, " +
                "Heart, Spyrometry, RightHand, LeftHand, CHSS, NewSysAD, NewDiasAD, Recovery)" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection conn = DBConnect.connect(DBName);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, PatientId);
        pstmt.setString(2, Weight);
        pstmt.setString(3, Height);
        pstmt.setString(4, Length);
        pstmt.setString(5, SysAD);
        pstmt.setString(6, DiasAD);
        pstmt.setString(7, Breath);
        pstmt.setString(8, Heart);
        pstmt.setString(9, Spyrometry);
        pstmt.setString(10, RightHand);
        pstmt.setString(11, LeftHand);
        pstmt.setString(12, CHSS);
        pstmt.setString(13, NewSysAD);
        pstmt.setString(14, NewDiasAD);
        pstmt.setString(15, Recovery);

        pstmt.executeUpdate();
    }


    //Add new filename to needed table

    public void insertInfo(String DBName, String TBName, Integer PatientId, String FileName, String isFilled) throws SQLException{
        String sql = "INSERT INTO " + TBName + "(PatientId, FileName, isFilled) VALUES(?,?,?)";

        Connection conn = DBConnect.connect(DBName);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, PatientId);
        pstmt.setString(2, FileName);
        pstmt.setString(3, isFilled);
        pstmt.executeUpdate();
    }

}
