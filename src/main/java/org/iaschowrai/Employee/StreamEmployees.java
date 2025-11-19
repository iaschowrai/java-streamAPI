package org.iaschowrai.Employee;

import org.iaschowrai.Employee.data.EmployeeDataList;
import org.iaschowrai.Employee.data.EmployeeDetails;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamEmployees {
    public static void main(String[] args) {

        // print all employees
        List<EmployeeDetails> empList = EmployeeDataList.getAllEmployees();
        System.out.println(empList);

        // filter all IT employees
        List<EmployeeDetails> itDeptEmpList =  empList.stream().filter(dep -> dep.getDepartment().equalsIgnoreCase("IT")).collect(Collectors.toList());
        System.out.println(itDeptEmpList);

        // Distinct departments
        empList.stream().map(EmployeeDetails::getDepartment)
                .distinct().forEach(System.out::println);

        // first 5 employee only
        empList.stream().limit(5).forEach(System.out::println);

        // skip first 5 employee
        empList.stream().skip(5).forEach(System.out::println);

        // Get all employee full name
        empList.stream()
                .map(emp -> emp.getEmpFirstName() + " " + emp.getEmpLastName())
                .forEach(System.out::println);

        // get all emp salaries
        empList.stream()
                .mapToDouble(EmployeeDetails::getEmpSalary).forEach(System.out::println);

        //Increase all salaries by 10%
        empList.stream()
                .map(emp -> emp.getEmpSalary() * 1.10).forEach(System.out::println);

        //sort employee by salary in asc order
        empList.stream().sorted(Comparator.comparing(EmployeeDetails::getEmpSalary)).forEach(System.out::println);

        //sort employee by salary in desc order
        empList.stream().sorted(Comparator.comparing(EmployeeDetails::getEmpSalary).reversed()).forEach(System.out::println);

        // sort by department and salary
        empList.stream()
                .sorted(Comparator.comparing(EmployeeDetails::getDepartment).thenComparing(EmployeeDetails::getEmpSalary))
                .forEach(System.out::println);

        // Average salary of an employees by IT dept
        double avgEmpSalary = empList.stream().filter(dep -> dep.getDepartment().equalsIgnoreCase("IT"))
                .mapToDouble(EmployeeDetails::getEmpSalary).average().orElse(0);
        System.out.println(avgEmpSalary);

        // Average salar by all department
        Map<String, Double> avgEmpSalaryByDepartment = empList.stream()
                .collect(Collectors.groupingBy(
                        EmployeeDetails::getDepartment,
                        Collectors.averagingDouble(EmployeeDetails::getEmpSalary)
                ));
        avgEmpSalaryByDepartment
                .forEach((dept,avg) ->
                        System.out.println("Dept: " + dept + " Avg Salary: " +String.format("%.2f", avg)));



    }
}
