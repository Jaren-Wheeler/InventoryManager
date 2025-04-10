package com.project;

import com.project.controller.LoginController;
import com.project.model.Model;
import com.project.view.DashboardView;
import com.project.view.ItemView;
import com.project.view.LoginView;


public class App 
{
    public static void main(String[] args )
    {
        Model model = new Model(); // create the model
        LoginView loginWindow = new LoginView(); //create the login window view, initially passing null to the controller
        DashboardView dashboard = new DashboardView(loginWindow.window); // initialize the dashboard
        LoginController loginController = new LoginController(model,loginWindow,dashboard); // create the controller object for login page which connects both model and view
        ItemView itemView = new ItemView(loginWindow.window);
    
        loginWindow.initializeWindow(loginController); // open the login window
       
    }
}
