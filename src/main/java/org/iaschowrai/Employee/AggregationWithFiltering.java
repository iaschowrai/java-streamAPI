package org.iaschowrai.Employee;

import org.iaschowrai.Employee.data.EmployeeDataList;
import org.iaschowrai.Employee.data.EmployeeDetails;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AggregationWithFiltering {
    public static void main(String[] args) {
        List<EmployeeDetails> empList = EmployeeDataList.getAllEmployees();

        // Average salary by IT Department
        double avgSalaryByIT = empList.stream().filter(e
                        -> e.getDepartment().equalsIgnoreCase("IT"))
                .mapToDouble(EmployeeDetails::getEmpSalary)
                .average()
                .orElse(0);
        System.out.println(avgSalaryByIT);

        // Total salary of employees older than 30
        double totalSalaryByAbove30 = empList.stream()
                .filter(e -> e.getEmpAge() > 30)
                .mapToDouble(EmployeeDetails::getEmpSalary)
                .sum();
        System.out.println(totalSalaryByAbove30);

        //Average salary of each department but only employees older than 30

        Map<String, Double> avgSalaryByDeptAbove30 = empList.stream()
                .filter( e -> e.getEmpAge() > 30)
                .collect(Collectors.groupingBy(EmployeeDetails::getDepartment,
                        Collectors.averagingDouble(EmployeeDetails::getEmpSalary)));
        System.out.println(avgSalaryByDeptAbove30);

        // ADVANCED AGGREGATION
        // Highest paid employee in each department with salary > 70000

        Map<String, Optional<EmployeeDetails>> highestPaidFiltered = empList.stream()
                .filter(e -> e.getEmpSalary() > 70000)
                .collect(Collectors.groupingBy(EmployeeDetails::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(EmployeeDetails::getEmpSalary))));

        highestPaidFiltered.forEach((dept, emp) ->
                emp.ifPresent( e -> System.out.println(dept +" Highest salary paid " + e.getEmpSalary())));


    }
}
