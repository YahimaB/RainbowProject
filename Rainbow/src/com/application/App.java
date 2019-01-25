package com.application;

import com.database.DBConnect;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class App {
    public static JFrame frame;

    public static void main(String[] args) {

        Connection conn = DBConnect.connect("prototype#1");

        frame = new JFrame("Rainbow App");
        frame.setSize(new Dimension(950,820));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setContentPane(new Greeting().getContentPane());
        frame.setVisible(true);



    }
}
