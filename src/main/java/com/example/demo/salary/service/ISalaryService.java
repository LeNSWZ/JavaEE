package com.example.demo.salary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.salary.domain.Salary;


public interface ISalaryService {
	public Salary save(Salary entity);
	public Optional<Salary> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	public Page<Salary> findAll(Specification<Salary> spec, Pageable pageable);
	public List<Salary> findAll();
}
