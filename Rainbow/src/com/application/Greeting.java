package com.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.application.App.frame;

public class Greeting extends JDialog {
    private JPanel GreetingPanel;
    private JButton LoginButt;
    private JLabel Logo;
    private JButton Preschool;


    public Greeting() {
        setContentPane(GreetingPanel);
        setModal(true);
        getRootPane().setDefaultButton(LoginButt);

        //set icon for logo
        Logo.setBounds(0, 0, 870, 340);
        ImageIcon LogoImg = new ImageIcon("src/com/resources/images/logo.png");
        LogoImg.setImage(LogoImg.getImage().getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_SMOOTH));
        Logo.setIcon(LogoImg);

        //set icon for button
        LoginButt.setBounds(0, 0, 475, 120);
        ImageIcon LoginImg = new ImageIcon("src/com/resources/images/but_orange_fwd.png");
        LoginImg.setImage(LoginImg.getImage().getScaledInstance(LoginButt.getWidth(), LoginButt.getHeight(), Image.SCALE_SMOOTH));
        LoginButt.setIcon(LoginImg);


        LoginButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onLoginButt();
            }
        });

        Preschool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Analysis().getContentPane());
                frame.setVisible(true);
            }
        });
    }

    private void onLoginButt() {
        Login frame = new Login();

        frame.setSize(new Dimension(500, 500));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        frame.setVisible(true);

    }


}
