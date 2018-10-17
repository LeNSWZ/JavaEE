package com.example.demo.nationality.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.nationality.domain.Nation;

public interface INationService {
	public void save(Nation nation);
	public void deleteById(int id);
	public Optional<Nation> findById(int id);
	public List<Nation> findAll();
}
