package org.iaschowrai.Employee;

import org.iaschowrai.Employee.data.EmployeeDataList;
import org.iaschowrai.Employee.data.EmployeeDetails;

import java.util.Comparator;
import java.util.List;

public class SortingStream {

    public static void main(String[] args) {
        List<EmployeeDetails> empList = EmployeeDataList.getAllEmployees();

//        //sort employee by salary in asc order
//        empList.stream()
        //        .sorted(Comparator.comparing(EmployeeDetails::getEmpSalary))
        //        .forEach(System.out::println);
//
//        //sort employee by salary in desc order
//        empList.stream()
//                .sorted(Comparator.comparing(EmployeeDetails::getEmpSalary).reversed())
//                .forEach(System.out::println);
//
//        // sort by department and salary
//        empList.stream()
//                .sorted(Comparator.comparing(EmployeeDetails::getDepartment)
//                .thenComparing(EmployeeDetails::getEmpSalary))
//                .forEach(System.out::println);
//
//        // sort by primitive numeric field asc
//        empList.stream()
//                .sorted(Comparator.comparingDouble(EmployeeDetails::getEmpSalary))
//                .forEach(System.out::println);

//        //INTERMEDIATE LEVEL QUESTIONS
//        // Sort by multiple criteria reverse secondary field
//        empList.stream()
//                .sorted(Comparator.comparing(EmployeeDetails::getDepartment)
//                        .thenComparing(EmployeeDetails::getEmpSalary, Comparator.reverseOrder()))
//                .forEach(System.out::println);

//        //Sort by length of firstname used comparingInt
//        empList.stream()
//                .sorted(Comparator.comparingInt(e -> e.getEmpFirstName().length()))
//                .forEach(System.out::println);

        // sort by combined field full name
        empList.stream()
                .sorted(Comparator.comparing(e-> e.getEmpFirstName() + " " + e.getEmpLastName()))
                .forEach(System.out::println);


        // sort-case insensitively
        empList.stream()
                .sorted(Comparator.comparing( e -> e.getEmpFirstName().toLowerCase()))
                .forEach(System.out::println);


        //ADVANCED LEVEL
        //sort using Comparator.comparingInt/comparingDouble for primitives
        empList.stream()
                .sorted(Comparator.comparingInt(EmployeeDetails::getEmpAge))
                .forEach(System.out::println);

        // Custom Comparator
        empList.stream()
                .sorted((e1,e2) -> Double.compare(e2.getEmpSalary(), e1.getEmpSalary()))
                .forEach(System.out::println);

        // sort employees whose department name start with "I" first
        empList.stream()
                .sorted(Comparator.comparing((EmployeeDetails e)-> e.getDepartment().startsWith("I")).reversed()
                        .thenComparing(EmployeeDetails::getEmpFirstName))
                .forEach(System.out::println);

        // Conditional sorting

        empList.stream()
                .sorted((e1,e2) -> {
                    int salaryComp = Double.compare(e2.getEmpSalary(), e1.getEmpSalary());
                    return (salaryComp !=0) ? salaryComp :e1.getEmpFirstName().compareTo(e2.getEmpFirstName());
                })
                .forEach(System.out::println);

        // EXPERT LEVEL
        // NULL sage sorting - avoid nullpointer exception
        empList.stream()
                .sorted(Comparator.comparing(EmployeeDetails::getDepartment,
                        Comparator.nullsLast(String::compareTo)))
                .forEach(System.out::println);

        // Chain multiple complex comparators
        empList.stream()
                .sorted(Comparator.comparing(EmployeeDetails::getDepartment)
                        .thenComparing(EmployeeDetails::getEmpDesignation)
                        .thenComparing(EmployeeDetails::getEmpSalary, Comparator.reverseOrder())
                        .thenComparing(EmployeeDetails::getEmpFirstName))
                .forEach(System.out::println);





    }
}
