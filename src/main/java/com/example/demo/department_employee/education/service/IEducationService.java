package com.example.demo.department_employee.education.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.department_employee.education.domain.Education;
import com.example.demo.department_employee.education.domain.EducationDTO;

public interface IEducationService {
	public void save(EducationDTO dto);
	public void deleteById(Long id);
	public void update(EducationDTO dto);
	public EducationDTO findById(Long id);
	public List<EducationDTO> findAll();
	public Page<EducationDTO> findAll(Specification<Education> spec,Pageable pageable);
}
