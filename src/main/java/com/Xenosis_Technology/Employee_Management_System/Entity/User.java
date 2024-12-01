package com.Xenosis_Technology.Employee_Management_System.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is mandatory")
    @Column(nullable = false, unique = true) // Ensure username is unique in the database
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false) // Ensure password is not null in the database
    private String password;

    @ElementCollection(fetch = FetchType.EAGER) // Persist roles as a separate table
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @NotNull(message = "Roles cannot be null") // Ensure roles are not null
    private List<String> role; // Use Set to avoid duplicate roles

}