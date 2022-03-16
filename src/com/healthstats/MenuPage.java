package com.healthstats;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPage extends JFrame implements ActionListener
{
    JPanel panel;

    MenuPage()
    {
        panel = new JPanel(new FlowLayout());
        panel.setBounds(0,0,350,800);
        panel.setBackground(Color.decode("#0f0080"));
        add(panel);



        setSize(800,800);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
