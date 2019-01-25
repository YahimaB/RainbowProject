package com.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.application.App.frame;

public class MainMenu extends JDialog {
    private JPanel SelectionPanel;
    private JButton NewPatient;
//    private JButton SearchPatient;
//    private JButton EditPatient;
    private JButton ListPatient;
    public static int check = 0;

    public MainMenu() {
        setContentPane(SelectionPanel);
        setModal(true);
        getRootPane().setDefaultButton(NewPatient);

        //set icon for button NewPatient
        NewPatient.setBounds(0, 0, 700, 180);
        ImageIcon NewPatImg = new ImageIcon("src/com/resources/images/but_yellow_add.png");
        NewPatImg.setImage(NewPatImg.getImage().getScaledInstance(NewPatient.getWidth(), NewPatient.getHeight(), Image.SCALE_SMOOTH));
        NewPatient.setIcon(NewPatImg);

//        //set icon for button SearchPatient
//        SearchPatient.setBounds(0, 0, 700, 180);
//        ImageIcon SearchImg = new ImageIcon("src/com/resources/images/but_violet_srch.png");
//        SearchImg.setImage(SearchImg.getImage().getScaledInstance(SearchPatient.getWidth(), SearchPatient.getHeight(), Image.SCALE_SMOOTH));
//        SearchPatient.setIcon(SearchImg);
//
//        //set icon for button ChangePatient
//        EditPatient.setBounds(0, 0, 700, 180);
//        ImageIcon EditImg = new ImageIcon("src/com/resources/images/but_darkgreen_chng.png");
//        EditImg.setImage(EditImg.getImage().getScaledInstance(EditPatient.getWidth(), EditPatient.getHeight(), Image.SCALE_SMOOTH));
//        EditPatient.setIcon(EditImg);

        //set icon for button ChangePatient
        ListPatient.setBounds(0, 0, 700, 180);
        ImageIcon ListImg = new ImageIcon("src/com/resources/images/but_blue_list.png");
        ListImg.setImage(ListImg.getImage().getScaledInstance(ListPatient.getWidth(), ListPatient.getHeight(), Image.SCALE_SMOOTH));
        ListPatient.setIcon(ListImg);

        if (check == 1) {
//            EditPatient.setVisible(false);
            ListPatient.setVisible(false);
        }


        NewPatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNewPatient();
            }
        });

//        SearchPatient.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onSearchPatient();
//            }
//        });
//
//        EditPatient.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onEditPatient();
//            }
//        });

        ListPatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onListPatient();
            }
        });

    }

    private void onNewPatient() {
        // add your code here
        frame.setContentPane(new NewStart().getContentPane());
        frame.setVisible(true);
        frame.setSize(new Dimension(950, 1000));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    private void onSearchPatient() {
        frame.setContentPane(new SrchPatient().getContentPane());
        frame.setVisible(true);
    }

    private void onEditPatient() {

    }

    private void onListPatient() {
        frame.setContentPane(new PatientList().getContentPane());
        frame.setVisible(true);
    }

}
