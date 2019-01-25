package com.application;

import com.database.DBSelect;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.application.App.frame;

public class PatientList extends JDialog {
    private JPanel contentPane;
    private JTable patientTable;
    private JScrollPane scrollPanel;
    private JTextArea nameInput;
    private JButton Menu;
    private DBSelect select = new DBSelect();


    public PatientList() {
        setContentPane(contentPane);
        setModal(true);
        tableCreate("");

        //set icon for button Menu
        Menu.setBounds(0, 0, 350, 90);
        ImageIcon MenuImg = new ImageIcon("src/com/resources/images/but_violet_menu.png");
        MenuImg.setImage(MenuImg.getImage().getScaledInstance(Menu.getWidth(), Menu.getHeight(), Image.SCALE_SMOOTH));
        Menu.setIcon(MenuImg);

        nameInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                showResult();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                showResult();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                showResult();
            }
        });

        Menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMenu();
            }
        });

        patientTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    System.out.println("doubleclick at row: " + row);
                    onSearch(row);
                }
            }
        });
    }

    private void onMenu() {
        frame.setContentPane(new MainMenu().getContentPane());
        frame.setVisible(true);
    }

    private void showResult() {
        tableCreate(nameInput.getText());
    }

    //Create a table where column "ФИО" has similarities with input "name"
    private void tableCreate(String name) {
        patientTable.setAutoCreateRowSorter(true);
        patientTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPanel.getHorizontalScrollBar().setUnitIncrement(5);
        scrollPanel.getViewport().setBackground(Color.decode("#616183"));

        ArrayList<ArrayList> patlist = new ArrayList<>();
        String[] columnNames = {"PatientId", "Full Name", "Date of Birth", "Age", "Place of Birth", "Gender", "Nationality", "Father Name",
                "Father DoB", "Father Age", "Mother Name", "Mother DoB", "Mother Age", "Family", "Arrived From", "Date of arrival",
                "How long in Astana", "Age Group"};
        int[] columnWidth = {60, 250, 110, 230, 140, 100, 120, 250, 135, 120, 250, 135, 120, 130, 140, 135, 120, 150};

        try {
            select.selectColumn("data", patlist, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() {
                return 18;
            }

            public int getRowCount() {
                return patlist.size();
            }

            public Object getValueAt(int row, int col) {
                return patlist.get(row).get(col);
            }

            public String getColumnName(int index) {
                return columnNames[index];
            }
        };

        patientTable.setModel(dataModel);

        for (int i = 0; i < columnWidth.length; i++) {
            if (i < patientTable.getColumnModel().getColumnCount()) {
                patientTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
            } else break;
        }
    }

    //Show patients card on double click
    private void onSearch(int row) {
        boolean isSuccess = true;

        // add your code here
        DBSelect select = new DBSelect();
        String PatientGroup = "";

        String Id = patientTable.getValueAt(row, 0).toString();
//        int pos = Id.indexOf(" ");
//
//        int PatientId = Integer.parseInt(Id.substring(0, pos));
        int PatientId = Integer.parseInt(Id);

        try {
            PatientGroup = select.selectItem("data", "Patients", "AgeGroup", "PatientId", PatientId);

//            int PatientId = select.selectId("data", "Patients", "PatientId",
//                    "FullName", patientTable.getValueAt(row, 1).toString() /*FullName.getText()*/,
//                    "DoB", patientTable.getValueAt(row, 1).toString()/*DoB.getText()*/);
//            filename = select.selectItem("data", PatientGroup, "FileName", "PatientId", PatientId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage() + "Error on search");
            isSuccess = false;
        }

//        PatientInfo.PatFile = filename;
//        PatientInfo.PatGroup = PatientGroup;
//
//        JOptionPane.showMessageDialog(frame, filename);

        if (isSuccess) {
            PatientCard.PatientID = PatientId;
            frame.setContentPane(new PatientCard().getContentPane());
            frame.setVisible(true);
        }

    }

}
