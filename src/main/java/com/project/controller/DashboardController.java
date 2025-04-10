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

        this.dashboard.initializeDashboard(this); // create the dashboard elemetns
        
    }

    // handles functionality of the search bar
    public boolean searchBar() {
        String itemSelection = dashboard.searchBar.getText(); // search bar items
        String selectedItemType = (String) dashboard.searchSelector.getSelectedItem(); //gets part or material from selection box

        // if it is an id specified, try block is executed, otherwise if a name is specified, it is caught
        try {
            int itemID = Integer.parseInt(itemSelection);
            // if ID is specified in bar, but doesn't exist in database, return false
            if (model.searchByID(itemID, selectedItemType) == null) {
                JOptionPane.showMessageDialog(null, "The inputed item ID doesn't exist.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            // if the name doesn't exist in the database, return false
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
            // if searchBar method returns true, open up item view window to show the item information
            if (searchBar()) {
                itemView = new ItemView(dashboard.window); //create itemView window
                itemViewController = new ItemViewController(model, loginView, itemView, dashboard); // call the controller
                itemView.initializeComponents(itemViewController); // initialize the components of the window
                dashboard.window.setContentPane(itemView.itemViewPanel); // set the content to the item view panel

                // try is executed if an ID is specified, catch if a name is specified
                try {
                    int id = Integer.parseInt(dashboard.searchBar.getText());

                    // if the combobox is parts, display the part, else display the material
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

        // handles clicking of inventory list button, making InventoryList class visible
        if (e.getSource() == dashboard.btnInventoryList) {
            new InventoryList().setVisible(true);
        }

        // handles clicking of work orders button, making WorkOrders class visible
        if (e.getSource() == dashboard.btnWorkOrders) {
            new WorkOrder().setVisible(true);
        }

        // handles clicking of sales button, making Sales class visible
        if (e.getSource() == dashboard.btnInvoices) {
            new Sales().setVisible(true);
        }

        // handles clicking of add item button, making AddItem view appear
        if (e.getSource() == dashboard.btnAddItem) {
            AddItemView addItemView = new AddItemView(dashboard.window); // create add item view
            AddItemController addItemController = new AddItemController(model,addItemView,dashboard); //call the controller
            addItemController.displayWindow(); // display the window depending on whether it is a part or material being added

            dashboard.window.revalidate();
            dashboard.window.repaint();
        }
        
        // If the logout button or change user button is clicked on profile menu, log out
        if (e.getSource() == dashboard.getChangeUserItem() || e.getSource() == dashboard.getLogoutItem()) {
            loginView.window.getContentPane().removeAll();
           
            LoginController loginController = new LoginController(model,loginView,dashboard);
            loginView.initializeWindow(this);
            loginView.enterBtn.addActionListener(loginController); // re initialize action listeners
            loginView.createAccBtn.addActionListener(loginController);
            loginView.window.revalidate();
            loginView.window.repaint();
        }

        // if the settings item is clicked, throw a message that it is a future implementation
        if (e.getSource() == dashboard.getSettingsItem()) {
            JOptionPane.showMessageDialog(loginView.window, "For Future Implementation", "Settings",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        // handles the clicking of the remove item button
        if (e.getSource() == dashboard.btnRemoveItem) {
            // create an input dialog
            String itemName = JOptionPane.showInputDialog(null,"Enter the name of the item or material you want to delete","Delete an item");
            Object part = model.searchByName(itemName,"Parts"); //part object
            Object material = model.searchByName(itemName,"Materials"); // material object
            // if part object isn't null (searched value is a part), execute removal
            if (part != null) {
                
                boolean success = model.removePart(itemName); // call removePart from the model. Returns true if succesful

                // if returend true, throw a success message, otherwise a failure message
                if (success) {
                    JOptionPane.showMessageDialog(null,"Part successfully deleted.");
                    
                } else {
                    JOptionPane.showMessageDialog(null,"Part deletion failed");
                }
                
            }
            // same as part above
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
