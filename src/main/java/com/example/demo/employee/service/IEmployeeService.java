package com.example.demo.employee.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.employee.domain.Employee;
import com.example.demo.employee.domain.EmployeeDTO;

public interface IEmployeeService {
	public void save(EmployeeDTO dto);
	public void saveAll(List<Employee> employees);
	
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	
	public void update(EmployeeDTO dto);
	
	public EmployeeDTO findById(Long id);
	public List<EmployeeDTO> findAll();
	
	public boolean existsById(Long id);
	public long count();
	//动态条件查询
	public Page<EmployeeDTO> findAll(Specification<Employee> spec, Pageable pageable);
}
