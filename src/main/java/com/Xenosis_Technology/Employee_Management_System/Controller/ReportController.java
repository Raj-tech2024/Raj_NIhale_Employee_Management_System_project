package com.Xenosis_Technology.Employee_Management_System.Controller;

import com.Xenosis_Technology.Employee_Management_System.Entity.Department;
import com.Xenosis_Technology.Employee_Management_System.Repository.Department_Repository;
import com.Xenosis_Technology.Employee_Management_System.Service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportController {

   @Autowired
   private   ReportService reportService;
   @Autowired
   private  Department_Repository departmentRepository;


    // Salary report endpoint
    @GetMapping("/salary")
    public ResponseEntity<List<Map<String, Object>>> getSalaryReport() {
        List<Map<String, Object>> salaryReport = reportService.getSalaryReport();
        return ResponseEntity.ok(salaryReport);
    }

    @PostMapping("/department")
    public ResponseEntity<?> createDepartment(@RequestBody Department department){
        Department department1=departmentRepository.save(department);
        return ResponseEntity.ok(department1);
    }
    // Department distribution report endpoint
    public ResponseEntity<Map<String, Long>> getDepartmentDistributionReport() {
        Map<String, Long> departmentDistributionReport = reportService.getDepartmentWiseDistribution();
        return ResponseEntity.ok(departmentDistributionReport);
    }
}
