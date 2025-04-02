package com.project.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;

public class WorkOrder extends JFrame {

    public WorkOrder() {
        setTitle("Work Orders");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = { "Work Order ID", "Length", "Dimensions","Customer ID", "Part ID" };
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        try (Connection conn = Model.databaseConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM WorkOrders ORDER BY order_id ASC")) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("order_id"));
                row.add(rs.getString("length_inches"));
                row.add(rs.getString("dimensions"));
                row.add(rs.getInt("cust_id"));
                row.add(rs.getInt("part_id"));
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error loading work orders: " + e.getMessage(),
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
            WorkOrder frame = new WorkOrder();
            frame.setVisible(true);
        });
    }
}
