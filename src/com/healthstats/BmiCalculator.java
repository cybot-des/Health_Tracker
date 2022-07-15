package com.healthstats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BmiCalculator extends JFrame implements ActionListener, FocusListener {
    JPanel panel1;
    JLabel height,weight,bmi, bmi_title;
    JTextField height1, weight1;
    JButton calculateBmi, back, clr, track;
    JTextArea bmi1;
    double ht=0,wt=0,res=0;
    int id;

    BmiCalculator(int id){
        this.id = id;
        panel1 = new JPanel();
        panel1.setBounds(0,0,350,800);
        panel1.setBackground(Color.decode("#0f0080"));
        add(panel1);

        ImageIcon i1 = new ImageIcon("images/meter.png");
        Image i2 = i1.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        panel1.add(l11);

        bmi_title = new JLabel("BMI Calculator");
        bmi_title.setFont(new Font("Times New Roman",Font.ITALIC,25));
        bmi_title.setBounds(490,50,200,30);
        add(bmi_title);

        // for entering height
        height = new JLabel("Enter your height (in m): ");
        height.setFont(new Font("Times New Roman", Font.BOLD,14));
        height.setBounds(380,150,200,30);
        add(height);

        height1 = new JTextField("Height in m");
        //height1.setText();
        height1.setForeground(Color.GRAY);
        height1.setToolTipText("Height in m");
        height1.setBorder(BorderFactory.createLineBorder(Color.black,1));
        height1.setBounds(550,150,200,30);
        height1.addFocusListener(this);
        add(height1);

        // for entering weight
        weight = new JLabel("Enter your weight (in kg): ");
        weight.setFont(new Font("Times New Roman", Font.BOLD,14));
        weight.setBounds(380,250,200,30);
        add(weight);

        weight1 = new JTextField("Weight in kg");
        //weight1.setText();
        weight1.setForeground(Color.GRAY);
        weight1.setToolTipText("Weight in kg");
        weight1.setBorder(BorderFactory.createLineBorder(Color.black,1));
        weight1.setBounds(550,250,200,30);
        weight1.addFocusListener(this);
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


        // track button
        track = new JButton("Track BMI");
        track.setFont(new Font("Arial", Font.BOLD,18));
        track.setLayout(new BoxLayout(track, BoxLayout.Y_AXIS));
        track.setAlignmentX(Component.CENTER_ALIGNMENT);
        track.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel1.add(Box.createRigidArea(new Dimension(0, 550)));
        track.setBackground(Color.decode("#EDC988"));
        track.addActionListener(this);
        panel1.add(track);

        // button for calculating bmi
        calculateBmi = new JButton("Calculate");
        calculateBmi.setFont(new Font("Arial", Font.BOLD,15));
        calculateBmi.setBounds(410,500,120,30);
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
        back.setBounds(510,580,120,30);
        back.setBackground(new Color(154, 225, 39));
        back.addActionListener(this);
        add(back);

        setIconImage(new ImageIcon("bmi.png").getImage());
        setSize(800,800);
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    void textBoxStyle()
    {
        height1.setText("Height in m");
        height1.setForeground(Color.GRAY);
        weight1.setText("Weight in Kg");
        weight1.setForeground(Color.GRAY);
        bmi1.setText("");

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            if(ae.getSource()==calculateBmi){
                ht = Double.parseDouble(height1.getText());
                wt = Double.parseDouble(weight1.getText());
                res = wt/(ht*ht);
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);
                bmi1.setEditable(true);
                bmi1.setText(String.valueOf(df.format(res)));
                bmi1.setEditable(false);

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String date1 = formatter.format(date);


                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/healthtracker","root","root");
                PreparedStatement pst = con.prepareStatement("insert into trackedbmi values(?,?,?)");
                pst.setInt(1,id);
                pst.setDouble(2,Double.parseDouble(bmi1.getText()));
                pst.setString(3,date1);
                pst.executeUpdate();


            }
            else if(ae.getSource()==clr)
            {
                textBoxStyle();
            }
            else if(ae.getSource()==back)
            {
                new MenuPage(id);
                dispose();
            }
            else if(ae.getSource()==track)
            {
                new BmiChart("BMI Count History",id);
            }

        }catch(Exception e1){
            e1.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        int id=1;
//        new BmiCalculator(id);
//    }

    @Override
    public void focusGained(FocusEvent fe) {
        if(fe.getSource()==height1)
        {
            height1.setText("");
        }
        else if(fe.getSource()==weight1)
        {
            weight1.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}