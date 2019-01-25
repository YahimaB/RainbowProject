package com.application;

import com.database.DBSelect;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static com.application.App.frame;

public class PatientCard extends JDialog {
    private JPanel PatientCardPanel;


    private JLabel FullName;
    private JLabel Age;
    private JLabel MotherName;
    private JLabel FatherName;
    private JLabel DoB;
    private JLabel Weight;
    private JLabel Height;


    private JButton Menu;
    private JButton ViewBasic;
    private JButton ViewExtra;
    private JButton AddExtra;
    private JButton AnaBut;


    private DBSelect select = new DBSelect();


    public static int PatientID;
    public String ageGroup;



    public PatientCard() {

        setContentPane(PatientCardPanel);
        setModal(true);

        getPatientData();
        setupButtons();

        //set icon for button Menu
        Menu.setBounds(0, 0, 350, 90);
        ImageIcon MenuImg = new ImageIcon("src/com/resources/images/but_violet_menu.png");
        MenuImg.setImage(MenuImg.getImage().getScaledInstance(Menu.getWidth(), Menu.getHeight(), Image.SCALE_SMOOTH));
        Menu.setIcon(MenuImg);

        //set icons for buttons
        int x = 380;
        int y = 90;
        ViewBasic.setBounds(0, 0, x, y);
        ViewExtra.setBounds(0, 0, x, y);
        ImageIcon BaseImg = new ImageIcon("src/com/resources/images/but_violet_srch.png");
        BaseImg.setImage(BaseImg.getImage().getScaledInstance(ViewBasic.getWidth(), ViewBasic.getHeight(), Image.SCALE_SMOOTH));
        ViewBasic.setIcon(BaseImg);
        ViewExtra.setIcon(BaseImg);

        AnaBut.setBounds(0,0, x, y);
        ImageIcon AnaImg = new ImageIcon("src/com/resources/images/but_darkgreen_star.png");
        AnaImg.setImage(AnaImg.getImage().getScaledInstance(ViewBasic.getWidth(), ViewBasic.getHeight(), Image.SCALE_SMOOTH));
        AnaBut.setIcon(AnaImg);

        AddExtra.setBounds(0, 0, x, y);
        ImageIcon AddExtraImg = new ImageIcon("src/com/resources/images/but_yellow_add.png");
        AddExtraImg.setImage(AddExtraImg.getImage().getScaledInstance(ViewBasic.getWidth(), ViewBasic.getHeight(), Image.SCALE_SMOOTH));
        AddExtra.setIcon(AddExtraImg);

        ViewBasic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CasualInfoWindow.PatientID = PatientID;
                frame.setContentPane(new CasualInfoWindow().getContentPane());
                frame.setVisible(true);
            }
        });

        AddExtra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowExtraSurveyRequest();
            }
        });

        ViewExtra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onViewExtra();
            }
        });

        Menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMenu();
            }
        });

        AnaBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAnalysis();
            }
        });
    }

    private void onAnalysis() {
        Analysis.PatientID = PatientID;

        Analysis frame = new Analysis();

        frame.setSize(new Dimension(500, 550));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        frame.setVisible(true);

    }

    private void ShowExtraSurveyRequest() {
        ExtraSurveyRequest.PatientID = PatientID;
        ExtraSurveyRequest.ageGroup = ageGroup;
        ExtraSurveyRequest.isPrevNewStart = false;

        ExtraSurveyRequest frame = new ExtraSurveyRequest();

        frame.setSize(new Dimension(500, 500));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        frame.setVisible(true);
    }

    private void onViewExtra() {
        boolean isSuccess = true;
        String filename = "";

        try {
            filename = select.selectItem("data", ageGroup, "FileName", "PatientId", PatientID);
        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        }

        if (isSuccess) {
            PatientInfo.PatFile = filename;
            PatientInfo.ageGroup = ageGroup;
            PatientInfo.PatientID = PatientID;

            frame.setContentPane(new PatientInfo().getContentPane());
            frame.setVisible(true);
        }
    }

    private void onMenu() {
        frame.setContentPane(new MainMenu().getContentPane());
        frame.setVisible(true);
    }

    private void getPatientData(){
        try {
            FullName.setText(select.selectItem("data", "Patients", "FullName", "PatientId", PatientID));
            Age.setText(select.selectItem("data", "Patients", "Age", "PatientId", PatientID));
            MotherName.setText(select.selectItem("data", "Patients", "MotherName", "PatientId", PatientID));
            FatherName.setText(select.selectItem("data", "Patients", "FatherName", "PatientId", PatientID));
            DoB.setText(select.selectItem("data", "Patients", "DoB", "PatientId", PatientID));
            Weight.setText(select.selectItem("data", "Info", "Weight", "PatientId", PatientID));
            Height.setText(select.selectItem("data", "Info", "Height", "PatientId", PatientID));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage() + "; Unable to get data");
        }
    }

    //setup buttons' visibility based on isFilled
    private void setupButtons(){

        int pos = Age.getText().indexOf(" ");
        int fullYears = Integer.parseInt(Age.getText().substring(0, pos));

        if (fullYears < 1) {
            ageGroup = "Toddler";
        } else if (fullYears < 3) {
            ageGroup = "Baby";
        } else if (fullYears < 7) {
            ageGroup = "Preschool";
        } else if (fullYears < 15) {
            ageGroup = "Intermediate";
        } else {
            ageGroup = "Adult";
        }

        String value = "";

        try {
            value = select.selectItem("data", ageGroup, "isFilled", "PatientId", PatientID);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage() + "; Unable to get isFilled");
        }

        if (value.equals("true")){
            AddExtra.setVisible(false);
        } else if (value.equals("false") && ageGroup.equals("Preschool")){
            ViewExtra.setVisible(false);
        }

    }
}
