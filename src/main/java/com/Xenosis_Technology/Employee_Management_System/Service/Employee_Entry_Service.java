package com.Xenosis_Technology.Employee_Management_System.Service;

import com.Xenosis_Technology.Employee_Management_System.Entity.Employee;
import com.Xenosis_Technology.Employee_Management_System.Repository.Employee_Repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class Employee_Entry_Service {
    @Autowired
    private Employee_Repository employeeRepository;

     public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }
    public Employee getEmployeeById(Long id)
    {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
    public Employee createEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }


    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent())
        {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setDob(updatedEmployee.getDob());
            existingEmployee.setDepartment(updatedEmployee.getDepartment());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            return employeeRepository.save(existingEmployee);
        }
        return null;
     }
    public boolean deleteEmployeeById(Long id)
    {
       if(employeeRepository.existsById(id))
        {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
     }
}
