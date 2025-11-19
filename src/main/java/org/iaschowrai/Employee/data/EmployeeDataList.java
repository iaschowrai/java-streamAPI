package org.iaschowrai.Employee.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeDataList {

    public static List<EmployeeDetails> getAllEmployees() {

        List<EmployeeDetails> employees = new ArrayList<>();

        employees.add(new EmployeeDetails(101, "John", "Doe", "Male", 28, 55000, "IT", EmpDesignation.JUNIOR));
        employees.add(new EmployeeDetails(102, "Alice", "Smith", "Female", 32, 72000, "Finance", EmpDesignation.SENIOR));
        employees.add(new EmployeeDetails(103, "Robert", "Johnson", "Male", 41, 88000, "HR", EmpDesignation.MANAGER));
        employees.add(new EmployeeDetails(104, "Emily", "Davis", "Female", 26, 50000, "IT", EmpDesignation.JUNIOR));
        employees.add(new EmployeeDetails(105, "Michael", "Brown", "Male", 35, 95000, "Operations", EmpDesignation.LEAD));
        employees.add(new EmployeeDetails(106, "Sophia", "Wilson", "Female", 29, 64000, "Finance", EmpDesignation.SENIOR));
        employees.add(new EmployeeDetails(107, "David", "Moore", "Male", 30, 62000, "IT", EmpDesignation.JUNIOR));
        employees.add(new EmployeeDetails(108, "Olivia", "Taylor", "Female", 38, 78000, "Marketing", EmpDesignation.LEAD));
        employees.add(new EmployeeDetails(109, "Daniel", "Anderson", "Male", 45, 105000, "Finance", EmpDesignation.MANAGER));
        employees.add(new EmployeeDetails(110, "Isabella", "Thomas", "Female", 27, 58000, "Operations", EmpDesignation.JUNIOR));
        employees.add(new EmployeeDetails(111, "James", "Jackson", "Male", 33, 73000, "IT", EmpDesignation.SENIOR));
        employees.add(new EmployeeDetails(112, "Mia", "White", "Female", 40, 97000, "Finance", EmpDesignation.MANAGER));
        employees.add(new EmployeeDetails(113, "William", "Harris", "Male", 24, 48000, "Marketing", EmpDesignation.JUNIOR));
        employees.add(new EmployeeDetails(114, "Charlotte", "Martin", "Female", 36, 85000, "Operations", EmpDesignation.LEAD));
        employees.add(new EmployeeDetails(115, "Ethan", "Thompson", "Male", 31, 66000, "HR", EmpDesignation.SENIOR));
        employees.add(new EmployeeDetails(116, "Amelia", "Garcia", "Female", 28, 54000, "Marketing", EmpDesignation.JUNIOR));
        employees.add(new EmployeeDetails(117, "Alexander", "Martinez", "Male", 39, 87000, "Finance", EmpDesignation.LEAD));
        employees.add(new EmployeeDetails(118, "Harper", "Robinson", "Female", 42, 99000, "Operations", EmpDesignation.MANAGER));
        employees.add(new EmployeeDetails(119, "Liam", "Clark", "Male", 25, 51000, "IT", EmpDesignation.JUNIOR));
        employees.add(new EmployeeDetails(120, "Evelyn", "Rodriguez", "Female", 37, 81000, "HR", EmpDesignation.LEAD));
        employees.add(new EmployeeDetails(121, "Benjamin", "Lewis", "Male", 29, 60000, "Finance", EmpDesignation.SENIOR));
        employees.add(new EmployeeDetails(122, "Abigail", "Lee", "Female", 34, 75000, "Marketing", EmpDesignation.LEAD));
        employees.add(new EmployeeDetails(123, "Lucas", "Walker", "Male", 43, 102000, "Operations", EmpDesignation.MANAGER));
        employees.add(new EmployeeDetails(124, "Ella", "Hall", "Female", 23, 47000, "HR", EmpDesignation.JUNIOR));
        employees.add(new EmployeeDetails(125, "Henry", "Allen", "Male", 27, 56000, "IT", EmpDesignation.JUNIOR));

        return employees;
    }


    public static Map<Long, EmployeeDetails> getAllEmployeesInMap(){
        return getAllEmployees().stream().collect(Collectors.toMap(EmployeeDetails::getEmpId, e-> e));
    }
}
