package com.example.demo.employee.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.beans.Status;
import com.example.demo.employee.domain.Employee;
import com.example.demo.employee.domain.EmployeeDTO;
import com.example.demo.employee.repositoty.EmployeeRepository;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public void save(EmployeeDTO dto) {
		Employee entity = new Employee();
		entity = EmployeeDTO.DtoToEntity(dto);
		employeeRepository.save(entity);
	}

	@Override
	public void saveAll(List<Employee> employees) {
		employeeRepository.saveAll(employees);
	}

	@Override
	public void deleteById(Long id) {
		Employee employee = employeeRepository.findById(id).get();
		if (employee != null) {
			employee.setStatus(Status.dimission);
		}
		//要测试是否需要重新save一遍
		employeeRepository.save(employee);
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idList = new ArrayList<>(Arrays.asList(ids));	
		List<Employee> employees = (List<Employee>) employeeRepository.findAllById(idList);
		if (employees.size() >0) {
			for (Employee employee : employees) {
				employee.setStatus(Status.dimission);
			}
		}
		//要测试是否需要重新save一遍
		employeeRepository.saveAll(employees);
	}
	
	@Override
	public void update(EmployeeDTO dto) {
		Employee employee = employeeRepository.findById(dto.getId()).get();
		employee = EmployeeDTO.DtoToEntity(dto);
		employeeRepository.save(employee);
	}

	@Override
	public EmployeeDTO findById(Long id) {
		Employee employee = employeeRepository.findById(id).get();
		EmployeeDTO dto = EmployeeDTO.EntityToDTO(employee);
		return dto;
	}

	@Override
	public List<EmployeeDTO> findAll() {
		List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
		List<EmployeeDTO> dtos = new ArrayList<>();
		for (Employee entity : employeeList) {
			EmployeeDTO dto = EmployeeDTO.EntityToDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public boolean existsById(Long id) {
		return employeeRepository.existsById(id);
	}

	@Override
	public long count() {
		return employeeRepository.count();
	}

	@Override
	public Page<EmployeeDTO> findAll(Specification<Employee> spec, Pageable pageable) {
		List<EmployeeDTO> dtos = new ArrayList<>();
		Page<Employee> employeePage = employeeRepository.findAll(spec, pageable);
		List<Employee> employees = employeePage.getContent();
		if (employees.size() > 0) {
			for (Employee employee : employees) {
				EmployeeDTO dto = EmployeeDTO.EntityToDTO(employee);
				dtos.add(dto);
			}
		}
		
		return new PageImpl<>(dtos, pageable, employeePage.getTotalElements());
	}

}
