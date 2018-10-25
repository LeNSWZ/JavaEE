package com.example.demo.department_employee.education.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.department_employee.education.domain.Education;
import com.example.demo.department_employee.education.domain.EducationDTO;
import com.example.demo.department_employee.education.repository.EducationRepository;


@Service
@Transactional
public class EducationService implements IEducationService {
	@Autowired
	private EducationRepository educationRepository;
	
	@Override
	public void save(EducationDTO dto) {
		Education entity = EducationDTO.dtoToEntity(dto);
		educationRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		educationRepository.deleteById(id);
	}

	@Override
	public void update(EducationDTO dto) {
		Education entity = EducationDTO.dtoToEntity(dto);
		educationRepository.save(entity);
	}

	@Override
	public EducationDTO findById(Long id) {
		Education entity = educationRepository.findById(id).get();
		EducationDTO dto = EducationDTO.entityToDTO(entity);
		return dto;
	}
	
	@Override
	public List<EducationDTO> findAll() {
		List<Education> educations = (List<Education>) educationRepository.findAll();
		List<EducationDTO> dtos = new ArrayList<>();
		for (Education education : educations) {
			EducationDTO dto = EducationDTO.entityToDTO(education);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public Page<EducationDTO> findAll(Specification<Education> spec, Pageable pageable) {
		Page<Education> educationPage = educationRepository.findAll(spec, pageable);
		List<EducationDTO> dtos = new ArrayList<>();
		for (Education education : educationPage.getContent()) {
			EducationDTO dto = EducationDTO.entityToDTO(education);
			dtos.add(dto);
		}
		
		return new PageImpl<>(dtos, pageable, educationPage.getTotalElements());
	}
}
