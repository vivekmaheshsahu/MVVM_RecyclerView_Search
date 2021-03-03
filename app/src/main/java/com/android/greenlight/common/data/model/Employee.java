package com.android.greenlight.common.data.model;

/**
 * @author Vivek
 * This model is used for Employee detail to use in project at different places for storing and displaying in app
 */
public class Employee {
    private int empId;

    private String empName;

    private String empArea;

    private String empTerritory;

    public Employee() {
    }

    public Employee(int empId, String empName, String empArea, String empTerritory) {
        this.empId = empId;
        this.empName = empName;
        this.empArea = empArea;
        this.empTerritory = empTerritory;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpArea() {
        return empArea;
    }

    public void setEmpArea(String empArea) {
        this.empArea = empArea;
    }

    public String getEmpTerritory() {
        return empTerritory;
    }

    public void setEmpTerritory(String empTerritory) {
        this.empTerritory = empTerritory;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empArea='" + empArea + '\'' +
                ", empTerritory='" + empTerritory + '\'' +
                '}';
    }
}
