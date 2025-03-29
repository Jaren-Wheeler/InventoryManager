package com.project.view;

import com.project.controller.*;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView {
    public JFrame window;
    public JPanel loginPanel;
    public JLabel usernameCreateLbl,passwordCreateLbl, confPasswordCreateLbl;
    public JTextField usernameField,passwordField,usernameCreateField, passwordCreateField, confPasswordCreateField;
    public JButton enterBtn, createAccBtn, submitBtn;

    private LoginController controller;
    
    public LoginView() {
        initializeWindow(controller);
    }

    //method for the window
    public void initializeWindow(ActionListener l) {


        // basic window information
        window = new JFrame("Inventory Manager");
        window.setSize(1280,720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());


        // create panel object to store login window elements.
        loginPanel = new JPanel(new GridLayout(3,2,10,100));
    
        //loginPanel.setBounds(500,300,300,300); // position on window
        loginPanel.setPreferredSize(new Dimension(300,700));

        // username label and text fiel d
        JLabel usernameLbl = new JLabel("Username:");
        usernameField = new JTextField();


        // password label and text field
        JLabel passwordLbl = new JLabel("Password:");
        passwordField = new JTextField();


        // enter button and create account button
        enterBtn = new JButton("Enter");
        createAccBtn = new JButton("Create Account");

        // action listeners for the button which get executed in Controller.java
        enterBtn.addActionListener(l);
        createAccBtn.addActionListener(l);

        // add all elements to the panel
        loginPanel.add(usernameLbl);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLbl);
        loginPanel.add(passwordField);
        loginPanel.add(enterBtn);
        loginPanel.add(createAccBtn);


        window.add(loginPanel); // add panel to window
        window.setVisible(true);

    }
    
    // create the pop up window when creating a new account
    public void createAccWindow(ActionListener l) {
        JPanel createAccPanel = new JPanel();
        createAccPanel.setLayout(new GridLayout(4,2,10,20)); // grid layout design
       
        
        // label and text field for username
        usernameCreateLbl = new JLabel("Enter a username");
        usernameCreateField = new JTextField();

        // label and text field for password
        passwordCreateLbl = new JLabel("Enter a passowrd (must be more than 8 characters)");
        passwordCreateField = new JTextField();

        // label and text field for confirming password
        confPasswordCreateLbl = new JLabel("Confirm your password");
        confPasswordCreateField = new JTextField();

        // button for submitting info
        submitBtn = new JButton("Submit");
      
        // action listener for submit btn which gets executed in Controller.java
        submitBtn.addActionListener(l);

        // add everything to the panel
        createAccPanel.add(usernameCreateLbl);
        createAccPanel.add(usernameCreateField);
        createAccPanel.add(passwordCreateLbl);
        createAccPanel.add(passwordCreateField);
        createAccPanel.add(confPasswordCreateLbl);
        createAccPanel.add(confPasswordCreateField);
        createAccPanel.add(submitBtn);
     
        
        // remove login panel and add create account panel to the window
        window.remove(loginPanel);
        window.add(createAccPanel);
       
        // update the UI of the window
        window.revalidate();
        window.repaint();    
    }
}
