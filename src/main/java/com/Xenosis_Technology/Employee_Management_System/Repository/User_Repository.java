package com.Xenosis_Technology.Employee_Management_System.Repository;

import com.Xenosis_Technology.Employee_Management_System.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface User_Repository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);
}
