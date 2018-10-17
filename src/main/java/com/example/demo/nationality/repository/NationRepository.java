package com.example.demo.nationality.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.nationality.domain.Nation;

@Repository
public interface NationRepository extends CrudRepository<Nation, Integer> {

}
