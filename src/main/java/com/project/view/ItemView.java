package com.project.view;

import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;

public class ItemView {
    private JFrame window;
    public JPanel itemViewPanel;
    public JLabel lblItemNum, lblItemName, lblItemDimension, lblRackNum, lblQty;
    public JButton btnReturn;

    public ItemView(JFrame window) {
        this.window = window; // this is the same window as the login page
        
    }

    // initialize everything in the item view window
    public void initializeComponents(ActionListener l) {
        itemViewPanel = new JPanel();
        itemViewPanel.setLayout(new FlowLayout());
        lblItemNum = new JLabel("Item Number: ");
        lblItemName = new JLabel("Item Name: ");
        lblItemDimension = new JLabel("Dimensions: ");
        lblRackNum = new JLabel("Rack Number: ");
        lblQty = new JLabel("Quantity:");

        btnReturn = new JButton("Back");

        itemViewPanel.add(lblItemNum);
        itemViewPanel.add(lblItemName);
        itemViewPanel.add(lblItemDimension);
        itemViewPanel.add(lblRackNum);
        itemViewPanel.add(lblQty);
        itemViewPanel.add(btnReturn);

        btnReturn.addActionListener(l);
        window.setContentPane(itemViewPanel); // set the content of the window to dashboard panel rather than login panel
      
        window.setVisible(true);

        window.revalidate();
        window.repaint();  
    }
}
