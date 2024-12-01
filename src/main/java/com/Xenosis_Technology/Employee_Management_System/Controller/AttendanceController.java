package com.Xenosis_Technology.Employee_Management_System.Controller;

import com.Xenosis_Technology.Employee_Management_System.Entity.Attendance;
import com.Xenosis_Technology.Employee_Management_System.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;


    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@RequestBody Map<String, Object> requestData) {
        Long employeeId = Long.valueOf(requestData.get("employeeId").toString());
        LocalDate attendanceDate = LocalDate.parse(requestData.get("attendanceDate").toString());
        Boolean isPresent = Boolean.valueOf(requestData.get("isPresent").toString());

        Attendance savedAttendance = attendanceService.createAttendance(employeeId, attendanceDate, isPresent);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
    }


    /**
     * Get all attendance records
     *
     * @return List of all Attendance
     */
    @GetMapping
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        List<Attendance> attendanceList = attendanceService.getAllAttendance();
        return ResponseEntity.ok(attendanceList);
    }

    /**
     * Get attendance records by employee ID
     *
     * @param employeeId ID of the employee
     * @return List of Attendance for the employee
     */
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Attendance>> getAttendanceByEmployee(@PathVariable Long employeeId) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByEmployee(employeeId);
        return ResponseEntity.ok(attendanceList);
    }

    /**
     * Get attendance records by date range
     *
     * @param startDate Start date
     * @param endDate   End date
     * @return List of Attendance within the date range
     */
    @GetMapping("/date-range")
    public ResponseEntity<List<Attendance>> getAttendanceByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByDateRange(startDate, endDate);
        return ResponseEntity.ok(attendanceList);
    }
}
