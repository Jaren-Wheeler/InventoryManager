package com.project.controller;


import com.project.model.Model;
import com.project.view.DashboardView;
import com.project.view.AddItemView;
import com.project.view.LoginView;

import java.awt.event.*;

public class AddItemController implements ActionListener {
    public Model model;
    private AddItemView addItemView;
    private DashboardView dashboard;
    
    
    public AddItemController(Model model, AddItemView addItemView, DashboardView dashboard) {
        this.model = model;
        this.addItemView = addItemView;
        this.dashboard = dashboard;

        addItemView.initializeView(this);
        addItemView.itemSelector.addActionListener(e -> displayWindow());

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addItemView.btnSubmit) {
            System.out.println("Did something");
        }

        if (e.getSource() == addItemView.btnBack) {
            System.out.println("Return to dashboard");
            dashboard.initializeDashboard(this);
        }
    }

    public void displayWindow() {
        if (addItemView.itemSelector.getSelectedItem() == "Part") {
            
            addItemView.initializeItemView(this);
        }
        if (addItemView.itemSelector.getSelectedItem() == "Material") {
            addItemView.initializeMaterialView(this);
        }
    }
}
