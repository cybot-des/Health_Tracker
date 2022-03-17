package com.healthstats;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPage extends JFrame implements ActionListener
{
    JPanel panel;
    JButton bmi,whr,calorie,track;

    MenuPage()
    {
        panel = new JPanel(new FlowLayout());
        panel.setBounds(0,0,350,800);
        panel.setBackground(Color.decode("#0f0080"));
        add(panel);

        // created a button to calculate bmi
        bmi = new JButton("Calculate BMI");
        bmi.setFont(new Font("Arial", Font.BOLD,15));
        bmi.setBounds(70,225,210,30);
        bmi.setBackground(Color.decode("#228B22"));
        bmi.setBorder(BorderFactory.createLineBorder(Color.black,2));
        bmi.addActionListener(this);
        add(bmi);

        // created a button to calculate w-to-h ratio
        whr = new JButton("Calculate W-to-H Ratio");
        whr.setFont(new Font("Arial", Font.BOLD,15));
        whr.setBounds(70,325,210,30);
        whr.setBackground(Color.decode("#228B22"));
        whr.setBorder(BorderFactory.createLineBorder(Color.black,2));
        whr.addActionListener(this);
        add(whr);

        // created a button to calculate calories
        calorie = new JButton("Calculate Calories");
        calorie.setFont(new Font("Arial", Font.BOLD,15));
        calorie.setBounds(70,425,210,30);
        calorie.setBackground(Color.decode("#228B22"));
        calorie.setBorder(BorderFactory.createLineBorder(Color.black,2));
        calorie.addActionListener(this);
        add(calorie);

        // created a button to track bmi
        track = new JButton("Track BMI");
        track.setFont(new Font("Arial", Font.BOLD,15));
        track.setBounds(475,321,210,30);
        track.setBackground(Color.decode("#4169E1"));
        track.setBorder(BorderFactory.createLineBorder(Color.black,2));
        track.addActionListener(this);
        add(track);

        setIconImage(new ImageIcon("scales.png").getImage());
        setSize(800,800);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            if(ae.getSource()==bmi){
                BmiCalculator bmical = new BmiCalculator();
                bmical.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MenuPage();
    }
}
