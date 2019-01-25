package com.application;

import com.database.DBSelect;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static com.application.App.frame;

public class ExtraSurveyRequest extends JDialog {
    public static String ageGroup;
    public static int PatientID;
    public static boolean isPrevNewStart = true;

    private JPanel contentPane;
    private JPanel PreschoolPanel;


    private JButton Proceed;
    private JButton Later;

    private DBSelect select = new DBSelect();
    private String filename = "";




    public ExtraSurveyRequest() {
        setContentPane(contentPane);
        setModal(true);

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
                onProceed();
            }
        });

        Later.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onLater();
            }
        });
    }

    private void onLater() {
        dispose();

        if (isPrevNewStart) {
            frame.setContentPane(new MainMenu().getContentPane());
            frame.setVisible(true);
        }
    }

    private void onProceed() {
        if(onCreateLabel()){
            QuestReader.isPrevNewStart = isPrevNewStart;
            QuestReader.ageGroup = ageGroup;
            QuestReader.filename = filename;

            if (ageGroup.equals("Preschool")) {
                QuestReader.reader("PreschoolQst.txt");
            } else {
                System.out.println("no test for this group");
            }

            dispose();
        } else {
            onLater();
        }
        System.out.println(PatientID);
    }

    private boolean onCreateLabel() {

        boolean isSuccess = true;

        String ageGroup;

        try {
            ageGroup = select.selectItem("data", "Patients", "AgeGroup", "PatientId", PatientID);
            filename = select.selectItem("data", ageGroup, "FileName", "PatientId", PatientID);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage() + "; Failed to start Extra Survey; Where ID = " +PatientID+
                    ", and filename = "+filename);
            isSuccess = false;
        }

        QuestReader.filename = filename;
        QuestReader.reader("TestQst.txt");

        return isSuccess;
    }

}
