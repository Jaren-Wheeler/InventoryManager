package com.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardView {
    public JFrame window;
    public JPanel dashboard;
    public JTextField searchBar;
    public JButton btnSearch, btnInventoryList, btnWorkOrders, btnInvoices, btnAddItem, btnRemoveItem;
    public JComboBox<String> searchSelector;

    public DashboardView(JFrame window) {
        this.window = window;
    }

    public void initializeDashboard(ActionListener l) {
        // Set up window properties
        window.setTitle("Dashboard");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        dashboard = new JPanel();
        dashboard.setLayout(new GridBagLayout());
        dashboard.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel db_title = new JLabel("Welcome");
        db_title.setFont(new Font("Arial", Font.BOLD, 24));

        searchBar = new JTextField("Search", 15);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 14));

        searchSelector = new JComboBox<>(new String[] { "Parts", "Materials" });
        searchSelector.setFont(new Font("Arial", Font.PLAIN, 14));

        btnSearch = new JButton("Search");
        btnInventoryList = new JButton("Inventory List");
        btnWorkOrders = new JButton("Work Order List");
        btnInvoices = new JButton("Sales List");
        btnAddItem = new JButton("Add Item");
        btnRemoveItem = new JButton("Remove Item");

        JButton[] buttons = { btnSearch, btnInventoryList, btnWorkOrders, btnInvoices, btnAddItem, btnRemoveItem};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Arial", Font.PLAIN, 14));
            btn.setFocusPainted(false);
            btn.setBackground(new Color(100, 149, 237));
            btn.setForeground(Color.WHITE);
            btn.setPreferredSize(new Dimension(150, 30));
            btn.addActionListener(l);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        dashboard.add(db_title, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        dashboard.add(searchBar, gbc);

        gbc.gridx = 1;
        dashboard.add(searchSelector, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        dashboard.add(btnSearch, gbc);

        gbc.gridy = 3;
        dashboard.add(btnInventoryList, gbc);

        gbc.gridy = 4;
        dashboard.add(btnWorkOrders, gbc);

        gbc.gridy = 5;
        dashboard.add(btnInvoices, gbc);

        gbc.gridy = 6;
        dashboard.add(btnAddItem, gbc);

        gbc.gridy = 7;
        dashboard.add(btnRemoveItem, gbc);

        window.setContentPane(dashboard);
        window.setVisible(true);
    }
}
