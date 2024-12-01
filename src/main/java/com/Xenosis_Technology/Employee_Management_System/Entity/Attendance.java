package com.Xenosis_Technology.Employee_Management_System.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique ID for attendance record

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;  // Link to Employee entity

    @Column(nullable = false)
    private LocalDate attendanceDate;  // Date of attendance

    @Column(nullable = false)
    private Boolean isPresent;  // Whether the employee was present
}
