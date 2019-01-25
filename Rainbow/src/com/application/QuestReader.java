package com.application;

import com.database.DBUpdateTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

import static com.application.App.frame;

public class QuestReader{
    public static String filename;
    public static boolean isPrevNewStart = true;
    public static String ageGroup;

    private static FileWriter fWriter;
    private static FileReader fileReader;
    private static PrintWriter fileWriter;
    private static Scanner in;
    private static JPanel qPanel;
    private static JLabel qLabel;
    private static JButton btnNext;
    private static Font qFont = new Font("Arial Rounded MT Bold", Font.ITALIC, 40);
    private static String qNum;

    private static DBUpdateTest update = new DBUpdateTest();

    public static void reader(String fileName){
        try {
            fileReader = new FileReader(fileName);
            fWriter = new FileWriter("src/com/resources/AnswerFiles/"+filename+".txt");
            fileWriter = new PrintWriter(fWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        in = new Scanner(fileReader);
        nextQ();
    }

    public static void nextQ(){
        if (in.hasNext()) {
            readNext();
        } else {
            try {
                fileReader.close();
                fileWriter.close();
                fWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            update.update("data",ageGroup,"isFilled","true","FileName", filename);

            if (isPrevNewStart) {
                frame.setContentPane(new MainMenu().getContentPane());
                frame.setVisible(true);
            } else {
                frame.setContentPane(new PatientCard().getContentPane());
                frame.setVisible(true);
            }
        }

    }


    private static void readNext(){
        String qheader = in.nextLine();

        String type ="";

        if (!qheader.isEmpty()) {

            qPanel = new JPanel();   // Make a JPanel;
            qPanel.setLayout(new GridLayout(15,1));
//            qPanel.setLayout(new GridBagLayout());
//            qPanel.setLayout(new BoxLayout(qPanel, BoxLayout.Y_AXIS));

            qPanel.setBackground(Color.decode("#FFD47B"));

            String Header = qheader.substring(4,qheader.length());
            qNum = Header.substring(0,Header.indexOf(":")+1);


            adder(Header);


            qNum = Header.substring(0,Header.indexOf(":")+1);

            btnNext = new JButton("NEXT>>");//Make a JButton

            switch (qheader.substring(0, 4)) {
                case "(OC)":
                    type = "only choice";
                    OCcreate();
                    break;

                case "(MC)":
                    type = "multiple choice";
                    MCcreate();
                    break;

                case "(FA)":
                    type = "full answer";
                    FAcreate();
                    break;

                case "(MA)":
                    type = "multiple answer";
                    MAcreate();
                    break;
                default:
                    nextQ();
                    break;
            }

            frame.setContentPane(qPanel);  // Add P to JFrame f
            frame.setVisible(true);
        } else {
            nextQ();
        }
    }

    private static void adder(String Header){
        JLabel qstLabel;


        int x = frame.getContentPane().getSize().width / qFont.getSize() * 2 - 6;
        boolean flag = true;
        do {
            int pos = Header.lastIndexOf(" ", x);
            if (Header.length()>x){
                qstLabel = new JLabel(Header.substring(0, pos+1));
                Header = Header.substring(pos, Header.length());
            } else{
                qstLabel = new JLabel(Header);
                flag = false;
            }
            qstLabel.setFont(qFont);
            qPanel.add(qstLabel);
        } while (flag);

    }

    //Only Choice create
    public static void OCcreate(){
        ButtonGroup buttonGroup = new ButtonGroup();
        ArrayList<JTextField> textFieldList = new ArrayList<>();

        while (true){
            String option = "";
            if (in.hasNext()) option = in.nextLine();
            if(option.isEmpty()) break;
            JRadioButton radioButton = new JRadioButton(option);
            radioButton.setFont(qFont);
            buttonGroup.add(radioButton);
            qPanel.add(radioButton);
            if(option.contains(":")){
                if (in.hasNext()) option = in.nextLine();
                JLabel qLabel = new JLabel(option);
                qLabel.setFont(qFont);
                qPanel.add(qLabel);
                JTextField qText = new JTextField();
                qText.setFont(qFont);
                textFieldList.add(qText);
                qPanel.add(qText);
                qLabel.setVisible(false);
                qText.setVisible(false);
                radioButton.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            qLabel.setVisible(true);
                            qText.setVisible(true);
                        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                            qLabel.setVisible(false);
                            qText.setVisible(false);

                        }
                    }
                });
            }
        }

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOCcreate(buttonGroup, textFieldList);
            }
        });

        qPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOCcreate(buttonGroup, textFieldList);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        qPanel.add(btnNext);
    }

    private static void onOCcreate(ButtonGroup buttonGroup, ArrayList<JTextField> textFieldList) {
        Enumeration<AbstractButton> bg = buttonGroup.getElements();
        String answer ="";
        while(bg.hasMoreElements()){
            JRadioButton radioButton = (JRadioButton)bg.nextElement();
            if (radioButton.isSelected()) answer += radioButton.getText() + "; ";
        }
        for ( JTextField qText : textFieldList ) {
            answer += qText.getText() + "; ";
        }
        insert(answer);
        nextQ();
    }

    //Multiple Choice create
    public static void MCcreate(){
        ArrayList<JCheckBox> checkBoxList = new ArrayList<>();
        ArrayList<JTextField> textFieldList = new ArrayList<>();

        while (true){
            String option = "";
            if (in.hasNext()) option = in.nextLine();
            if(option.isEmpty()) break;
            JCheckBox checkBox = new JCheckBox(option);
            checkBox.setFont(qFont);
            checkBoxList.add(checkBox);
            qPanel.add(checkBox);
            if(option.contains(":")){
                if (in.hasNext()) option = in.nextLine();
                JLabel qLabel = new JLabel(option);
                qLabel.setFont(qFont);
                qPanel.add(qLabel);
                JTextField qText = new JTextField();
                qText.setFont(qFont);
                textFieldList.add(qText);
                qPanel.add(qText);
                qLabel.setVisible(false);
                qText.setVisible(false);
                checkBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            qLabel.setVisible(true);
                            qText.setVisible(true);
                        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                            qLabel.setVisible(false);
                            qText.setVisible(false);

                        }
                    }
                });
            }
        }

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { onMCcreate(checkBoxList, textFieldList); }
        });

        qPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onMCcreate(checkBoxList, textFieldList); }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        qPanel.add(btnNext);
    }

    private static void onMCcreate(ArrayList<JCheckBox> checkBoxList, ArrayList<JTextField> textFieldList) {
        String answer ="";
        for ( JCheckBox checkBox : checkBoxList ) {
            if( checkBox.isSelected()) {
                System.out.println(checkBox);
                 answer += checkBox.getText() + "; ";
            }
        }
        for ( JTextField qText : textFieldList ) {
            answer += qText.getText() + "; ";
        }
        insert(answer);
        nextQ();
    }

    //Full Answer create
    public static void FAcreate(){
        JTextField qText = new JTextField();
        qText.setFont(qFont);

        qPanel.add(qText);


        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onFAcreate(qText.getText());
            }
        });

        qPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onFAcreate(qText.getText());
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        qPanel.add(btnNext);
    }

    private static void onFAcreate(String answer){
        answer+=";";
        insert(answer);
        nextQ();
    }

    //Multiple Answer create
    public static void MAcreate(){
        ArrayList<JTextField> textFieldList = new ArrayList<>();

        while (true){
            String option = "";
            if (in.hasNext()) option = in.nextLine();
            if(option.isEmpty()) break;
            JLabel qLabel = new JLabel(option);
            qLabel.setFont(qFont);
            qPanel.add(qLabel);

            JTextField qText = new JTextField();
            qText.setFont(qFont);
            textFieldList.add(qText);
            qPanel.add(qText);
        }

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMAcreate(textFieldList);
            }
        });

        qPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onMAcreate(textFieldList);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        qPanel.add(btnNext);
    }

    private static void onMAcreate(ArrayList<JTextField> textFieldList) {
        String answer ="";
        for ( JTextField qText : textFieldList ) {
            answer += qText.getText() + "; ";
        }
        insert(answer);
        nextQ();
    }

    private static void insert(String answer){
        answer=qNum+answer;
        while (answer.contains("  ")){
            answer = answer.replaceAll("  ","");
        }
        while (answer.contains(" ;")){
            answer = answer.replaceAll(" ;",";");
        }
        while (answer.contains("; ")){
            answer = answer.replaceAll("; ",";");
        }
        while (answer.contains("; ;")){
            answer = answer.replaceAll("; ;",";");
        }
        while (answer.contains(";;")){
            answer = answer.replaceAll(";;",";");
        }
        fileWriter.println(answer);
    }


}
//boolean isMore = true;
//        if(answer.length()>1) {
//            while (isMore) {
//                if (answer.charAt(answer.length() - 1) == answer.charAt(answer.length() - 2)) {
//                    answer = answer.substring(0, answer.length() - 1);
//                } else {
//                    isMore = false;
//                }
//            }