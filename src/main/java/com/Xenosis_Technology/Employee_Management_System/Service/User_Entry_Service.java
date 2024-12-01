package com.Xenosis_Technology.Employee_Management_System.Service;

import com.Xenosis_Technology.Employee_Management_System.Entity.User;
import com.Xenosis_Technology.Employee_Management_System.Repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;

@Service
public class User_Entry_Service {

    @Autowired
    private User_Repository userRepository;

    private final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();


    /**
     * Fetch all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Fetch a user by ID.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    /**
     * create user
     */
    public void createUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Arrays.asList("ROLE_USER"));
         userRepository.save(user);
    }

    public void createAdminUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Arrays.asList("ROLE_USER","ROLE_ADMIN"));
        userRepository.save(user);
    }


    /**
     * delete user by id
     */
   public void deleteUserById ( Long id)
    {
       User user =  userRepository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("user not found with id:"+id) );
           userRepository.delete(user);
    }
}
