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
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        try (Connection conn = Model.databaseConnection();
                Statement stmt = conn.createStatement()) {

            // Load from "part" table
            ResultSet partRS = stmt.executeQuery("SELECT * FROM Parts ORDER BY part_id ASC");
            while (partRS.next()) {
                Vector<Object> row = new Vector<>();
                row.add(partRS.getInt("part_id"));
                row.add(partRS.getString("part_name"));
                row.add("Parts");
                row.add(partRS.getInt("quantity"));
                tableModel.addRow(row);
            }

            // Load from "material" table
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

        JTable table = new JTable(tableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);

        // Search bar
        JTextField searchField = new JTextField(30);
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText().trim();
                if (searchText.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }
            }
        });

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);

        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener((ActionEvent e) -> dispose());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        // Add panels to layout
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryList frame = new InventoryList();
            frame.setVisible(true);
        });
    }
}