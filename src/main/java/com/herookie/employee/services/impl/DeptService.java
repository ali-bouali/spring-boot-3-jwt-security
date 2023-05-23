package com.herookie.employee.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.herookie.employee.entities.Dept;
import com.herookie.employee.exceptions.EntityNotFoundException;
import com.herookie.employee.exceptions.ErrorProcessingException;
import com.herookie.employee.exceptions.UnsavedEntityException;
import com.herookie.employee.repositories.DeptRepository;
import com.herookie.employee.services.IDeptService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeptService implements IDeptService {
  @Autowired
  private DeptRepository deptRepository;

  @Override
  public Dept findById(final long deptId) throws ErrorProcessingException, EntityNotFoundException {
    try {
      return this.deptRepository.findById(deptId).orElseThrow(() -> new EntityNotFoundException());
    } catch (final EntityNotFoundException e) {
      throw e;
    } catch (final Exception e) {
      log.error("Dept findById(\"{}\"):{}", deptId, e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  @Override
  public List<Dept> findAll() throws ErrorProcessingException {
    try {
      return this.deptRepository.findAll();
    } catch (final Exception e) {
      log.error("Dept findAll():{}", e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  // @Override
  // public List<Dept> findAllActive() throws ErrorProcessingException {
  //   try {
  //     return this.deptRepository.findAllActive();
  //   } catch (final Exception e) {
  //     log.error("Dept findAllActive():{}", e.getMessage());
  //     throw new ErrorProcessingException(e.getMessage());
  //   }
  // }

  @Override
  public Dept save(final Dept dept) throws UnsavedEntityException {
    try {
      return this.deptRepository.save(dept);
    } catch (final Exception e) {
      log.error("Dept save(\"{}\"):{}", dept.toString(), e.getMessage());
      throw new UnsavedEntityException(e.getMessage());
    }
  }

  @Override
  public Dept update(final Dept dept) throws UnsavedEntityException {
    try {
      return this.deptRepository.save(dept);
    } catch (final Exception e) {
      log.error("Dept update(\"{}\"):{}", dept.toString(), e.getMessage());
      throw new UnsavedEntityException(e.getMessage());
    }
  }

  @Override
  public Page<Dept> findAllPaginatedBySearch(final String search, final PageRequest pageable)
      throws ErrorProcessingException {
    try {
      return this.deptRepository.findAllBySearch(search, pageable);
    } catch (final Exception e) {
      log.error("Dept findAllPaginatedBySearch(\"{}\"):{}", search, e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }
}