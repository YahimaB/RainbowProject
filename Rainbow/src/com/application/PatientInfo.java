package com.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static com.application.App.frame;

public class PatientInfo extends JDialog {
    public static String PatFile;
    public static int PatientID;
    public static String ageGroup;

    private JPanel contentPane;
    private JButton Rerun;
    private JButton Back;
    private JScrollPane ScrollPanel;
    private static FileReader patFileReader;
    private static FileReader qstFileReader;

    private static Scanner PatScan;
    private static Scanner QstScan;
    private static Font font = new Font("Arial Rounded MT Bold", Font.ITALIC, 35);
    private static JPanel jPanel;


    public PatientInfo() {

        showInfo();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(Rerun);

        //set icon for button Menu
        Back.setBounds(0, 0, 350, 90);
        ImageIcon MenuImg = new ImageIcon("src/com/resources/images/but_orange_bck.png");
        MenuImg.setImage(MenuImg.getImage().getScaledInstance(Back.getWidth(), Back.getHeight(), Image.SCALE_SMOOTH));
        Back.setIcon(MenuImg);

        //set icon for button Menu
        Rerun.setBounds(0, 0, 350, 90);
        ImageIcon RerunImg = new ImageIcon("src/com/resources/images/but_blue_chng.png");
        RerunImg.setImage(RerunImg.getImage().getScaledInstance(Rerun.getWidth(), Rerun.getHeight(), Image.SCALE_SMOOTH));
        Rerun.setIcon(RerunImg);


        Rerun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShowExtraSurveyRequest();
            }
        });

        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBack();
            }
        });

    }

    private void onBack() {
        frame.setContentPane(new PatientCard().getContentPane());
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



    private void showInfo() {
        reader(PatFile, "TestQst");
        jPanel = new JPanel();

        while (QstScan.hasNext()) {
            String qst = QstScan.nextLine();
            if (qst.contains("(OC)") || qst.contains("(MC)") || qst.contains("(FA)") || qst.contains("(MA)")) {
                qst = qst.substring(4, qst.length());
                adder(qst);

                String ans = PatScan.nextLine();
                if (ans.contains(":")) {
                    int pos = ans.indexOf(":");
                    ans = ans.substring(pos + 1, ans.length());
                }
                adder(ans);
                jPanel.add(Box.createRigidArea(new Dimension(1, 20)));
            }
        }

        try {
            patFileReader.close();
            qstFileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);

        ScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
        ScrollPanel.getHorizontalScrollBar().setUnitIncrement(16);
        jPanel.setBackground(Color.decode("#FFD47B"));

        ScrollPanel.getViewport().add(jPanel);
    }

    private static void adder(String qst) {
        JLabel qstLabel;
        int x = frame.getContentPane().getSize().width / font.getSize() * 2 - 12;
        boolean flag = true;
        do {
            int pos = qst.lastIndexOf(" ", x);
            if (qst.length() > x) {
                qstLabel = new JLabel(qst.substring(0, pos + 1));
                qst = qst.substring(pos, qst.length());
            } else {
                qstLabel = new JLabel(qst);
                flag = false;
            }
            qstLabel.setFont(font);
            jPanel.add(qstLabel);
            System.out.println(qstLabel.getText());
        } while (flag);
    }

    private static void reader(String PatFile, String QstFile) {
        try {
            patFileReader = new FileReader("src/com/resources/AnswerFiles/" + PatFile + ".txt");
            qstFileReader = new FileReader(QstFile + ".txt");
            PatScan = new Scanner(patFileReader);
            QstScan = new Scanner(qstFileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
