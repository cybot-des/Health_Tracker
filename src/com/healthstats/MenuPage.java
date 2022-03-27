package com.healthstats;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPage extends JFrame implements ActionListener
{
    JPanel panel;
    JButton bmi,whr,bfc,track,idwc;
    int id;

    MenuPage(int id)
    {
        this.id = id;
        System.out.println(id);
        panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setBounds(0,0,350,800);
        panel.setBackground(Color.decode("#0f0080"));
        add(panel);

        bmi = new JButton("Calculate BMI");
        bmi.setFont(new Font("Arial", Font.BOLD,15));
        //bmi.setBounds(70,225,210,30);
        bmi.setBackground(Color.decode("#228B22"));
        //bmi.setBorder(BorderFactory.createLineBorder(Color.black,2));
        bmi.setAlignmentX(Component.CENTER_ALIGNMENT);
        bmi.setAlignmentY(Component.CENTER_ALIGNMENT);
        bmi.addActionListener(this);
        panel.add(bmi);

        whr = new JButton("Calculate W-to-H Ratio");
        whr.setFont(new Font("Arial", Font.BOLD,15));
        //whr.setBounds(70,325,210,30);
        whr.setBackground(Color.decode("#228B22"));
        whr.setAlignmentX(Component.CENTER_ALIGNMENT);
        whr.setAlignmentY(Component.CENTER_ALIGNMENT);
        //whr.setBorder(BorderFactory.createLineBorder(Color.black,2));
        whr.addActionListener(this);
        panel.add(whr);

        // calorie
        bfc = new JButton("Calculate Body Fat Percentage");
        bfc.setFont(new Font("Arial", Font.BOLD,15));
        //calorie.setBounds(70,425,210,30);
        bfc.setBackground(Color.decode("#228B22"));
        bfc.setAlignmentX(Component.CENTER_ALIGNMENT);
        bfc.setAlignmentY(Component.CENTER_ALIGNMENT);
        //calorie.setBorder(BorderFactory.createLineBorder(Color.black,2));
        bfc.addActionListener(this);
        panel.add(bfc);

        idwc = new JButton("Calculate Ideal Weight");
        idwc.setFont(new Font("Arial", Font.BOLD,15));
        //calorie.setBounds(70,425,210,30);
        idwc.setBackground(Color.decode("#228B22"));
        idwc.setAlignmentX(Component.CENTER_ALIGNMENT);
        idwc.setAlignmentY(Component.CENTER_ALIGNMENT);
        //calorie.setBorder(BorderFactory.createLineBorder(Color.black,2));
        idwc.addActionListener(this);
        panel.add(idwc);

        // track button
        track = new JButton("Track BMI");
        track.setFont(new Font("Arial", Font.BOLD,15));
        track.setBounds(475,321,210,30);
        track.setBackground(Color.decode("#4169E1"));
        //track.setBorder(BorderFactory.createLineBorder(Color.black,2));
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
        if(ae.getSource()==bmi)
        {
            new BmiCalculator(id);
        }

        else if(ae.getSource()==whr)
        {
            new WaistToHipRatio();
        }
        else if(ae.getSource()==bfc)
        {
            new BodyFatCalculator();
        }
        else if(ae.getSource()==idwc)
        {
            new IdealWeightCalculator();
        }
        else if(ae.getSource()==track)
        {
            new BmiChart("BMI Count History",id);
        }

    }

    public static void main(String[] args) {
        new MenuPage(0);
    }
}
