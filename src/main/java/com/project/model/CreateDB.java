package com.project.model;

import java.sql.*;

public class CreateDB {
    // Create the database tables
    public static void createDatabase(Connection connection) {
        /*String dbDrop = "DROP TABLE Sales" +
                        "DROP TABLE Customers" +
                        "DROP TABLE Invoices" +
                        "DROP TABLE Materials" +
                        "DROP TABLE Parts" +
                        "DROP TABLE WorkOrders" +
                        "DROP TABLE Users";*/
        
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


        /*String dbInsert = "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (150, 1, 16);" +
             "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (259, 2, 3);" +
             "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (358, 3, 25);" +
             "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (457, 4, 13);" +
             "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (456, 5, 7);" +
             "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (105, 6, 15);" +
             "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (295, 7, 75);" +
             "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (385, 8, 44);" +
             "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (475, 9, 19);" +
             "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (465, 10, 11);" +

             "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (4, '2x4', 774, 150);" +
             "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (9, '2x4', 853, 259);" +
             "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (15, '11x15', 248, 358);" +
             "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (23, '3x7', 747, 457);" +
             "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (12, '1x6', 160, 456);" +
             "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (25, '10x20', 342, 105);" +
             "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (6, '5x12', 269, 295);" +
             "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (4, '2x4', 609, 385);" +
             "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (6, '8x12', 198, 475);" +
             "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (425, '16x24', 754, 465);" +

            "INSERT INTO Invoices (date, cust_id) VALUES ('06/11/24', 269); "
            + "INSERT INTO Invoices (date, cust_id) VALUES ('22/10/24', 16, 269); "
            + "INSERT INTO Invoices (date, cust_id) VALUES ('15/02/25', 16, 342); "
            + "INSERT INTO Invoices (date, cust_id) VALUES ('19/03/25', 16, 609); "
            + "INSERT INTO Invoices (date, cust_id) VALUES ('01/01/25', 198); "
            + "INSERT INTO Invoices (date, cust_id) VALUES ('23/02/25', 609); "
            + "INSERT INTO Invoices (date, cust_id) VALUES ('13/12/25', 342); "
            + "INSERT INTO Invoices (date, cust_id) VALUES ('27/01/25', 269); "
            + "INSERT INTO Invoices (date, cust_id) VALUES ('08/01/25', 609); "
            + "INSERT INTO Invoices (date, cust_id) VALUES ('01/03/25', 198); "

            + "INSERT INTO Customers (cust_id, cust_name, street, building_num, city, province, country, postal_code) "
            + "VALUES (342, 'Q2', 45, 37, 'Red Deer', 'AB', 'Can', 'T7H 1T8'); "
            + "INSERT INTO Customers (cust_id, cust_name, street, building_num, city, province, country, postal_code) "
            + "VALUES (269, 'McLevin', 32, 13, 'Red Deer', 'AB', 'Can', 'T9A 4U7'); "
            + "INSERT INTO Customers (cust_id, cust_name, street, building_num, city, province, country, postal_code) "
            + "VALUES (609, 'Texaco', 63, 9, 'Waco', 'Tx', 'US', 'B8U 9O7'); "
            + "INSERT INTO Customers (cust_id, cust_name, street, building_num, city, province, country, postal_code) "
            + "VALUES (198, 'Adams Steel Fabrication', 19, 48, 'Red Deer', 'AB', 'Can', 'T7B 6T1'); "

            + "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) "
            + "VALUES (1, 'plunger', 123456, '16''x 14''', 1, 20, 10); "
            + "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) "
            + "VALUES (2, 'Tow Ball', 234567, '4''x 10''', 2, 13, 21); "
            + "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) "
            + "VALUES (3, 'Flanges', 345678, '22''x 32''', 3, 43, 32); "
            + "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) "
            + "VALUES (4, 'Valves', 456789, '10''x 15''', 4, 19, 43); "
            + "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) "
            + "VALUES (5, 'Rods', 567891, '4''x 20''', 5, 23, 54); "
            + "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) "
            + "VALUES (6, 'Drill Collar', 678912, '30''x 50''', 6, 14, 65); "
            + "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) "
            + "VALUES (7, 'Drill Connector', 789123, '31''x 15''', 7, 32, 76); "
            + "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) "
            + "VALUES (8, 'Stabalizer', 891234, '8''x 11''', 8, 4, 87); "
            + "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) "
            + "VALUES (9, 'Hangers', 912345, '22''x 12''', 9, 63, 98); "
            + "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) "
            + "VALUES (10, 'BOP', 012345, '13''x 16''', 10, 15, 109); "

            + "INSERT INTO Materials (mat_id, mat_name, sub_type, diameter, rack_num, [length(Inches)]) "
            + "VALUES (10, 'Stainless Steel', 316, '3 1/4', 1, 216); "
            + "INSERT INTO Materials (mat_id, mat_name, sub_type, diameter, rack_num, [length(Inches)]) "
            + "VALUES (21, 'Stainless Steel', 316, '3 1/4', 2, 216); "
            + "INSERT INTO Materials (mat_id, mat_name, diameter, rack_num, [length(Inches)]) "
            + "VALUES (32, 'Mild Steel', '1 3/4', 3, 154); "
            + "INSERT INTO Materials (mat_id, mat_name, sub_type, diameter, rack_num, [length(Inches)]) "
            + "VALUES (43, 'Aluminum', 7075, '2 1/2', 4, 422); "
            + "INSERT INTO Materials (mat_id, mat_name, diameter, rack_num, [length(Inches)]) "
            + "VALUES (54, 'Brass', '2 1/4', 5, 117); "
            + "INSERT INTO Materials (mat_id, mat_name, sub_type, diameter, rack_num, [length(Inches)]) "
            + "VALUES (65, 'Stainless Steel', 316, '3 1/4', 6, 312); "
            + "INSERT INTO Materials (mat_id, mat_name, diameter, rack_num, [length(Inches)]) "
            + "VALUES (76, 'Copper', '4', 7, 367); "
            + "INSERT INTO Materials (mat_id, mat_name, diameter, rack_num, [length(Inches)]) "
            + "VALUES (87, 'Cast Iron', '2 3/4', 8, 67); "
            + "INSERT INTO Materials (mat_id, mat_name, sub_type, diameter, rack_num, [length(Inches)]) "
            + "VALUES (98, 'Aluminum', 6061, '3', 9, 167); "
            + "INSERT INTO Materials (mat_id, mat_name, sub_type, diameter, rack_num, [length(Inches)]) "
            + "VALUES (109, 'Stainless Steel', 304, '4 1/2', 10, 312);";*/

        try {
            Statement stmt = connection.createStatement();
            //stmt.executeUpdate(dbDrop);
            stmt.executeUpdate(dbCreate);
            //stmt.executeUpdate(dbInsert);

            System.out.println("Database created and data inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating the tables or inserting data.");
            e.printStackTrace();
        }
    }
}
