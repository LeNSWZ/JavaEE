package com.example.demo.nationality.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.nationality.domain.Nation;
import com.example.demo.nationality.repository.NationRepository;

@Service
@Transactional
public class NationService implements INationService {
	@Autowired
	private NationRepository nationRepository;
	
	@Override
	public void save(Nation nation) {
		nationRepository.save(nation);
	}

	@Override
	public void deleteById(int id) {
		nationRepository.deleteById(id);
	}

	@Override
	public Optional<Nation> findById(int id) {
		return nationRepository.findById(id);
	}

	@Override
	public List<Nation> findAll() {
		return (List<Nation>) nationRepository.findAll();
	}

}
