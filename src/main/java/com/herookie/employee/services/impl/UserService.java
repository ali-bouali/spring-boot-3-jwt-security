package com.herookie.employee.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.herookie.employee.entities.Role;
import com.herookie.employee.entities.User;
import com.herookie.employee.exceptions.EntityNotFoundException;
import com.herookie.employee.exceptions.ErrorProcessingException;
import com.herookie.employee.exceptions.UnsavedEntityException;
import com.herookie.employee.repositories.UserRepository;
import com.herookie.employee.services.IUserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements IUserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public User findById(final long userId) throws ErrorProcessingException, EntityNotFoundException {
    try {
      return this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException());
    } catch (final EntityNotFoundException e) {
      throw e;
    } catch (final Exception e) {
      log.error("User findById(\"{}\"):{}", userId, e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  @Override
  public List<User> findAll() throws ErrorProcessingException {
    try {
      return this.userRepository.findAll();
    } catch (final Exception e) {
      log.error("User findAll():{}", e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  @Override
  public User save(final User user) throws UnsavedEntityException {
    try {
      String encodedPassword = passwordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
      return this.userRepository.save(user);
    } catch (final Exception e) {
      log.error("User save(\"{}\"):{}", user.toString(), e.getMessage());
      throw new UnsavedEntityException(e.getMessage());
    }
  }

  @Override
  public User update(final User user) throws UnsavedEntityException {
    try {
      if (user.getPassword() != null) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
      }
      return this.userRepository.save(user);
    } catch (final Exception e) {
      log.error("User update(\"{}\"):{}", user.toString(), e.getMessage());
      throw new UnsavedEntityException(e.getMessage());
    }
  }

  @Override
  public Page<User> findAllPaginatedBySearch(final String search, final PageRequest pageable)
      throws ErrorProcessingException {
    try {
      return this.userRepository.findAllBySearch(search, pageable);
    } catch (final Exception e) {
      log.error("User findAllPaginatedBySearch(\"{}\"):{}", search, e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  @Override
  public User createUser(final User user) throws UnsavedEntityException {
    try {
      String encodedPassword = passwordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
      return this.userRepository.save(user);
    } catch (final Exception e) {
      log.error("User save(\"{}\"):{}", user.toString(), e.getMessage());
      throw new UnsavedEntityException(e.getMessage());
    }
  }

    @Override
    public void deleteUser(long userId) throws EntityNotFoundException {
        User user = findById(userId);
        userRepository.delete(user);
    }

    @Override
    public User updateUserRole(long userId, Role role) throws EntityNotFoundException, UnsavedEntityException {
        User user = findById(userId);
        user.setRole(role);
        return userRepository.save(user);
    }
}