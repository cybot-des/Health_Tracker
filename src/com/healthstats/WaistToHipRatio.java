package com.healthstats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WaistToHipRatio extends JFrame implements ActionListener, FocusListener {
    JPanel panel;
    JLabel WaistCircumTxt, HipCircumTxt, resultRatioTxt, wth_title;
    JTextField WaistCircum, HipCircum, resultRatio;
    JButton calculate, clear, back, track_wth;
    int id;

    WaistToHipRatio(int id)
    {
        this.id =id;
        //ADDING LEFT PANEL
        panel = new JPanel(new FlowLayout());
        panel.setBounds(0,0,350,800);
        panel.setBackground(Color.decode("#0f0080"));
        add(panel);

        ImageIcon i1 = new ImageIcon("images/wth_table.png");
        Image i2 = i1.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        panel.add(l11);

        wth_title = new JLabel("Waist-to-Hip Calculator");
        wth_title.setFont(new Font("Times New Roman",Font.ITALIC,25));
        wth_title.setBounds(450,50,500,30);
        add(wth_title);

        //-----Waist Circumference-----
        WaistCircumTxt = new JLabel("Waist Circumference");
        WaistCircumTxt.setBounds(430,100,130,30);
        add(WaistCircumTxt);

        WaistCircum = new JTextField("Waist Circumference in m");
        WaistCircum.setForeground(Color.GRAY);
        WaistCircum.setToolTipText("Waist Circumference in m");
        WaistCircum.setBounds(430,130,300,40);
        WaistCircum.addFocusListener(this);
        add(WaistCircum);

        //------Hip Circumference------
        HipCircumTxt = new JLabel("Hip Circumference");
        HipCircumTxt.setBounds(430,200,120,30);
        add(HipCircumTxt);

        HipCircum = new JTextField("Hip Circumference in m");
        HipCircum.setForeground(Color.GRAY);
        HipCircum.setToolTipText("Hip Circumference in m");
        HipCircum.setBounds(430,230,300,40);
        HipCircum.addFocusListener(this);
        add(HipCircum);

        //-------Result Ratio-------
        resultRatioTxt = new JLabel("Your W-to-H Ratio");
        resultRatioTxt.setBounds(430,300,200,30);
        add(resultRatioTxt);

        resultRatio = new JTextField();
        resultRatio.setBackground(Color.decode("#E6EE9C"));
        resultRatio.setEditable(false);
        resultRatio.setBounds(430,330,300,40);
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

        // waist-hip track button
        track_wth = new JButton("Track Waist-to-Hip");
        track_wth.setFont(new Font("Arial", Font.BOLD,18));
        track_wth.setLayout(new BoxLayout(track_wth, BoxLayout.Y_AXIS));
        track_wth.setAlignmentX(Component.CENTER_ALIGNMENT);
        track_wth.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 590)));
        track_wth.setBackground(Color.decode("#EDC988"));
        track_wth.addActionListener(this);
        panel.add(track_wth);

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
        WaistCircum.setText("Waist Circumference in m");
        WaistCircum.setForeground(Color.GRAY);
        HipCircum.setText("Hip Circumference in m");
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

                java.util.Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String date1 = formatter.format(date);


                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/healthtracker","root","root");
                PreparedStatement pst = con.prepareStatement("insert into trackedwhr values(?,?,?)");
                pst.setInt(1,id);
                pst.setDouble(2,Double.parseDouble(resultRatio.getText()));
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
            resultRatio.setText("");
        }
        else if(ae.getSource()==back)
        {
            new MenuPage(id);
            dispose();
        }
        else if(ae.getSource()==track_wth)
        {
            new WHRChart("WHR Count History",id);
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
//        new WaistToHipRatio(2);
//    }

}
