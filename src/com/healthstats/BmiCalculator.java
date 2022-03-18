package com.healthstats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;


public class BmiCalculator extends JFrame implements ActionListener {
    JPanel panel1;
    JLabel height,weight,bmi;
    JTextField height1, weight1;
    JButton calculateBmi, back, clr;
    JTextArea bmi1;
    double ht=0,wt=0,res=0;

    BmiCalculator(){
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

        // creating a label for bmi
        bmi = new JLabel("Your BMI:");
        bmi.setFont(new Font("Times New Roman", Font.BOLD,14));
        bmi.setBounds(380,350,200,30);
        add(bmi);

        bmi1 = new JTextArea();
        bmi1.setBackground(Color.decode("#E6EE9C"));
        bmi1.setFont(new Font("Times New Roman", Font.BOLD,14));
        bmi1.setBorder(BorderFactory.createLineBorder(Color.black,1));
        bmi1.setEditable(false);
        bmi1.setBounds(550,350,200,30);
        add(bmi1);

        // button for calculating bmi
        calculateBmi = new JButton("Calculate");
        calculateBmi.setFont(new Font("Arial", Font.BOLD,15));
        calculateBmi.setBounds(430,500,120,30);
        calculateBmi.setBackground(new Color(154, 225, 39));
        calculateBmi.addActionListener(this);
        add(calculateBmi);

        clr = new JButton("Clear");
        clr.setFont(new Font("Arial", Font.BOLD,15));
        clr.setBounds(610,500,120,30);
        clr.setBackground(new Color(154, 225, 39));
        clr.addActionListener(this);
        add(clr);

        back = new JButton("Back");
        back.setBounds(520,580,120,30);
        back.setBackground(new Color(154, 225, 39));
        back.addActionListener(this);
        add(back);

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
                res = wt/(ht*ht);
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);
                bmi1.setEditable(true);
                bmi1.setText(String.valueOf(df.format(res)));
                bmi1.setEditable(false);
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

    public static void main(String[] args) {
        new BmiCalculator();
    }
}