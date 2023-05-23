package com.herookie.employee.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.herookie.employee.entities.Attendance;
import com.herookie.employee.entities.Role;
import com.herookie.employee.entities.User;
import com.herookie.employee.exceptions.EntityNotFoundException;
import com.herookie.employee.exceptions.ErrorProcessingException;
import com.herookie.employee.exceptions.UnsavedEntityException;

public interface IUserService {
  User findById(long userId) throws ErrorProcessingException, EntityNotFoundException;

  List<User> findAll() throws ErrorProcessingException;

  // List<User> findAllActive() throws ErrorProcessingSException;

  User save(User user) throws UnsavedEntityException;

  User update(User user) throws UnsavedEntityException;

  Page<User> findAllPaginatedBySearch(String search, PageRequest pageable) throws ErrorProcessingException;

  User createUser(User user) throws UnsavedEntityException;
    
  void deleteUser(long userId) throws EntityNotFoundException;
  
  User updateUserRole(long userId, Role role) throws EntityNotFoundException, UnsavedEntityException;
}