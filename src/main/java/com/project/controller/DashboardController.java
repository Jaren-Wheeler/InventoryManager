package com.project.controller;

import java.awt.event.*;
import javax.swing.JOptionPane;

import com.project.model.Model;
import com.project.view.DashboardView;
import com.project.view.ItemView;
import com.project.view.LoginView;

public class DashboardController implements ActionListener {
    private Model model;
    private LoginView loginView;
    private DashboardView dashboard;
    private ItemView itemView;
    private ItemViewController itemViewController;

    public DashboardController(Model model, DashboardView dashboard, LoginView loginView) {
        this.model = model;
        this.dashboard = dashboard;
        this.loginView = loginView;

        this.dashboard.initializeDashboard(this);
    }

   
    // gets user input and calls the model to see if it exists in the database, returning true if it does
    public boolean searchBar() {

        String itemSelection = dashboard.searchBar.getText(); // get the user selection from the search bar.
        int idSelection = Integer.parseInt(dashboard.searchBar.getText());
        String selectedItemType = (String)dashboard.searchSelector.getSelectedItem(); // user selection on combobox (part or material)

        // if the search returns true from the model for either name or id, then return true here, otherwise the item doesn't exist so return false
        if (itemSelection != null) {
            model.searchBar(itemSelection, selectedItemType);
            return true;
        } else {
            JOptionPane.showMessageDialog(null,"The search item doesn't exist");
            return false;
        }
       
    }

    // implements button clicks for dashboard items
    public void actionPerformed(ActionEvent e) {

        // click event for search button
        if (e.getSource() == dashboard.btnSearch) {

            // If the searched item exists in the database, open the window displaying the information about it
            if (searchBar()) {
                itemView = new ItemView(dashboard.window);
                itemViewController = new ItemViewController(model,loginView,itemView,dashboard);
                itemView.initializeComponents(itemViewController);
                dashboard.window.setContentPane(itemView.itemViewPanel);
                dashboard.window.revalidate();
                dashboard.window.repaint();
            }
        }

        if (e.getSource() == dashboard.btnInventoryList) {
            // generate a list of all inventory of parts and materials in a table
            JOptionPane.showMessageDialog(null,"Inventory List");
        }
        if (e.getSource() == dashboard.btnWorkOrders) {
            // generate a list of all work orders in a table
            JOptionPane.showMessageDialog(null,"Work Orders");
        }
        if (e.getSource() == dashboard.btnInvoices) {
            // generate a list of all invoices in a table
            JOptionPane.showMessageDialog(null,"Invoices");
        }
    }
}
