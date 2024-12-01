package com.Xenosis_Technology.Employee_Management_System.Controller;

import com.Xenosis_Technology.Employee_Management_System.Entity.User;
import com.Xenosis_Technology.Employee_Management_System.Service.User_Entry_Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class User_Controller {

    @Autowired
    private User_Entry_Service userEntryService;

    /**
     * Fetch user by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userEntryService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if not found
        }
    }

    /**
     * Fetch all users.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userEntryService.getAllUsers();
    }


    @PostMapping("/create-User")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        try {
            userEntryService.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);  // Return created user with 201 status
        }
          catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Handle any other exceptions
        }
    }

    @PostMapping("/create-admin")
    public ResponseEntity<User> createAdminUser(@Valid @RequestBody User user) {
        try {
            userEntryService.createAdminUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);  // Return created user with 201 status
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Handle any other exceptions
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        try {
            userEntryService.deleteUserById(id); // Assume this method deletes the user
            return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
    }
}
