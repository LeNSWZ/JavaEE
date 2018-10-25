package com.example.demo.department_employee.salary.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.department_employee.salary.domain.Salary;
import com.example.demo.department_employee.salary.domain.SalaryDTO;
import com.example.demo.department_employee.salary.repository.SalaryRepository;

@Service
@Transactional
public class SalaryService implements ISalaryService {
	
	@Autowired
	private SalaryRepository salaryRepository;
	
	@Override
	public Salary save(SalaryDTO dto) {
		Salary entity = new Salary();
		SalaryDTO.dtoToEntity(dto,entity);
		return salaryRepository.save(entity);
	}
	
	/*@Override
	public Salary save(Salary entity) {
		return salaryRepository.save(entity);
	}*/
	
	@Override
	public void saveAll(List<Salary> salaries) {
		salaryRepository.saveAll(salaries);
	}
	

	@Override
	public boolean existsById(Long id) {
		return salaryRepository.existsById(id);
	}

	@Override
	public long count() {
		return salaryRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		salaryRepository.deleteById(id);

	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Salary> salaries = (List<Salary>) salaryRepository.findAllById(idLists);
		if(salaries!=null) {
			salaryRepository.deleteAll(salaries);
		}
	}

	/*@Override
	public Page<Salary> findAll(Specification<Salary> spec, Pageable pageable) {
		return salaryRepository.findAll(spec, pageable);
	}*/
	
	@Override
	public Optional<Salary> findById(Long id) {
		return salaryRepository.findById(id);
	}
	
	@Override
	public Page<SalaryDTO> findAll(Specification<Salary> spec, Pageable pageable) {
		List<SalaryDTO> dtos = new ArrayList<>();
		Page<Salary> salaryPage = salaryRepository.findAll(spec, pageable);
		for (Salary salary : salaryPage.getContent()) {
			SalaryDTO dto = new SalaryDTO();
			SalaryDTO.entityToDTO(salary, dto);
			dtos.add(dto);
		}
		return new PageImpl<>(dtos, pageable, salaryPage.getTotalElements());
	}
	
	
	
	@Override
	public List<Salary> findAll() {
		return (List<Salary>) salaryRepository.findAll();
	}

	/*@Override
	public void save(SalaryDTO dto) {
		Salary salary = SalaryDTO.dtoToEntity(dto);
		salaryRepository.save(salary);
	}*/
	
	@Override
	public void update(SalaryDTO dto) {
		Salary salary = salaryRepository.findById(dto.getId()).get();
		salary = SalaryDTO.dtoToEntity(dto,salary);
		salaryRepository.save(salary);
	}
	
	/*@Override
	public SalaryDTO findById(Long id) {
		Salary salary = salaryRepository.findById(id).get();
		SalaryDTO dto = SalaryDTO.entityToDTO(null, salary);
		return dto;
	}*/
}
