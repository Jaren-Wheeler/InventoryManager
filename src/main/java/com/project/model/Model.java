package com.project.model;

import java.sql.*;

public class Model {
// initialize the database entities
    Part part = null;
    Material material = null;
    WorkOrder workOrder = null;

    // method to connect to the database
    public static Connection databaseConnection() {
        String url = "jdbc:sqlite:database.db"; // the database file
        try {

            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection(url); // connection object using driver manager class to access the database

            CreateDB.createDatabase(connection); // the database tables
        
            return connection;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    // inserts a new user into the database when an account is created. Returns true if insertion happens, otherwise returns false.
    public static boolean createAccount(String username, String password) {
        
        try {
            Connection db = databaseConnection();

            //Statement s = db.createStatement(); // create a statement object
    
            String query = "INSERT INTO Person (username,password_hash) VALUES (?, ?)"; // the query for username and password insertion
            PreparedStatement ps = db.prepareStatement(query);
    
            // insert the user's inputted values into the query
            ps.setString(1,username);
            ps.setString(2,password);
    
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
      
       
    }

    public static boolean enterAccount(String username, String password) {
        Connection db = databaseConnection();

        String query = "SELECT * FROM Person WHERE username = ? AND password_hash = ?";

        try {
            PreparedStatement ps = db.prepareStatement(query);

            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet r = ps.executeQuery();
            if (r.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // The query for the search bar in the dashboard
    public Object searchByID(int IDInput, String searchType) {
        Connection db = databaseConnection();

        String query = "";
        // change query based on if user has clicked part or material in drop down box
        if (searchType == "Parts") {
            query = "SELECT * FROM Part WHERE part_id = ?"; // query to search for a part by id from Part table
        } else {
            query = "SELECT * FROM Material WHERE mat_id = ?"; // query to search for a material by id from Material table
        }
          
        try {

            // two prepared statements for both tables
            PreparedStatement ps = db.prepareStatement(query);
  
            ps.setInt(1,IDInput);

            ResultSet r = ps.executeQuery();

            // get values for all of the columns of the part or material and pass them as variables to create a part or variable object.
            if (r.next()) {
                if (searchType == "Parts") {
                    int itemNumber = r.getInt("part_id");
                    String itemName = r.getString("part_name");
                    int serialNum = r.getInt("serial_num");
                    String dimensions = r.getString("dimensions");
                    int rackNum = r.getInt("rack_num");
                    int qty = r.getInt("qty");
                    int materialID = r.getInt("mat_id");

                    part = new Part(itemNumber,itemName,serialNum,dimensions,rackNum,qty,materialID);
                    return part;
                } else {
                    int itemNumber = r.getInt("mat_id");
                    String itemName = r.getString("mat_name");
                    String subType = r.getString("sub_type");
                    float diameter = r.getFloat("diameter");
                    int rackNum = r.getInt("rack_num");
                    int length = r.getInt("length_inches");

                    material = new Material(itemNumber,itemName,subType,diameter,rackNum,length);
                    return material;
                }
            } else {
                return null;
            }
           
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
       
    }

    // The query for the search bar in the dashboard based on inputting the name of it. searchType checks whether it is a part or material
    public Object searchByName(String nameInput, String searchType) {
        Connection db = databaseConnection();

        String query = "";
        // change query based on if user has clicked part or material in drop down box
        if (searchType == "Parts") {
            query = "SELECT * FROM Part WHERE part_name = ?"; // query to search for a part by id from Part table
        } else {
            query = "SELECT * FROM Material WHERE mat_name = ?"; // query to search for a material by id from Material table
        }

        try {
            PreparedStatement ps = db.prepareStatement(query);

            ps.setString(1,nameInput);

            ResultSet r = ps.executeQuery();

            if (r.next()) {
                if (searchType == "Parts") {
                    int itemNumber = r.getInt("part_id");
                    String itemName = r.getString("part_name");
                    int serialNum = r.getInt("serial_num");
                    String dimensions = r.getString("dimensions");
                    int rackNum = r.getInt("rack_num");
                    int qty = r.getInt("qty");
                    int materialID = r.getInt("mat_id");

                    part = new Part(itemNumber,itemName,serialNum,dimensions,rackNum,qty,materialID);
                    return part;
                } else {
                    int itemNumber = r.getInt("mat_id");
                    String itemName = r.getString("mat_name");
                    String subType = r.getString("sub_type");
                    float diameter = r.getFloat("diameter");
                    int rackNum = r.getInt("rack_num");
                    int length = r.getInt("length_inches");

                    material = new Material(itemNumber,itemName,subType,diameter,rackNum,length);
                    return material;
                }
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
       
    }

    // The query for displaying all of the information about a part or material
    public static boolean displayItemInfo() {
        Connection db = databaseConnection();
        return true;
    }

    // Query for displaying all work orders
    public static boolean displayWorkOrders() {
        Connection db = databaseConnection();
        
        String query = "SELECT * FROM Work_Order";
        try {
            Statement s = db.createStatement();
            ResultSet r = s.executeQuery(query);

            if (r.next()) {
                int wo_id = r.getInt("work_order_id");
                String wo_type = r.getString("wo_type");
                Date wo_date = r.getDate("order_date");
                int customer_id = r.getInt("cust_id");
                int part_id = r.getInt("part_id");


            }

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    // Query to add new item or material to the database
    public static boolean addNewItem() {
        return true;
    }

    // Query to remove item from the database
    public static boolean removeItem() {
        return true;
    }

    // returns the top 20 items and their inventory levels
    public static boolean generateTopItems() {
        return true;
    }
}
