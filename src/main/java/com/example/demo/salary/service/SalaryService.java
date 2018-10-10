package com.example.demo.salary.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.salary.domain.Salary;
import com.example.demo.salary.repository.SalaryRepository;

@Service
@Transactional
public class SalaryService implements ISalaryService {
	
	@Autowired
	private SalaryRepository salaryRepository;

	@Override
	public Salary save(Salary entity) {
		return salaryRepository.save(entity);
	}

	@Override
	public Optional<Salary> findById(Long id) {
		return salaryRepository.findById(id);
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

	@Override
	public Page<Salary> findAll(Specification<Salary> spec, Pageable pageable) {
		return salaryRepository.findAll(spec, pageable);
	}

}
