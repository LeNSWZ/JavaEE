package com.example.demo.department_employee.nationality.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.department_employee.nationality.domain.Nationality;

@Repository
public interface NationalityRepository extends PagingAndSortingRepository<Nationality, Long>,JpaSpecificationExecutor<Nationality> {

}
