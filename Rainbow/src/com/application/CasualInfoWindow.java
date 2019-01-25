package com.application;

import com.database.DBSelect;
import com.database.DBUpdateTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.application.App.frame;

public class CasualInfoWindow extends JDialog {

    public static int PatientID;

    private JPanel CasualInfoPanel;
    private JPanel MainPanel;


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
    private JScrollPane ScrollPanel;
    private JButton Back;
    private JButton Save;
    private JButton Edit;

    private ArrayList<JTextField> textFields = new ArrayList<>();

    private DBSelect select = new DBSelect();
    private DBUpdateTest update = new DBUpdateTest();



    public CasualInfoWindow() {
        setContentPane(CasualInfoPanel);
        setModal(true);

        initialize();


        //set icon for button Menu
        Back.setBounds(0, 0, 350, 90);
        ImageIcon BackImg = new ImageIcon("src/com/resources/images/but_orange_bck.png");
        BackImg.setImage(BackImg.getImage().getScaledInstance(Back.getWidth(), Back.getHeight(), Image.SCALE_SMOOTH));
        Back.setIcon(BackImg);

        //set icon for button Menu
        Edit.setBounds(0, 0, 350, 90);
        ImageIcon EditImg = new ImageIcon("src/com/resources/images/but_blue_edit.png");
        EditImg.setImage(EditImg.getImage().getScaledInstance(Edit.getWidth(), Edit.getHeight(), Image.SCALE_SMOOTH));
        Edit.setIcon(EditImg);

        Save.setBounds(0, 0, 350, 90);
        ImageIcon SaveImg = new ImageIcon("src/com/resources/images/but_red_save.png");
        SaveImg.setImage(SaveImg.getImage().getScaledInstance(Save.getWidth(), Save.getHeight(), Image.SCALE_SMOOTH));
        Save.setIcon(SaveImg);


        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBack();
            }
        });

        Edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEdit();
            }
        });

        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSave();
            }
        });
    }


    private void initialize(){
        Save.setVisible(false);

        ScrollPanel.getVerticalScrollBar().setUnitIncrement(5);

        for (Component element: MainPanel.getComponents()) {
            if (element instanceof JTextField){
                textFields.add((JTextField) element);
                System.out.println("true");
            }
        }


        ArrayList<String> list = new ArrayList<>();
        try {
            select.GetBasicData("data",list, PatientID);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        for (int k = 0; k<=13; k++){
            textFields.get(k).setText(list.get(k));
        }

        for (String str: list
                ) {
            System.out.println(str);
        }


    }
    
    private void onEdit(){

        Back.setVisible(false);
        Edit.setVisible(false);
        Save.setVisible(true);

        for (JTextField field: textFields) {
            field.setEditable(true);
        }
    }

    private void onSave(){

        update.update("data","info","Weight",Weight.getText(),"PatientId", PatientID);
        update.update("data","info","Height",Height.getText(),"PatientId", PatientID);
        update.update("data","info","Length",Length.getText(),"PatientId", PatientID);
        update.update("data","info","SysAD",SysAD.getText(),"PatientId", PatientID);
        update.update("data","info","DiasAD",DiasAD.getText(),"PatientId", PatientID);
        update.update("data","info","Breath",Breath.getText(),"PatientId", PatientID);
        update.update("data","info","Heart",Heart.getText(),"PatientId", PatientID);
        update.update("data","info","Spyrometry",Spyrometry.getText(),"PatientId", PatientID);
        update.update("data","info","RightHand",RightHand.getText(),"PatientId", PatientID);
        update.update("data","info","LeftHand",LeftHand.getText(),"PatientId", PatientID);
        update.update("data","info","CHSS",CHSS.getText(),"PatientId", PatientID);
        update.update("data","info","NewSysAD",NewSysAD.getText(),"PatientId", PatientID);
        update.update("data","info","NewDiasAD",NewDiasAD.getText(),"PatientId", PatientID);
        update.update("data","info","Recovery",Recovery.getText(),"PatientId", PatientID);

        Save.setVisible(false);
        Back.setVisible(true);
        Edit.setVisible(true);

        for (JTextField field: textFields) {
            field.setEditable(false);
        }

    }

    private void onBack(){
        PatientCard.PatientID = PatientID;
        frame.setContentPane(new PatientCard().getContentPane());
        frame.setVisible(true);
    }

}
