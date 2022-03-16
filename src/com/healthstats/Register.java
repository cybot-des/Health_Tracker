package com.healthstats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Register extends JFrame implements ActionListener
{
    JPanel panel;
    JLabel nameTxt, emailidTxt ,usernameTxt, passwordTxt, cnf_pwdTxt, successTxt;
    JTextField name, emailid,username;
    JPasswordField password, cnf_pwd;
    JButton submit,back_to_login;

    Register()
    {
        // panel
        panel = new JPanel();
        panel.setBounds(0,0,350,800);
        panel.setBackground(Color.decode("#0f0080"));
        add(panel);

        // name
        nameTxt = new JLabel("Name");
        nameTxt.setBounds(430,90,300,30);
        add(nameTxt);

        name = new JTextField();
        name.setBounds(430,115,300,30);
        add(name);


        // username
        usernameTxt = new JLabel("Username");
        usernameTxt.setBounds(430,160,300,30);
        add(usernameTxt);

        username = new JTextField();
        username.setBounds(430,185 , 300, 30);
        add(username);

        // email-id
        emailidTxt = new JLabel("e-mail id"); //.startsWith()
        emailidTxt.setBounds(430,230,300,30);
        add(emailidTxt);

        emailid = new JTextField();
        emailid.setBounds(430,255,300,30);
        add(emailid);

        // password
        passwordTxt = new JLabel("Password");
        passwordTxt.setBounds(430,300,300,30);
        add(passwordTxt);

        password = new JPasswordField();
        password.setBounds(430,325,300,30);
        add(password);

        // confirm password
        cnf_pwdTxt = new JLabel("Confirm Password");
        cnf_pwdTxt.setBounds(430,370,300,30);
        add(cnf_pwdTxt);

        cnf_pwd = new JPasswordField("");
        cnf_pwd.setBounds(430,395,300,30);
        add(cnf_pwd);

        // submit
        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.BOLD,15));
        submit.setBounds(530,465,100,30);
        submit.setBackground(Color.decode("#228B22"));
        submit.addActionListener(this);
        add(submit);

        // success
        successTxt = new JLabel("");
        successTxt.setBounds(505,510,200,30);
        successTxt.setBackground(new Color(154, 225, 39));
        add(successTxt);

        //Back to Login
        back_to_login = new JButton("Back to Login");
        back_to_login.setFont(new Font("Arial", Font.BOLD,15));
        back_to_login.setBounds(530,520,100,30);
        back_to_login.setBackground(Color.decode("#228B22"));
        back_to_login.addActionListener(this);
        add(back_to_login);

        // frame
        setSize(800,800);
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==submit)
        {
            try
            {
                String uname = username.getText();
                String passwd = password.getText();
                String name1 = name.getText();
                String cnf_passwd = cnf_pwd.getText();
                String email = emailid.getText();

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/healthtracker","root","root");
                if(passwd.equals(cnf_passwd))
                {
                    PreparedStatement pst = conn.prepareStatement("insert into login values(?,?,?,?)");
                    ResultSet rs;
                    pst.setString(1,uname);
                    pst.setString(2,passwd);
                    pst.setString(3,name1);
                    pst.setString(4,email);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null,name1+" your details submitted !");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Password and confirm password dont match!!");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==back_to_login)
        {
            new Login();
            setVisible(false);
        }

    }

    public static void main(String args[])
    {
        new Register();
    }
}
