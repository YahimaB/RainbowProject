package com.application;

import com.database.DBSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.application.App.frame;

public class SrchPatient extends JDialog {
    private JPanel SrchPatientPanel;
    private JButton Search;
    private JLabel FullNameLabel;
    private JTextField FullName;
    private JLabel DoBLabel;
    private JTextField DoB;
    private JButton Menu;
    private String filename;


    public SrchPatient() {
        setContentPane(SrchPatientPanel);
        setModal(true);
        getRootPane().setDefaultButton(Search);

        //set icon for button SearchPatient
        Search.setBounds(0, 0, 350, 90);
        ImageIcon SearchImg = new ImageIcon("src/com/resources/images/but_violet_srch.png");
        SearchImg.setImage(SearchImg.getImage().getScaledInstance(Search.getWidth(), Search.getHeight(), Image.SCALE_SMOOTH));
        Search.setIcon(SearchImg);

        //set icon for button Menu
        Menu.setBounds(0, 0, 350, 90);
        ImageIcon MenuImg = new ImageIcon("src/com/resources/images/but_violet_menu.png");
        MenuImg.setImage(MenuImg.getImage().getScaledInstance(Menu.getWidth(), Menu.getHeight(), Image.SCALE_SMOOTH));
        Menu.setIcon(MenuImg);

        Search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSearch();
            }
        });

        Menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMenu();
            }
        });
    }

    private void onMenu() {
        frame.setContentPane(new MainMenu().getContentPane());
        frame.setVisible(true);
    }

    private void onSearch() {
        // add your code here
        DBSelect select = new DBSelect();
        String PatientGroup = "";
        try {
            int PatientId = select.selectId("data", "Patients", "PatientId",
                    "FullName", FullName.getText(), "DoB", DoB.getText());

            PatientGroup = select.selectItem("data", "Patients", "AgeGroup", "PatientId", PatientId);

            filename = select.selectItem("data", PatientGroup, "FileName", "PatientId", PatientId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }

        PatientInfo.PatFile = filename;

        JOptionPane.showMessageDialog(frame, filename);

        frame.setContentPane(new PatientInfo().getContentPane());
        frame.setVisible(true);

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


}
