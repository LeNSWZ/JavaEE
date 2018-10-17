package com.example.demo.nationality.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.nationality.domain.Country;

public interface ICountryService {
	public void save(Country country);
	public void deleteById(int id);
	public Optional<Country> findById(int id);
	public List<Country> findAll();
}
