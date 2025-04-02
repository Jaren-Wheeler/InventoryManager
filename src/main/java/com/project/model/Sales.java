package com.project.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;

public class Sales extends JFrame {

    public Sales() {
        setTitle("Sales");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Define table columns
        String[] columns = { "Invoice ID", "Customer ID", "Part ID","Quantity"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Query and fill table from database
        try (Connection conn = Model.databaseConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Sales LEFT JOIN Invoices ON Sales.inv_num = Invoices.inv_num ORDER BY inv_num ASC")) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("inv_num"));
                row.add(rs.getInt("cust_id"));
                row.add(rs.getInt("part_id"));
                row.add(rs.getInt("quantity"));
            ;
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error loading sales: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Create table with scroll and sorting
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);

        // Back button
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener((ActionEvent e) -> dispose());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        // Layout
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Sales frame = new Sales();
            frame.setVisible(true);
        });
    }
}
