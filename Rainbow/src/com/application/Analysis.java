package com.application;

import com.database.DBSelect;
import org.apache.poi.xssf.usermodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;

public class Analysis extends JDialog {

    public static int PatientID = 3;

    private JPanel AnalysisPanel;
    private JLabel Weight;
    private JLabel Height;
    private JButton AnaBut;
    private JLabel Bmi;

    private int years;
    private int months;
    private int fullMonths;

    private String gender;
    private String weight;
    private String height;

    private FileInputStream file;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private int rows;

    private DBSelect select = new DBSelect();

    public Analysis() {
        setContentPane(AnalysisPanel);
        setModal(true);

        AnaBut.setBounds(0,0, 350, 90);
        ImageIcon AnaImg = new ImageIcon("src/com/resources/images/but_darkgreen_star.png");
        AnaImg.setImage(AnaImg.getImage().getScaledInstance(AnaBut.getWidth(), AnaBut.getHeight(), Image.SCALE_SMOOTH));
        AnaBut.setIcon(AnaImg);

        if(getAge()){

            analyseWeight();
            analyseHeight();
            analyseBmi();

        }

//        getExcellDoc("TestBook.xlsx");

        AnaBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    private boolean getAge(){

        boolean isSuccess=true;

        String age;

        try {
            age = select.selectItem("data", "Patients", "Age", "PatientId", PatientID);
            gender = select.selectItem("data", "Patients", "Gender", "PatientId", PatientID);
            weight = select.selectItem("data", "Info", "Weight", "PatientId", PatientID);
            height = select.selectItem("data", "Info", "Height", "PatientId", PatientID);

            int pos1 = 0;
            int pos2 = age.indexOf(" ");

            years = Integer.parseInt(age.substring(pos1, pos2));

            pos1 = age.indexOf(" ", pos2+1);
            pos2 = age.indexOf(" ", pos1+1);

            months = Integer.parseInt(age.substring(pos1+1, pos2));

            fullMonths = years*12 + months;
        } catch (SQLException e) {
            isSuccess = false;
            e.printStackTrace();
        }

        return isSuccess;
    }

    private void getExcellDoc(String filename){

//        try {
//            file.close();
//            workbook.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println("getting file");

            file = new FileInputStream("ExcellTables/" + filename);
            workbook = new XSSFWorkbook(file);
            file.close();
            sheet = workbook.getSheetAt(0);
            rows = sheet.getPhysicalNumberOfRows();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void analyseWeight(){

        if (gender.equals("Male")){
            getExcellDoc("Weight_Boys.xlsx");
        } else {
            getExcellDoc("Weight_Girls.xlsx");
        }

        if (fullMonths <= rows){

            String values = sheet.getRow(fullMonths).getCell(0).getStringCellValue();
            System.out.println(values);

            int centel = centelAnalys(values, Float.parseFloat(weight));

            setupText(Weight, centel);
        } else {
            setupText(Weight, 8);
        }
    }

    private void analyseHeight(){

        if (gender.equals("Male")){
            getExcellDoc("Height_Boys.xlsx");
        } else {
            getExcellDoc("Height_Girls.xlsx");
        }

        if (fullMonths <= rows){

            String values = sheet.getRow(fullMonths).getCell(0).getStringCellValue();
            System.out.println(values);

            int centel = centelAnalys(values, Float.parseFloat(height));

            setupText(Height, centel);
        } else {
            setupText(Height, 8);
        }

    }

    private void analyseBmi(){

        if (gender.equals("Male")){
            getExcellDoc("Bmi_Boys.xlsx");
        } else {
            getExcellDoc("Bmi_Girls.xlsx");
        }

        if (fullMonths <= rows){

            String values = sheet.getRow(fullMonths).getCell(0).getStringCellValue();
            System.out.println(values);

            float bmiValue = Float.parseFloat(height) / (Float.parseFloat(weight) * Float.parseFloat(weight));

            int centel = centelAnalys(values, bmiValue);

            setupText(Bmi, centel);
        } else {
            setupText(Bmi, 8);
        }

    }


    private int centelAnalys(String values, float value){

        int pos1 = 0;
        int pos2 = values.indexOf(" ");

        pos1 = values.indexOf(" ", pos2+1);
        pos2 = values.indexOf(" ", pos1+1);

        pos1 = pos2;
        pos2 = values.indexOf(" ", pos1+1);

        int counter = 0;

        while(true){

            float tableValue = Float.parseFloat(values.substring(pos1, pos2));

            counter++;
            System.out.println(tableValue);

            if (value<=tableValue){
                break;
            } else if (counter == 7) {
                counter++;
                break;
            }

            pos1 = pos2;
            if (counter!=6) {
                pos2 = values.indexOf(" ", pos1 + 1);
            } else {
                pos2 = values.length();
            }
        }

        if (counter>4) counter--;

        return counter;
    }


    private void setupText(JLabel label, int centel){
        switch (centel) {
            case 1:
                label.setText("Очень низкий");
                label.setForeground(Color.decode("#8F0200"));
                break;
            case 2:
                label.setText("Низкий");
                label.setForeground(Color.decode("#D55C00"));
                break;
            case 3:
                label.setText("Ниже Среднего");
                label.setForeground(Color.decode("#FFF100"));
                break;
            case 4:
                label.setText("Средний");
                label.setForeground(Color.decode("#00FF00"));
                break;
            case 5:
                label.setText("Выше Среднего");
                label.setForeground(Color.decode("#FFF100"));
                break;
            case 6:
                label.setText("Высокий");
                label.setForeground(Color.decode("#D55C00"));
                break;
            case 7:
                label.setText("Очень высокий");
                label.setForeground(Color.decode("#8F0200"));
                break;
            case 8:
                label.setText("Нет данных для возраста");
                label.setForeground(Color.decode("#000000"));
                break;
        }
    }

}
