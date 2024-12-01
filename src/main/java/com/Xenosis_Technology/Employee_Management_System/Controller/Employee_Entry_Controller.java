package com.Xenosis_Technology.Employee_Management_System.Controller;

import com.Xenosis_Technology.Employee_Management_System.Entity.Employee;
 import com.Xenosis_Technology.Employee_Management_System.Service.Employee_Entry_Service;
import com.Xenosis_Technology.Employee_Management_System.Service.User_Entry_Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class Employee_Entry_Controller {
    @Autowired
    private Employee_Entry_Service employeeEntryService;


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return ResponseEntity.ok(employeeEntryService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        try {
            Employee employee = employeeEntryService.getEmployeeById(id);
            if (employee != null) {
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{create}")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeEntryService.createEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{update}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        Employee updateEmployee = employeeEntryService.updateEmployee(id, employee);
        return updateEmployee != null ? ResponseEntity.ok(updateEmployee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        try {
            boolean isDeleted = employeeEntryService.deleteEmployeeById(id);

            if (isDeleted) {
                return ResponseEntity.ok("Employee deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
            }
        } catch (Exception e) {
           // e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the employee.");
        }
    }
}
