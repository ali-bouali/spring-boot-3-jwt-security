package com.herookie.employee.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.herookie.employee.entities.Dept;
import com.herookie.employee.exceptions.EntityNotFoundException;
import com.herookie.employee.exceptions.ErrorProcessingException;
import com.herookie.employee.exceptions.UnsavedEntityException;

public interface IDeptService {
  Dept findById(long deptId) throws ErrorProcessingException, EntityNotFoundException;

  List<Dept> findAll() throws ErrorProcessingException;

  // List<Dept> findAllActive() throws ErrorProcessingException;

  Dept save(Dept dept) throws UnsavedEntityException;

  Dept update(Dept dept) throws UnsavedEntityException;

  Page<Dept> findAllPaginatedBySearch(String search, PageRequest pageable) throws ErrorProcessingException;
}