package com.example.demo.nationality.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.nationality.domain.Nationality;
import com.example.demo.nationality.domain.NationalityDTO;

public interface INationalityService {
	public void save(NationalityDTO dto);
	public void deleteById(Long id);
	public void update(NationalityDTO dto);
	public NationalityDTO findById(Long id);
	public Page<NationalityDTO> findAll(Specification<Nationality> spec,Pageable pageable);
}
