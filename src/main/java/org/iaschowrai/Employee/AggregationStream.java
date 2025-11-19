package org.iaschowrai.Employee;

import org.iaschowrai.Employee.data.EmployeeDataList;
import org.iaschowrai.Employee.data.EmployeeDetails;

import java.util.*;
import java.util.stream.Collectors;

public class AggregationStream {
    public static void main(String[] args) {
        List<EmployeeDetails> empList = EmployeeDataList.getAllEmployees();

        // Sum
        double totalSalary = empList.stream()
                .mapToDouble(EmployeeDetails::getEmpSalary)
                .sum();
        System.out.println(totalSalary);
        // Avg
        double avgSalary = empList.stream()
                .mapToDouble(EmployeeDetails::getEmpSalary)
                .average()
                .orElse(0);
        System.out.println(avgSalary);

        // max
        double maxSalary = empList.stream()
                .mapToDouble(EmployeeDetails::getEmpSalary)
                .max() // min()
                .orElse(0);
        System.out.println(maxSalary);

        // Employee with Max Salary
        empList.stream()
                .max(Comparator.comparing(EmployeeDetails::getEmpSalary))
                .ifPresent( e -> System.out.println("Highest salary: " + e.getEmpSalary() + " by employee: " + e.getEmpFirstName()+" "+ e.getEmpLastName()));

        // Average salary by department
        Map<String, Double> avgSalaryByDepart =  empList.stream()
                .collect(Collectors.groupingBy(EmployeeDetails::getDepartment,
                        Collectors.averagingDouble(EmployeeDetails::getEmpSalary)));
        avgSalaryByDepart.forEach((dept, avg) ->
        System.out.println(dept +" -> "+ String.format("%.2f", avg)));

        //Total salary by department
        Map<String, Double> totalSalaryByDepartment = empList.stream()
                .collect(Collectors.groupingBy(EmployeeDetails::getDepartment,
                        Collectors.summingDouble(EmployeeDetails::getEmpSalary)));
        totalSalaryByDepartment.forEach((dept, total) ->
                System.out.println(dept +" -> "+ String.format("%.2f", total)));

        //Employee count by each dept
        Map<String, Long> countEmpByDept = empList.stream()
                .collect(Collectors.groupingBy(EmployeeDetails::getDepartment,Collectors.counting()));
        countEmpByDept.forEach((dept, count) ->
                System.out.println(dept + " "+ count));

        //ADVANCED AGGREGATIONS
        //Salary statistics by department

        Map<String, DoubleSummaryStatistics> statsByDept = empList.stream()
                .collect(Collectors.groupingBy(
                        EmployeeDetails::getDepartment,
                        Collectors.summarizingDouble(EmployeeDetails::getEmpSalary)
                ));

        statsByDept.forEach((dept, stats) ->{
            System.out.println(dept + " AVG: " + stats.getAverage() +
                    " getMax: " + stats.getMax() +
                    " getMin: " + stats.getMin() +
                    " getCount: " + stats.getCount() +
                    " getSum: " + stats.getSum());
        });

        //Highest Paid employee by department
        Map<String, Optional<EmployeeDetails>> highestPaidByDept = empList.stream()
                .collect(Collectors.groupingBy(
                        EmployeeDetails::getDepartment,
                        Collectors.maxBy(
                                Comparator.comparing(EmployeeDetails::getEmpSalary)
                        )
                ));
        highestPaidByDept.forEach((dept, emp) ->
                emp.ifPresent(e -> System.out.println(dept + " → " + e.getEmpFirstName() + ": " + e.getEmpSalary()))
        );

//        Custom Aggregation with reduce()
        double totSalary = empList.stream()
                .map(EmployeeDetails::getEmpSalary)
                .reduce(0.0, Double::sum);
        System.out.println("Total Salary: " + totSalary);


    }

}
