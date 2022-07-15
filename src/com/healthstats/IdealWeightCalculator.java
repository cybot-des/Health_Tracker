package com.healthstats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class IdealWeightCalculator extends JFrame implements ActionListener, FocusListener
{
    JPanel panel;
    JTextField height, cur_weight, ideal;
    String gen[] = {"Male","Female"};
    JComboBox gender;
    JLabel genderTxt, heightTxt, cur_weightTxt, idealTxt;
    JButton calculate, clear, back;
    int id;

    IdealWeightCalculator(int id)
    {
        this.id = id;
        // ADDING LEFT PANEL
        panel = new JPanel(new FlowLayout());
        panel.setBounds(0,0,350,800);
        panel.setBackground(Color.decode("#0f0080"));
        add(panel);

        // ADDING LABELS & TEXTFIELDS

        //---gender---
        genderTxt = new JLabel("Gender");
        genderTxt.setBounds(430,70,120,30);
        add(genderTxt);

        gender = new JComboBox<String>(gen);
        gender.setFocusable(false);
        gender.setBackground(new Color(255,255,255));
        gender.setBounds(430,100,300,40);
        add(gender);

        //---height---
        heightTxt = new JLabel("Height");
        heightTxt.setBounds(430,170,120,30);
        add(heightTxt);

        height = new JTextField("Height in M");
        //height.setFocusable(false);
        height.setForeground(Color.GRAY);
        height.setToolTipText("Height in M");
        height.setBounds(430,200,300,40);
        height.addFocusListener(this);
        add(height);

        //---Current Weight---
        cur_weightTxt = new JLabel("Current Weight");
        cur_weightTxt.setBounds(430,270,120,30);
        add(cur_weightTxt);

        cur_weight = new JTextField("Weight in Kg");
        //cur_weight.setFocusable(false);
        cur_weight.setForeground(Color.GRAY);
        cur_weight.setToolTipText("Weight in cm");
        cur_weight.setBounds(430,300,300,40);
        cur_weight.addFocusListener(this);
        add(cur_weight);

        //-----Ideal weight result------
        idealTxt = new JLabel("Your Ideal Weight");
        idealTxt.setBounds(430,370,120,30);
        add(idealTxt);

        ideal = new JTextField();
        //cur_weight.setFocusable(false);
        ideal.setBackground(Color.decode("#E6EE9C"));
        ideal.setEditable(false);
        ideal.setBounds(430,400,300,40);
        add(ideal);


        // ADDING CALCULATE, CLEAR & BACK BUTTONS
        calculate = new JButton("Calculate");
        calculate.setBounds(430,500,120,30);
        calculate.setBackground(new Color(154, 225, 39));
        calculate.addActionListener(this);
        add(calculate);

        clear = new JButton("Clear");
        clear.setBounds(610,500,120,30);
        clear.setBackground(new Color(154, 225, 39));
        clear.addActionListener(this);
        add(clear);

        back = new JButton("Back");
        back.setBounds(520,580,120,30);
        back.setBackground(new Color(154, 225, 39));
        back.addActionListener(this);
        add(back);

        // FRAME PROPERTIES
        setLayout(null);
        setSize(800,800);
        setResizable(false);
        setVisible(true);
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void textBoxStyle()
    {
        height.setText("Height in M");
        height.setForeground(Color.GRAY);
        cur_weight.setText("Weight in Kg");
        cur_weight.setForeground(Color.GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==calculate)
        {
            //Ideal Weight (kg) = 2.2 x BMItarget + 3.5 x BMItarget x (Height (m) - 1.5 m)
            try{
                double w = Double.parseDouble(cur_weight.getText());
                double h = Double.parseDouble(height.getText());

                double ideal_weight = (2.2*22) + (3.5 * 22 * (h-1.5));
                ideal.setEditable(true);
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);
                ideal.setText(String.valueOf(df.format(ideal_weight)));
                ideal.setEditable(false);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null,"Details Incomplete !");
            }

        }
        else if(ae.getSource()==clear)
        {
            textBoxStyle();
            ideal.setText("");
        }

        else if(ae.getSource()==back)
        {
            new MenuPage(id);
            dispose();
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
    public void focusLost(FocusEvent fe) {
//        if(fe.getSource()==height)
//        {
//
//        }

    }

//    public static void main(String[] args) {
//        new IdealWeightCalculator(0);
//    }
}
