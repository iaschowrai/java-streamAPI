package org.iaschowrai.Employee;

import org.iaschowrai.Employee.data.EmployeeDataList;
import org.iaschowrai.Employee.data.EmployeeDetails;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MapWithStream {
    public static void main(String[] args) {
        Map<Long, EmployeeDetails> empMap = EmployeeDataList.getAllEmployeesInMap();

//        empMap.keySet().stream().forEach(System.out::println);  // only key from map key
//        empMap.values().stream().forEach(System.out::println);  // value from map value
//        empMap.entrySet().stream().forEach(System.out::println); // entry set with both key and value from map

        //Filtering on Map Values for IT department
        empMap.values().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("IT"))
                .forEach(System.out::println);

        // sorting from Map
        empMap.values().stream()
                .sorted(Comparator.comparingDouble(EmployeeDetails::getEmpSalary).reversed())
                .forEach(System.out::println);

        // sort by department then salary
        empMap.values().stream()
                .sorted(Comparator.comparing(EmployeeDetails::getDepartment)
                        .thenComparing(EmployeeDetails::getEmpSalary))
                .forEach(System.out::println);

        //Aggregation from Map
//        Map Filtering + Aggregation
        double avgSalaryIT = empMap.values().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("IT"))
                .mapToDouble(EmployeeDetails::getEmpSalary)
                .average()
                .orElse(0);

        System.out.printf("Average Salary (IT): %.2f%n", avgSalaryIT);

//        Highest paid employee by department:
        Map<String, EmployeeDetails> highestPaidByDept = empMap.values().stream()
                .collect(Collectors.groupingBy(
                        EmployeeDetails::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(EmployeeDetails::getEmpSalary)),
                                Optional::get
                        )));
        highestPaidByDept.forEach((dept, emp) ->
                System.out.printf("%s → %s %.2f%n", dept, emp.getEmpFirstName(), emp.getEmpSalary()));


//        Highest Salary in a Department
        empMap.values().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("IT"))
                .max(Comparator.comparingDouble(EmployeeDetails::getEmpSalary))
                .ifPresent(e -> System.out.printf("Highest Salary (IT): %.2f — %s %s%n",
                        e.getEmpSalary(), e.getEmpFirstName(), e.getEmpLastName()));

//        Lowest Salary in a Department
        empMap.values().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("IT"))
                .min(Comparator.comparingDouble(EmployeeDetails::getEmpSalary))
                .ifPresent(e -> System.out.printf("Lowest Salary (IT): %.2f — %s %s%n",
                        e.getEmpSalary(), e.getEmpFirstName(), e.getEmpLastName()));

//        First Highest Salary (Sorted)
        empMap.values().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("IT"))
                .sorted(Comparator.comparingDouble(EmployeeDetails::getEmpSalary).reversed())
                .findFirst()
                .ifPresent(e -> System.out.printf("First Highest Salary (IT): %.2f — %s %s%n",
                        e.getEmpSalary(), e.getEmpFirstName(), e.getEmpLastName()));


//        Second Highest Salary in a Department
        empMap.values().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("IT"))
                .sorted(Comparator.comparingDouble(EmployeeDetails::getEmpSalary).reversed())
                .skip(1) // skip the first highest
                .findFirst()
                .ifPresent(e -> System.out.printf("Second Highest Salary (IT): %.2f — %s %s%n",
                        e.getEmpSalary(), e.getEmpFirstName(), e.getEmpLastName()));

//        Second Lowest Salary in a Department
        empMap.values().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("IT"))
                .sorted(Comparator.comparingDouble(EmployeeDetails::getEmpSalary)) // ascending
                .skip(1) // skip the lowest
                .findFirst()
                .ifPresent(e -> System.out.printf("Second Lowest Salary (IT): %.2f — %s %s%n",
                        e.getEmpSalary(), e.getEmpFirstName(), e.getEmpLastName()));


    }
}
