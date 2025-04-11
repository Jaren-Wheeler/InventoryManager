package com.project.model;

public class Part {
    // declare part variables from SQL server table
    private int partId; // primary key
    private String partName;
    private int serialNum;
    private String dimensions;
    private int rackNum;
    private int quantity;
    private int materialID; // foreign key

    // constructor. Primary key partId not included since auto-incremented. Foreign key materialID
    public Part(int partId, String partName, int serialNum, String dimensions, int rackNum, int quantity, int materialID) {
        this.partId = partId;
        this.partName = partName;
        this.serialNum = serialNum;
        this.dimensions = dimensions;
        this.rackNum = rackNum;
        this.quantity = quantity;
        this.materialID = materialID;
    }

    // Getter and Setter methods
    public int getPartNumber() {
        return partId;
    }

    public void setPartNumber() {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName() {
        this.partName = partName;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum() {
        this.serialNum = serialNum;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions() {
        this.dimensions = dimensions;
    }

    public int getRackNums() {
        return rackNum;
    }

    public void setRackNums() {
        this.rackNum = rackNum;
    }

    public int getQty() {
        return quantity;
    }

    public void setQty() {
        this.quantity = quantity;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID() {
        this.materialID = materialID;
    }
}
