package com.project.model;

import java.sql.*;

public class CreateDB {
    // Create the database tables
    public static void createDatabase(Connection connection) {
       
        String dbCreate = 
            "CREATE TABLE IF NOT EXISTS Sales (" +
            "part_id INT NOT NULL, " +
            "inv_num INT NOT NULL, " +
            "Quantity INT, " +
            "PRIMARY KEY(part_id, inv_num));" +

            "CREATE TABLE IF NOT EXISTS Customers (" +
            "cust_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "cust_name VARCHAR(255) NOT NULL, " +
            "street VARCHAR(255) NOT NULL, " +
            "building_num INT NOT NULL, " +
            "city VARCHAR(255) NOT NULL, " +
            "province VARCHAR(255) NOT NULL, " +
            "country VARCHAR(255) NOT NULL, " +
            "postal_code VARCHAR(255) NOT NULL" +
            ");" +

            "CREATE TABLE IF NOT EXISTS Invoices (" +
            "inv_num INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "date DATE NOT NULL, " +
            "cust_id INT" +
            ");" +

            "CREATE TABLE IF NOT EXISTS Materials (" +
            "mat_id INTEGER PRIMARY KEY NOT NULL, " +
            "mat_name VARCHAR(255) NOT NULL, " +
            "sub_type VARCHAR(255), " +
            "diameter FLOAT NOT NULL, " +
            "rack_num INT, " +
            "length_inches INT" +
            ");" + 

            "CREATE TABLE IF NOT EXISTS Parts (" +
            "part_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "part_name VARCHAR(255) NOT NULL, " +
            "serial_num INT NOT NULL, " +
            "dimensions VARCHAR(255) NOT NULL, " +
            "rack_num INT NOT NULL, " +
            "price REAL NOT NULL, " +
            "quantity INT, " +
            "mat_id INT NOT NULL, " +
            "FOREIGN KEY (mat_id) REFERENCES Materials(mat_id)" +
            ");" +

            "CREATE TABLE IF NOT EXISTS WorkOrders (" +
            "order_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "length_inches FLOAT NOT NULL, " +
            "dimensions VARCHAR(255) NOT NULL, " +
            "cust_id INT NOT NULL, " +
            "part_id INT NOT NULL, " +
            "FOREIGN KEY (cust_id) REFERENCES Customers(cust_id), " +
            "FOREIGN KEY (part_id) REFERENCES Parts(part_id));" +

            "CREATE TABLE IF NOT EXISTS Person ("+
            "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username VARCHAR(255) NOT NULL," +
            "password_hash VARCHAR(255) NOT NULL);";

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(dbCreate);
            

            System.out.println("Database created and data inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating the tables or inserting data.");
            e.printStackTrace();
        }
    }
}
