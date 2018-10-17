package com.example.demo.nationality.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.nationality.domain.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {

}
