package com.healthstats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;


public class NewWindow extends JFrame implements ActionListener {
    JPanel panel1;
    JLabel height,weight,bmi;
    JTextField height1, weight1;
    JButton calculateBmi;
    JTextArea bmi1;
    double ht=0,wt=0,res=0;

    NewWindow(){
        panel1 = new JPanel(new FlowLayout());
        panel1.setBounds(0,0,350,800);
        panel1.setBackground(Color.decode("#0f0080"));
        add(panel1);

        // for entering height
        height = new JLabel("Enter your height (in m): ");
        height.setFont(new Font("Times New Roman", Font.BOLD,14));
        height.setBounds(380,150,200,30);
        add(height);

        height1 = new JTextField();
        height1.setBorder(BorderFactory.createLineBorder(Color.black,1));
        height1.setBounds(550,150,200,30);
        add(height1);

        // for entering weight
        weight = new JLabel("Enter your weight (in kg): ");
        weight.setFont(new Font("Times New Roman", Font.BOLD,14));
        weight.setBounds(380,250,200,30);
        add(weight);

        weight1 = new JTextField();
        weight1.setBorder(BorderFactory.createLineBorder(Color.black,1));
        weight1.setBounds(550,250,200,30);
        add(weight1);

        // button for calculating bmi
        calculateBmi = new JButton("Compute BMI");
        calculateBmi.setFont(new Font("Arial", Font.BOLD,15));
        calculateBmi.setBounds(470,350,200,30);
        calculateBmi.setBackground(Color.decode("#228B22"));
        calculateBmi.addActionListener(this);
        add(calculateBmi);

        // creating a label for bmi
        bmi = new JLabel("Your BMI:");
        bmi.setFont(new Font("Times New Roman", Font.BOLD,14));
        bmi.setBounds(380,450,200,30);
        add(bmi);

        bmi1 = new JTextArea();
        bmi1.setFont(new Font("Times New Roman", Font.BOLD,14));
        bmi1.setBorder(BorderFactory.createLineBorder(Color.black,1));
        bmi1.setBounds(550,450,200,30);
        add(bmi1);


        setIconImage(new ImageIcon("bmi.png").getImage());
        setSize(800,800);
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource()==calculateBmi){
                ht = Double.parseDouble(height1.getText());
                wt = Double.parseDouble(weight1.getText());
                res = ht/(wt*wt);
                bmi1.setText(String.valueOf(res));
//                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/healthtracker","root","root");
//                Statement st = con.createStatement();
//                ResultSet rs = st.executeQuery("insert into BMI where values(?,?,?)",(ht,wt,res));
//
//                if(rs.next()) {
//                    System.out.println("Data inserted!");
//                }
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }
}
