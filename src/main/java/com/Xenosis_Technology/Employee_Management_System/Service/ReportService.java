package com.Xenosis_Technology.Employee_Management_System.Service;

import com.Xenosis_Technology.Employee_Management_System.Entity.Employee;
import com.Xenosis_Technology.Employee_Management_System.Repository.Attendance_Repository;
import com.Xenosis_Technology.Employee_Management_System.Repository.Employee_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private Attendance_Repository attendanceRepository;
    @Autowired
    private  Employee_Repository employeeRepository;

    // Generate salary report
    public List<Map<String, Object>> getSalaryReport() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(employee -> {
                    Map<String, Object> reportEntry = new HashMap<>();
                    reportEntry.put("employeeId", employee.getId());
                    reportEntry.put("name", employee.getName());
                    reportEntry.put("department", employee.getDepartment().getName());
                    reportEntry.put("salary", employee.getSalary());

                    return reportEntry;
                })
                .collect(Collectors.toList());
    }


    // Generate department-wise distribution report
    // Generate Department-wise Distribution Report
    public Map<String, Long> getDepartmentWiseDistribution() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment() != null ? employee.getDepartment().getName() : "Unknown",
                        Collectors.counting()
                ));
    }


}
