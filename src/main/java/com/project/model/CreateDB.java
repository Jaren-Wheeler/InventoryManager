package com.project.model;

import java.sql.*;

public class CreateDB {
    // Create the database tables
    public static void createDatabase(Connection connection) {
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

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(dbString);  // Execute the SQL statement
        } catch (SQLException e) {
            System.out.println("Error creating the tables or inserting data.");
            System.out.println(e);
        }
    }
}
