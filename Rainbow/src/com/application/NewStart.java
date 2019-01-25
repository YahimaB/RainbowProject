package com.application;

import com.database.DBInsert;
import com.database.DBSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import static com.application.App.frame;

public class NewStart extends JDialog {
    private JPanel NewStartPanel;
    private JPanel Page1;
    private JPanel Page2;
    private JPanel Page3;
    private JPanel Page4;


    private JTextField FullName;
    private JTextField DoB;
    private JTextField PoB;
    private JTextField Nationality;
    private JTextField AstanaFrom;
    private JTextField AstanaArrive;
    private JTextField AstanaAge;
    private JTextField FatherName;
    private JTextField FatherDoB;
    private JTextField MotherName;
    private JTextField MotherDoB;


    private JTextField Weight;
    private JTextField Height;
    private JTextField Length;
    private JTextField SysAD;
    private JTextField DiasAD;
    private JTextField Breath;
    private JTextField Heart;
    private JTextField Spyrometry;
    private JTextField RightHand;
    private JTextField LeftHand;
    private JTextField CHSS;
    private JTextField NewSysAD;
    private JTextField NewDiasAD;
    private JTextField Recovery;


    private JRadioButton GenderMale;
    private JRadioButton GenderFemale;
    private JRadioButton FullFamily;
    private JRadioButton NotFullFamily;


    private JButton autofill;
    private JButton Page1To2;
    private JButton Page2To1;
    private JButton Page2To3;
    private JButton Page3To2;
    private JButton Page3To4;
    private JButton Page4To3;
    private JButton Menu;
    private JButton Finish;


    private String Age="";
    private String AgeGroup="";
    private String Gender="";
    private String Family="";

    private int FatherAge;
    private int MotherAge;

    private DBInsert ins = new DBInsert();
    private DBSelect sel = new DBSelect();

    private RandomStringGenerator generator = new RandomStringGenerator(6);

    private static int newPatientId;

