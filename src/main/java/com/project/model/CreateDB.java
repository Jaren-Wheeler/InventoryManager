package com.project.model;

import java.sql.*;

public class CreateDB {
    // Create the database tables
    public static void createDatabase(Connection connection) {
<<<<<<< HEAD
        String dbString = "CREATE TABLE IF NOT EXISTS Sales (" +
                                "part_id INT, " +
                                "inv_num INT, " +
                                "Quantity INT, " +
                                "PRIMARY KEY(part_id, inv_num)" +
                                ");\n" +

                                "CREATE TABLE IF NOT EXISTS Customers (" +
                                "cust_id INT IDENTITY (1,1) PRIMARY KEY NOT NULL, " +
                                "cust_name VARCHAR(255) NOT NULL, " +
                                "street VARCHAR(255) NOT NULL, " +
                                "building_num INT NOT NULL, " +
                                "city VARCHAR(255) NOT NULL, " +
                                "province VARCHAR(255) NOT NULL, " +
                                "country VARCHAR(255) NOT NULL, " +
                                "postal_code VARCHAR(255) NOT NULL" +
                                ");\n" +

                                "CREATE TABLE IF NOT EXISTS Invoices (" +
                                "inv_num INT IDENTITY (1,1) PRIMARY KEY, " +
                                "date DATE NOT NULL, " +
                                "cust_id INT" +
                                ");\n" +

                                "CREATE TABLE IF NOT EXISTS Materials (" +
                                "mat_id INT PRIMARY KEY NOT NULL, " +
                                "mat_name VARCHAR(255) NOT NULL, " +
                                "sub_type VARCHAR(255) NOT NULL, " +
                                "diameter FLOAT NOT NULL, " +
                                "rack_num INT, " +
                                "\"length(Inches)\" INT" +
                                ");\n" +

                                "CREATE TABLE IF NOT EXISTS Parts (" +
                                "part_id INT IDENTITY (1,1) PRIMARY KEY NOT NULL, " +
                                "part_name VARCHAR(255) NOT NULL, " +
                                "serial_num INT NOT NULL, " +
                                "dimensions VARCHAR(255) NOT NULL, " +
                                "rack_num INT NOT NULL, " +
                                "price MONEY NOT NULL, " +
                                "quantity INT, " +
                                "mat_id INT NOT NULL, " +
                                "CONSTRAINT fk_mat_id FOREIGN KEY (mat_id) REFERENCES Materials(mat_id)" +
                                ");\n" +

                                "CREATE TABLE IF NOT EXISTS WorkOrders (" +
                                "order_id INT NOT NULL, " +
                                "\"length(Inches)\" FLOAT NOT NULL, " +
                                "dimensions VARCHAR(255) NOT NULL," +

                                "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (150, 1, 16);\n" +
                                "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (259, 2, 3);\n" +
                                "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (358, 3, 25);\n" +
                                "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (457, 4, 13);\n" +
                                "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (456, 5, 7);\n" +
                                "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (105, 6, 15);\n" +
                                "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (295, 7, 75);\n" +
                                "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (385, 8, 44);\n" +
                                "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (475, 9, 19);\n" +
                                "INSERT INTO Sales (part_id, inv_num, Quantity) VALUES (465, 10, 11);\n" +
                            
                                "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (4, '2x4', 774, 150);\n" +
                                "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (9, '2x4', 853, 259);\n" +
                                "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (15, '11x15', 248, 358);\n" +
                                "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (23, '3x7', 747, 457);\n" +
                                "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (12, '1x6', 160, 456);\n" +
                                "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (25, '10x20', 342, 105);\n" +
                                "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (6, '5x12', 269, 295);\n" +
                                "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (4, '2x4', 609, 385);\n" +
                                "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (6, '8x12', 198, 475);\n" +
                                "INSERT INTO WorkOrders ([length(Inches)], dimensions, cust_id, part_id) VALUES (425, '16x24', 754, 465);\n" +
                            
                                "INSERT INTO Invoices (date, cust_id) VALUES ('06/11/24', 269);\n" +
                                "INSERT INTO Invoices (date, cust_id) VALUES ('22/10/24', 269);\n" +
                                "INSERT INTO Invoices (date, cust_id) VALUES ('15/02/25', 342);\n" +
                                "INSERT INTO Invoices (date, cust_id) VALUES ('19/03/25', 609);\n" +
                                "INSERT INTO Invoices (date, cust_id) VALUES ('01/01/25', 198);\n" +
                                "INSERT INTO Invoices (date, cust_id) VALUES ('23/02/25', 609);\n" +
                                "INSERT INTO Invoices (date, cust_id) VALUES ('13/12/25', 342);\n" +
                                "INSERT INTO Invoices (date, cust_id) VALUES ('27/01/25', 269);\n" +
                                "INSERT INTO Invoices (date, cust_id) VALUES ('08/01/25', 609);\n" +
                                "INSERT INTO Invoices (date, cust_id) VALUES ('01/03/25', 198);\n" +
                            
                                "INSERT INTO Customers (cust_id, cust_name, street, building_num, city, province, country, postal_code) VALUES (342, 'Q2', 45, 37, 'Red Deer', 'AB', 'Can', 'T7H 1T8');\n" +
                                "INSERT INTO Customers (cust_id, cust_name, street, building_num, city, province, country, postal_code) VALUES (269, 'McLevin', 32, 13, 'Red Deer', 'AB', 'Can', 'T9A 4U7');\n" +
                                "INSERT INTO Customers (cust_id, cust_name, street, building_num, city, province, country, postal_code) VALUES (609, 'Texaco', 63, 9, 'Waco', 'Tx', 'US', 'B8U 9O7');\n" +
                                "INSERT INTO Customers (cust_id, cust_name, street, building_num, city, province, country, postal_code) VALUES (198, 'Adams Steel Fabrication', 19, 48, 'Red Deer', 'AB', 'Can', 'T7B 6T1');\n" +
                            
                                "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) VALUES (1, 'plunger', 123456, '16''x 14''', 1, 20, 10);\n" +
                                "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) VALUES (2, 'Tow Ball', 234567, '4''x 10''', 2, 13, 21);\n" +
                                "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) VALUES (3, 'Flanges', 345678, '22''x 32''', 3, 43, 32);\n" +
                                "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) VALUES (4, 'Valves', 456789, '10''x 15''', 4, 19, 43);\n" +
                                "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) VALUES (5, 'Rods', 567891, '4''x 20''', 5, 23, 54);\n" +
                                "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) VALUES (6, 'Drill Collar', 678912, '30''x 50''', 6, 14, 65);\n" +
                                "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) VALUES (7, 'Drill Connector', 789123, '31''x 15''', 7, 32, 76);\n" +
                                "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) VALUES (8, 'Stabalizer', 891234, '8''x 11''', 8, 4, 87);\n" +
                                "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) VALUES (9, 'Hangers', 912345, '22''x 12''', 9, 63, 98);\n" +
                                "INSERT INTO Parts (part_id, part_name, serial_num, dimensions, rack_num, quantity, mat_id) VALUES (10, 'BOP', 012345, '13''x 16''', 10, 15, 109);\n" +
                            
                                "INSERT INTO Materials (mat_id, mat_name, sub_type, diameter, rack_num, [length(Inches)]) VALUES (10, 'Stainless Steel', 316, '3 1/4', 1, 216);\n" +
                                "INSERT INTO Materials (mat_id, mat_name, sub_type, diameter, rack_num, [length(Inches)]) VALUES (21, 'Stainless Steel', 316, '3 1/4', 2, 216);\n" +
                                "INSERT INTO Materials (mat_id, mat_name, diameter, rack_num, [length(Inches)]) VALUES (32, 'Mild Steel', '1 3/4', 3, 154);\n" +
                                "INSERT INTO Materials (mat_id, mat_name, sub_type, diameter, rack_num, [length(Inches)]) VALUES (43, 'Aluminum', 7075, '2 1/2', 4, 422);\n" +
                                "INSERT INTO Materials (mat_id, mat_name, diameter, rack_num, [length(Inches)]) VALUES (54, 'Brass', '2 1/4', 5, 117);\n" +
                                "INSERT INTO Materials (mat_id, mat_name, sub_type, diameter, rack_num, [length(Inches)]) VALUES (65, 'Stainless Steel', 316, '3 1/4', 6, 312);\n" +
                                "INSERT INTO Materials (mat_id, mat_name, diameter, rack_num, [length(Inches)]) VALUES (76, 'Copper', '4', 7, 367);\n" +
                                "INSERT INTO Materials (mat_id, mat_name, diameter, rack_num, [length(Inches)]) VALUES (87, 'Cast Iron', '2 3/4', 8, 67);\n" +
                                "INSERT INTO Materials (mat_id, mat_name, sub_type, diameter, rack_num, [length(Inches)]) VALUES (98, 'Aluminum', 6061, '3', 9, 167);\n" +
                                "INSERT INTO Materials (mat_id, mat_name, sub_type, diameter, rack_num, [length(Inches)]) VALUES (109, 'Stainless Steel', 304, '4 1/2', 10, 312);";
                            
=======
        String dbString = 
            "CREATE TABLE IF NOT EXISTS CUSTOMER ("
            + "cust_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "cust_name TEXT, "
            + "street TEXT, "
            + "building_num TEXT, "
            + "city TEXT, "
            + "province TEXT, "
            + "country TEXT); "

            + "CREATE TABLE IF NOT EXISTS INVOICE ("
            + "invoices_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "invoice_date DATE, "
            + "cust_id INTEGER, "
            + "FOREIGN KEY (cust_id) REFERENCES CUSTOMER(cust_id)); "

            + "CREATE TABLE IF NOT EXISTS MATERIAL ("
            + "mat_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "mat_name TEXT, "
            + "sub_type TEXT, "
            + "diameter REAL, "
            + "rack_num TEXT, "
            + "length_inches REAL); "

            + "CREATE TABLE IF NOT EXISTS PART ("
            + "part_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "part_name TEXT, "
            + "serial_num TEXT, "
            + "dimensions TEXT, "
            + "rack_num TEXT, "
            + "qty INTEGER, "
            + "mat_id INTEGER, "
            + "FOREIGN KEY (mat_id) REFERENCES MATERIAL(mat_id)); "

            + "CREATE TABLE IF NOT EXISTS WORK_ORDER ("
            + "work_order_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "wo_type TEXT, "
            + "order_date DATE, "
            + "cust_id INTEGER, "
            + "part_id INTEGER, "
            + "FOREIGN KEY (part_id) REFERENCES PART(part_id), "
            + "FOREIGN KEY (cust_id) REFERENCES CUSTOMER(cust_id)); "

            + "CREATE TABLE IF NOT EXISTS SALE ("
            + "part_id INTEGER, "
            + "inv_id INTEGER, "
            + "qty INTEGER, "
            + "PRIMARY KEY (part_id, inv_id), "
            + "FOREIGN KEY (part_id) REFERENCES PART(part_id), "
            + "FOREIGN KEY (inv_id) REFERENCES INVOICE(invoices_id)); "

            + "CREATE TABLE IF NOT EXISTS PERSON ("
            + "person_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "username TEXT, "
            + "password_hash TEXT);"

            // Insert sample data into MATERIAL table
            + "INSERT INTO MATERIAL (mat_name, sub_type, diameter, rack_num, length_inches) VALUES('Stainless Steel', '316', '3 1/4', 1, 216); "
            + "INSERT INTO MATERIAL (mat_name, sub_type, diameter, rack_num, length_inches) VALUES('Stainless Steel', '316', '3 1/4', 2, 216); "
            + "INSERT INTO MATERIAL (mat_name, diameter, rack_num, length_inches) VALUES('Mild Steel', '1 3/4', 3, 154); "
            + "INSERT INTO MATERIAL (mat_name, sub_type, diameter, rack_num, length_inches) VALUES('Aluminum', '7075', '2 1/2', 4, 422); "
            + "INSERT INTO MATERIAL (mat_name, diameter, rack_num, length_inches) VALUES('Brass', '2 1/4', 5, 117); "
            + "INSERT INTO MATERIAL (mat_name, sub_type, diameter, rack_num, length_inches) VALUES('Stainless Steel', '316', '3 1/4', 6, 312); "
            + "INSERT INTO MATERIAL (mat_name, diameter, rack_num, length_inches) VALUES('Copper', '4', 7, 367); "
            + "INSERT INTO MATERIAL (mat_name, diameter, rack_num, length_inches) VALUES('Cast Iron', '2 3/4', 8, 67); "
            + "INSERT INTO MATERIAL (mat_name, sub_type, diameter, rack_num, length_inches) VALUES('Aluminum', '6061', '3', 9, 167); "
            + "INSERT INTO MATERIAL (mat_name, sub_type, diameter, rack_num, length_inches) VALUES('Stainless Steel', '304', '4 1/2', 10, 312); "

            // Insert sample data into PART table
            + "INSERT INTO PART (part_name, serial_num, dimensions, rack_num, qty, mat_id) VALUES ('plunger', 123456, '16''x 14''', 1, 20, 1); "
            + "INSERT INTO PART (part_name, serial_num, dimensions, rack_num, qty, mat_id) VALUES ('Tow Ball', 234567, '4''x 10''', 2, 13, 2); "
            + "INSERT INTO PART (part_name, serial_num, dimensions, rack_num, qty, mat_id) VALUES ('Flanges', 345678, '22''x 32''', 3, 43, 3); "
            + "INSERT INTO PART (part_name, serial_num, dimensions, rack_num, qty, mat_id) VALUES ('Valves', 456789, '10''x 15''', 4, 19, 4); "
            + "INSERT INTO PART (part_name, serial_num, dimensions, rack_num, qty, mat_id) VALUES ('Rods', 567891, '4''x 20''', 5, 23, 5); "
            + "INSERT INTO PART (part_name, serial_num, dimensions, rack_num, qty, mat_id) VALUES ('Drill Collar', 678912, '30''x 50''', 6, 14, 6); "
            + "INSERT INTO PART (part_name, serial_num, dimensions, rack_num, qty, mat_id) VALUES ('Drill Connector', 789123, '31''x 15''', 7, 32, 7); "
            + "INSERT INTO PART (part_name, serial_num, dimensions, rack_num, qty, mat_id) VALUES ('Stabilizer', 891234, '8''x 11''', 8, 4, 8); "
            + "INSERT INTO PART (part_name, serial_num, dimensions, rack_num, qty, mat_id) VALUES ('Hangers', 912345, '22''x 12''', 9, 63, 9); "
            + "INSERT INTO PART (part_name, serial_num, dimensions, rack_num, qty, mat_id) VALUES ('BOP', 012345, '13''x 16''', 10, 15, 10);";
>>>>>>> 0d49c33d51bae4d11757a834c9c382709052292d

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(dbString);  // Execute the SQL statement
        } catch (SQLException e) {
            System.out.println("Error creating the tables or inserting data.");
            System.out.println(e);
        }
    }
}
