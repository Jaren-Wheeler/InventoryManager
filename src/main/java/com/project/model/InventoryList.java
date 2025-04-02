package com.project.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
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
            ResultSet partRS = stmt.executeQuery("SELECT * FROM part ORDER BY part_id ASC");
            while (partRS.next()) {
                Vector<Object> row = new Vector<>();
                row.add(partRS.getInt("part_id"));
                row.add(partRS.getString("part_name"));
                row.add("Part");
                row.add(partRS.getInt("qty"));
                tableModel.addRow(row);
            }

            // Load from "material" table using mat_id and mat_name
            ResultSet materialRS = stmt.executeQuery("SELECT * FROM material ORDER BY mat_id ASC");
            while (materialRS.next()) {
                Vector<Object> row = new Vector<>();
                row.add(materialRS.getInt("mat_id")); // ✅ Correct ID column
                row.add(materialRS.getString("mat_name")); // ✅ Updated name column
                row.add("Material");
                row.add(materialRS.getInt("qty"));
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
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener((ActionEvent e) -> dispose());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

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
