package com.project.controller;


import com.project.model.Model;
import com.project.view.DashboardView;
import com.project.view.AddItemView;
import com.project.view.LoginView;

import java.awt.event.*;
import javax.swing.*;

public class AddItemController implements ActionListener {
    public Model model;
    private AddItemView addItemView;
    private DashboardView dashboard;
    private LoginView loginView;
  
    
    public AddItemController(Model model, AddItemView addItemView, DashboardView dashboard) {
        this.model = model;
        this.addItemView = addItemView;
        this.dashboard = dashboard;

        addItemView.initializeView(this); // initialize the elements of the page
        addItemView.itemSelector.addActionListener(e -> displayWindow()); // add action listener to itemSelector which executes display window method

    }
    
    // checks for empty inputs. Every text field must have an input
    public boolean emptyInputValidation() {
        JTextField partsFields[] = {addItemView.txtPartID, addItemView.txtPartName, addItemView.txtSerialNum, addItemView.txtDimensions, addItemView.txtRackNum, addItemView.txtPrice,
                                    addItemView.txtQty, addItemView.txtMatID};
        JTextField materialsFields[] = {addItemView.txtMatID, addItemView.txtMatName,addItemView.txtSubType,addItemView.txtDiameter,addItemView.txtRackNum,addItemView.txtLength};

        // for parts, if any part field is empty, throw an error and return false
        if (addItemView.itemSelector.getSelectedItem() == "Part") {
            for (int i=0; i<partsFields.length; i++) {
                if (partsFields[i].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "All inputs must be filled" );
                    return false;
                }
            }
        }
            
        // for materials, if any material field is empty, throw an error and return false
        if (addItemView.itemSelector.getSelectedItem() == "Material") {
            for (int i=0; i<materialsFields.length; i++) {
                if (materialsFields[i].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "All inputs must be filled" );
                    return false;
                }
            }
        }

        return true;
    }

    // handle button clicks
    public void actionPerformed(ActionEvent e) {
        
        // submit button
        if (e.getSource() == addItemView.btnSubmit) {
           
            // if the combobox selected part, and if input validation passes, execute adding the part
            if(addItemView.itemSelector.getSelectedItem().equals("Part")) {
                if (emptyInputValidation()) {

                    // put each table column into variables
                    Integer partID = Integer.parseInt(addItemView.txtPartID.getText());
                    String partName = addItemView.txtPartName.getText();
                    int serialNum = Integer.parseInt(addItemView.txtSerialNum.getText());
                    String dimensions = addItemView.txtDimensions.getText();
                    int rackNum = Integer.parseInt(addItemView.txtRackNum.getText());
                    Double price = Double.parseDouble(addItemView.txtPrice.getText());
                    int qty = Integer.parseInt(addItemView.txtQty.getText());
                    int matID = Integer.parseInt(addItemView.txtMatID.getText());

                    boolean success = model.addNewPart(partID,partName,serialNum,dimensions,rackNum,price,qty,matID); // adds new part, returns true if successful

                    // if success is true, print success message, otherwise print eerror message
                    if(success) {
                        JOptionPane.showMessageDialog(null,"Part added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null,"Part add failed.");
                    }
                }  
            }

            // same logic as above but for when the combobox has material selected
            if (addItemView.itemSelector.getSelectedItem().equals("Material")) {
                if (emptyInputValidation()) {
                    Integer matID = Integer.parseInt(addItemView.txtMatID.getText());
                    String matName = addItemView.txtMatName.getText();
                    String subType = addItemView.txtSubType.getText();
                    Double diameter = Double.parseDouble(addItemView.txtDiameter.getText());
                    int rackNum = Integer.parseInt(addItemView.txtRackNum.getText());
                    Double length = Double.parseDouble(addItemView.txtLength.getText());
                    

                    boolean success = model.addNewMaterial(matID,matName,subType,diameter,rackNum,length);
                    if(success) {
                        JOptionPane.showMessageDialog(null,"Material added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null,"Material add failed.");
                    }
                }
                
            }
     
            // call dashboard view and controller to return to functional dashboard when the insertion is complete
            DashboardController dashboardController = new DashboardController(model,dashboard,loginView);
            dashboard.initializeDashboard(dashboardController); // make sure the dashboard is functional again after navigating back
        }

        // handles back button functionality
        if (e.getSource() == addItemView.btnBack) {
            DashboardController dashboardController = new DashboardController(model,dashboard,loginView);
            dashboard.initializeDashboard(dashboardController);
        }
    }

    // chooses which window to display depending on the combobox choice specified.
    public void displayWindow() {
        if (addItemView.itemSelector.getSelectedItem() == "Part") {
            
            addItemView.initializeItemView(this); // intialize part window
        }
        if (addItemView.itemSelector.getSelectedItem() == "Material") {
            addItemView.initializeMaterialView(this); //initalize material window
        }
    }
}
