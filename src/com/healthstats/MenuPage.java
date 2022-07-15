package com.healthstats;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPage extends JFrame implements ActionListener
{
    JPanel panel;
    JButton bmi,whr,bfc,track,idwc, logout;
    JLabel title;
    int id;

    MenuPage(int id)
    {
        this.id = id;
        System.out.println(id);
        panel = new JPanel();
        panel.setBounds(0,0,350,800);
        panel.setBackground(Color.decode("#0f0080"));
        add(panel);

        Icon imgIcon = new ImageIcon(new ImageIcon("./images/healthcare(1).png").getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH));
        JLabel label = new JLabel();
        //label.setBounds(10, 43, 100, 100); // You can use your own values
        label.setIcon(imgIcon);
        label.setFont(new Font("Verdana",Font.BOLD,100));
        panel.add(label);

        title  = new JLabel("HealthiFy");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana", Font.BOLD,40));
        title.setBounds(70,500,300,300);
        panel.add(title);

        bmi = new JButton("Calculate BMI");
        bmi.setFont(new Font("Arial", Font.BOLD,15));
        bmi.setBounds(440,200,252,30);
        bmi.setBackground(new Color(154, 225, 39));
        bmi.addActionListener(this);
        add(bmi);

        whr = new JButton("Calculate W-to-H Ratio");
        whr.setFont(new Font("Arial", Font.BOLD,15));
        whr.setBounds(440,300,252,30);
        whr.setBackground(new Color(154, 225, 39));
        whr.addActionListener(this);
        add(whr);

        // calorie
        bfc = new JButton("Calculate Body Fat %");
        bfc.setFont(new Font("Arial", Font.BOLD,15));
        bfc.setBounds(440,400,252,30);
        bfc.setBackground(new Color(154, 225, 39));
        bfc.addActionListener(this);
        add(bfc);

        idwc = new JButton("Calculate Ideal Weight");
        idwc.setFont(new Font("Arial", Font.BOLD,15));
        idwc.setBounds(440,500,252,30);
        idwc.setBackground(new Color(154, 225, 39));
        idwc.addActionListener(this);
        add(idwc);

        logout = new JButton("Logout");
        logout.setFont(new Font("Arial", Font.BOLD,15));
        logout.setBounds(500,600,120,30);
        logout.setBackground(Color.RED);
        logout.addActionListener(this);
        add(logout);


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
            dispose();
        }
        else if(ae.getSource()==whr)
        {
            new WaistToHipRatio(id);
            dispose();
        }
        else if(ae.getSource()==bfc)
        {
            new BodyFatCalculator(id);
            dispose();
        }
        else if(ae.getSource()==idwc)
        {
            new IdealWeightCalculator(id);
            dispose();
        }
        else if(ae.getSource()==logout)
        {
            int result = JOptionPane.showConfirmDialog(null,"Are you sure?","logout confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION)
            {
                new Login();
                dispose();
            }
            else if (result == JOptionPane.NO_OPTION){

            }

        }


    }

//    public static void main(String[] args) {
//        new MenuPage(0);
//    }
}
