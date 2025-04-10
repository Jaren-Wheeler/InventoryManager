package com.project.controller;

import javax.swing.JOptionPane;

import com.project.model.Model;
import com.project.view.DashboardView;
import com.project.view.LoginView;

import java.awt.event.*;

public class LoginController implements ActionListener {
    private Model model; 
    private LoginView loginWindow; 
    private DashboardView dashboard;

    public LoginController(Model model, LoginView loginWindow, DashboardView dashboard) {
        this.model = model; // the model
        this.loginWindow = loginWindow; // the login window
        this.dashboard = dashboard; // the dashboard window

        loginWindow.initializeWindow(this);
        loginWindow.enterBtn.addActionListener(this);
        loginWindow.createAccBtn.addActionListener(this);
    }

    // handles button clicks
    public void actionPerformed(ActionEvent e) {

        // opens the dashboard if the entered information is accurate
        if (e.getSource() == loginWindow.enterBtn) {
            boolean entered = Model.enterAccount(loginWindow.usernameField.getText(),loginWindow.passwordField.getText()); // search for matching account

            // if entered is true (account exists), execute openign of dashboard
            if (entered) {
                loginWindow.window.getContentPane().removeAll(); // remove all content from the login window
             
                DashboardController dashboardController = new DashboardController(model,dashboard,loginWindow); // call the dashboard controller
                dashboard.initializeDashboard(dashboardController); // create the dashboard elements

                loginWindow.window.revalidate();
                loginWindow.window.repaint();    
            } else {
                JOptionPane.showMessageDialog(null,"The account information does not exist.");
            }
        }

        // opens create account window if the create new account button is clicked
        if (e.getSource() == loginWindow.createAccBtn) {
            loginWindow.createAccWindow(this);
        }

        // writes the inputted account information into the database
        if (e.getSource() == loginWindow.submitBtn) {
            Model.createAccount(loginWindow.usernameCreateField.getText(),loginWindow.passwordCreateField.getText()); 
        }
        // Back button to login screen
        if (e.getSource() == loginWindow.backBtn) {
            loginWindow.window.getContentPane().removeAll(); //reset login window
            loginWindow.initializeWindow(this); //re-intialize the login elements
           
            loginWindow.window.revalidate();
            loginWindow.window.repaint();
        }
      
    }
}
