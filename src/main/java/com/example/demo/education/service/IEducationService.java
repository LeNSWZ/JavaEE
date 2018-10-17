package com.example.demo.education.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.education.domain.Education;
import com.example.demo.education.domain.EducationDTO;

public interface IEducationService {
	public void save(EducationDTO dto);
	public void deleteById(Long id);
	public void update(EducationDTO dto);
	public EducationDTO findById(Long id);
	public Page<EducationDTO> findAll(Specification<Education> spec,Pageable pageable);
}
