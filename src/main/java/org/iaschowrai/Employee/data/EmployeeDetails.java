package org.iaschowrai.Employee.data;

public class EmployeeDetails {
    private long empId;
    private String empFirstName;
    private String empLastName;
    private String gender;
    private int empAge;
    private double empSalary;
    private String department;
    private EmpDesignation empDesignation;

    // Constructor
    public EmployeeDetails(long empId, String empFirstName, String empLastName, String gender,
                           int empAge, double empSalary, String department, EmpDesignation empDesignation) {
        this.empId = empId;
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.gender = gender;
        this.empAge = empAge;
        this.empSalary = empSalary;
        this.department = department;
        this.empDesignation = empDesignation;
    }

    // Getters
    public long getEmpId() { return empId; }
    public String getEmpFirstName() { return empFirstName; }
    public String getEmpLastName() { return empLastName; }
    public String getGender() { return gender; }
    public int getEmpAge() { return empAge; }
    public double getEmpSalary() { return empSalary; }
    public String getDepartment() { return department; }
    public EmpDesignation getEmpDesignation() { return empDesignation; }

    // toString
    @Override
    public String toString() {
        return empId + " - " + empFirstName + " " + empLastName + " | " + department +
                " | " + empDesignation + " | $" + empSalary;
    }
}
