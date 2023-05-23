package com.herookie.employee.services.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.herookie.employee.entities.Attendance;
import com.herookie.employee.entities.User;
import com.herookie.employee.exceptions.EntityNotFoundException;
import com.herookie.employee.exceptions.ErrorProcessingException;
import com.herookie.employee.exceptions.UnsavedEntityException;
import com.herookie.employee.repositories.AttendanceRepository;
import com.herookie.employee.repositories.UserRepository;
import com.herookie.employee.services.IAttendanceService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AttendanceService implements IAttendanceService {
  @Autowired
  private AttendanceRepository attendanceRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public Attendance findById(long attendanceId) throws ErrorProcessingException, EntityNotFoundException {
    try {
      return this.attendanceRepository.findById(attendanceId).orElseThrow(() -> new EntityNotFoundException());
    } catch (EntityNotFoundException e) {
      throw e;
    } catch (Exception e) {
      log.error("Attendance findById(\"{}\"):{}", attendanceId, e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  @Override
  public List<Attendance> findAll() throws ErrorProcessingException {
    try {
      return this.attendanceRepository.findAll();
    } catch (Exception e) {
      log.error("Attendance findAll():{}", e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  // @Override
  // public List<Attendance> findAllActive() throws ErrorProcessingException {
    // try {
      // return this.attendanceRepository.findAllActive();
    // } catch (Exception e) {
      // log.error("Attendance findAllActive():{}", e.getMessage());
      // throw new ErrorProcessingException(e.getMessage());
    // }
  // }

  @Override
  public Attendance save(Attendance attendance) throws UnsavedEntityException {
    try {
      return this.attendanceRepository.save(attendance);
    } catch (Exception e) {
      log.error("Attendance save(\"{}\"):{}", attendance.toString(), e.getMessage());
      throw new UnsavedEntityException(e.getMessage());
    }
  }

  @Override
  public Attendance update(Attendance attendance) throws UnsavedEntityException {
    try {
      return this.attendanceRepository.save(attendance);
    } catch (Exception e) {
      log.error("Attendance update(\"{}\"):{}", attendance.toString(), e.getMessage());
      throw new UnsavedEntityException(e.getMessage());
    }
  }

  @Override
  public Page<Attendance> findAllPaginatedBySearch(String search, PageRequest pageable)
      throws ErrorProcessingException {
    try {
      return this.attendanceRepository.findAllBySearch(search, pageable);
    } catch (Exception e) {
      log.error("Attendance findAllPaginatedBySearch(\"{}\"):{}", search, e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  @Override
  public Attendance checkIn(long userId) throws EntityNotFoundException {
      User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
      Attendance attendance = new Attendance();
      attendance.setUser(user);
      attendance.setCheckIn(new Date());
      attendance.setHasCheckIn(true);
      return attendanceRepository.save(attendance);
  }

  @Override
  public Attendance checkOut(long userId) throws EntityNotFoundException {
      User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
      Attendance attendance = attendanceRepository.findByUserAndHasCheckOutFalse(user);
      attendance.setCheckOut(new Date());
      attendance.setHasCheckOut(true);
      return attendanceRepository.save(attendance);
  }
}