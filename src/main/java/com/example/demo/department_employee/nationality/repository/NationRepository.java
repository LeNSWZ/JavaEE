package com.example.demo.department_employee.nationality.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.department_employee.nationality.domain.Nation;

@Repository
public interface NationRepository extends CrudRepository<Nation, Integer> {

}
