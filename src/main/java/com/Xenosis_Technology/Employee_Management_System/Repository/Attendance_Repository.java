package com.Xenosis_Technology.Employee_Management_System.Repository;

import com.Xenosis_Technology.Employee_Management_System.Entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface Attendance_Repository extends JpaRepository<Attendance,Long> {


    List<Attendance> findByEmployeeId(Long employeeId);

    List<Attendance> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate);
}
