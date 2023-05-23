package com.herookie.employee.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.herookie.employee.entities.Dept;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Long> {
  // @Query("select p from Dept p where p.active=1")
  // List<Dept> findAllActive();

  @Query("select p from Dept p")
  Page<Dept> findAllBySearch(String search, Pageable pageable);
}