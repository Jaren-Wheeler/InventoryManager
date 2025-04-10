package com.project.model;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class InventoryList extends JFrame {

    public InventoryList() {
        setTitle("Inventory List");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table headers
        String[] columns = { "Item ID", "Item Name", "Item Type", "Quantity" };
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0); // create table model to hold the data

        try (Connection conn = Model.databaseConnection();
                Statement stmt = conn.createStatement()) {

            // Load from "part" table
            ResultSet partRS = stmt.executeQuery("SELECT * FROM Parts ORDER BY part_id ASC");
            while (partRS.next()) {
                Vector<Object> row = new Vector<>(); // create a vector object to hold row items

                // add column value to row
                row.add(partRS.getInt("part_id"));
                row.add(partRS.getString("part_name"));
                row.add("Parts");
                row.add(partRS.getInt("quantity"));
                tableModel.addRow(row); //add row to the table
            }

            // Load from material table
            ResultSet materialRS = stmt.executeQuery("SELECT * FROM Materials ORDER BY mat_id ASC");
            while (materialRS.next()) {
                Vector<Object> row = new Vector<>();
                row.add(materialRS.getInt("mat_id"));
                row.add(materialRS.getString("mat_name"));
                row.add("Materials");
                row.add(materialRS.getInt("length_inches"));
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error loading inventory: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        JTable table = new JTable(tableModel); // create the physical table with the data from the table model
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel); // allows for clickable headers to sort data
        table.setRowSorter(sorter); // add tot able

        JScrollPane scrollPane = new JScrollPane(table); // allows scrolling on the table

        // Search bar for table
        JTextField searchField = new JTextField(30);

        // functionality to filter table as users are typing in the search bar. 
        // Use key listener event with a keyReleased method
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText().trim();
                // if there is nothing in the search bar, the filter is zero, showing the entire table. Othersie apply regexFilter
                if (searchText.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }
            }
        });

        // create panel for the serarch bar. Add a label and searchField to it
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);


        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener((ActionEvent e) -> dispose()); // implements dispose() method to close the window

        // new panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        // Add panels to layout
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }


    public static void main(String[] args) {
        
        InventoryList frame = new InventoryList(); // open the inventory list
        frame.setVisible(true);
        
    }
}