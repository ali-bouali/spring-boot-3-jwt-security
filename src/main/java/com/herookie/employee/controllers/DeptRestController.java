package com.herookie.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.herookie.employee.utils.SearchPagination;
import com.herookie.employee.entities.Dept;
import com.herookie.employee.exceptions.EntityNotFoundException;
import com.herookie.employee.exceptions.ErrorProcessingException;
import com.herookie.employee.exceptions.UnsavedEntityException;
import com.herookie.employee.services.IDeptService;

@RestController
@RequestMapping("/api/dept")
public class DeptRestController {

	@Autowired
	IDeptService deptService;


	@GetMapping("/{id}")
	public ResponseEntity<Dept> findById(@PathVariable("id") long deptId) throws ErrorProcessingException, EntityNotFoundException {
		Dept salida = deptService.findById(deptId);
		return new ResponseEntity<Dept>(salida, HttpStatus.OK);
	}

	@GetMapping("/find-all")
		public ResponseEntity<List<Dept>> findAll() throws ErrorProcessingException {
			List<Dept> salida = deptService.findAll();
			return new ResponseEntity<List<Dept>>(salida, HttpStatus.OK);
		}

	// @GetMapping("/find-all-active")
	// 	public ResponseEntity<List<Dept>> findAllActive() throws ErrorProcessingException {
	// 		List<Dept> salida = deptService.findAllActive();
	// 		return new ResponseEntity<List<Dept>>(salida, HttpStatus.OK);
	// 	}

	@PostMapping
		public ResponseEntity<Dept> save(@RequestBody Dept dept) throws UnsavedEntityException {
			Dept salida = deptService.save(dept);
			return new ResponseEntity<Dept>(salida, HttpStatus.OK);
		}

	@PutMapping
		public ResponseEntity<Dept> update(@RequestBody Dept dept) throws UnsavedEntityException {
			Dept salida = deptService.update(dept);
			return new ResponseEntity<Dept>(salida, HttpStatus.OK);
		}

	@PostMapping("/page-all-by-search")
		public ResponseEntity<Page<Dept>> findAllPaginatedBySearch(
				@RequestBody SearchPagination<String> searchPagination) throws ErrorProcessingException {
			PageRequest pageable = searchPagination.getPageRequest();
			String search = searchPagination.getSeek();
			Page<Dept> salida = deptService.findAllPaginatedBySearch(search,
				pageable);
			return new ResponseEntity<Page<Dept>>(salida, HttpStatus.OK);
		}
}