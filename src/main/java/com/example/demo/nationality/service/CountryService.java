package com.example.demo.nationality.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.nationality.domain.Country;
import com.example.demo.nationality.repository.CountryRepository;

@Service
@Transactional
public class CountryService implements ICountryService {
	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	public void save(Country country) {
		countryRepository.save(country);
	}

	@Override
	public void deleteById(int id) {
		countryRepository.deleteById(id);
	}

	@Override
	public Optional<Country> findById(int id) {
		return countryRepository.findById(id);
	}

	@Override
	public List<Country> findAll() {
		return (List<Country>) countryRepository.findAll();
	}

}
