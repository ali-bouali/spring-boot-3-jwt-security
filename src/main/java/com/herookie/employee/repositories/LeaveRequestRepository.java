package com.herookie.employee.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.herookie.employee.entities.LeaveRequest;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long>{
    // @Query("select p from LeaveRequest p where p.active=1")
    // List<LeaveRequest> findAllActive();
  
    @Query("select p from LeaveRequest p")
    Page<LeaveRequest> findAllBySearch(String search, Pageable pageable);
}