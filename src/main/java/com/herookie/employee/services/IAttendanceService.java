package com.herookie.employee.services;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.herookie.employee.entities.Attendance;
import com.herookie.employee.exceptions.EntityNotFoundException;
import com.herookie.employee.exceptions.ErrorProcessingException;
import com.herookie.employee.exceptions.UnsavedEntityException;

public interface IAttendanceService {
  Attendance findById(long attendanceId) throws ErrorProcessingException, EntityNotFoundException;

  List<Attendance> findAll() throws ErrorProcessingException;

  // List<Attendance> findAllActive() throws ErrorProcessingException;

  Attendance save(Attendance attendance) throws UnsavedEntityException;

  Attendance update(Attendance attendance) throws UnsavedEntityException;

  Page<Attendance> findAllPaginatedBySearch(String search, PageRequest pageable) throws ErrorProcessingException;

  Attendance checkIn(long userId) throws EntityNotFoundException;

  Attendance checkOut(long userId) throws EntityNotFoundException;
}