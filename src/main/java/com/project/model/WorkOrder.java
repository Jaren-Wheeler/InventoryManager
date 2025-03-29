package com.project.model;
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
    }
}
