package com.herookie.employee.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.herookie.employee.entities.Attendance;
import com.herookie.employee.entities.User;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
  // @Query("select p from Attendance p where p.active=1")
  // List<Attendance> findAllActive();

  @Query("select p from Attendance p")
  Page<Attendance> findAllBySearch(String search, Pageable pageable);

  Attendance findByUserAndHasCheckOutFalse(User user);
}