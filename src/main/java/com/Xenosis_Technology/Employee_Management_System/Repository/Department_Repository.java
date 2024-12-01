package com.Xenosis_Technology.Employee_Management_System.Repository;

import com.Xenosis_Technology.Employee_Management_System.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Department_Repository extends JpaRepository<Department,Long> {
}
