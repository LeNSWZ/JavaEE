package com.example.demo.department_employee.salary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.department_employee.salary.domain.Salary;
import com.example.demo.department_employee.salary.domain.SalaryDTO;


public interface ISalaryService {
	//public Salary save(Salary entity);
	public void saveAll(List<Salary> salaries);
	public Salary save(SalaryDTO dto);
	
	
	public boolean existsById(Long id);
	public long count();
	
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	
	public Optional<Salary> findById(Long id);
	public Page<SalaryDTO> findAll(Specification<Salary> spec, Pageable pageable);
	public List<Salary> findAll();
	
	public void update(SalaryDTO dto);
	
}
