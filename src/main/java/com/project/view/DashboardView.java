package com.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardView {
    public JFrame window;
    public JPanel dashboard;
    public JTextField searchBar;
    public JButton btnSearch, btnInventoryList, btnWorkOrders, btnInvoices;
    public JComboBox<String> searchSelector;

    public DashboardView(JFrame window) {
        this.window = window; // this is the same window as the login page
    }

    // create the main outline of the dashboard
    public void initializeDashboard(ActionListener l) {
       
        dashboard = new JPanel(); // new panel for dashboard
        dashboard.setLayout(new FlowLayout());
        JLabel db_title = new JLabel("Welcome");
        
        searchBar = new JTextField("Search");
        searchSelector = new JComboBox<>(new String[]{"Parts","Materials"}); // combo box for selecting whether parts or materials should be searched for
        
        btnSearch = new JButton("Search");
        btnInventoryList = new JButton("Inventory List");
        btnWorkOrders = new JButton("Work Orders");
        btnInvoices = new JButton("Sales");

        btnSearch.addActionListener(l);
        btnInventoryList.addActionListener(l);
        btnWorkOrders.addActionListener(l);
        btnInvoices.addActionListener(l);

        dashboard.add(db_title);
        dashboard.add(searchBar);
        dashboard.add(searchSelector);
        dashboard.add(btnSearch);
        dashboard.add(btnInventoryList);
        dashboard.add(btnWorkOrders);
        dashboard.add(btnInvoices);

        window.setContentPane(dashboard); // set the content of the window to dashboard panel rather than login panel
        window.setVisible(true);
    }
}
