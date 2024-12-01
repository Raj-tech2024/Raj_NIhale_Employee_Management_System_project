package com.Xenosis_Technology.Employee_Management_System.Service;

import com.Xenosis_Technology.Employee_Management_System.Entity.Attendance;
import com.Xenosis_Technology.Employee_Management_System.Entity.Employee;
import com.Xenosis_Technology.Employee_Management_System.Repository.Attendance_Repository;
import com.Xenosis_Technology.Employee_Management_System.Repository.Employee_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private Attendance_Repository attendanceRepository;

    @Autowired
    private Employee_Repository employeeRepository;

    /**
     * Create an attendance record
     *
     * @param employeeId      ID of the employee
     * @param attendanceDate  Date of attendance
     * @param isPresent       Attendance status
     * @return Saved Attendance entity
     */
    public Attendance createAttendance(Long employeeId, LocalDate attendanceDate, Boolean isPresent) {
        // Log inputs
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Attendance Date: " + attendanceDate);
        System.out.println("Is Present: " + isPresent);

        // Validate employee
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));

        // Create attendance record
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setAttendanceDate(attendanceDate);
        attendance.setIsPresent(isPresent);

        // Save attendance record
        return attendanceRepository.save(attendance);
    }

    /**
     * Get all attendance records
     *
     * @return List of Attendance
     */
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    /**
     * Get attendance records for a specific employee
     *
     * @param employeeId ID of the employee
     * @return List of Attendance for the employee
     */
    public List<Attendance> getAttendanceByEmployee(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    /**
     * Get attendance records by date range
     *
     * @param startDate Start date
     * @param endDate   End date
     * @return List of Attendance within the date range
     */
    public List<Attendance> getAttendanceByDateRange(LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByAttendanceDateBetween(startDate, endDate);
    }


}
