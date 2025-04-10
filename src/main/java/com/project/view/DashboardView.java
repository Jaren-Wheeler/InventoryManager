package com.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardView {
    public JFrame window;
    public JPanel dashboard, mainPanel;
    public JTextField searchBar;
    public JButton btnSearch, btnInventoryList, btnWorkOrders, btnInvoices, btnAddItem, btnRemoveItem;
    public JComboBox<String> searchSelector;

    private JPopupMenu profileMenu;
    private JMenuItem changeUserItem, settingsItem, logoutItem;

    public DashboardView(JFrame window) {
        this.window = window;
    }

    // create the dashboard elements
    public void initializeDashboard(ActionListener l) {
        window.setTitle("Dashboard");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main container layout
        mainPanel = new JPanel(new BorderLayout());
       
        // Panel for top right profile bar
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        // profile button
        JButton profileButton = new JButton("☰");
        profileButton.setFocusPainted(false);
        profileButton.setMargin(new Insets(2, 8, 2, 8));

        // add to the top bar panel
        topBar.add(profileButton);

        // create a popup menu for the profile button, including the items
        profileMenu = new JPopupMenu();
        changeUserItem = new JMenuItem("Change User");
        settingsItem = new JMenuItem("Settings");
        logoutItem = new JMenuItem("Log Out");

        // add the items to the profile menu
        profileMenu.add(changeUserItem);
        profileMenu.add(settingsItem);
        profileMenu.add(logoutItem);

        // add action listeners to the profile button and its items
        profileButton.addActionListener(e -> profileMenu.show(profileButton, 0, profileButton.getHeight()));
        changeUserItem.addActionListener(l);
        settingsItem.addActionListener(l);
        logoutItem.addActionListener(l);

        // add the top bar, containing the profile button, to the top of the main panel
        mainPanel.add(topBar, BorderLayout.NORTH);

        // Dashboard content with GridBagLayout
        dashboard = new JPanel();
        dashboard.setLayout(new GridBagLayout());
       
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel db_title = new JLabel("Welcome");
        db_title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        dashboard.add(db_title, gbc);

        // Search Selector (dropdown)
        searchSelector = new JComboBox<>(new String[] { "Parts", "Materials"});
        gbc.gridx = 0;
        gbc.gridy = 1;
        dashboard.add(searchSelector, gbc);

        // Search Bar
        searchBar = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        dashboard.add(searchBar, gbc);

        btnSearch = new JButton("Search");
        gbc.gridx = 2;
        gbc.gridy = 1;
        dashboard.add(btnSearch, gbc);

        // Inventory list button
        btnInventoryList = new JButton("Inventory List");
        gbc.gridx = 1;
        gbc.gridy = 3;
        dashboard.add(btnInventoryList, gbc);

        // work order list button
        btnWorkOrders = new JButton("Work Orders");
        gbc.gridx = 1;
        gbc.gridy = 4;
        dashboard.add(btnWorkOrders, gbc);

        // sales list button
        btnInvoices = new JButton("Invoices");
        gbc.gridx = 1;
        gbc.gridy = 5;
        dashboard.add(btnInvoices, gbc);

        // add item button
        btnAddItem = new JButton("Add Items");
        gbc.gridx = 1;
        gbc.gridy = 6;
        dashboard.add(btnAddItem,gbc);

        // remove item button
        btnRemoveItem = new JButton("Remove Items");
        gbc.gridx = 1;
        gbc.gridy = 7;
        dashboard.add(btnRemoveItem,gbc);
        mainPanel.add(dashboard, BorderLayout.CENTER);

        

        // add the same styling to all of the button and add action listeners
        JButton[] buttons = { btnSearch, btnInventoryList, btnWorkOrders, btnInvoices, btnAddItem, btnRemoveItem};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Arial", Font.PLAIN, 14));
            btn.setFocusPainted(false);
            btn.setBackground(new Color(100, 149, 237));
            btn.setForeground(Color.WHITE);
            btn.setPreferredSize(new Dimension(150, 30));
            btn.addActionListener(l); // action listeners
        }

        window.getContentPane().removeAll();
        window.add(mainPanel);
        window.revalidate();
        window.repaint();
    }

    // the menu items for the profile button. Allows us to call them and access in other classes

    public JMenuItem getChangeUserItem() {
        return changeUserItem;
    }

    public JMenuItem getSettingsItem() {
        return settingsItem;
    }

    public JMenuItem getLogoutItem() {
        return logoutItem;
    }
}
