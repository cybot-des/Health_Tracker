package com.healthstats;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Pattern;

class EmailInvalidException extends Exception{
    EmailInvalidException()
    {
        JOptionPane.showMessageDialog(null,"Email Invalid");
    }
}

class NameInvalidException extends Exception{
    NameInvalidException()
    {
        JOptionPane.showMessageDialog(null,"Name Invalid");
    }
}

class NullFieldException extends Exception{
    NullFieldException()
    {
        JOptionPane.showMessageDialog(null,"All fields Required !");
    }
}


public class Register extends JFrame implements ActionListener
{
    JPanel panel;
    JLabel nameTxt, emailidTxt ,usernameTxt, passwordTxt, cnf_pwdTxt, successTxt, title;
    JTextField name, emailid,username;
    JPasswordField password, cnf_pwd;
    JButton submit,back_to_login;
    Font font;

    Register()
    {
        // panel
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
        submit.setBackground(new Color(154, 225, 39));
        submit.addActionListener(this);
        add(submit);

        // success
        successTxt = new JLabel("");
        successTxt.setBounds(505,510,200,30);
        successTxt.setBackground(new Color(154, 225, 39));
        add(successTxt);

        //Back to Login
        back_to_login = new JButton("Back");
        back_to_login.setFont(new Font("Arial", Font.BOLD,15));
        back_to_login.setBounds(530,520,100,30);
        back_to_login.setBackground(new Color(154, 225, 39));
        back_to_login.addActionListener(this);
        add(back_to_login);

        // frame
        setSize(800,800);
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        setResizable(true);
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

                // VALIDATE FIELDS
                if(isValidField(name1,uname,passwd,cnf_passwd,email)==false)
                {
                    throw new NullFieldException();
                }

                if(isValidName(name1)==false)
                {
                    throw new NameInvalidException();
                }

                if(isValidEmail(email)==false)
                {
                    throw new EmailInvalidException();
                }






                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/healthtracker","root","root");
                if(passwd.equals(cnf_passwd))
                {
                    PreparedStatement pst = conn.prepareStatement("insert into login(username,password,name,email) values(?,?,?,?)");
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
            catch(SQLIntegrityConstraintViolationException se)
            {
                JOptionPane.showMessageDialog(null,"Username already exists!");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==back_to_login)
        {
            new Login();
            dispose();
        }

    }

    public boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public boolean isValidName(String name)
    {
        String nameRegex = "[a-zA-Z][a-zA-Z]*";
        Pattern pat = Pattern.compile(nameRegex);
        if(name == null)
            return false;
        return pat.matcher(name).matches();
    }

    public boolean isValidField(String name, String uname, String pass, String conf_pass, String email)
    {
        if(name.equals("") == true || uname.equals("") == true || pass.equals("") == true || conf_pass.equals("") == true || email.equals("") == true)
        {
            return false;
        }
        else{
            return true;
        }
    }





    public static void main(String args[])
    {
        new Register();
    }

}
