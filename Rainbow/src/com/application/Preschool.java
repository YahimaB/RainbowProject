package com.application;

import com.database.DBSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Preschool extends JDialog {
    public static int preschoolId;
    private JPanel PreschoolPanel;
    private DBSelect sel = new DBSelect();
    private String filename;

    {
        try {
            filename = sel.selectItem("data", "Preschool", "FileName", "PatientId", preschoolId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private JButton Proceed;
    private JButton Later;

    public Preschool() {
        setContentPane(PreschoolPanel);
        setModal(true);
        getRootPane().setDefaultButton(Proceed);

        //set icon for button
        Proceed.setBounds(0, 0, 400, 100);
        ImageIcon ProceedImg = new ImageIcon("src/com/resources/images/but_orange_fwd.png");
        ProceedImg.setImage(ProceedImg.getImage().getScaledInstance(Proceed.getWidth(), Proceed.getHeight(), Image.SCALE_SMOOTH));
        Proceed.setIcon(ProceedImg);

        //set icon for button
        Later.setBounds(0, 0, 400, 100);
        ImageIcon LaterImg = new ImageIcon("src/com/resources/images/but_darkblue_fwd.png");
        LaterImg.setImage(LaterImg.getImage().getScaledInstance(Later.getWidth(), Later.getHeight(), Image.SCALE_SMOOTH));
        Later.setIcon(LaterImg);

        Proceed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCreateLabel();
            }
        });

    }

    private void onCreateLabel() {
        System.out.println(preschoolId);
        QuestReader.filename = filename;
        System.out.println(QuestReader.filename);
        QuestReader.reader("TestQst.txt");
    }


}
