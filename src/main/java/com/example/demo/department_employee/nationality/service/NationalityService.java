package com.example.demo.department_employee.nationality.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.department_employee.nationality.domain.Nationality;
import com.example.demo.department_employee.nationality.domain.NationalityDTO;
import com.example.demo.department_employee.nationality.repository.NationalityRepository;

@Service
@Transactional
public class NationalityService implements INationalityService {
	@Autowired
	private NationalityRepository nationalityRepository;
	
	@Override
	public void save(NationalityDTO dto) {
		Nationality entity = NationalityDTO.dtoToEntity(dto);		
		nationalityRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		nationalityRepository.deleteById(id);
	}

	@Override
	public void update(NationalityDTO dto) {
		Nationality entity = NationalityDTO.dtoToEntity(dto);		
		nationalityRepository.save(entity);
	}

	@Override
	public NationalityDTO findById(Long id) {
		Nationality entity = nationalityRepository.findById(id).get();
		NationalityDTO dto = NationalityDTO.entityToDTO(entity);
		return dto;
	}

	@Override
	public Page<NationalityDTO> findAll(Specification<Nationality> spec, Pageable pageable) {
		Page<Nationality> nationalityPage = nationalityRepository.findAll(spec, pageable);
		List<NationalityDTO> dtos = new ArrayList<>();
		for (Nationality nationality : nationalityPage.getContent()) {
			NationalityDTO dto = NationalityDTO.entityToDTO(nationality);
			dtos.add(dto);
		}
		
		return new PageImpl<>(dtos, pageable, nationalityPage.getTotalElements());
	}

}
