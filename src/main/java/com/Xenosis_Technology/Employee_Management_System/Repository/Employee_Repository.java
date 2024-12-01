package com.Xenosis_Technology.Employee_Management_System.Repository;

import com.Xenosis_Technology.Employee_Management_System.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Employee_Repository extends JpaRepository<Employee,Long> {

}
