package com.healthstats;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener
{
    JLabel lbl1, lbl2, title;
    JTextField username;
    JPasswordField password;
    JButton login, register;
    JPanel panel;
    JTree tree;
    JOptionPane optionPane;

    Login()
    {
        // ADDING LEFT PANEL
        panel = new JPanel(new FlowLayout());
        panel.setBounds(0,0,350,800);
        panel.setBackground(Color.decode("#0f0080"));
        add(panel);

        // ADDING IMAGE & PROJECT TITLE TO PANEL
        ImageIcon i1 = new ImageIcon("./images/healthcare(1).png");
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(70, 70, 100, 100);
        panel.add(l11);

        title  = new JLabel("HealthiFy");
        title.setForeground(Color.WHITE);
        title.setBounds(70,200,150,150);
        panel.add(title);

        // ADDING LABEL & TEXT FIELD COMPONENTS
        lbl1 = new JLabel("Username: ");
        lbl1.setBounds(430,70,120,30);
        add(lbl1);

        username = new JTextField();
        username.setBounds(430,100,300,40);
        add(username);

        lbl2 = new JLabel("Password: ");
        lbl2.setBounds(430,150,120,30);
        add(lbl2);

        password = new JPasswordField();
        password.setBounds(430,180,300,40);
        add(password);

        // ADDING LOGIN & REGISTER BUTTON
        login = new JButton("Login");
        login.setBounds(450,350,100,40);
        login.setBackground(new Color(154, 225, 39));
        add(login);

        register = new JButton("Register");
        register.setBounds(610,350,100,40);
        register.setBackground(new Color(154, 225, 39));
        add(register);

        login.addActionListener(this);
        register.addActionListener(this);

        // FRAME PROPERTIES
        setLayout(null);
        setSize(800,800);
        setResizable(false);
        setVisible(true);
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    @Override
    public void actionPerformed(ActionEvent ae) {
        try
        {
            if(ae.getSource()==login)
            {
                String uname = username.getText();
                String passwd = password.getText();

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/healthtracker","root","root");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from login where username='"+uname+"' and password='"+passwd+"'");

                if(rs.next())
                {
                    System.out.println("User Logged in!");
                    MenuPage menupage = new MenuPage();
                    setVisible(false);
                }
                else
                {
                    optionPane.showMessageDialog(null, "Incorrect Login Details!!");
                }
                con.close();
            }

            else if(ae.getSource()==register)
            {
                Register register = new Register();
                setVisible(false);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String args[])
    {
        new Login();
    }
}
