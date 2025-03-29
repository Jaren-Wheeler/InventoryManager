package com.project.model;

public class Material {
    // declare material variables from SQL table
    private int matId; // primary key
    private String matName;
    private String subType;
    private float diameter;
    private int rackNum;
    private int length;

    // constructor. Primary key not included since auto-incremented.
    public Material(String matName, String subType, float diameter, int rackNum, int length) {
        this.matName = matName;
        this.subType = subType;
        this.diameter = diameter;
        this.rackNum = rackNum;
        this.length = length;
    
    }

    // Getter and Setter methods
    public String getMatName() {
        return matName;
    }

    public void setMatName() {
        this.matName = matName;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType() {
        this.subType = subType;
    }

    public float getDiameter() {
        return diameter;
    }

    public void setDiameter() {
        this.diameter = diameter;
    }

    public int getRackNums() {
        return rackNum;
    }

    public void setRackNums() {
        this.rackNum = rackNum;
    }

    public int getLength() {
        return length;
    }

    public void setLength() {
        this.length = length;
    }
}
