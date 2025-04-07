package com.project.view;

import com.project.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddItemView {
    public JFrame window;
    public JPanel addItemPanel, formPanel;
    public JLabel lblSelection, lblPartID, lblPartName, lblSerialNum,lblDimensions,lblRackNum, lblPrice, lblQty, lblMatID;
    public JLabel lblMatName, lblSubType, lblDiameter, lblLength;
    public JTextField txtPartID, txtPartName, txtSerialNum,txtDimensions,txtRackNum,txtPrice,txtQty,txtMatID;
    public JTextField txtMatName, txtSubType, txtDiameter, txtLength;
    public JButton btnSubmit, btnBack;
    public JComboBox<String> itemSelector;

    public AddItemView(JFrame window) {
        this.window = window;
    }

    public void initializeView(ActionListener l) {
        addItemPanel = new JPanel(new BorderLayout());
        itemSelector = new JComboBox<String>(new String[] {"Select part or material","Part","Material"});
        window.setContentPane(addItemPanel);

        addItemPanel.add(itemSelector, BorderLayout.NORTH);

        formPanel = new JPanel();
        addItemPanel.add(formPanel,BorderLayout.CENTER);

        window.setContentPane(addItemPanel);
        window.setVisible(true);
    }


    public void initializeItemView(ActionListener l) {
        formPanel.removeAll();
        formPanel.setLayout(new GridLayout(9, 2, 10, 10));

        lblPartID = new JLabel("Part ID: ");
        txtPartID = new JTextField();
        lblPartName = new JLabel("Part Name: ");
        txtPartName = new JTextField();
        lblSerialNum = new JLabel("Serial Number: ");
        txtSerialNum = new JTextField();
        lblDimensions = new JLabel("Dimensions: ");
        txtDimensions = new JTextField();
        lblRackNum = new JLabel("Rack Number: ");
        txtRackNum = new JTextField();
        lblPrice = new JLabel("Price: ");
        txtPrice = new JTextField();
        lblQty = new JLabel("Quantity: ");
        txtQty = new JTextField();
        lblMatID = new JLabel("Material ID: ");
        txtMatID = new JTextField();

        btnSubmit = new JButton("Submit");
        btnBack = new JButton("Back");

        formPanel.add(lblPartID);
        formPanel.add(txtPartID);
        formPanel.add(lblPartName);
        formPanel.add(txtPartName);
        formPanel.add(lblSerialNum);
        formPanel.add(txtSerialNum);
        formPanel.add(lblDimensions);
        formPanel.add(txtDimensions);
        formPanel.add(lblRackNum);
        formPanel.add(txtRackNum);
        formPanel.add(lblPrice);
        formPanel.add(txtPrice);
        formPanel.add(lblQty);
        formPanel.add(txtQty);
        formPanel.add(lblMatID);
        formPanel.add(txtMatID);
        
        formPanel.add(btnSubmit);
        formPanel.add(btnBack);

        btnSubmit.addActionListener(l);
        btnBack.addActionListener(l);
        formPanel.revalidate();
        formPanel.repaint();
        
    }

    public void initializeMaterialView(ActionListener l) {
        formPanel.removeAll();
        formPanel.setLayout(new GridLayout(9, 2, 10, 10));

        lblMatID = new JLabel("Material ID: ");
        txtMatID = new JTextField();
        lblMatName = new JLabel("Material Name: ");
        txtMatName = new JTextField();
        lblSubType = new JLabel("Sub Type: ");
        txtSubType = new JTextField();
        lblDiameter = new JLabel("Diameter: ");
        txtDiameter = new JTextField();
        lblRackNum = new JLabel("Rack Number: ");
        txtRackNum = new JTextField();
        lblLength = new JLabel("Length: ");
        txtLength = new JTextField();

        btnSubmit = new JButton("Submit");
        btnBack = new JButton("Back");
        
        formPanel.add(lblMatID);
        formPanel.add(txtMatID);
        formPanel.add(lblMatName);
        formPanel.add(txtMatName);
        formPanel.add(lblSubType);
        formPanel.add(txtSubType);
        formPanel.add(lblDiameter);
        formPanel.add(txtDiameter);
        formPanel.add(lblRackNum);
        formPanel.add(txtRackNum);
        formPanel.add(lblLength);
        formPanel.add(txtLength);

        formPanel.add(btnSubmit);
        formPanel.add(btnBack);

        btnSubmit.addActionListener(l);
        btnBack.addActionListener(l);

        formPanel.revalidate();
        formPanel.repaint();
    }
}
