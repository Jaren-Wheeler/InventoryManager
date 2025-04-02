package com.project.controller;

import java.awt.event.*;
import javax.swing.JOptionPane;

<<<<<<< HEAD
import com.project.model.InventoryList;
import com.project.model.Model;
import com.project.model.Sales;
import com.project.model.WorkOrder; // ✅ Import the table popup window class
=======
import com.project.model.Model;
>>>>>>> 0d49c33d51bae4d11757a834c9c382709052292d
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

<<<<<<< HEAD
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

=======
   
    // gets user input and calls the model to see if it exists in the database, returning true if it does
    public boolean searchBar() {

        String itemSelection = dashboard.searchBar.getText(); // get the user selection from the search bar.
        String selectedItemType = (String)dashboard.searchSelector.getSelectedItem(); // user selection on combobox (part or material)

        // if the search returns true from the model for either name or id, then return true here, otherwise the item doesn't exist so return false
        try {
            int itemID = Integer.parseInt(itemSelection);
            if (model.searchByID(itemID, selectedItemType) == null) {
                JOptionPane.showMessageDialog(null,"The inputed item ID doesn't exist.");
                return false;
            };
            return true;
        } catch (NumberFormatException e) {
            if(model.searchByName(itemSelection, selectedItemType) == null) {
                JOptionPane.showMessageDialog(null,"The inputed item name doesn't exist.");
                return false;
            };
            return true;
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

                // display the item info depending on whether part or material is specified and if an id or name is given
>>>>>>> 0d49c33d51bae4d11757a834c9c382709052292d
                try {
                    int id = Integer.parseInt(dashboard.searchBar.getText());
                    if (dashboard.searchSelector.getSelectedItem() == "Parts") {
                        itemViewController.displayItem(id, "Parts");
                    } else if (dashboard.searchSelector.getSelectedItem() == "Materials") {
<<<<<<< HEAD
                        itemViewController.displayItem(id, "Materials");
=======
                        itemViewController.displayItem(id,"Materials");
>>>>>>> 0d49c33d51bae4d11757a834c9c382709052292d
                    }
                } catch (NumberFormatException ex) {
                    if (dashboard.searchSelector.getSelectedItem() == "Parts") {
                        itemViewController.displayItemByName(dashboard.searchBar.getText(), "Parts");
                    } else if (dashboard.searchSelector.getSelectedItem() == "Materials") {
<<<<<<< HEAD
                        itemViewController.displayItemByName(dashboard.searchBar.getText(), "Materials");
                    }
                }

=======
                        itemViewController.displayItemByName(dashboard.searchBar.getText(),"Materials");
                    }
                }
                
>>>>>>> 0d49c33d51bae4d11757a834c9c382709052292d
                dashboard.window.revalidate();
                dashboard.window.repaint();
            }
        }

        if (e.getSource() == dashboard.btnInventoryList) {
<<<<<<< HEAD
            new InventoryList().setVisible(true);
        }

        if (e.getSource() == dashboard.btnWorkOrders) {
            new WorkOrder().setVisible(true);
        }

        if (e.getSource() == dashboard.btnInvoices) {
            new Sales().setVisible(true);
=======
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
>>>>>>> 0d49c33d51bae4d11757a834c9c382709052292d
        }
    }
}
