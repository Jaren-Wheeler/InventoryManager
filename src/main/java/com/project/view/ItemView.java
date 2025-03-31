package com.project.view;

import com.project.model.*;
import com.project.controller.DashboardController;
import com.project.controller.ItemViewController;

import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;

public class ItemView {
    private JFrame window;
    public JPanel itemViewPanel;
    public JLabel lblItemNum, lblItemName, lblSerialNum, lblItemDimension, lblRackNum, lblQty;
    public JButton btnReturn;
    private Object item; 

    public ItemView(JFrame window) {
        this.window = window; // this is the same window as the login page
    }

   
    // Initialize everything in the item view window
    public void initializeComponents(ActionListener l) {
        itemViewPanel = new JPanel();
        itemViewPanel.setLayout(new FlowLayout());

        // Initialize the labels and ensure they're not null  
        lblItemNum = new JLabel("Item Number: ");
        lblItemName = new JLabel("Item Name: ");
        lblSerialNum = new JLabel("Serial Number: ");
        lblItemDimension = new JLabel("Dimensions: ");
        lblRackNum = new JLabel("Rack Number: ");
        lblQty = new JLabel("Quantity: ");

        // Add all components to the panel
        itemViewPanel.add(lblItemNum);
        itemViewPanel.add(lblItemName);
        itemViewPanel.add(lblSerialNum);
        itemViewPanel.add(lblItemDimension);
        itemViewPanel.add(lblRackNum);
        itemViewPanel.add(lblQty);
        

        // Initialize the return button
        btnReturn = new JButton("Back");
        itemViewPanel.add(btnReturn);
    

        // Add ActionListener to the button
        btnReturn.addActionListener(l);

        // Make sure the panel is properly set as the content pane
        window.setContentPane(itemViewPanel);
        

        // Update the window
        window.setVisible(true);
        window.revalidate();
        window.repaint();  
    }

    public void updatePartDetails(Part part) {
        lblItemNum.setText("Item Number: " + part.getPartNumber());
        lblItemName.setText("Item Name: " + part.getPartName());
        lblSerialNum.setText("Serial Number: " + part.getSerialNum());
        lblItemDimension.setText("Dimensions: " + part.getDimensions());
        lblRackNum.setText("Rack Number: " + part.getRackNums());
        lblQty.setText("Quantity: " + part.getQty());
    }

    public void updateMaterialDetails(Material material) {
        lblItemNum.setText("Item Number: " + material.getMatNumber());
        lblItemName.setText("Item Name: " + material.getMatName());
        lblSerialNum.setText("Sub Type: " + material.getSubType());
        lblItemDimension.setText("Diameter: " + material.getDiameter());
        lblRackNum.setText("Rack Number: " + material.getRackNums());
        lblQty.setText("Length: " + material.getLength());
    }
}
