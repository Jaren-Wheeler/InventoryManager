package com.project.model;
<<<<<<< HEAD

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

        String[] columns = { "Work Order ID", "WO Type", "Order Date", "Customer ID", "Part ID" };
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        try (Connection conn = Model.databaseConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Work_Order ORDER BY work_order_id ASC")) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("work_order_id"));
                row.add(rs.getString("wo_type"));
                row.add(rs.getDate("order_date"));
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
=======
import java.sql.Date;

public class WorkOrder {
    // declare work order variables from SQL server table
    private int woID; // primary key
    private String woType;
    private Date woDate;
    private int custID; // foreign key for customer table
    private int partID; // foreign key for parts table


    // constructor. Primary key partId not included since auto-incremented. Foreign key's custID and partID
    public WorkOrder(String woType, Date woDate, int custID, int partID) {
        this.woType = woType;
        this.woDate = woDate;
        this.custID = custID;
        this.partID = partID;

    }

    // Getter and Setter methods
    public String getWOType() {
        return woType;
    }

    public void setWOType() {
        this.woType = woType;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID() {
        this.custID = custID;
    }

    public int getPartID() {
        return partID;
    }

    public void setPartID() {
        this.partID = partID;
>>>>>>> 0d49c33d51bae4d11757a834c9c382709052292d
    }
}
