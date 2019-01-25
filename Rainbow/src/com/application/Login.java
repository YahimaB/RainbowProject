package com.application;

import com.database.DBSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import static com.application.App.frame;

/**
 * Created by Yahima on 28.11.2017.
 */
public class Login extends JDialog {
    private JTextField Username_in;
    JPanel LoginPanel;
    private JLabel Username;
    private JLabel Password;
    private JButton ProceedButt;
    private JPasswordField Password_in;

    public Login() {
        setContentPane(LoginPanel);
        setModal(true);
        getRootPane().setDefaultButton(ProceedButt);

        //set icon for button
        ProceedButt.setBounds(0, 0, 350, 90);
        ImageIcon ProImg = new ImageIcon("src/com/resources/images/but_darkblue_fwd.png");
        ProImg.setImage(ProImg.getImage().getScaledInstance(ProceedButt.getWidth(), ProceedButt.getHeight(), Image.SCALE_SMOOTH));
        ProceedButt.setIcon(ProImg);

        ProceedButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onProceedButt();
            }
        });

        LoginPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onProceedButt();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }


    private void onProceedButt() {
        DBSelect sel = new DBSelect();

        String login = Username_in.getText();
        String passwordIn = Password_in.getText();
        String password = null;
        try {
            password = sel.selectItem("prototype#1", "Users", "Password", "Username", login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!password.equals(passwordIn) || login.isEmpty() || passwordIn.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Wrong Username \n or Password", "Something is wrong", JOptionPane.ERROR_MESSAGE);
        } else {
            if (login.equals("admin")) {
                MainMenu.check = 0;
                selectionWindow();
            } else if (login.equals("user")) {
                MainMenu.check = 1;
                selectionWindow();
            }
        }
    }


    private void selectionWindow() {
        dispose();
        frame.setContentPane(new MainMenu().getContentPane());
        frame.setVisible(true);
    }

}
