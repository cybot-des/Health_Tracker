package com.healthstats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class WaistToHipRatio extends JFrame implements ActionListener, FocusListener {
    JPanel panel;
    JLabel WaistCircumTxt, HipCircumTxt, resultRatioTxt;
    JTextField WaistCircum, HipCircum, resultRatio;
    JButton calculate, clear, back;

    WaistToHipRatio()
    {
        //ADDING LEFT PANEL
        panel = new JPanel(new FlowLayout());
        panel.setBounds(0,0,350,800);
        panel.setBackground(Color.decode("#0f0080"));
        add(panel);

        //-----Waist Circumference-----
        WaistCircumTxt = new JLabel("Waist Circumference");
        WaistCircumTxt.setBounds(430,70,120,30);
        add(WaistCircumTxt);

        WaistCircum = new JTextField("Waist Circumference in M");
        WaistCircum.setForeground(Color.GRAY);
        WaistCircum.setToolTipText("Waist Circumference in M");
        WaistCircum.setBounds(430,100,300,40);
        WaistCircum.addFocusListener(this);
        add(WaistCircum);

        //------Hip Circumference------
        HipCircumTxt = new JLabel("Hip Circumference");
        HipCircumTxt.setBounds(430,170,120,30);
        add(HipCircumTxt);

        HipCircum = new JTextField("Hip Circumference in M");
        HipCircum.setForeground(Color.GRAY);
        HipCircum.setToolTipText("Hip Circumference in M");
        HipCircum.setBounds(430,200,300,40);
        HipCircum.addFocusListener(this);
        add(HipCircum);

        //-------Result Ratio-------
        resultRatioTxt = new JLabel("Your W-to-H Ratio");
        resultRatioTxt.setBounds(430,270,200,30);
        add(resultRatioTxt);

        resultRatio = new JTextField();
        resultRatio.setBackground(Color.decode("#E6EE9C"));
        resultRatio.setEditable(false);
        resultRatio.setBounds(430,300,300,40);
        add(resultRatio);

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

        // FRAME PROPERTIES
        setIconImage(new ImageIcon("bmi.png").getImage());
        setSize(800,800);
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    void textBoxStyle() {
        WaistCircum.setText("Waist Circumference in M");
        WaistCircum.setForeground(Color.GRAY);
        HipCircum.setText("Hip Circumference in M");
        HipCircum.setForeground(Color.GRAY);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==calculate)
        {
            try
            {
                double w = Double.parseDouble(WaistCircum.getText());
                double h = Double.parseDouble(HipCircum.getText());
                double ratio_result;

                ratio_result = w/h;
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);
                resultRatio.setText(df.format(ratio_result));

            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Details Incomplete !");
            }

        }
        else if(ae.getSource()==clear)
        {
            textBoxStyle();
            resultRatio.setText("");
        }
        else if(ae.getSource()==back)
        {
            int id=0;
            new MenuPage(id);
            dispose();
        }
    }

    @Override
    public void focusGained(FocusEvent fe) {
        if(fe.getSource()==WaistCircum)
        {
            WaistCircum.setText("");
        }
        else if(fe.getSource()==HipCircum)
        {
            HipCircum.setText("");
        }

    }

    @Override
    public void focusLost(FocusEvent fe) {

    }

//    public static void main(String[] args) {
//        new WaistToHipRatio();
//    }

}
