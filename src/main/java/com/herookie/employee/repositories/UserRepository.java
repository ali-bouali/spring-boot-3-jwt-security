package com.herookie.employee.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.herookie.employee.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  // @Query("select p from User p where p.active=1")
  // List<User> findAllActive();

  @Query("select p from User p")
  Page<User> findAllBySearch(String search, Pageable pageable);

  Optional<User> findByEmail(String email);
}