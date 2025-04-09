package com.project.controller;

import java.awt.event.*;
import javax.swing.JOptionPane;

import com.project.model.InventoryList;
import com.project.model.Model;
import com.project.model.Sales;
import com.project.model.WorkOrder;
import com.project.view.AddItemView;
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

    // gets user input and calls the model to see if it exists in the database,
    // returning true if it does
    public boolean searchBar() {
        String itemSelection = dashboard.searchBar.getText();
        String selectedItemType = (String) dashboard.searchSelector.getSelectedItem();

        try {
            int itemID = Integer.parseInt(itemSelection);
            if (model.searchByID(itemID, selectedItemType) == null) {
                JOptionPane.showMessageDialog(null, "The inputed item ID doesn't exist.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            if (model.searchByName(itemSelection, selectedItemType) == null) {
                JOptionPane.showMessageDialog(null, "The inputed item name doesn't exist.");
                return false;
            }
            return true;
        }
    }

    // handles dashboard button clicks
    public void actionPerformed(ActionEvent e) {

        // search button logic
        if (e.getSource() == dashboard.btnSearch) {
            if (searchBar()) {
                itemView = new ItemView(dashboard.window);
                itemViewController = new ItemViewController(model, loginView, itemView, dashboard);
                itemView.initializeComponents(itemViewController);
                dashboard.window.setContentPane(itemView.itemViewPanel);

                try {
                    int id = Integer.parseInt(dashboard.searchBar.getText());
                    if (dashboard.searchSelector.getSelectedItem() == "Parts") {
                        itemViewController.displayItem(id, "Parts");
                    } else if (dashboard.searchSelector.getSelectedItem() == "Materials") {
                        itemViewController.displayItem(id, "Materials");
                    }
                } catch (NumberFormatException ex) {
                    if (dashboard.searchSelector.getSelectedItem() == "Parts") {
                        itemViewController.displayItemByName(dashboard.searchBar.getText(), "Parts");
                    } else if (dashboard.searchSelector.getSelectedItem() == "Materials") {
                        itemViewController.displayItemByName(dashboard.searchBar.getText(), "Materials");
                    }
                }
            }
        }

        if (e.getSource() == dashboard.btnInventoryList) {
            new InventoryList().setVisible(true);
        }

        if (e.getSource() == dashboard.btnWorkOrders) {
            new WorkOrder().setVisible(true);
        }

        if (e.getSource() == dashboard.btnInvoices) {
            new Sales().setVisible(true);
        }

        if (e.getSource() == dashboard.btnAddItem) {
            AddItemView addItemView = new AddItemView(dashboard.window);
            AddItemController addItemController = new AddItemController(model,addItemView,dashboard);
            addItemController.displayWindow();

            dashboard.window.revalidate();
            dashboard.window.repaint();
            //model.addNewPart();
        }
        
        // Handle profile menu options
        if (e.getSource() == dashboard.getChangeUserItem() || e.getSource() == dashboard.getLogoutItem()) {
            loginView.window.getContentPane().removeAll();
           
            LoginController loginController = new LoginController(model,loginView,dashboard);
            loginView.initializeWindow(this);
            loginView.enterBtn.addActionListener(loginController);
            loginView.createAccBtn.addActionListener(loginController);
            loginView.window.revalidate();
            loginView.window.repaint();
        }

        if (e.getSource() == dashboard.getSettingsItem()) {
            JOptionPane.showMessageDialog(loginView.window, "For Future Implementation", "Settings",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == dashboard.btnRemoveItem) {
            String itemName = JOptionPane.showInputDialog(null,"Enter the name of the item or material you want to delete","Delete an item");
            Object part = model.searchByName(itemName,"Parts");
            Object material = model.searchByName(itemName,"Materials");
            if (part != null) {
                
                boolean success = model.removePart(itemName);
                if (success) {
                    JOptionPane.showMessageDialog(null,"Part successfully deleted.");
                    
                } else {
                    JOptionPane.showMessageDialog(null,"Part deletion failed");
                }
                
            }
            if (material != null) {
    
                boolean success = model.removePart(itemName);
                if (success) {
                    JOptionPane.showMessageDialog(null,"Material sucessfully deleted.");
                } else {
                    JOptionPane.showMessageDialog(null,"Material deletion failed.");
                }  
            }
            dashboard.initializeDashboard(this); // make sure the dashboard is functional again after navigating back
        }
    }
}
