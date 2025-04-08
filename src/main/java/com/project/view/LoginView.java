package com.project.view;

import com.project.controller.*;

import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class LoginView {
    public JFrame window;
    public JPanel loginPanel;
    public JLabel usernameCreateLbl,passwordCreateLbl, confPasswordCreateLbl;
    public JTextField usernameField,passwordField,usernameCreateField, passwordCreateField, confPasswordCreateField;
    public JButton enterBtn, createAccBtn, submitBtn, backBtn;

    private Scanner scan;
    private BufferedImage topImage;

    private LoginController controller;
    
    
    public LoginView() {
        
        scan = new Scanner(System.in);

        try {
            topImage = ImageIO.read(new File("src/main/java/com/project/images/ATI.Logo.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        window = new JFrame("Inventory Manager");
        window.setSize(500,400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        
        window.setVisible(true);
        initializeWindow(controller);
    }

    class TopImagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            if (topImage != null) {
                int x = (getWidth() - topImage.getWidth()) / 2;
                int y = 10;
                g.drawImage(topImage, x, y, this);
            } 
        }
    }
    
    //method for the window
    public void initializeWindow(ActionListener l) {

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.WHITE);

        TopImagePanel topPanel = new TopImagePanel();
        topPanel.setPreferredSize(new Dimension(500, topImage != null ? topImage.getHeight() + 20 : 100));

        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);

        // Moved everything up by ~50px
        JLabel usernameLbl = new JLabel("Username:");
        usernameLbl.setBounds(100, 50, 80, 25);
        formPanel.add(usernameLbl);

        usernameField = new JTextField(20);
        usernameField.setBounds(180, 50, 200, 25);
        formPanel.add(usernameField);

        JLabel passwordLbl = new JLabel("Password:");
        passwordLbl.setBounds(100, 90, 80, 25);
        formPanel.add(passwordLbl);

        passwordField = new JTextField(20);
        passwordField.setBounds(180, 90, 200, 25);
        formPanel.add(passwordField);

        enterBtn = new JButton("Enter");
        enterBtn.setBounds(180, 130, 90, 25);
        formPanel.add(enterBtn);

        createAccBtn = new JButton("Create New Account");
        createAccBtn.setBounds(280, 130, 160, 25);
        formPanel.add(createAccBtn);

        content.add(topPanel, BorderLayout.NORTH);
        content.add(formPanel, BorderLayout.CENTER);

        enterBtn.addActionListener(l);
        createAccBtn.addActionListener(l);

        window.setContentPane(content);
        window.revalidate();
        window.repaint();

    }
    
    // create the pop up window when creating a new account
    public void createAccWindow(ActionListener l) {
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.WHITE);

        TopImagePanel topPanel = new TopImagePanel();
        topPanel.setPreferredSize(new Dimension(500, topImage != null ? topImage.getHeight() + 20 : 100));

        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);

        // 🠕 Moved everything up by ~50px
        usernameCreateLbl = new JLabel("Create Username:");
        usernameCreateLbl.setBounds(100, 0, 150, 25);
        formPanel.add(usernameCreateLbl);

        usernameCreateField = new JTextField(20);
        usernameCreateField.setBounds(250, 0, 150, 25);
        formPanel.add(usernameCreateField);

        passwordCreateLbl = new JLabel("Create Password:");
        passwordCreateLbl.setBounds(100, 40, 150, 25);
        formPanel.add(passwordCreateLbl);

        passwordCreateField = new JTextField(20);
        passwordCreateField.setBounds(250, 40, 150, 25);
        formPanel.add(passwordCreateField);

        confPasswordCreateLbl = new JLabel("Confirm Password:");
        confPasswordCreateLbl.setBounds(100, 80, 150, 25);
        formPanel.add(confPasswordCreateLbl);

        confPasswordCreateField = new JTextField(20);
        confPasswordCreateField.setBounds(250, 80, 150, 25);
        formPanel.add(confPasswordCreateField);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(190, 130, 90, 25);
        submitBtn.addActionListener(controller);
        formPanel.add(submitBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(290, 130, 90, 25);
        backBtn.addActionListener(controller);
        formPanel.add(backBtn);

        content.add(topPanel, BorderLayout.NORTH);
        content.add(formPanel, BorderLayout.CENTER);

        submitBtn.addActionListener(l);
        backBtn.addActionListener(l);
        window.setContentPane(content);
        window.revalidate();
        window.repaint();
    }
}
