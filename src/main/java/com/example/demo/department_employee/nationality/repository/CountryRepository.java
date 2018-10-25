package com.example.demo.department_employee.nationality.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.department_employee.nationality.domain.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {

//	@Query("")
//	public List<Nation> findNationsByCountryId(Long countryId);
}