    public NewStart() {
        setContentPane(NewStartPanel);
        setModal(true);
        Page2.setVisible(false);
        Page3.setVisible(false);
        Page4.setVisible(false);

        //set icon for button Menu
        Menu.setBounds(0,0,300,80);
        ImageIcon MenuImg = new ImageIcon("src/com/resources/images/but_violet_menu.png");
        MenuImg.setImage(MenuImg.getImage().getScaledInstance(Menu.getWidth(), Menu.getHeight(), Image.SCALE_SMOOTH));
        Menu.setIcon(MenuImg);

        //set icon for button
        Page1To2.setBounds(0,0,300,80);
        Page2To3.setBounds(0,0,300,80);
        Page3To4.setBounds(0,0,300,80);
        Finish.setBounds(0,0,300,80);
        ImageIcon NextImg = new ImageIcon("src/com/resources/images/but_orange_fwd.png");
        NextImg.setImage(NextImg.getImage().getScaledInstance(Page1To2.getWidth(), Page1To2.getHeight(), Image.SCALE_SMOOTH));
        Page1To2.setIcon(NextImg);
        Page2To3.setIcon(NextImg);
        Page3To4.setIcon(NextImg);
        Finish.setIcon(NextImg);

        //set icon for button
        Page2To1.setBounds(0,0,300,80);
        Page3To2.setBounds(0,0,300,80);
        Page4To3.setBounds(0,0,300,80);
        ImageIcon BackImg = new ImageIcon("src/com/resources/images/but_orange_bck.png");
        BackImg.setImage(BackImg.getImage().getScaledInstance(Page2To1.getWidth(), Page2To1.getHeight(), Image.SCALE_SMOOTH));
        Page2To1.setIcon(BackImg);
        Page3To2.setIcon(BackImg);
        Page4To3.setIcon(BackImg);

        Page1To2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPage1To2();
            }
        });

        Page2To1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPage2To1();
            }
        });

        Page2To3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPage2To3();
            }
        });

        Page3To2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPage3To2();
            }
        });

        Page3To4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPage3To4();
            }
        });

        Page4To3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPage4To3();
            }
        });

        Menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMenu();
            }
        });

        Finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onFinish();
            }
        });

        autofill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAutofill();
            }
        });
    }

    private void onMenu() {
        frame.setContentPane(new MainMenu().getContentPane());
        frame.setVisible(true);
    }

    private void onFinish() {
        if(addPatient()){
            if (AgeGroup.equals("Preschool")) {
                ShowExtraSurveyRequest();
            }
        }
    }

    private void ShowExtraSurveyRequest() {
        ExtraSurveyRequest.PatientID = newPatientId;
        ExtraSurveyRequest.ageGroup = AgeGroup;
        ExtraSurveyRequest.isPrevNewStart = true;

        ExtraSurveyRequest frame = new ExtraSurveyRequest();

        frame.setSize(new Dimension(500, 500));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        frame.setVisible(true);
    }

    private void onAutofill() {
        FullName.setText("Боев Ярослав Романович");
        DoB.setText("03.01.2000");
        PoB.setText("Казахстан");
        GenderMale.setSelected(true);
        Nationality.setText("Русский");

        AstanaFrom.setText("-");
        AstanaArrive.setText("-");
        AstanaAge.setText("18");

        FatherName.setText("Боев Роман Николаевич");
        FatherDoB.setText("28.04.1973");
        MotherName.setText("Боева Наталья Сергеевна");
        MotherDoB.setText("19.06.1974");
        FullFamily.setSelected(true);
    }

    //Handle data from Pages 1 and 2
    private boolean addPatient(){

        boolean isSuccess = true;

        //Get the age Group from String "Age" (18 лет 7 месяцев 3 дня)
        try {
            int pos = Age.indexOf(" ");
            int fullYears = Integer.parseInt(Age.substring(0, pos));

            if (fullYears < 1) {
                AgeGroup = "Toddler";
            } else if (fullYears < 3) {
                AgeGroup = "Baby";
            } else if (fullYears < 7) {
                AgeGroup = "Preschool";
            } else if (fullYears < 15) {
                AgeGroup = "Intermediate";
            } else {
                AgeGroup = "Adult";
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(frame,"Some dates may be wrong:"+e.getMessage());
            isSuccess = false;
        }

        //Autogenerate a new name for answer file
        String filename="dumb";
        boolean exist = false;
        while(!exist){
            filename = generator.nextString();
            if (!Files.exists(Paths.get("src/com/resources/AnswerFiles/"+filename+".txt"))){
                exist=true;
            }
        }

        //Try to add first portion of data to the DataTable and create a file
        try { //add data
            ins.insertNewStartOne("data", FullName.getText(), DoB.getText(), Age, PoB.getText(), Gender, Nationality.getText(),
                    FatherName.getText(), FatherDoB.getText(), FatherAge, MotherName.getText(), MotherDoB.getText(),
                    MotherAge, Family, AstanaFrom.getText(), AstanaArrive.getText(), AstanaAge.getText(), AgeGroup);

            newPatientId = Integer.parseInt(sel.selectItem("data", "Patients", "PatientId", "FullName", FullName.getText()).substring(0, 1));
            ins.insertInfo("data", AgeGroup, newPatientId, filename, "false");

            try { //create a file with name "filename"
                newFile(filename);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "File Creating Problems: " + e.getMessage());
                isSuccess = false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage() + "; Pages 1 and 2; Where ID = "+newPatientId+", and filename = "+filename);
            isSuccess = false;
        }

        //Try to add the second portion of data to the DataTable
        try {
            ins.insertNewStartTwo("data", newPatientId, Weight.getText(),Height.getText(),Length.getText(),SysAD.getText(),
                    DiasAD.getText(),Breath.getText(),Heart.getText(),Spyrometry.getText(),RightHand.getText(),LeftHand.getText(),
                    CHSS.getText(),NewSysAD.getText(),NewDiasAD.getText(),Recovery.getText());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage() + "; Pages 3 and 4; Where ID = "+newPatientId);
            isSuccess = false;
        }

        return isSuccess;
    }

    private void goToExtraTest() {
        if (AgeGroup.equals("Preschool")){
            Preschool.preschoolId = newPatientId;
            frame.setContentPane(new Preschool().getContentPane());
            frame.setVisible(true);
        }
    }

    private void onPage1To2() {

        try {
            Age = ageDiff(DoB.getText());
        } catch (Exception e){
            JOptionPane.showMessageDialog(frame,"Date of birth may be wrong");
        }

        if (GenderFemale.isSelected()) {
            Gender = "Female";
        }

        if (GenderMale.isSelected()) {
            Gender = "Male";
        }

        Page1.setVisible(false);
        Page2.setVisible(true);

    }

    private void onPage2To1() {
        Page1.setVisible(true);
        Page2.setVisible(false);
    }

    private void onPage2To3() {

        try {
            Age = ageDiff(DoB.getText());
            FatherAge = Integer.parseInt(ageDiff(FatherDoB.getText(), DoB.getText()).substring(0,2));
            MotherAge = Integer.parseInt(ageDiff(MotherDoB.getText(), DoB.getText()).substring(0,2));
        } catch (Exception e){
            JOptionPane.showMessageDialog(frame,"Date of birth may be wrong");
        }

        if (FullFamily.isSelected()){
            Family = "Full";
        }

        if (NotFullFamily.isSelected()){
            Family = "NotFull";
        }

        Page3.setVisible(true);
        Page2.setVisible(false);
    }

    private void onPage3To2() {
        Page2.setVisible(true);
        Page3.setVisible(false);
    }

    private void onPage3To4() {
        Page4.setVisible(true);
        Page3.setVisible(false);
    }

    private void onPage4To3() {
        Page3.setVisible(true);
        Page4.setVisible(false);
    }




    private void newFile(String path){
        Path file = Paths.get("src/com/resources/AnswerFiles/"+path+".txt");
        try {
            // Create the empty file with default permissions, etc.
            Files.createFile(file);
        } catch (FileAlreadyExistsException x) {
            System.err.format("file named %s" +
                    " already exists%n", file);
        } catch (IOException x) {
            // Some other sort of failure, such as permissions.
            System.err.format("createFile error: %s%n", x);
        }
    }

    private String ageDiff(String fdat) throws Exception {
            String presdat = LocalDate.now().toString();
            String sdat = presdat.substring(8,10)+"."+ presdat.substring(5,7)+"."+ presdat.substring(0,4);
            return ageDiff(fdat,sdat);
    }

    private String ageDiff(String fdat, String sdat) throws Exception {
        int day = Integer.parseInt(fdat.substring(0, 2));
        int month = Integer.parseInt(fdat.substring(3, 5));
        int year = Integer.parseInt(fdat.substring(6, 10));

        int day2 = Integer.parseInt(sdat.substring(0, 2));
        int month2 = Integer.parseInt(sdat.substring(3, 5));
        int year2 = Integer.parseInt(sdat.substring(6, 10));

        LocalDate baseday = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.of(year2, month2, day2);

        int btwYear = Period.between(baseday, today).getYears();
        int btwMonth = Period.between(baseday, today).getMonths();
        int btwDay = Period.between(baseday, today).getDays();

        if (btwYear<0 || btwYear>120){
            throw new Exception();
        }
        return String.valueOf(btwYear) + " Лет " + String.valueOf(btwMonth) + " Месяцев " + String.valueOf(btwDay) + " Дней";
    }

}
