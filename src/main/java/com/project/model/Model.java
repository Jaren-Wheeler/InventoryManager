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
        
        String query = "INSERT INTO Person (username,password_hash) VALUES (?, ?)"; // the query for username and password insertion
        try (Connection db = databaseConnection();
            PreparedStatement ps = db.prepareStatement(query);) {
        
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
 

        String query = "SELECT * FROM Person WHERE username = ? AND password_hash = ?";

        try (Connection db = databaseConnection();
            PreparedStatement ps = db.prepareStatement(query)) {

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
        

        String query = "";
        // change query based on if user has clicked part or material in drop down box
        if (searchType == "Parts") {
            query = "SELECT * FROM Parts WHERE part_id = ?"; // query to search for a part by id from Part table
        } else {
            query = "SELECT * FROM Materials WHERE mat_id = ?"; // query to search for a material by id from Material table
        }
          
        try (Connection db = databaseConnection();
            PreparedStatement ps = db.prepareStatement(query)){

        
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
                    int qty = r.getInt("quantity");
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
    

        String query = "";
        // change query based on if user has clicked part or material in drop down box
        if (searchType == "Parts") {
            query = "SELECT * FROM Parts WHERE part_name = ?"; // query to search for a part by id from Part table
        } else {
            query = "SELECT * FROM Materials WHERE mat_name = ?"; // query to search for a material by id from Material table
        }

        try (Connection db = databaseConnection();
            PreparedStatement ps = db.prepareStatement(query)){
            
            ps.setString(1,nameInput);

            ResultSet r = ps.executeQuery();

            if (r.next()) {
                if (searchType == "Parts") {
                    int itemNumber = r.getInt("part_id");
                    String itemName = r.getString("part_name");
                    int serialNum = r.getInt("serial_num");
                    String dimensions = r.getString("dimensions");
                    int rackNum = r.getInt("rack_num");
                    int qty = r.getInt("quantity");
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


    // Query for displaying all work orders
    public static boolean displayWorkOrders() {
      
        
        String query = "SELECT * FROM WorkOrders";
        try (Connection db = databaseConnection();
            Statement s = db.createStatement();){
         
            ResultSet r = s.executeQuery(query);

            if (r.next()) {
                int wo_id = r.getInt("work_order_id");
                String wo_type = r.getString("wo_type");
                Date wo_date = r.getDate("order_date");
                int customer_id = r.getInt("cust_id");
                int part_id = r.getInt("part_id");
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }

    // Query to add new part to the database
    public static boolean addNewPart(int partID, String partName,int serialNum, String dimensions, int rack_num, Double price,int quantity,int matID) {
        
        String queryParts = "INSERT INTO Parts (part_id,part_name,serial_num,dimensions,rack_num,price,quantity,mat_id) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection db = databaseConnection();
            PreparedStatement stmt = db.prepareStatement(queryParts)){
            
            stmt.setInt(1,partID);
            stmt.setString(2,partName);
            stmt.setInt(3,serialNum);
            stmt.setString(4,dimensions);
            stmt.setInt(5,rack_num);
            stmt.setDouble(6,price);
            stmt.setInt(7,quantity);
            stmt.setInt(8,matID);

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }  
    }

        // Query to add new material to the database
        public static boolean addNewMaterial(int matID, String matName,String subType, double diameter, int rack_num, double length) {
           
            
            String queryMaterials = "INSERT INTO Materials (mat_id,mat_name,sub_type,diameter,rack_num,length_inches) VALUES (?,?,?,?,?,?) ";
            try (Connection db = databaseConnection();
                PreparedStatement stmt = db.prepareStatement(queryMaterials);){
                
                
                stmt.setInt(1,matID);
                stmt.setString(2,matName);
                stmt.setString(3,subType);
                stmt.setDouble(4,diameter);
                stmt.setInt(5,rack_num);
                stmt.setDouble(6,length);

                stmt.executeUpdate();

                return true;
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }  
        }

    // Query to remove part from the database
    public static boolean removePart(String partName) {
      
        String query = "DELETE FROM Parts WHERE part_name = ?";

        try (Connection db = databaseConnection();
            PreparedStatement stmt = db.prepareStatement(query);) {

            stmt.setString(1,partName);
            stmt.executeUpdate();            
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 

        return true;
    }

    // Query to remove material from the database
    public static boolean removeMaterial(String matName) {
        
        String query = "DELETE FROM Materials WHERE mat_name = ?";

        try (Connection db = databaseConnection();
            PreparedStatement stmt = db.prepareStatement(query)){
            
            stmt.setString(1,matName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }
}
