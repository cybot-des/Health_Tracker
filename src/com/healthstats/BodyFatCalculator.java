package com.healthstats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BodyFatCalculator extends JFrame implements ActionListener, FocusListener
{
    JPanel panel;
    JTextField age, height, cur_weight, bfp;
    JComboBox gender;
    String gen[] = {"Male","Female"};
    JLabel ageTxt, heightTxt, cur_weightTxt, genderTxt, bfpTxt, bf_title;
    JButton calculate, clear, back, track_bf;
    int id;

    BodyFatCalculator(int id)
    {
        this.id = id;
        // ADDING LEFT PANEL
        panel = new JPanel(new FlowLayout());
        panel.setBounds(0,0,350,800);
        panel.setBackground(Color.decode("#0f0080"));
        add(panel);

        ImageIcon i1 = new ImageIcon("images/body-fat-chart.jpg");
        Image i2 = i1.getImage().getScaledInstance(350, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        panel.add(l11);

        bf_title = new JLabel("Body Fat Calculator");
        bf_title.setFont(new Font("Times New Roman",Font.ITALIC,25));
        bf_title.setBounds(480,30,500,30);
        add(bf_title);

        // ADDING TEXTFIELDS & LABELS
        //---gender---
        genderTxt = new JLabel("Gender");
        genderTxt.setBounds(430,70,120,30);
        add(genderTxt);

        gender = new JComboBox<String>(gen);
        gender.setFocusable(false);
        gender.setBackground(new Color(255,255,255));
        gender.setBounds(430,100,300,40);
        add(gender);

        //---age---
        ageTxt = new JLabel("Age");
        ageTxt.setBounds(430,170,120,30);
        add(ageTxt);

        age = new JTextField();
        age.setForeground(Color.GRAY);
        age.setBounds(430,200,300,40);
        add(age);


        //---height---
        heightTxt = new JLabel("Height");
        heightTxt.setBounds(430,270,120,30);
        add(heightTxt);

        height = new JTextField("Height in M");
        height.setForeground(Color.GRAY);
        height.setToolTipText("Height in M");
        height.setBounds(430,300,300,40);
        height.addFocusListener(this);
        add(height);


        //---Current Weight---
        cur_weightTxt = new JLabel("Current Weight");
        cur_weightTxt.setBounds(430,370,120,30);
        add(cur_weightTxt);

        cur_weight = new JTextField("Weight in Kg");
        cur_weight.setForeground(Color.GRAY);
        cur_weight.setToolTipText("Weight in cm");
        cur_weight.setBounds(430,400,300,40);
        cur_weight.addFocusListener(this);
        add(cur_weight);


        //-----BFP Result-----
        bfpTxt = new JLabel("Your Body Fat Percentage (in %)");
        bfpTxt.setBounds(430,470,200,30);
        add(bfpTxt);

        bfp = new JTextField();
        bfp.setBackground(Color.decode("#E6EE9C"));
        bfp.setEditable(false);
        bfp.setBounds(430,500,300,40);
        add(bfp);

        // ADDING CALCULATE, CLEAR & BACK BUTTONS
        calculate = new JButton("Calculate");
        calculate.setBounds(430,600,120,30);
        calculate.setBackground(new Color(154, 225, 39));
        calculate.addActionListener(this);
        add(calculate);

        clear = new JButton("Clear");
        clear.setBounds(610,600,120,30);
        clear.setBackground(new Color(154, 225, 39));
        clear.addActionListener(this);
        add(clear);

        back = new JButton("Back");
        back.setBounds(520,680,120,30);
        back.setBackground(new Color(154, 225, 39));
        back.addActionListener(this);
        add(back);

        // bodyfat track button
        track_bf = new JButton("Track Body Fat");
        track_bf.setFont(new Font("Arial", Font.BOLD,18));
        track_bf.setLayout(new BoxLayout(track_bf, BoxLayout.Y_AXIS));
        track_bf.setAlignmentX(Component.CENTER_ALIGNMENT);
        track_bf.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 800)));
        track_bf.setBackground(Color.decode("#EDC988"));
        track_bf.addActionListener(this);
        panel.add(track_bf);

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
        height.setText("Height in M");
        height.setForeground(Color.GRAY);
        cur_weight.setText("Weight in Kg");
        cur_weight.setForeground(Color.GRAY);
        age.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==calculate)
        {
            try
            {
                double w = Double.parseDouble(cur_weight.getText());
                double h = Double.parseDouble(height.getText());
                double a = Double.parseDouble(age.getText());
                double bfp_result;

                double bmi = w/(h*h);
                // Males --> BFP = 1.20 × BMI + 0.23 × Age - 16.2
                // Females --> BFP = 1.20 × BMI + 0.23 × Age - 5.4

                if(gender.getSelectedItem()=="Male")
                {
                    bfp_result = (1.20 * bmi) + (0.23 * a - 16.2);
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.CEILING);
                    bfp.setText(df.format(bfp_result));
                }
                else if(gender.getSelectedItem()=="Female")
                {
                    bfp_result = (1.20 * bmi) + (0.23 * a - 5.4);
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.CEILING);
                    bfp.setText(df.format(bfp_result));
                }

                java.util.Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String date1 = formatter.format(date);


                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/healthtracker","root","root");
                PreparedStatement pst = con.prepareStatement("insert into trackedbodyfat values(?,?,?)");
                pst.setInt(1,id);
                pst.setDouble(2,Double.parseDouble(bfp.getText()));
                pst.setString(3,date1);
                pst.executeUpdate();
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null,"Invalid Input Given !");
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
            catch(NullPointerException ne)
            {
                JOptionPane.showMessageDialog(null,"Details Incomplete !");
            }


        }
        else if(ae.getSource()==clear)
        {
            textBoxStyle();
            bfp.setText("");
        }
        else if(ae.getSource()==back)
        {
            new MenuPage(id);
            dispose();
        }
        else if(ae.getSource()==track_bf)
        {
            new BodyFatChart("Body Fat % History",id);
        }

    }

    @Override
    public void focusGained(FocusEvent fe) {
        if(fe.getSource()==height)
        {
            height.setText("");
        }
        else if(fe.getSource()==cur_weight)
        {
            cur_weight.setText("");
        }

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

//    public static void main(String[] args) {
//        new BodyFatCalculator(1);
//    }


}
