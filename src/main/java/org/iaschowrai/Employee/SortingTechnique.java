package org.iaschowrai.Employee;

import org.iaschowrai.Employee.data.EmployeeDataList;
import org.iaschowrai.Employee.data.EmployeeDetails;

import java.util.*;
import java.util.stream.Collectors;

public class SortingTechnique {
    public static void main(String[] args) {

        List<EmployeeDetails> empList = EmployeeDataList.getAllEmployees();
        Map<Long, EmployeeDetails> empMap = EmployeeDataList.getAllEmployeesInMap();


        Collections.sort(empList, new Comparator<EmployeeDetails>() {
            @Override
            public int compare(EmployeeDetails o1, EmployeeDetails o2) {
                return o2.getEmpFirstName().compareTo(o1.getEmpFirstName());
            }
        });

        for (EmployeeDetails e : empList){
            System.out.println(e.getEmpFirstName());
        }

        //If you want to sort by the key (e.g., employee ID):
        Map<Long, EmployeeDetails> sortedByKey = empMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())  // ascending
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new)); // keep insertion order


        // If you want to sort by employee first name (a property of the value):
        Map<Long, EmployeeDetails> sortedByValue = empMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(
                        Comparator.comparing(EmployeeDetails::getEmpFirstName)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));


        // Convert to List and Sort

        List<Map.Entry<Long, EmployeeDetails>> entryList = new ArrayList<>(empMap.entrySet());

        entryList.sort((e1, e2) -> e1.getValue().getEmpFirstName()
                .compareTo(e2.getValue().getEmpFirstName()));

        for (Map.Entry<Long, EmployeeDetails> entry : entryList) {
            System.out.println(entry.getValue().getEmpFirstName());
        }

    }
}
