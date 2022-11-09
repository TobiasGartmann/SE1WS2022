package org.hbrs.se1.ws22.uebung4.view;

import org.hbrs.se1.ws22.uebung4.model.entities.Employee;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeView {
    public static void dump(List<Employee> list) {
        list = list.stream().sorted().collect(Collectors.toList());
        for (Employee emp : list) {
            System.out.println(emp.toString());
        }
    }

    public static void search(List<Employee> list, String expertise) {
        Collections.sort(list);
        for (Employee emp : list) {
            for (int i = 0; i < 3; ++i) {
                if(emp.getExpertise()[i][0].equals(expertise)) {
                    System.out.println(emp.toString());
                    break;
                }
            }
        }
    }
}
